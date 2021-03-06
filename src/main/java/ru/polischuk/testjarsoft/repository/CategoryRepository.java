package ru.polischuk.testjarsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.polischuk.testjarsoft.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByDeletedFalse();
    Optional<Category> findByIdAndDeletedFalse(Long id);
}
