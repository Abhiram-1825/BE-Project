package spring.framework.spring5Framework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.framework.spring5Framework.domain.Supplier;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier , Long> {

    Optional<Supplier> findByName(String name);
}
