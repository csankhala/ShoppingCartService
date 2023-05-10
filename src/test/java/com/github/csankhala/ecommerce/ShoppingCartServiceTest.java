package com.github.csankhala.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.csankhala.ecommerce.dto.ShoppingCartDto;
import com.github.csankhala.ecommerce.dto.ShoppingCartRequestDto;
import com.github.csankhala.ecommerce.entity.ShoppingCart;
import com.github.csankhala.ecommerce.mapper.ShoppingCartMapper;
import com.github.csankhala.ecommerce.service.ShoppingCartService;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
class ShoppingCartServiceTest {

  @Inject EmbeddedApplication<?> application;

  @Inject
  @Client("/")
  HttpClient client;

  @Inject ShoppingCartService shoppingCartService;

  @Inject ShoppingCartMapper shoppingCartMapper;

  @Test
  void testItWorks() {
    Assertions.assertTrue(application.isRunning());
  }

  @Test
  void testCreateShoppingCart() {
    // given
    ShoppingCartRequestDto requestDto = new ShoppingCartRequestDto();
    requestDto.setCustomerId(1L);
    requestDto.setProductId(1L);

    // when
    HttpRequest<ShoppingCartRequestDto> request =
        HttpRequest.POST("/shopping-cart", requestDto).contentType(MediaType.APPLICATION_JSON);
    ShoppingCartDto responseDto = client.toBlocking().retrieve(request, ShoppingCartDto.class);

    // then
    ShoppingCart shoppingCart = shoppingCartService.get(1L);
    assertNotNull(shoppingCart);
    assertEquals(requestDto.getCustomerId(), shoppingCart.getCustomerId());
    assertEquals(requestDto.getProductId(), shoppingCart.getProducts().get(0));
  }

  @Test
  void testGetShoppingCartByCustomerId() {
    this.shoppingCartService.clear();
    // given
    long customerId = 2L;
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.setCustomerId(customerId);
    shoppingCart.getProducts().add(2L);
    shoppingCartService.add(shoppingCart.getCustomerId(), shoppingCart.getProducts().get(0));

    // when
    HttpRequest<?> request = HttpRequest.GET("/shopping-cart/" + customerId);
    ShoppingCartDto responseDto = client.toBlocking().retrieve(request, ShoppingCartDto.class);

    // then
    assertEquals(shoppingCart.getCustomerId(), responseDto.getCustomerId());
    assertEquals(shoppingCart.getProducts(), responseDto.getProducts());
  }

  @Test
  void testGetAllShoppingCarts() {
    this.shoppingCartService.clear();
    ShoppingCart shoppingCart1 = new ShoppingCart();
    shoppingCart1.setCustomerId(1L);
    shoppingCart1.getProducts().add(1L);
    ShoppingCart shoppingCart2 = new ShoppingCart();
    shoppingCart2.setCustomerId(2L);
    shoppingCart2.getProducts().add(2L);
    shoppingCartService.add(shoppingCart1.getCustomerId(), shoppingCart1.getProducts().get(0));
    shoppingCartService.add(shoppingCart2.getCustomerId(), shoppingCart2.getProducts().get(0));

    // when
    HttpRequest<?> request = HttpRequest.GET("/shopping-cart");
    List<ShoppingCartDto> responseDto =
        client.toBlocking().retrieve(request, Argument.listOf(ShoppingCartDto.class));

    // then
    assertEquals(2, responseDto.size());
  }
}
