package ru.manicure.service;

import ru.manicure.model.dto.appointment.AppointmentDto;
import ru.manicure.model.dto.appointment.AppointmentFullDto;
import ru.manicure.model.dto.appointment.AppointmentUpdateDto;

import java.util.List;

public interface AppointmentService {
    AppointmentFullDto create(AppointmentDto appointmentDto);

    List<AppointmentFullDto> getAllByMasterId(Long masterId, String time);

    AppointmentFullDto getById(Long id);

    void removeById(Long appointmentId);

    AppointmentFullDto update(Long appointmentId, AppointmentUpdateDto appointmentUpdateDto);

    AppointmentFullDto approveOrReject(Long appointmentId, String approveOrReject);
}
