package com.example.nobsv2.product.exceptions;

import com.example.nobsv2.product.model.Product;
import io.micrometer.common.util.StringUtils;

public enum ErrorMessages {
    //messages all in one place
    PRODUCT_NOT_FOUND("Product Not Found"),
    NAME_REQUIRE("Name is required"),
    DESCRIPTION_LENGTH("Description must be 20 characters long"),
    PRICE_CANNOT_BE_NEGATIVE("Price cannot be negative");


    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

