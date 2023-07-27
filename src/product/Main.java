package product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch = 0;
        ProductService productService = new ProductService();
        boolean validNum;
        String name;
        String manufacturer;
        String category;
        double price = 0;
        Product newProduct;

        do {
            System.out.println("Menu");
            System.out.println("----------------------------------");
            System.out.println("1. View list product");
            System.out.println("2. Search product by name");
            System.out.println("3. Search product by ID");
            System.out.println("4. Add product");
            System.out.println("5. Update product ID");
            System.out.println("6. Delete product ID");
            System.out.println("7. EXIT");
            System.out.print("Input choice: ");
            try {
                ch = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("You must input a number!");
                continue;
            }
            switch (ch) {
                case 1:
                    productService.getAllProducts();
                    break;
                case 2:
                    System.out.print("Enter product name to search: ");
                    String searchProductName = sc.nextLine();
                    productService.getProductsByName(searchProductName);
                    break;
                case 3:
                    validNum = false;
                        do {
                            try {
                                System.out.print("Enter product ID to search: ");
                                productService.getProductsById(Integer.parseInt(sc.nextLine()));
                                validNum = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid! Please enter a valid number.");
                            }
                        } while (!validNum);

                    break;
                case 4:
                    System.out.println("Enter info product:");
                    System.out.print("Product Name: ");
                    name = sc.nextLine();
                    System.out.print("Manufacturer: ");
                    manufacturer = sc.nextLine();
                    System.out.print("Category: ");
                    category = sc.nextLine();
                    validNum = false;
                    price = 0;
                    do {
                        try {
                            System.out.print("Price: ");
                            price = Double.parseDouble(sc.nextLine());
                            validNum = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid! Please enter a valid number.");
                        }
                    } while (!validNum);

                    newProduct = new Product( name, manufacturer, category, price);
                    productService.addProduct(newProduct);
                    break;
                case 5:
                    validNum = false;
                    int id = 0;
                    do {
                        try {
                            System.out.print("Enter product ID to update: ");
                            id = Integer.parseInt(sc.nextLine());
                            validNum = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid! Please enter a valid number.");
                        }
                    } while (!validNum);
                    if(!productService.getProductsById(id)){
                        break;
                    }
                    System.out.println("Enter info product:");
                    System.out.print("Product Name: ");
                    name = sc.nextLine();
                    System.out.print("Manufacturer: ");
                    manufacturer = sc.nextLine();
                    System.out.print("Category: ");
                    category = sc.nextLine();
                    do {
                        try {
                            System.out.print("Price: ");
                            price = Double.parseDouble(sc.nextLine());
                            validNum = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid! Please enter a valid number.");
                        }
                    } while (!validNum);
                    newProduct = new Product(id, name, manufacturer, category, price);
                    productService.updateProduct(newProduct);
                    break;
                case 6:
                    validNum = false;
                    do {
                        try {
                            System.out.print("Enter product ID to delete: ");
                            productService.deleteById(Integer.parseInt(sc.nextLine()));
                            validNum = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid! Please enter a valid number.");
                        }
                    } while (!validNum);

                    break;
                case 7:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid number.");
            }

        } while (ch != 7);
    }
}
