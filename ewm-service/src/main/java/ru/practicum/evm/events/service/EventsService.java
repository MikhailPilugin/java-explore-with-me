package ru.practicum.evm.events.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.evm.events.model.dto.EventFullDto;
import ru.practicum.evm.events.model.dto.EventShortDto;
import ru.practicum.evm.events.model.dto.NewEventDto;
import ru.practicum.evm.events.model.EventRequestStatusUpdateRequest;
import ru.practicum.evm.events.model.EventRequestStatusUpdateResult;
import ru.practicum.evm.events.model.EventState;
import ru.practicum.evm.events.model.UpdateEventAdminRequest;
import ru.practicum.evm.requests.model.dto.ParticipationRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface EventsService {

    List<EventShortDto> getEventsByUser(Long userId, Pageable pageable);

    EventFullDto addEventByUser(Long userId, NewEventDto newEventDto);

    EventFullDto getEventByUser(Long userId, Long eventId);

    EventFullDto changeEventByUser(Long userId, Long eventId, UpdateEventAdminRequest updateEventAdminRequest);

    List<ParticipationRequestDto> getRequestsOfEventByUser(Long userId, Long eventId);

    EventRequestStatusUpdateResult changeRequestStatusOfEvent(Long userId, Long eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);

    List<EventFullDto> searchForEventsByAdmin(List<Long> users, List<EventState> states, List<Long> categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    EventFullDto editEventByAdmin(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);

    List<EventFullDto> getEventsByFilter(String ip, String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                         LocalDateTime rangeEnd, boolean onlyAvailable, String sort, Pageable pageable, HttpServletRequest request);

    EventFullDto getEventInfo(HttpServletRequest request, Long eventId);
}