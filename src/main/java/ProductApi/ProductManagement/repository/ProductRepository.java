package ProductApi.ProductManagement.repository;


import ProductApi.ProductManagement.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findByNameContainingIgnoreCaseAndPriceLessThanEqual(String name, Double price);

    // أو باستخدام JPQL يدوي
    @Query("SELECT p FROM ProductModel p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:price IS NULL OR p.price <= :price)")
    List<ProductModel> searchProducts(@Param("name") String name, @Param("price") Double price);
}