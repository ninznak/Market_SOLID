package ru.alex.product;

import java.util.ArrayList;
import java.util.List;

public record StorageProducts() {

    private static ArrayList<Product> productList;



    public static ArrayList<Product> getProductList() {
        return productList;
    }
}
