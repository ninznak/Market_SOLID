package ru.alex.order;

import ru.alex.buyers.Buyer;
import ru.alex.product.Product;

import java.util.List;

public class Order {                                   //Подклассы можно использовать вместо базовых классов
    private Integer id;                                    //Принцип подстановки Лисков
    private Buyer buyer;
    private List<Product> products;
    private OrderStatus status;

    public Order(int id, Buyer buyer, List<Product> products) {
        this.id = id;
        this.buyer = buyer;
        this.products = products;
        this.status = OrderStatus.NEW;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getID() {
        return id.toString();
    }

    public List<Product> getProducts() {
        return products;
    }
}