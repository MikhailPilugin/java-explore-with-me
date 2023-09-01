package ru.practicum.evmservice.comments.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventComments {

    private Long eventId;
    private List<CommentDto> comments;
}
