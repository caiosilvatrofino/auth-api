package auth_api.com.auth_api.domain.product;

public record ProductResponseDTO(Long id, String name, Integer price) {

    public ProductResponseDTO(Product product) {this(product.getId(), product.getName(), product.getPrice());}
}
