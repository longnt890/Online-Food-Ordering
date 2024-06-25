package com.project1.Online.Food.Ordering.request;

import com.project1.Online.Food.Ordering.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;

    private Address deliveryAddress;

}
