package com.github.csankhala.ecommerce.mapper;

import com.github.csankhala.ecommerce.dto.ShoppingCartDto;
import com.github.csankhala.ecommerce.entity.ShoppingCart;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface ShoppingCartMapper {
    ShoppingCartDto map(ShoppingCart shoppingCart);
    List<ShoppingCartDto> map(Collection<ShoppingCart> product);
}
