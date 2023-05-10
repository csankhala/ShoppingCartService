package com.github.csankhala.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ShoppingCart {
  private long customerId;
  private List<Long> products;

  public ShoppingCart() {
    this.products = new ArrayList<>();
  }
}
