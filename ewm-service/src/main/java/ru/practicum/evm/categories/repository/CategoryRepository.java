package ru.practicum.evm.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.evm.categories.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getCategoriesByName(String name);
}
