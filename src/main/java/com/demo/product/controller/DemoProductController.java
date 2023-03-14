package com.demo.product.controller;

import io.micronaut.http.annotation.*;

@Controller("/demoProduct")
public class DemoProductController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }


}