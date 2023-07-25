package product;


public class Util {
    public static Product parseProduct(String data) {
        int startIdx = data.indexOf("id=");
        int endIdx = data.indexOf(",", startIdx);
        if (startIdx < 0 || endIdx < 0) {
            return null;
        }
        int id = Integer.parseInt(data.substring(startIdx + 3, endIdx));

        startIdx = data.indexOf("name=");
        endIdx = data.indexOf(",", startIdx);
        if (startIdx < 0 || endIdx < 0) {
            return null;
        }
        String name = data.substring(startIdx + 5, endIdx);

        startIdx = data.indexOf("manufacturer=");
        endIdx = data.indexOf(",", startIdx);
        if (startIdx < 0 || endIdx < 0) {
            return null;
        }
        String manufacturer = data.substring(startIdx + 13, endIdx);

        startIdx = data.indexOf("category=");
        endIdx = data.indexOf(",", startIdx);
        if (startIdx < 0 || endIdx < 0) {
            return null;
        }
        String category = data.substring(startIdx + 9, endIdx);

        startIdx = data.indexOf("price=");
        endIdx = data.indexOf("}", startIdx);
        if (startIdx < 0 || endIdx < 0) {
            return null;
        }

        String priceStr = data.substring(startIdx + 6, endIdx);
        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return new Product(id, name, manufacturer, category, price);

    }

    public static String stringifyProduct(Product product) {
        return "{id=" + product.getId() +
                ",name=" + product.getName() +
                ",manufacturer=" + product.getManufacturer() +
                ",category=" + product.getCategory() +
                ",price=" + product.getPrice() +
                "}";
    }
}