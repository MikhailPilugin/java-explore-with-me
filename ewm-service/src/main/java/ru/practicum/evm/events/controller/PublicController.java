package ru.practicum.evm.events.controller;

import lombok.AllArgsConstructor;
import org.mapstruct.Context;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.practicum.evm.events.model.dto.EventFullDto;
import ru.practicum.evm.events.service.EventsService;
import ru.practicum.evm.users.model.dto.DateFormat;
import ru.practicum.evm.users.model.dto.PageRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@AllArgsConstructor
public class PublicController {

    private final EventsService eventsService;

    @GetMapping
    public List<EventFullDto> getEventsByFilter(@RequestParam(required = false) String text,
                                                @RequestParam(required = false) List<Long> categories,
                                                @RequestParam(required = false) Boolean paid,
                                                @RequestParam(required = false) String rangeStart,
                                                @RequestParam(required = false) String rangeEnd,
                                                @RequestParam(required = false) boolean onlyAvailable,
                                                @RequestParam(required = false) String sort,
                                                @RequestParam(value = "from", defaultValue = "0") Integer from,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @Context HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        LocalDateTime formattedRangeStart = DateFormat.formatStringToDate(rangeStart);
        LocalDateTime formattedRangeEnd = DateFormat.formatStringToDate(rangeEnd);
        Pageable pageable = PageRequest.createPageRequest(from, size);

        return eventsService.getEventsByFilter(ip, text, categories, paid, formattedRangeStart, formattedRangeEnd,
                onlyAvailable, sort, pageable, request);
    }

    @GetMapping("/{id}")
    public EventFullDto getEventInfo(@Context HttpServletRequest request, @PathVariable long id) {
        return eventsService.getEventInfo(request, id);
    }
}
