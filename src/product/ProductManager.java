package product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {


    private final String filePath = "D:\\java_core\\ProductManagement\\Product.txt";

    public ProductManager() {
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = Util.parseProduct(line);
                if (product != null) {
                    productList.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getProductByName(String name) {
        List<Product> foundProducts = new ArrayList<>();
        String nameLower = name.toLowerCase();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String lineLower = line.toLowerCase();
                if (lineLower.contains(nameLower)) {
                    Product product = Util.parseProduct(line);
                    if (product != null && product.getName().toLowerCase().contains(nameLower)) {
                        foundProducts.add(product);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foundProducts;
    }

    public Product getProductById(int id) {
        Product product;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("id=" + id + ",")) {
                     product = Util.parseProduct(line);
                    if (product != null && product.getId() == id) {
                        return product;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private int getMaxProductId() {
        int maxId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = Util.parseProduct(line);
                if (product != null && product.getId() > maxId) {
                    maxId = product.getId();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxId;
    }

    public boolean addProduct(Product product) {
        int newId = getMaxProductId() + 1;
        product.setId(newId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(Util.stringifyProduct(product));
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String deleteById(int productId) {
        String result;
        if (getProductById(productId) == null) {
            return "Product Id not found";
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.contains("id=" + productId + ",")) {
                    Product product = Util.parseProduct(line);
                    if (product != null && product.getId() != productId) {
                        content.append(line).append("\n");
                    }
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(content.toString());
                result = "Delete success";
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = "Delete fail";

        }
        return result;
    }


    public String updateById(Product updatedProduct) {
        String result;
        if (getProductById(updatedProduct.getId()) == null) {
            return "Product Id not found";
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("id=" + updatedProduct.getId() + ",")) {
                    Product product = Util.parseProduct(line);
                    if (product != null && product.getId() == updatedProduct.getId()) {
                        product.setName(updatedProduct.getName());
                        product.setManufacturer(updatedProduct.getManufacturer());
                        product.setCategory(updatedProduct.getCategory());
                        product.setPrice(updatedProduct.getPrice());
                    }
                    content.append(Util.stringifyProduct(product)).append("\n");
                }else{
                    content.append(line).append("\n");
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(content.toString());
                result = "Update success";
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = "Update success";
        }
        return result;
    }

}