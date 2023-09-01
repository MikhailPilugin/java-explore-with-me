package ru.practicum.evm.compilations.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.evm.compilations.model.dto.CompilationDto;
import ru.practicum.evm.compilations.model.dto.NewCompilationDto;
import ru.practicum.evm.compilations.model.dto.UpdateCompilationRequest;

import java.util.List;

public interface CompilationService {
    List<CompilationDto> getCompilations(boolean pinned, Pageable pageable);

    CompilationDto getCompilationById(long compId);

    CompilationDto addCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilation(long compId);

    CompilationDto updateCompilation(long compId, UpdateCompilationRequest updateCompilationRequest);
}
