package com.project1.Online.Food.Ordering.request;

import com.project1.Online.Food.Ordering.model.Category;
import com.project1.Online.Food.Ordering.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;
    private Long restaurantId;


    private boolean vegetarin;
    private boolean seasional;
    private List<IngredientsItem> ingredients;


}
