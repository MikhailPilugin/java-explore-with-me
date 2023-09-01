package ru.practicum.evmservice.compilations.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.practicum.evmservice.compilations.model.dto.CompilationDto;
import ru.practicum.evmservice.compilations.service.CompilationService;
import ru.practicum.evmservice.utils.PageRequestUtil;

import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
@AllArgsConstructor
public class PublicController {

    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(required = false) boolean pinned,
                                                @RequestParam(defaultValue = "0") int from,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequestUtil.createPageRequest(from, size);
        return compilationService.getCompilations(pinned, pageable);
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationsById(@PathVariable long compId) {
        return compilationService.getCompilationById(compId);
    }
}
