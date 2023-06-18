package ru.manicure.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.manicure.model.dto.appointment.AppointmentDto;
import ru.manicure.model.dto.appointment.AppointmentFullDto;
import ru.manicure.model.dto.appointment.AppointmentUpdateDto;
import ru.manicure.service.AppointmentService;

import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/appointment")
@Slf4j
@Validated
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping()
    public AppointmentFullDto create(@RequestBody AppointmentDto appointmentDto) {
        log.info("A request to create an appointment has been sent to the AppointmentService");
        return appointmentService.create(appointmentDto);
    }

    @GetMapping("/{masterId}/all")
    public List<AppointmentFullDto> getAllByMasterId(@PositiveOrZero @PathVariable Long masterId) {
        log.info("A request to get all appointments for master with id {} has been sent to the AppointmentService", masterId);
        return appointmentService.getAllByMasterId(masterId, "all");
    }

    @GetMapping("/{appointmentId}")
    public AppointmentFullDto getById(@PositiveOrZero @PathVariable Long appointmentId) {
        log.info("A request to get an appointment with id {} has been sent to the AppointmentService", appointmentId);
        return appointmentService.getById(appointmentId);
    }

    @GetMapping("/{masterId}/future")
    public List<AppointmentFullDto> getAllByMasterIdFuture(@PositiveOrZero @PathVariable Long masterId) {
        log.info("A request to get all appointments for master with id {} in the future has been sent to the " +
                        "AppointmentService"
                , masterId);
        return appointmentService.getAllByMasterId(masterId, "future");
    }

    @GetMapping("/{masterId}/past")
    public List<AppointmentFullDto> getAllByMasterIdPast(@PositiveOrZero @PathVariable Long masterId) {
        log.info("A request to get all appointments for master with id {} in the past has been sent to the " +
                        "AppointmentService"
                , masterId);
        return appointmentService.getAllByMasterId(masterId, "past");
    }

    @DeleteMapping("/{appointmentId}")
    public void removeById(@PositiveOrZero @PathVariable Long appointmentId) {
        log.info("A request to delete an appointment with id {} has been sent to the AppointmentService",
                appointmentId);
        appointmentService.removeById(appointmentId);
    }

    @PatchMapping("/{appointmentId}")
    public AppointmentFullDto update(@PositiveOrZero @PathVariable Long appointmentId,
                                     @RequestBody AppointmentUpdateDto appointmentUpdateDto) {
        log.info("A request to update an appointment with id {} has been sent to the AppointmentService",
                appointmentId);
        return appointmentService.update(appointmentId, appointmentUpdateDto);
    }

    @PatchMapping("/{appointmentId}/resolution")
    public AppointmentFullDto approveOrReject(@PositiveOrZero @PathVariable Long appointmentId,
                                     @RequestParam String approveOrReject) {
        log.info("A request to {} an appointment with id {} has been sent to the AppointmentService",
                approveOrReject, appointmentId);
        return appointmentService.approveOrReject(appointmentId, approveOrReject);
    }
}
