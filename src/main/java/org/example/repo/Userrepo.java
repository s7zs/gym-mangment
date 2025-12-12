package org.example.repo;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.model.users;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class Userrepo {

    private final MongoCollection<Document> collection;
    private final Gson gson;

    public Userrepo(MongoDatabase database) {
        this.collection = database.getCollection("users");
        this.gson = new Gson();
    }

    public String create(users user) {
        String json = gson.toJson(user);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
        return doc.getObjectId("_id").toString();
    }

    public users findByUsername(String username) {
        Document doc = collection.find(eq("username", username)).first();
        
        if (doc != null) {
            String json = doc.toJson();
            return gson.fromJson(json, users.class);
        }
        return null;
    }

    public users findById(String id) {
        Document doc = collection.find(eq("_id", new ObjectId(id))).first();
        
        if (doc != null) {
            String json = doc.toJson();
            return gson.fromJson(json, users.class);
        }
        return null;
    }

    public List<users> findAll() {
        List<users> usersList = new ArrayList<>();
        
        for (Document doc : collection.find()) {
            String json = doc.toJson();
            users user = gson.fromJson(json, users.class);
            usersList.add(user);
        }
        
        return usersList;
    }

    public List<users> findByRole(String role) {
        List<users> usersList = new ArrayList<>();
        
        for (Document doc : collection.find(eq("role", role))) {
            String json = doc.toJson();
            users user = gson.fromJson(json, users.class);
            usersList.add(user);
        }
        
        return usersList;
    }

    public boolean update(String username, users updatedUser) {
        String json = gson.toJson(updatedUser);
        Document updateDoc = Document.parse(json);
        updateDoc.remove("_id");
        
        UpdateResult result = collection.updateOne(
            eq("username", username),
            new Document("$set", updateDoc)
        );
        
        return result.getModifiedCount() > 0;
    }

    public boolean updateField(String username, String fieldName, Object newValue) {
        UpdateResult result = collection.updateOne(
            eq("username", username),
            Updates.set(fieldName, newValue)
        );
        
        return result.getModifiedCount() > 0;
    }

    public boolean delete(String username) {
        DeleteResult result = collection.deleteOne(eq("username", username));
        return result.getDeletedCount() > 0;
    }

    public boolean deleteById(String id) {
        DeleteResult result = collection.deleteOne(eq("_id", new ObjectId(id)));
        return result.getDeletedCount() > 0;
    }

    public long count() {
        return collection.countDocuments();
    }

    public long countByRole(String role) {
        return collection.countDocuments(eq("role", role));
    }

    public boolean exists(String username) {
        return collection.countDocuments(eq("username", username)) > 0;
    }
}

