package ru.practicum.evm.comments.model.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.practicum.evm.comments.model.Comment;
import ru.practicum.evm.events.model.Event;
import ru.practicum.evm.users.model.User;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "author", target = "authorId", qualifiedByName = "UserToUserId")
    @Mapping(source = "event", target = "eventId", qualifiedByName = "EventToEventId")
    CommentDto commentToCommentDto(Comment comment);

    Comment commentDtoToComment(CommentDto commentDto);

    @Named("UserToUserId")
    static Long userId(User user) {
        return user.getId();
    }

    @Named("EventToEventId")
    static Long eventId(Event event) {
        return event.getId();
    }
}
