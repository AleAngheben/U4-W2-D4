package org.example;

import org.example.elements.Customer;
import org.example.elements.Order;
import org.example.elements.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        // CREO UNA LISTA DI PRODOTTI

        List<Product> productList = new ArrayList<>();

        Product prod1 = new Product("Scarpe Ginnastica", "Boys", 100.0);
        Product prod2 = new Product("Scarpe Calcio", "Boys", 100.0);
        Product prod3 = new Product("Maglietta Juventus", "Baby", 70.0);
        Product prod4 = new Product("Call of Duty IV", "Baby", 70.0);
        Product prod5 = new Product("Scarpe da ballo", "Books", 140.8);
        Product prod6 = new Product("Computer", "Boys", 100.0);
        Product prod7 = new Product("Mouse da gaming", "Informatica", 82.5);
        Product prod8 = new Product("Candela", "Books", 108.7);
        Product prod9 = new Product("Libro cuore", "Baby", 70.0);
        Product prod10 = new Product("Posacenere", "Books", 3.9);


        productList.add(prod1);
        productList.add(prod2);
        productList.add(prod3);
        productList.add(prod4);
        productList.add(prod5);
        productList.add(prod6);
        productList.add(prod7);
        productList.add(prod8);
        productList.add(prod9);
        productList.add(prod10);

        //CREO UNA LISTA DI CUSTOMER

        List<Customer> customerList = new ArrayList<>();

        Customer otman = new Customer("Otman", 9);
        Customer luca = new Customer("Luca", 7);
        Customer alessandro = new Customer("Alessandro", 2);
        Customer stefano = new Customer("Stefano", 2);
        Customer antonio = new Customer("Antonio", 1);


        //CREO UNA LISTA DI ORDINI

        List<Order> orderList = new ArrayList<>();

        LocalDate day1 = LocalDate.of(2023, 8, 5);
        LocalDate day2 = LocalDate.of(2021, 4, 17);
        LocalDate day3 = LocalDate.of(2021, 3, 12);
        LocalDate day4 = LocalDate.of(2021, 2, 15);
        LocalDate day5 = LocalDate.of(2020, 7, 15);

        LocalDate deliveryD = LocalDate.of(2024, 1, 17);


        Order order1 = new Order("Cnosegnato", day1, deliveryD, List.of(productList.get(0)), otman);
        Order order2 = new Order("Cnosegnato", day2, deliveryD, List.of(productList.get(1)), luca);
        Order order3 = new Order("Cnosegnato", day3, deliveryD, List.of(productList.get(2)), alessandro);
        Order order4 = new Order("Cnosegnato", day4, deliveryD, List.of(productList.get(3)), stefano);
        Order order5 = new Order("Cnosegnato", day5, deliveryD, List.of(productList.get(4)), antonio);
        Order order6 = new Order("Cnosegnato", day1, deliveryD, List.of(productList.get(5)), otman);
        Order order7 = new Order("Cnosegnato", day2, deliveryD, List.of(productList.get(6)), luca);
        Order order8 = new Order("Cnosegnato", day3, deliveryD, List.of(productList.get(7)), alessandro);
        Order order9 = new Order("Cnosegnato", day4, deliveryD, List.of(productList.get(8)), stefano);
        Order order10 = new Order("Cnosegnato", day5, deliveryD, List.of(productList.get(9)), luca);

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
        orderList.add(order6);
        orderList.add(order7);
        orderList.add(order8);
        orderList.add(order9);
        orderList.add(order10);

//ESERCIZIO 1
        System.out.println("");
        System.out.println("Esercizio 1");
        orderList.stream().collect(Collectors.groupingBy(Order::getCustomer)).forEach((cos, ord) -> System.out.println(cos.getName() + " " + ord));


//ESERCIZIO 2

        System.out.println("");
        System.out.println("Esercizio 2");

        Map<Customer, Double> totPricesByCust = orderList.stream().collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(ord -> ord.getProduct().stream().mapToDouble(Product::getPrice).sum())));

        totPricesByCust.forEach((costumer, totale) -> System.out.println("Il totale delle spese di " + costumer.getName() + " ammonta a :" + totale)


        );


//ESERCIZIO 3
        System.out.println("");
        System.out.println("Esercizio 3");
        productList.stream().sorted(Comparator.comparing(Product::getPrice, Comparator.reverseOrder())).forEach(o -> System.out.println(o));


        //ESERCIZIO 4

        System.out.println("");
        System.out.println("Esercizio 4");

        double averagePrice = orderList.stream().mapToDouble(order -> order.getProduct().stream().mapToDouble(Product::getPrice).sum()).average().orElse(0.0);
        System.out.println("Media prezzi per ordine : " + averagePrice);


        //ESERCIZIO 5

        System.out.println("");
        System.out.println("Esercizio 5");

        productList.stream().collect(Collectors.groupingBy(prod -> prod.getCategory(), Collectors.summingDouble(prod -> prod.getPrice()))).forEach((cat, sum) -> System.out.println(cat + "" + sum));
    }
}
