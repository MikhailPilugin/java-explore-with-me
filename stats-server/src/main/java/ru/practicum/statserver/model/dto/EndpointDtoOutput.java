package ru.practicum.statserver.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EndpointDtoOutput {
    private String app;
    private String uri;
    private long hits;
}
