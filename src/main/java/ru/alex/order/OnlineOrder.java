package ru.alex.order;

import ru.alex.product.Product;

import java.util.List;

public class OnlineOrder extends Order {                                    //Класс "OnlineOrder" может заменять "Order", не нарушая работу программы.
    public OnlineOrder(int id, List<Product> products) {
        super(id, products);
    }
    // Дополнительные методы для онлайн-заказов
}
