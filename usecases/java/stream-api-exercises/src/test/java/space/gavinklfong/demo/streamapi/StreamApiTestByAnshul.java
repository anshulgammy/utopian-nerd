package space.gavinklfong.demo.streamapi;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

@Slf4j
@DataJpaTest
public class StreamApiTestByAnshul {

  @Autowired
  private OrderRepo orderRepo;

  @Autowired
  private ProductRepo productRepo;

  /**
   * Obtain a list of products belongs to category “Books” with price > 100".
   */
  @Test
  @DisplayName("Exercise 1")
  public void exercise_1() {
    List<Product> products = productRepo.findAll().stream()
        .filter(product -> product.getCategory().equalsIgnoreCase("Books"))
        .filter(product -> product.getPrice() > 100)
        .collect(Collectors.toList());

    products.forEach(product -> log.info(product.toString()));
  }

  /**
   * Obtain a list of order with products belong to category “Baby”
   */
  @Test
  @DisplayName("Exercise 2")
  public void exercise_2() {
    List<Order> orders = orderRepo.findAll().stream()
        .flatMap(order -> order.getProducts().stream())
        .filter(product -> product.getCategory().equalsIgnoreCase("Baby"))
        .flatMap(product -> product.getOrders().stream())
        .distinct()
        .collect(Collectors.toList());

    orders.forEach(order -> log.info(order.toString()));
  }

  /**
   * Obtain a list of order with products belong to category “Baby”
   */
  @Test
  @DisplayName("Exercise 2")
  public void exercise_2_better_solution() {
    List<Order> orders = orderRepo.findAll().stream()
        .filter(order -> order.getProducts().stream()
            .anyMatch(product -> product.getCategory().equalsIgnoreCase("Baby")))
        .collect(Collectors.toList());

    orders.forEach(order -> log.info(order.toString()));
  }

  /**
   * Obtain a list of product with category = “Toys” and then apply 10% discount
   */
  @Test
  @DisplayName("Exercise 3")
  public void exercise_3() {
    List<Product> products = productRepo.findAll().stream()
        .filter(product -> product.getCategory().equalsIgnoreCase("Toys"))
        .map(product -> {
          double newPrice = product.getPrice() - (product.getPrice() * 10) / 100;
          product.withPrice(newPrice);
          return product;
        }).collect(Collectors.toList());

    products.forEach(product -> log.info(product.toString()));
  }

  /**
   * Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
   */
  @Test
  @DisplayName("Exercise 4")
  public void exercise_4() {
    List<Product> products = orderRepo.findAll().stream()
        .filter(order -> order.getCustomer().getTier() == 2)
        .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
        .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
        .flatMap(order -> order.getProducts().stream())
        .distinct()
        .collect(Collectors.toList());

    products.forEach(product -> log.info(product.toString()));
  }

  /**
   * Get the cheapest products of “Books” category
   */
  @Test
  @DisplayName("Exercise 5")
  public void exercise_5() {
    Product bookProduct = productRepo.findAll().stream()
        .filter(product -> product.getCategory().equalsIgnoreCase("Books"))
        .sorted((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()))
        //.sorted(Comparator.comparing(Product::getPrice))
        //.min(Comparator.comparing(Product::getPrice))
        .findFirst().get();

    log.info(bookProduct.toString());
  }

  /**
   * Get the 3 most recent placed order
   */
  @Test
  @DisplayName("Exercise 6")
  public void exercise_6() {
    List<Order> orders = orderRepo.findAll().stream()
        .sorted((o1, o2) -> (int) (o2.getOrderDate().toEpochDay() - o1.getOrderDate().toEpochDay()))
        //.sorted(Comparator.comparing(Order::getOrderDate).reversed())
        .limit(3)
        .collect(Collectors.toList());

    orders.forEach(order -> log.info(order.toString()));
  }

  /**
   * Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console
   * and then return its product list
   */
  @Test
  @DisplayName("Exercise 7")
  public void exercise_7() {
    List<Product> products = orderRepo.findAll().stream()
        .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 3, 15)) == 0)
        //.peek(order -> log.info(order.toString()))
        .flatMap(order -> {
          log.info(order.toString());
          return order.getProducts().stream();
        })
        .distinct()
        .collect(Collectors.toList());

    products.forEach(product -> log.info(product.toString()));

  }

}