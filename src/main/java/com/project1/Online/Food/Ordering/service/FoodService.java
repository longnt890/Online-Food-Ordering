package com.project1.Online.Food.Ordering.service;

import com.project1.Online.Food.Ordering.model.Category;
import com.project1.Online.Food.Ordering.model.Food;
import com.project1.Online.Food.Ordering.model.Restaurant;
import com.project1.Online.Food.Ordering.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood (CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood (Long foodId) throws Exception;

    public List<Food> getRestaurantsFood (Long restaurantId,
                                           boolean isVegitarain,
                                           boolean isNonveg,
                                           boolean isSeasonal,
                                           String foodCategory
    );

    public List<Food> searchFood (String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailibiityStatus (Long foodId) throws Exception;


}
