package ru.practicum.evm.comments.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.evm.comments.model.Comment;
import ru.practicum.evm.comments.model.CommentStatus;
import ru.practicum.evm.comments.model.dto.CommentDto;
import ru.practicum.evm.comments.model.dto.CommentMapper;
import ru.practicum.evm.comments.repository.CommentsRepository;
import ru.practicum.evm.events.model.Event;
import ru.practicum.evm.events.repository.EventRepository;
import ru.practicum.evm.exceptions.ApiError;
import ru.practicum.evm.exceptions.ConditionsException;
import ru.practicum.evm.exceptions.NotFoundException;
import ru.practicum.evm.users.model.User;
import ru.practicum.evm.users.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<CommentDto> getAllCommentsByEvent(Long eventId) {
        eventRepository.findById(eventId).orElseThrow(NotFoundException::new);
        return commentsRepository.getCommentsByEventId(eventId).stream()
                .filter(comment -> comment.getCommentStatus().equals(CommentStatus.PUBLISH))
                .map(CommentMapper.INSTANCE::commentToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto moderateCommentByAdmin(Long commentId, String status) {
        Comment comment = commentsRepository.findById(commentId).orElseThrow(NotFoundException::new);
        comment.setCommentStatus(CommentStatus.valueOf(status));
        return CommentMapper.INSTANCE.commentToCommentDto(commentsRepository.save(comment));
    }

    @Override
    public List<CommentDto> getAllUserCommentsByEvent(Long userId, Long eventId) {
        return null;
    }

    @Override
    public CommentDto createCommentByUser(Long userId, CommentDto commentDto) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        commentDto.setCreated(LocalDateTime.now());
        Event event = eventRepository.findById(commentDto.getEventId()).orElseThrow(NotFoundException::new);
        commentDto.setCommentStatus(CommentStatus.PENDING);
        Comment comment = CommentMapper.INSTANCE.commentDtoToComment(commentDto);
        comment.setAuthor(user);
        comment.setEvent(event);
        return CommentMapper.INSTANCE.commentToCommentDto(commentsRepository.save(comment));
    }

    @Override
    public CommentDto updateCommentByUser(Long userId, Long commentId, CommentDto commentDto) {
        userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Comment comment = commentsRepository.findById(commentId).orElseThrow(NotFoundException::new);
        if (!userId.equals(comment.getAuthor().getId())) {
            throw new ConditionsException(new ApiError(
                    HttpStatus.CONFLICT.toString(), "Incorrectly made request.",
                    "Только автор комментария может его редактировать", LocalDateTime.now().format(formatter)));
        }
        comment.setText(commentDto.getText());
        return CommentMapper.INSTANCE.commentToCommentDto(commentsRepository.save(comment));
    }

    @Override
    public void deleteCommentByUser(Long userId, Long commentId) {
        userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Comment comment = commentsRepository.findById(commentId).orElseThrow(NotFoundException::new);
        commentsRepository.delete(comment);
    }
}
