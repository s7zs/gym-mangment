package services;

import org.example.model.member;
import org.example.model.Appointment;
import org.example.model.receptionist;
import org.example.repo.ReceptionistRepository;
import java.util.List;

/**
 * ReceptionistService - rewritten to match the NEW member model.
 */
public class ReceptionistService {

    private final ReceptionistRepository repo;

    public ReceptionistService(ReceptionistRepository repo) {
        this.repo = repo;
    }

    // ---------------------------------------------------------
    // MEMBER APIs
    // ---------------------------------------------------------

    /**
     * Register a new member.
     * Uses username as the primary ID (because memberId does not exist).
     */
    public void createMember(member m) {
        if (m == null) throw new IllegalArgumentException("Member is null");
        if (m.getUsername() == null || m.getUsername().isBlank())
            throw new IllegalArgumentException("username is required");
     

        repo.saveMember(m);
    }

    // UI compatibility
    public void registerNewMember(member m) {
        createMember(m);
    }

    public member getMemberById(String username) {
        return repo.findMemberById(username);
    }

    public List<member> getAllMembers() {
        return repo.findAllMembers();
    }

    /**
     * Renew membership (update type and date range)
     */
    public void renewMembership(
            String username,
            String membershipType,
            String startIso,
            String endIso
    ) {

        member m = repo.findMemberById(username);
        if (m == null)
            throw new IllegalArgumentException("Member not found");

        m.setMembershipType(membershipType);
        m.setMembershipStart(startIso);
        m.setMembershipEnd(endIso);

        repo.saveMember(m);
    }

    /**
     * Cancel membership by setting membershipType = CANCELLED
     */
    public boolean cancelMembership(String username, String reason) {
        member m = repo.findMemberById(username);
        if (m == null) return false;

        m.setMembershipType("CANCELLED");
        repo.saveMember(m);

        return true;
    }

    // ---------------------------------------------------------
    // ATTENDANCE
    // ---------------------------------------------------------

    /**
     * Increase attendance count by 1.
     */
    public void recordAttendance(String username) {
        member m = repo.findMemberById(username);
        if (m == null) throw new IllegalArgumentException("Member not found");

        m.attendance += 1;
        repo.saveMember(m);
    }

    public int getAttendance(String username) {
        member m = repo.findMemberById(username);
        if (m == null) throw new IllegalArgumentException("Member not found");

        return m.attendance;
    }

    // ---------------------------------------------------------
    // INVOICES — NOT SUPPORTED BECAUSE your member model has no invoice fields
    // ---------------------------------------------------------

    public void saveInvoice(Object o) {
        throw new UnsupportedOperationException("Invoices are not implemented with new model");
    }

    // ---------------------------------------------------------
    // APPOINTMENTS
    // ---------------------------------------------------------

    public void scheduleAppointment(Appointment appt) {

        if (appt == null) throw new IllegalArgumentException("Appointment is null");

        if (appt.getAppointmentName() == null || appt.getAppointmentName().isBlank())
            appt.setAppointmentName("AP-" + System.currentTimeMillis());

        if (appt.getMemberId() == null || appt.getMemberId().isBlank())
            throw new IllegalArgumentException("username (memberId) required");

        repo.saveAppointment(appt);
    }

    public List<Appointment> getAppointmentsForMember(String username) {
        return repo.findAppointmentsByMember(username);
    }

    public List<Appointment> getAllAppointments() {
        return repo.findAllAppointments();
    }

    public boolean cancelAppointment(String id) {
        // Not implemented because repo does not support update/delete
        return false;
    }

    // ---------------------------------------------------------
    // SCHEDULE
    // ---------------------------------------------------------

    public List<Appointment> getDailySchedule(String date) {

        List<Appointment> out = new java.util.ArrayList<>();

        for (Appointment a : repo.findAllAppointments()) {
            if (date.equals(a.getDate())) {
                out.add(a);
            }
        }

        return out;
    }
}
