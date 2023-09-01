package ru.practicum.evm.comments.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.evm.comments.model.dto.CommentDto;
import ru.practicum.evm.comments.service.CommentsService;

@RestController
@RequestMapping(path = "/admin/comments")
@AllArgsConstructor
public class AdminController {

    private final CommentsService commentsService;

    @PatchMapping("/{commentId}")
    public CommentDto moderateCommentByAdmin(@PathVariable long commentId, @RequestParam String status) {
        return commentsService.moderateCommentByAdmin(commentId, status);
    }

}
