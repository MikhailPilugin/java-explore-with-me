package ru.practicum.evm.comments.service;

import ru.practicum.evm.comments.model.dto.CommentDto;

import java.util.List;

public interface CommentsService {
    List<CommentDto> getAllCommentsByEvent(Long eventId);

    CommentDto moderateCommentByAdmin(Long commentId, String status);

    List<CommentDto> getAllUserCommentsByEvent(Long userId, Long eventId);

    CommentDto createCommentByUser(Long userId, CommentDto commentDto);

    CommentDto updateCommentByUser(Long userId, Long commentId, CommentDto commentDto);

    void deleteCommentByUser(Long userId, Long commentId);
}
