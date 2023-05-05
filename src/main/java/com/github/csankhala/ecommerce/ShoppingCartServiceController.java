package com.github.csankhala.ecommerce;

import io.micronaut.http.annotation.*;

@Controller("/shoppingCartService")
public class ShoppingCartServiceController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}