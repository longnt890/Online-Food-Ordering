package com.project1.Online.Food.Ordering.repository;

import com.project1.Online.Food.Ordering.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory,Long> {

    List<IngredientCategory> findRestaurantById(Long id);

}
