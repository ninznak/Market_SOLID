package ru.alex.filter;

import ru.alex.product.Product;

import java.util.List;

public interface Filter {                                           // Принцип открытости-закрытости
    List<Product> applyFilter(List<Product> products);              // Реализация различных фильтров через наследование
}                                                                   // этого интерфейса позволяет добавлять новые типы фильтров,
                                                                    // не изменения существующего кода.
