package ru.manicure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.manicure.model.Procedure;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
}
