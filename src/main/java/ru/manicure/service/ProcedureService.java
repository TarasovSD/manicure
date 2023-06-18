package ru.manicure.service;

import ru.manicure.model.Procedure;

import java.util.List;

public interface ProcedureService {

    Procedure getById(Long procedureId);

    List<Procedure> getAll();
}
