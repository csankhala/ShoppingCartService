package com.github.csankhala.ecommerce.service;

import com.github.csankhala.ecommerce.entity.ShoppingCart;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
public class ShoppingCartService {
  private final Map<Long, ShoppingCart> shippingCarts = new HashMap<>();

  public ShoppingCart add(long customerId, long productId) {
    ShoppingCart shoppingCart = this.shippingCarts.getOrDefault(customerId, new ShoppingCart());

    shoppingCart.setCustomerId(customerId);
    List<Long> products = Optional.ofNullable(shoppingCart.getProducts()).orElse(new ArrayList<>());
    products.add(productId);
    shoppingCart.setProducts(products);
    this.shippingCarts.put(customerId, shoppingCart);
    return shoppingCart;
  }

  public ShoppingCart get(long customerId) {
    return this.shippingCarts.get(customerId);
  }

  public void delete(long customerId) {
    this.shippingCarts.remove(customerId);
  }

  public ShoppingCart removeProductFromCart(long customerId, long productId) {
    ShoppingCart shoppingCart = this.shippingCarts.get(customerId);
    if (shoppingCart == null) {
      return null;
    }
    shoppingCart.getProducts().remove(productId);
    return this.shippingCarts.get(customerId);
  }

  public Collection<ShoppingCart> getAll() {
    return this.shippingCarts.values();
  }

  public void clear() {
    this.shippingCarts.clear();
  }
}
