package product;

import java.util.List;

public class ProductService {
    private ProductManager productManager;

    public ProductService() {
        productManager = new ProductManager();
    }

    public void displayAllProducts() {
        List<Product> productList = productManager.getAllProducts();
        if (productList.isEmpty()) {
            System.out.println("Not found product.");
        } else {
            for (Product product : productList) {
                System.out.println(product.toString());
            }
        }
    }

    public void getProductsByName(String name) {
        List<Product> products = productManager.getProductByName(name);
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : products) {
                System.out.println(product.toString());
            }
        }
    }

    public boolean getProductsById(int id) {
        Product product = productManager.getProductById(id);
        if (product == null) {
            System.out.println("No products found.");
            return false;
        } else {
            System.out.println(product);
        }
        return true;
    }

    public void addProduct(Product product) {
        if (productManager.addProduct(product)) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Failed to add product.");
        }
    }

    public void updateProduct(Product product) {
        System.out.println(productManager.updateById(product));
    }

    public void deleteById(int id) {
        System.out.println(productManager.deleteById(id));
    }


}
