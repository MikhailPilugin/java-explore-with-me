package ru.practicum.evm.requests.model.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.practicum.evm.events.model.Event;
import ru.practicum.evm.requests.model.Request;
import ru.practicum.evm.users.model.User;

@Component
@Mapper(componentModel = "spring")
public interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(target = "created", source = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "requester", target = "requester", qualifiedByName = "setUserToLong")
    @Mapping(source = "event", target = "event", qualifiedByName = "setEventToLong")
    ParticipationRequestDto requestToParticipationRequestDto(Request request);

    @Named("setUserToLong")
    static Long userId(User user) {
        return user.getId();
    }

    @Named("setEventToLong")
    static Long eventId(Event event) {
        return event.getId();
    }


}
