import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class OnlineMarket {

  private static StringBuilder output = new StringBuilder();
  private static HashMap<String, TreeSet<Product>> productsByType = new HashMap<>();
  private static HashMap<String, Product> allProducts = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader bf  = new BufferedReader(new InputStreamReader(System.in));
    String[] command = bf.readLine().split(" ");


    while (!command[0].equals("end")) {

      switch (command[0]) {
        case "add":
          String name = command[1];
          double price = Double.parseDouble(command[2]);
          String type = command[3];
          handleAdd(name, price, type);
          break;
        case "filter":
          filter(command);
          break;
      }

      command = bf.readLine().split(" ");
    }
    System.out.println(output);
  }

  private static void filter(String[] command) {

    switch (command[2]) {
      case "type":
        String type = command[3];
        filterByType(type);
        break;
      case "price":
        switch (command[3]){
          case "to":
            double priceTo = Double.parseDouble(command[4]);
            filterByPriceTo(priceTo);
            break;
          case "from":
            double priceFrom = Double.parseDouble(command[4]);

            if (command.length == 5) {
              filterByPriceFrom(priceFrom);
            } else {
              double to = Double.parseDouble(command[6]);
              filterByPriceFromTo(priceFrom, to);
            }
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }

  }

  private static void filterByPriceFromTo(double from, double to) {
    TreeSet<Product> sorted = new TreeSet<>();

    for (String name : allProducts.keySet()) {
      Product current = allProducts.get(name);
      if (current.price > from && current.price < to) {
        sorted.add(allProducts.get(name));
      }

    }

    ArrayList<Product> result = new ArrayList<Product>(sorted);
    output.append("Ok: ");

    for (int i = 0; i < result.size(); i++) {

      if (i == 10) {
        break;
      }

      Product current = result.get(i);
      output.append(current);
      output.append(", ");

    }

    if (output.charAt(output.length() - 2) == ',') {
      output.deleteCharAt(output.length() - 1);
      output.deleteCharAt(output.length() - 1);
    }

    output.append("\n");

  }

  private static void filterByPriceFrom(double price) {

    TreeSet<Product> sorted = new TreeSet<>();

    for (String name : allProducts.keySet()) {

      if (allProducts.get(name).price > price) {
        sorted.add(allProducts.get(name));
      }

    }

    ArrayList<Product> result = new ArrayList<Product>(sorted);
    output.append("Ok: ");
    for (int i = 0; i < result.size(); i++) {

      if (i == 10) {
        break;
      }
      Product current = result.get(i);
      output.append(current);
      output.append(", ");

    }

    if (output.charAt(output.length() - 2) == ',') {
      output.deleteCharAt(output.length() - 1);
      output.deleteCharAt(output.length() - 1);
    }

    output.append("\n");

  }

  private static void filterByPriceTo(double price) {
    TreeSet<Product> sorted = new TreeSet<>();

    for (String name : allProducts.keySet()) {

      if (allProducts.get(name).price < price) {
        sorted.add(allProducts.get(name));
      }

    }

    ArrayList<Product> result = new ArrayList<Product>(sorted);
    output.append("Ok: ");
    for (int i = 0; i < result.size(); i++) {

      if (i == 10) {
        break;
      }

      Product current = result.get(i);
      output.append(current);
      output.append(", ");

    }

    if (output.charAt(output.length() - 2) == ',') {
      output.deleteCharAt(output.length() - 1);
      output.deleteCharAt(output.length() - 1);
    }

    output.append("\n");
  }

  private static void filterByType(String type) {

    if (!productsByType.containsKey(type)) {
      output.append("Error: Type ");
      output.append(type);
      output.append(" does not exists\n");
      return;
    }

    ArrayList<Product> temp = new ArrayList<>(productsByType.get(type));
    output.append("Ok: ");
    boolean shouldBeDeleted = false;

    for (int i = 0; i < 10; i++) {

      if (i >= temp.size()) {
        break;
      }

      shouldBeDeleted = true;
      Product current = temp.get(i);
      output.append(current);
      output.append(", ");

    }

    if (shouldBeDeleted) {
      output.deleteCharAt(output.length() - 1);
      output.deleteCharAt(output.length() - 1);
    }
    output.append("\n");
  }

  private static void handleAdd(String name, double price, String type) {

    if (allProducts.containsKey(name)) {
      output.append("Error: Product ");
      output.append(name);
      output.append(" already exists\n");
      return;
    }


    if (!productsByType.containsKey(type)) {
      productsByType.put(type, new TreeSet<>());
    }

    Product newProduct = new Product(name, price, type);
    productsByType.get(type).add(newProduct);
    allProducts.put(name, newProduct);
    output.append("Ok: Product ");
    output.append(name);
    output.append(" added successfully\n");
  }

  static class Product implements Comparable<Product>{
    String name;
    double price;
    String type;

    Product(String name, double price, String type) {
      this.name = name;
      this.price = price;
      this.type = type;
    }

    @Override
    public String toString() {
      BigDecimal price = new BigDecimal(String.valueOf(this.price));
      StringBuilder toString = new StringBuilder();
      toString.append(name);
      toString.append("(");
      toString.append(price.stripTrailingZeros().toPlainString());
      toString.append(")");
      return toString.toString();
    }

    @Override
    public int compareTo(Product other) {
      if (price > other.price) {
        return 1;
      } else if (price < other.price) {
        return -1;
      } else {
        if (!name.equals(other.name)) {
          return name.compareTo(other.name);
        } else {
          return type.compareTo(other.type);
        }
      }
    }
  }

}