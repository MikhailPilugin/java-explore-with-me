package ru.practicum.model;

import dto.EndpointHitDto;
import dto.ViewStatsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface StatsMapper {

    StatsMapper INSTANCE = Mappers.getMapper(StatsMapper.class);

    ViewStatsDto viewStatsToViewStatsDto(ViewStats viewStats);

    @Mapping(target = "timestamp", source = "timestamp", dateFormat = "yyyy-MM-dd HH:mm:ss")
    EndpointHit endpointHitDtoToEndpointHit(EndpointHitDto endpointHitDto);
}
