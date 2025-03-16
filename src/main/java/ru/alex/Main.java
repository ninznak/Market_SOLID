package ru.alex;

import ru.alex.basket.Basket;
import ru.alex.basket.SimpleBasket;
import ru.alex.buyers.Buyer;
import ru.alex.buyers.SimpleBuyer;
import ru.alex.filter.Filter;
import ru.alex.filter.PriceFilter;
import ru.alex.order.OnlineOrder;
import ru.alex.order.Order;
import ru.alex.order.OrderStatus;
import ru.alex.product.Product;
import ru.alex.product.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Product> productList = new ArrayList<>();
    static int orderNumber = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Программа Мазазин запущена!");
        initializeStorage();
        Buyer buyer = createBuyer(sc);
        Basket basket = new SimpleBasket();
        buyer.setBasket(basket);

        System.out.println("Всего товаров в корзине на сумму : " + basket.getBasketTotal() + " руб.");
        while (true) {
            printMenu();
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Введите номер товара который хотите добавить в корзину:");
                int i = 1;
                for (Product product : productList) {
                    System.out.println(i + ". " + product.getName());
                    i++;
                }
                i = sc.nextInt();
                basket.addToBasket(productList.get(i - 1));
                System.out.println("Товар " + productList.get(i - 1).getName() + " добавлен в корзину!");
            } else if (choice == 2) {
                System.out.println("Введите номер товара который хотите удалить из корзины:");
                int i = 1;
                for (Product product : basket.getBasketItems()) {
                    System.out.println(i + ". " + product.getName());
                    i++;
                }
                int j = sc.nextInt();
                basket.removeFromBasket(j - 1);
            } else if (choice == 3) {
                System.out.println("Товары в корзине на сумму:");
                System.out.println(basket.getBasketTotal());
                int i = 1;
                for (Product product : basket.getBasketItems()) {
                    System.out.println(i++ + ". " + product.getName());
                }
            } else if (choice == 4) {
                System.out.println("Введите сумму, чтобы посмотреть товары, которые меньше указанной суммы:");
                double filterLimit = sc.nextDouble();
                Filter priceFilter = new PriceFilter(filterLimit);
                List<Product> filteredProducts = priceFilter.applyFilter(productList);
                for (Product product : filteredProducts) {
                    System.out.println(product.getName() + ": " + product.getPrice());
                }
            } else if (choice == 5) {
                System.out.println("Список товаров в корзине:");
                for (Product product : basket.getBasketItems()) {
                    System.out.println(product.getName());
                }
                System.out.println("Оплачиваем сумму: " + basket.getBasketTotal() + " руб.");
                Order order = new OnlineOrder(orderNumber++, basket.getBasketItems());
                buyer.makeOrder(order);
                System.out.println("Ваш заказ оформлен. Статус заказа: " + order.getStatus());
                System.out.println("Необходимо оплатить заказ. Сумма к оплате: " + basket.getBasketTotal() + " руб.");
                System.out.println("Введите сумму для оплаты заказа:");
                double paymentAmount = sc.nextDouble();
                if (paymentAmount >= basket.getBasketTotal()) {
                    if (paymentAmount >= basket.getBasketTotal()) {
                        System.out.println("Ваш заказ оплачен. Сумма = " + (basket.getBasketTotal()) + " руб.");
                        order.setStatus(OrderStatus.PAID);
                        System.out.println("Статус заказа: " + order.getStatus());
                    } else {
                        System.out.println("Сумма недостаточна!");
                        System.out.println("Заказ отменен!");
                        order.setStatus(OrderStatus.CANCELLED);
                        buyer.cancelOrder(order.getID());
                    }
                }
                basket.clearBasket();
            } else if (choice == 6) {
                System.out.println("Введите номер заказа для проверки статуса заказа:");
                buyer.getOrderHistory();
                int orderId = sc.nextInt();
                Order order = buyer.getOrder(Integer.toString(orderId));
                if (order != null) {
                    System.out.println("Статус заказа #" + orderId + ": " + order.getStatus());
                } else {
                    System.out.println("Заказ с номером #" + orderId + " не найден!");
                }
            } else if (choice == 7) {
                System.out.println("Введите номер заказа для возврата заказа:");
                buyer.getOrderHistory();
                int orderId = sc.nextInt();
                Order order = buyer.getOrder(Integer.toString(orderId));
                if (order != null) {
                    if (order.getStatus() == OrderStatus.PAID) {
                        buyer.cancelOrder(order.getID());
                        double returnedAmount = basket.getBasketTotal();
                        buyer.addToWallet(basket.getBasketTotal());
                        System.out.println("Возвращено в кошелек покупателя: " + returnedAmount + " руб.");
                        System.out.println("Возврат заказа #" + orderId + ": произведен");
                    } else {
                        System.out.println("Заказ #" + orderId + " не может быть отменен!");
                        System.out.println("Так как заказ в статусе " + order.getStatus() + " из которого его нельзя отменить");
                    }
                }
            } else if (choice == 8) {
                System.out.println("Выход из программы");
                return;
            } else {
                System.out.println("Такой команды нет!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите действие которое нужно выполнить:");
        System.out.println("1. Добавить товар в корзину");
        System.out.println("2. Удалить товар из корзины");
        System.out.println("3. Посмотреть товары в корзине и общую сумму");
        System.out.println("4. Отфильтровать товары по категории");
        System.out.println("5. Оформить заказ из товаров в корзине");
        System.out.println("6. Посмотреть статус заказа");
        System.out.println("7. Сделать возврат заказа");
        System.out.println("8. Выйти");
    }

    public static Buyer createBuyer(Scanner scanner) {              // Принцип DRY - Делаем один раз для всех покупателей
        System.out.println("Создаем нового покупателя");
        System.out.println("Введите имя покупателя:");
        String name = scanner.next();
        System.out.println("Введите фамилию покупателя:");
        String surname = scanner.next();
        System.out.println("Введите электронную почту покупателя:");
        String email = scanner.next();
        return new SimpleBuyer(name, surname, email);
    }

    public static void initializeStorage() {
        productList.add(new Product(1, "Молоко", 70.00, ProductCategory.FOOD, (byte) 6));
        productList.add(new Product(2, "Хлеб", 100.00, ProductCategory.FOOD, (byte) 3));
        productList.add(new Product(3, "Мясо", 300.00, ProductCategory.FOOD, (byte) 8));
        productList.add(new Product(4, "Яблоки", 120.00, ProductCategory.FOOD, (byte) 5));
        productList.add(new Product(5, "Кофе", 200.00, ProductCategory.FOOD, (byte) 4));
        productList.add(new Product(6, "Батон", 100.00, ProductCategory.FOOD, (byte) 2));
        productList.add(new Product(7, "Рубашка", 300.00, ProductCategory.CLOTHES, (byte) 7));
        productList.add(new Product(8, "Куртка", 500.00, ProductCategory.CLOTHES, (byte) 4));
        productList.add(new Product(9, "Шорты", 200.00, ProductCategory.CLOTHES, (byte) 5));
        productList.add(new Product(10, "Шляпа", 600.00, ProductCategory.CLOTHES, (byte) 9));
        productList.add(new Product(11, "Тапки", 50.00, ProductCategory.CLOTHES, (byte) 1));
        productList.add(new Product(12, "Шорты", 250.00, ProductCategory.CLOTHES, (byte) 6));
        productList.add(new Product(13, "Платье", 500.00, ProductCategory.CLOTHES, (byte) 10));
        productList.add(new Product(14, "Телефон", 4000.00, ProductCategory.ELECTRONICS, (byte) 8));
        productList.add(new Product(15, "Ноутбук", 25000.00, ProductCategory.ELECTRONICS, (byte) 11));
        productList.add(new Product(16, "Компьютер", 20000.00, ProductCategory.ELECTRONICS, (byte) 9));
        productList.add(new Product(17, "Мышка", 500.00, ProductCategory.ELECTRONICS, (byte) 12));
        productList.add(new Product(18, "Наушники", 2000.00, ProductCategory.ELECTRONICS, (byte) 7));
        productList.add(new Product(19, "Телевизор", 5000.00, ProductCategory.ELECTRONICS, (byte) 10));
    }
}