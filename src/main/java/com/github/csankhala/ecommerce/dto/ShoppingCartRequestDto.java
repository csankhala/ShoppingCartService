package com.github.csankhala.ecommerce.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Serdeable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartRequestDto {
    private long customerId;
    private long productId;
    //add qty support later
}
