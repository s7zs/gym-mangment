package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {

    public static void main(String[] args) {

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017/")) {
            MongoDatabase db = client.getDatabase("gym");
            System.out.println("Connected to database: " + db.getName());

            MongoCollection<Document> aooseCollection = db.getCollection("first");
//comm

            Document member = new Document("name", "Test Member")
                    .append("age", 25)
                    .append("membershipType", "Basic")
                    .append("active", true);

            aooseCollection.insertOne(member);

            System.out.println("Inserted document into collection 'Aoose': " + member.toJson());
        }
    }
}