package com.app.repository;

import com.app.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByCategoryId(String categoryId);

    Optional<Category> findCategoryByMcc(String mcc);
}
