package product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch = 7;
        ProductManager productManager = new ProductManager();

        do {
            System.out.println("Menu");
            System.out.println("----------------------------------");
            System.out.println("1. View list product");
            System.out.println("2. Search product by name");
            System.out.println("3. Search product by ID");
            System.out.println("4. Add product");
            System.out.println("5. Update product ID");
            System.out.println("6. Delete product ID");
            System.out.println("0. EXIT");
            System.out.print("Input choice: ");
            try {
                ch = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("You must input a number!");
                continue;
            }
            switch (ch) {
                case 1:
                    productManager.getAllProducts();
                    break;
                case 2:
                    System.out.print("Enter product name to search: ");
                    String searchProductName = sc.nextLine();
                    productManager.getProductByName(searchProductName);
                    break;
                case 3:
                    System.out.print("Enter product ID to search: ");
                    int searchProductId = Integer.parseInt(sc.nextLine());
                    Product foundProduct = productManager.findProductById(searchProductId);
                    if (foundProduct != null) {
                        System.out.println(foundProduct.toString());
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter product details:");
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Manufacturer: ");
                    String manufacturer = sc.nextLine();
                    System.out.print("Category: ");
                    String category = sc.nextLine();
                    System.out.print("Price: ");
                    double price = Double.parseDouble(sc.nextLine());

                    Product newProduct = new Product(0, name, manufacturer, category, price);
                    productManager.addProduct(newProduct);
                    break;
                case 5:
                    System.out.print("Enter product ID to update: ");
                    int updateProductId = Integer.parseInt(sc.nextLine());
                    Product productToUpdate = productManager.findProductById(updateProductId);
                    if (productToUpdate != null) {
                        System.out.println("Enter updated product details:");
                        System.out.print("Name: ");
                        productToUpdate.setName(sc.nextLine());
                        System.out.print("Manufacturer: ");
                        productToUpdate.setManufacturer(sc.nextLine());
                        System.out.print("Category: ");
                        productToUpdate.setCategory(sc.nextLine());
                        System.out.print("Price: ");
                        boolean validPrice = false;
                        do {
                            try {
                                System.out.print("Price: ");
                                productToUpdate.setPrice(Double.parseDouble(sc.nextLine()));
                                validPrice = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid price! Please enter a valid number.");
                            }
                        } while (!validPrice);

                        productManager.updateProductById(updateProductId, productToUpdate);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter product ID to delete: ");
                    int deleteProductId = Integer.parseInt(sc.nextLine());
                    productManager.deleteProduct(deleteProductId);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid number.");
            }

        } while (ch != 0);
    }
}
