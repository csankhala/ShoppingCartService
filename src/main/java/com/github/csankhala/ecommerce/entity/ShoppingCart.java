package com.github.csankhala.ecommerce.entity;

import io.micronaut.serde.annotation.Serdeable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@Serdeable
@Data
public class ShoppingCart {
  private long customerId;
  private List<Long> products;

  public ShoppingCart() {
    this.products = new ArrayList<>();
  }
}
