package ru.alex.buyers;

import ru.alex.basket.Basket;
import ru.alex.order.Order;
import ru.alex.order.OrderStatus;

import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Buyer {                   // Избегаем дублирования кода в наследниках SimpleBuyer и Последующих
    private double wallet;                          // за счет реализации основных свойств и методов в родителе
    private final String name;
    private final String surname;
    private String email;
    private Basket basket;
    private final HashMap<String, Order> orderHistory = new LinkedHashMap<>();


    public Buyer(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public void setBasket(Basket basket) {          // Принцип Dependency Inversion Principle (Инверсия зависимости)
        this.basket = basket;                       // Вместо конкретной реализации передаем интерфейс Busket
    }                                               // Так мы отделяем покупателя от конкретной реализации корзины

    public double getWallet() {
        return wallet;
    }

    public void addToWallet(double wallet) {
        this.wallet += wallet;
    }

    public void makeOrder(Order order) {
        orderHistory.put(order.getID(), order);
    }

    public void cancelOrder(String orderId) {
        orderHistory.get(orderId).setStatus(OrderStatus.CANCELLED);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Order getOrder(String orderId) {
        return orderHistory.get(orderId);
    }

    public void getOrderHistory() {
        System.out.println("Список заказов: ");
        for (Order order : orderHistory.values()) {
            System.out.println("Заказ #" + order.getID() + ": " + order.getStatus());
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Покупатель: " + "{" + "имя ="
                + getName() + ", фамилия ="
                + getSurname() + ", email ="
                + getEmail() + '}';
    }
}
