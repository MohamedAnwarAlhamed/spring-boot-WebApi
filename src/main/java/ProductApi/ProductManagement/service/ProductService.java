package ProductApi.ProductManagement.service;

import ProductApi.ProductManagement.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProductApi.ProductManagement.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductModel createProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public ProductModel updateProduct(Long id, ProductModel productDetails) {
        ProductModel product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setDescription(productDetails.getDescription());
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductModel> search(String name, Double price) {
        if (name != null && price != null) {
            return productRepository.findByNameContainingIgnoreCaseAndPriceLessThanEqual(name, price);
        } else {
            return productRepository.findAll();
        }
    }
    // الطريقة 2: استخدام JPQL مرن (مُفضّل للشروط المعقدة)
    public List<ProductModel> searchFlexible(String name, Double price) {
        return productRepository.searchProducts(name, price);
    }
}