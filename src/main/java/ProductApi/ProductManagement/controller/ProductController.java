package ProductApi.ProductManagement.controller;

import ProductApi.ProductManagement.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ProductApi.ProductManagement.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

//    @GetMapping("/all")
//    public String test() {
//        return "API is working!";
//    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductModel> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Long id) {
        ProductModel product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ProductModel createProduct(@RequestBody ProductModel product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody ProductModel productDetails) {
        ProductModel updatedProduct = productService.updateProduct(id, productDetails);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("search")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<ProductModel>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price
    ) {
        // البحث في قاعدة البيانات بناءً على المعطيات
//        List<ProductModel> products = productService.search(name, price);
        List<ProductModel> products = productService.searchFlexible(name, price);

        return ResponseEntity.ok(products);
    }

    @PostMapping("form")
    public ResponseEntity<String> handleForm(@ModelAttribute ProductModel product) {

        return ResponseEntity.ok("Product saved!" + product.getName() + " "  + product.getPrice() +  " " + product.getDescription());
    }

    @GetMapping("Header")
    public ResponseEntity<String> getWithHeader(
            @RequestHeader("User-Agent") String userAgent
//            ,
//            @CookieValue("JSESSIONID") String sessionId
    ) {
        return ResponseEntity.ok("User-Agent: " + userAgent);
    }
}