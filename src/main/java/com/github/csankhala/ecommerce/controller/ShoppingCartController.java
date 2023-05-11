package com.github.csankhala.ecommerce.controller;

import com.github.csankhala.ecommerce.dto.ShoppingCartDto;
import com.github.csankhala.ecommerce.dto.ShoppingCartRequestDto;
import com.github.csankhala.ecommerce.entity.ShoppingCart;
import com.github.csankhala.ecommerce.mapper.ShoppingCartMapper;
import com.github.csankhala.ecommerce.service.ShoppingCartService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import java.util.List;

@Controller("/shopping-cart")
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;
  private final ShoppingCartMapper shoppingCartMapper;

  @Inject
  public ShoppingCartController(
      ShoppingCartService shoppingCartService, ShoppingCartMapper shoppingCartMapper) {
    this.shoppingCartService = shoppingCartService;
    this.shoppingCartMapper = shoppingCartMapper;
  }

  @Post(uri = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
  public ShoppingCartDto create(@Body ShoppingCartRequestDto shoppingCartRequestDto) {
    ShoppingCart shoppingCart = this.shoppingCartService.add(
        shoppingCartRequestDto.getCustomerId(), shoppingCartRequestDto.getProductId());
    return this.shoppingCartMapper.map(shoppingCart);
  }

  @Get(uri = "/{customerId}", produces = MediaType.APPLICATION_JSON)
  public ShoppingCartDto get(@PathVariable long customerId) {
    return this.shoppingCartMapper.map(this.shoppingCartService.get(customerId));
  }

  @Get(uri = "/", produces = MediaType.APPLICATION_JSON)
  public List<ShoppingCartDto> get() {
    return this.shoppingCartMapper.map(this.shoppingCartService.getAll());
  }

  @Delete(uri = "/{customerId}")
  public void delete(@PathVariable long customerId) {
    this.shoppingCartService.delete(customerId);
  }

  @Post(uri="/checkout/{customerId}")
  public void checkout(@PathVariable long customerId) {
    this.shoppingCartService.checkout(customerId);
  }

}
