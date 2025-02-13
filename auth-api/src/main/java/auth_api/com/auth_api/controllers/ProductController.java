package auth_api.com.auth_api.controllers;

import auth_api.com.auth_api.domain.product.Product;
import auth_api.com.auth_api.domain.product.ProductRequestDTO;
import auth_api.com.auth_api.domain.product.ProductResponseDTO;
import auth_api.com.auth_api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductRepository repository;


    @PostMapping
    public ResponseEntity saveProduct(@RequestBody ProductRequestDTO body){
        Product saveProduct = new Product(body);

        this.repository.save(saveProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity findAllProduct() {
        List<ProductResponseDTO> productList = this.repository.findAll()
                .stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }
}
