package tekiela.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PaymentRequest {
    private String notifyUrl;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private Integer totalAmount;
    private Buyer buyer;
    private List<Product> products;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static
    class Buyer {
        private String email;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class Product {
        private String name;
    }
//    "notifyUrl": "https://your.eshop.com/notify",
//    "customerIp": "127.0.0.1",
//    "merchantPosId": "300746",
//    "description": "RTV market",
//    "currencyCode": "PLN",
//
//            "buyer": {
//        "email": "john.doe@example.com",
//                "phone": "654111654",
//                "firstName": "John",
//                "lastName": "Doe",
//                "language": "pl"
//    },
//            "products": [
//    {
//        "name": "Wireless Mouse for Laptop",
//            "unitPrice": "15000",
//            "quantity": "1"
//    },
//    {
//        "name": "HDMI cable",
//            "unitPrice": "6000",
//            "quantity": "1"
//    }
//    ]
}


