package ru.alex.filter;

import ru.alex.product.Product;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PriceFilter implements Filter {
    private double maxPrice;

    public PriceFilter(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public List<Product> applyFilter(List<Product> products) {
        return products.stream()
                .filter(product -> product.getPrice() <= maxPrice)
                .collect(toList());
    }
}