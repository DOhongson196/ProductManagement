package product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> productList;
    private FileManager fileManager;

    public ProductManager() {
        productList = new ArrayList<>();
        fileManager = new FileManager();
        loadProducts();
    }

    private void loadProducts() {
        String content = fileManager.readFile();
        if (content != null) {
            String[] productData = content.split("\n");
            for (String data : productData) {
                Product product = Util.parseProduct(data);
                if (product != null) {
                    productList.add(product);
                }
            }
        }
    }
    public void getProductByName(String name) {
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                foundProducts.add(product);
            }
        }

        if (!foundProducts.isEmpty()) {
            System.out.println("Found " + foundProducts.size() + " product(s) matching the name '" + name + "':");
            for (Product product : foundProducts) {
                System.out.println(product.toString());
            }
        } else {
            System.out.println("No product found matching the name '" + name + "'.");
        }
    }

    private void saveProducts() {
        StringBuilder content = new StringBuilder();
        for (Product product : productList) {
            content.append(Util.stringifyProduct(product)).append("\n");
        }
        fileManager.writeFile(content.toString());
    }

    public void addProduct(Product product) {
        int lastProductId = getLastProductId();
        product.setId(lastProductId + 1);
        productList.add(product);
        saveProducts();
    }

    public void updateProductById(int productId, Product updatedProduct) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                product.setName(updatedProduct.getName());
                product.setManufacturer(updatedProduct.getManufacturer());
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                break;
            }
        }
        saveProducts();
    }

    public void deleteProduct(int productId) {
        productList.removeIf(product -> product.getId() == productId);
        saveProducts();
    }

    public void getAllProducts() {
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    public Product findProductById(int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    private int getLastProductId() {
        int lastProductId = 0;
        for (Product product : productList) {
            if (product.getId() > lastProductId) {
                lastProductId = product.getId();
            }
        }
        return lastProductId;
    }
}