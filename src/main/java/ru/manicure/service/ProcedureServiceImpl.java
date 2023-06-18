package ru.manicure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manicure.exceptions.ProcedureNotFoundException;
import ru.manicure.model.Procedure;
import ru.manicure.repository.ProcedureRepository;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;

    @Override
    public Procedure getById(Long procedureId) {
        String message = "Procedure id " + procedureId + " not found";
        return procedureRepository.findById(procedureId)
                .orElseThrow(() -> new ProcedureNotFoundException(message));
    }

    @Override
    public List<Procedure> getAll() {
        return procedureRepository.findAll();
    }
}
