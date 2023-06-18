package ru.manicure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.manicure.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where (a.master.id = :masterId or a.client.id = :clientId or " +
            "a.master.id = :clientId) and " +
            "((a.end > :start and a.start<:start) " +
            "or (a.start<:end and a.end> :end) or (a.start > :start and a.end<:end) or " +
            "(a.start = :start and a.end = :end)) and a.status <> 'REJECTED'")
    List<Appointment> getAppointmentsToSearchForIntersections(Long masterId, Long clientId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findAppointmentsByMasterIdOrderByCreated(Long masterId);

    @Query("select a from Appointment a where a.master.id = :masterId and a.start > :thisMoment")
    List<Appointment> findAppointmentsByMasterIdInFuture(Long masterId, LocalDateTime thisMoment);

    @Query("select a from Appointment a where a.master.id = :masterId and a.end < :thisMoment")
    List<Appointment> findAppointmentsByMasterIdInPast(Long masterId, LocalDateTime thisMoment);
}
