package ru.manicure.mapper;

import lombok.experimental.UtilityClass;
import ru.manicure.model.Procedure;
import ru.manicure.model.dto.procedure.ProcedureFullDto;

@UtilityClass
public class ProcedureMapper {

    public Procedure toProcedure(ProcedureFullDto procedureFullDto) {
        return new Procedure(procedureFullDto.getId(),
                procedureFullDto.getProcedureName(),
                procedureFullDto.getDescription());
    }

    public ProcedureFullDto toProcedureDto(Procedure procedure) {
        return new ProcedureFullDto(procedure.getId(),
                procedure.getProcedureName(),
                procedure.getDescription());
    }
}
