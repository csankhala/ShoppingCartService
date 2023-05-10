package com.github.csankhala.ecommerce.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Serdeable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    private long customerId;
    private List<Long> products;
}
