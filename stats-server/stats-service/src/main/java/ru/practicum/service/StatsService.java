package ru.practicum.service;

import ru.practicum.model.dto.EndpointHitDto;
import ru.practicum.model.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {

    void saveEndpointHit(EndpointHitDto endpointHit);

    List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, String[] uris, boolean unique);
}
