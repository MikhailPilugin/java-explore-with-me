package ru.practicum.evmservice.categories.model.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.practicum.evmservice.categories.model.Category;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);

    Category newCategoryDtoToCategory(NewCategoryDto newCategoryDto);
}
