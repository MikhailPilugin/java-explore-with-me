package ru.practicum.evm.comments.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.evm.comments.model.CommentStatus;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    @Size(min = 5, max = 7000)
    private String text;
    private LocalDateTime created;
    private CommentStatus commentStatus;
    private Long authorId;
    private Long eventId;
}
