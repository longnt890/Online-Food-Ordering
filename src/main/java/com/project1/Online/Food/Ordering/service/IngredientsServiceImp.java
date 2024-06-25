package com.project1.Online.Food.Ordering.service;

import com.project1.Online.Food.Ordering.model.IngredientCategory;
import com.project1.Online.Food.Ordering.model.IngredientsItem;
import com.project1.Online.Food.Ordering.model.Restaurant;
import com.project1.Online.Food.Ordering.repository.IngredientCategoryRepository;
import com.project1.Online.Food.Ordering.repository.IngredientItemRepository;
import com.project1.Online.Food.Ordering.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImp implements IngredientsService{

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = new IngredientCategory();

        category.setName(name);
        category.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {

        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Ingredient category not found");
        }

        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {

        restaurantService.findRestaurantById(id);

        return ingredientCategoryRepository.findRestaurantById(id);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientsName, Long categoryId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setRestaurant(restaurant);
        ingredientsItem.setCategory(category);
        ingredientsItem.setName(ingredientsName);

        IngredientsItem ingredient = ingredientItemRepository.save(ingredientsItem);
        category.getIngredientsItems().add(ingredient);

        return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) throws Exception {

        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {

        Optional<IngredientsItem> opt = ingredientItemRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Ingredient item not found");
        }
        IngredientsItem ingredient = opt.get();
        ingredient.setStoke(!ingredient.isStoke());

        return ingredientItemRepository.save(ingredient);
    }
}
