package spring.framework.spring5Framework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.framework.spring5Framework.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product , Long> {
        @Query("select p from Product p join p.suppliers s where s.name like %:supplierName% and p.stock>0 and p.exp > CURRENT_DATE")
        List<Product> findBySupplierNameAndNotExpired(@Param("supplierName") String supplierName);

        @Query("select p from Product p join p.suppliers s where p.exp > CURRENT_DATE")
        List<Product> findAllByNotExpired();
}
