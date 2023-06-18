package ru.manicure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.manicure.model.Appointment;
import ru.manicure.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByAppointmentOrderByCreated(Appointment appointment);

    @Query("select c from Comment c where c.appointment.id in :ids")
    List<Comment> getAllByAppointmentsId(List<Long> ids);

    List<Comment> getCommentsByAppointmentIdOrderByCreated(Long appointmentId);
}
