package org.example.repo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import org.example.model.member;
import org.example.model.Appointment;

public class ReceptionistRepository {

    private final MongoCollection<Document> members;
    private final MongoCollection<Document> appointments;

    public ReceptionistRepository(MongoDatabase db,
                                  String membersColl,
                                  String attendanceCollIgnored,
                                  String appointmentsColl,
                                  String invoicesCollIgnored) {

        this.members = db.getCollection(membersColl);
        this.appointments = db.getCollection(appointmentsColl);
    }

    // -------------------- MEMBERS --------------------

    public void saveMember(member m) {
        // Replace existing member using username as ID
        members.replaceOne(
                Filters.eq("username", m.getUsername()),
                memberToDoc(m),
                new com.mongodb.client.model.ReplaceOptions().upsert(true)
        );
    }

    public member findMemberById(String username) {
        Document d = members.find(Filters.eq("username", username)).first();
        return d == null ? null : memberFromDoc(d);
    }

    public List<member> findAllMembers() {
        List<member> out = new ArrayList<>();
        for (Document d : members.find()) {
            out.add(memberFromDoc(d));
        }
        return out;
    }

    // -------------------- APPOINTMENTS --------------------

    public void saveAppointment(Appointment ap) {
        appointments.insertOne(appointmentToDoc(ap));
    }

    public List<Appointment> findAppointmentsByMember(String username) {
        List<Appointment> out = new ArrayList<>();
        for (Document d : appointments.find(Filters.eq("memberId", username))) {
            out.add(appointmentFromDoc(d));
        }
        return out;
    }

    public List<Appointment> findAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        for (Document d : appointments.find()) {
            list.add(appointmentFromDoc(d));
        }
        return list;
    }

    // -------------------- CONVERTERS --------------------

    private Document memberToDoc(member m) {
        return new Document()
                .append("username", m.getUsername())
                .append("password", m.getPassword())
                .append("role", m.role != null ? m.role.toString() : "member")

                .append("age", m.age)
                .append("gender", m.gender)
                .append("address", m.address)
                .append("attendance", m.attendance)
                .append("isActive", m.isActive)
                .append("freezed", m.freezed)

                .append("phone", m.getPhone())
                .append("email", m.getEmail())
                .append("membershipType", m.getMembershipType())
                .append("membershipStart", m.getMembershipStart())
                .append("membershipEnd", m.getMembershipEnd());
    }

    private member memberFromDoc(Document d) {
        member m = new member(
                null,                              // role ignored in constructor (you override anyway)
                d.getString("password"),
                d.getString("username"),
                d.getString("phone"),
                d.getString("email"),
                d.getString("membershipType"),
                d.getString("membershipStart"),
                d.getString("membershipEnd")
        );

        // assign extra fields manually:
        m.age = d.getInteger("age", 0);
        m.gender = d.getString("gender");
        m.address = d.getString("address");
        m.attendance = d.getInteger("attendance", 0);
        m.isActive = d.getBoolean("isActive", true);
        m.freezed = d.getBoolean("freezed", false);

        return m;
    }

    // -------------------- APPOINTMENT CONVERTERS --------------------

    private Document appointmentToDoc(Appointment s) {
        return new Document()
                .append("appointmentName", s.getAppointmentName())
                .append("memberId", s.getMemberId())
                .append("trainerId", s.getTrainerId())
                .append("date", s.getDate())
                .append("time", s.getTime())
                .append("status", s.getStatus())
                .append("note", s.getNote());
    }

    private Appointment appointmentFromDoc(Document d) {
        Appointment s = new Appointment();
        s.setAppointmentName(d.getString("appointmentName"));
        s.setMemberId(d.getString("memberId"));
        s.setTrainerId(d.getString("trainerId"));
        s.setDate(d.getString("date"));
        s.setTime(d.getString("time"));
        s.setStatus(d.getString("status"));
        s.setNote(d.getString("note"));
        return s;
    }
}
