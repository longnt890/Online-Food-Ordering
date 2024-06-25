package com.project1.Online.Food.Ordering.service;

import com.project1.Online.Food.Ordering.model.Category;
import com.project1.Online.Food.Ordering.model.Food;
import com.project1.Online.Food.Ordering.model.Restaurant;
import com.project1.Online.Food.Ordering.repository.CategoryRepository;
import com.project1.Online.Food.Ordering.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    public CategoryRepository  categoryRepository;

    @Autowired
    public RestaurantService restaurantService;

    @Override
    public Category createCategory(String name, Long userId) throws Exception{

        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);

        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new Exception("No such category");
        }

        return optionalCategory.get();
    }
}
