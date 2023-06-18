package ru.manicure.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.manicure.mapper.ProcedureMapper;
import ru.manicure.model.dto.procedure.ProcedureFullDto;
import ru.manicure.service.ProcedureService;

import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/procedure")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ProcedureController {

    private final ProcedureService procedureService;

    @GetMapping("/{procedureId}")
    public ProcedureFullDto getById(@PositiveOrZero @PathVariable Long procedureId) {
        log.info("A request to get a procedure with id {}  has been sent to the procedureService", procedureId);
        return ProcedureMapper.toProcedureDto(procedureService.getById(procedureId));
    }

    @GetMapping()
    public List<ProcedureFullDto> getAll() {
        log.info("A request to get all procedures has been sent to the procedureService");
        return procedureService.getAll().stream().map(ProcedureMapper::toProcedureDto).collect(Collectors.toList());
    }
}
