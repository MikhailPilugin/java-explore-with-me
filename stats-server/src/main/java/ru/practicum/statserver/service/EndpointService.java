package ru.practicum.statserver.service;

import ru.practicum.statserver.model.Endpoint;
import ru.practicum.statserver.model.dto.EndpointDtoOutput;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointService {
    Endpoint create(Endpoint endpoint);

    List<EndpointDtoOutput> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
