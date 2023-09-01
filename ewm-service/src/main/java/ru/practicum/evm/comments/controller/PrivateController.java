package ru.practicum.evm.comments.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.evm.comments.model.dto.CommentDto;
import ru.practicum.evm.comments.service.CommentsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users/comments")
@AllArgsConstructor
public class PrivateController {

    private final CommentsService commentsService;

    @GetMapping("/{userId}/event/{eventId}")
    public List<CommentDto> getAllUserCommentsByEvent(@PathVariable long userId, @PathVariable long eventId) {
        return commentsService.getAllUserCommentsByEvent(userId, eventId);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createCommentByUser(@PathVariable long userId, @Valid @RequestBody CommentDto commentDto) {
        return commentsService.createCommentByUser(userId, commentDto);
    }
}
