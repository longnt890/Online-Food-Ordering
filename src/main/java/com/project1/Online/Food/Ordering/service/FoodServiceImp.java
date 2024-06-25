package com.project1.Online.Food.Ordering.service;

import com.project1.Online.Food.Ordering.model.Category;
import com.project1.Online.Food.Ordering.model.Food;
import com.project1.Online.Food.Ordering.model.Restaurant;
import com.project1.Online.Food.Ordering.repository.FoodRepository;
import com.project1.Online.Food.Ordering.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements FoodService{

    @Autowired
    public FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food =new Food();
        food.setFoodCategory(category);
        food.setRestaurant (restaurant);
        food.setDescription (req.getDescription());
        food.setImages (req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredientsItems(req.getIngredients());
        food.setSeasonal(req.isSeasional());
        food.setVegatarian(req.isVegetarin());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain, boolean isNonveg, boolean isSeasonal, String foodCategory) {

       List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

       if (isVegitarain) {
           foods = filterByVegetaion(foods,isVegitarain);
       }
        if(isNonveg) {
            foods=filterByNonveg(foods, isNonveg);
        }
        if(isSeasonal) {
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if(foodCategory!= null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory() != null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food -> food.isVegatarian() == isNonveg).collect(Collectors.toList());
    }

    private List<Food> filterByVegetaion(List<Food> foods, boolean isVegitarain) {

        return foods.stream().filter(food -> food.isVegatarian() == isVegitarain).collect(Collectors.toList());

    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);

        if(optionalFood.isEmpty()){
            throw new Exception("No such food");
        }

        return optionalFood.get();
    }

    @Override
    public Food updateAvailibiityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
