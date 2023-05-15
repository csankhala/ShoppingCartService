package com.github.csankhala.ecommerce.mq;

import com.github.csankhala.ecommerce.entity.ShoppingCart;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface MQClient {

  @Topic("cart-checkout")
  void send(@KafkaKey Long customerId, ShoppingCart shoppingCart);
}
