package ru.practicum.evm.categories.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.evm.categories.model.dto.CategoryDto;
import ru.practicum.evm.categories.model.dto.NewCategoryDto;

import java.util.List;

public interface CategoriesService {
    CategoryDto addCategory(NewCategoryDto newCategoryDto);

    void deleteCategory(Long catId);

    CategoryDto changeCategory(Long catId, NewCategoryDto newCategoryDto);

    List<CategoryDto> getCategories(Pageable pageable);

    CategoryDto getCategoryInfo(Long catId);
}
