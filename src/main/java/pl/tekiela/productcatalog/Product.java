package tekiela.productcatalog;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    private String productId;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private boolean published;

    Product() {}

    public Product(String productId, String name, String description, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.published = false;
    }

    public Product(String productId, String name, String description) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.published = false;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void publish() {
        this.published = true;
    }

    public boolean isPublished() {
        return published;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
