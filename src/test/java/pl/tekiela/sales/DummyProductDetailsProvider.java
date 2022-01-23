package tekiela.sales;

import java.util.HashMap;

class DummyProductDetailsProvider implements ProductDetailsProvider {
    HashMap<String, Product> products;

    public DummyProductDetailsProvider() {
        this.products = new HashMap<>();
    }

    @Override
    public Product getDetails(String productId) {
        return products.get(productId);
    }
}