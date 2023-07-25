package product;

import java.io.*;

public class FileManager {
    private final String filePath = "D:\\CODE\\JavaCore\\ProductManagement";

    public FileManager() {
    }

    public String readFile() {
        File file = new File(filePath, "Product.txt");
        if (!file.exists()) {
            System.out.println("File not found.");
            return null;
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void writeFile(String content) {
        File file = new File(filePath, "Product.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
