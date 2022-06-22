package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select distinct this from Category this where this.name = :name and this.topCategory.id=:topId")
    Optional<Category>findCategory(@Param("name") String name, @Param("topId")Long id);

    @Query("select this from Category this where this.topCategory is null")
    List<Category>topCategoryList();
}
