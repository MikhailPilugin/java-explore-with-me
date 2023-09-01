package ru.practicum.evm.events.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.evm.events.model.dto.EventFullDto;
import ru.practicum.evm.events.model.dto.EventShortDto;
import ru.practicum.evm.events.model.dto.NewEventDto;
import ru.practicum.evm.events.model.EventRequestStatusUpdateRequest;
import ru.practicum.evm.events.model.EventRequestStatusUpdateResult;
import ru.practicum.evm.events.model.UpdateEventAdminRequest;
import ru.practicum.evm.events.service.EventsService;
import ru.practicum.evm.requests.model.dto.ParticipationRequestDto;
import ru.practicum.evm.users.model.dto.PageRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class PrivateController {

    private final EventsService eventsService;

    @GetMapping("/{userId}/events")
    public List<EventShortDto> getEventsByUser(@PathVariable long userId,
                                               @RequestParam(value = "from", defaultValue = "0") Integer from,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.createPageRequest(from, size);
        return eventsService.getEventsByUser(userId, pageable);
    }

    @PostMapping("/{userId}/events")
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto addEventByUser(@PathVariable long userId,
                                       @Valid @RequestBody NewEventDto newEventDto) {
        return eventsService.addEventByUser(userId, newEventDto);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public EventFullDto getEventByUser(@PathVariable long userId,
                                       @PathVariable long eventId) {
        return eventsService.getEventByUser(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventFullDto changeEventByUser(@PathVariable long userId,
                                          @PathVariable long eventId,
                                          @Valid @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return eventsService.changeEventByUser(userId, eventId, updateEventAdminRequest);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> getRequestsOfEventByUser(@PathVariable long userId,
                                                                  @PathVariable long eventId) {
        return eventsService.getRequestsOfEventByUser(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public EventRequestStatusUpdateResult changeRequestStatusOfEvent(@PathVariable long userId,
                                                                     @PathVariable long eventId,
                                                                     @Valid @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return eventsService.changeRequestStatusOfEvent(userId, eventId, eventRequestStatusUpdateRequest);
    }
}
