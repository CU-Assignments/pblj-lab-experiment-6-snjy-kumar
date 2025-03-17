To develop a Java program that processes a large dataset of products using Streams class to:
  - Group products by category
  - Find the most expensive product in each category
  - Calculate the average price of all products


Instruction
Step 1: Create the Product Class
- Define a Product class with attributes:
    name (String)
    category (String)
    price (double)
  
or (Reads product data from a CSV file)
For Example: "Laptop", "Electronics", 1200
             "Phone", "Electronics", 800


Step 2: Create the ProductProcessor Class
- Create a list of products with multiple categories and prices.
- Use Streams API to:
    Group products by category using Collectors.groupingBy().
    Find the most expensive product in each category using Collectors.maxBy().
    Calculate the average price of all products using Collectors.averagingDouble().
- Display the results.

---------------------------------------------------------------------------------------------------------------------------------------------------------
  import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name;
    String category;
    double price;
    
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
    
    public void display() {
        System.out.println(name + " (Category: " + category + ", Price: " + price + ")");
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        
        System.out.print("Enter number of products: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Product " + (i + 1) + " (name, category, price):");
            String name = scanner.nextLine();
            String category = scanner.nextLine();
            double price = scanner.nextDouble();
            scanner.nextLine();
            products.add(new Product(name, category, price));
        }
        
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));
            
        System.out.println("\nProducts Grouped by Category:");
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ":");
            productList.forEach(Product::display);
        });
        
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category,
                Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));
                
        System.out.println("\nMost Expensive Product in Each Category:");
        mostExpensiveByCategory.forEach((category, product) ->
            System.out.println(category + ": " + product.map(p -> p.name + " (" + p.price + ")").orElse("No product")));
            
        double averagePrice = products.stream()
            .collect(Collectors.averagingDouble(p -> p.price));
            
        System.out.println("\nAverage Price of All Products: " + averagePrice);
    }
}
-----------------------------------------------------------------------------------------------------------------------------------------------------------
  
    Test Cases
    Test Case	                                     Input Data	                                                                           Expected Output
    Case 1: Normal Case             	     Sample dataset with Electronics, Clothing, and Footwear	                      Grouped products, Most Expensive per category, Average price
    Case 2: Single Category Only           All products in "Electronics"	                                                One category, Most Expensive in Electronics, Average of all
    Case 3: Same Price in a Category	     Two products with the same highest price in "Footwear"	                        Any of the most expensive ones is displayed
    Case 4: Only One Product	             One product "Laptop" in "Electronics"	                                        Laptop as the most expensive, Laptop as the only average
    Case 5: Empty List	                   No products	                                                                  No grouping, No most expensive product, Average price = 0
