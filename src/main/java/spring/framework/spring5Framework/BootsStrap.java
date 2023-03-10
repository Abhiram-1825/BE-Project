//package spring.framework.spring5Framework;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//import spring.framework.spring5Framework.domain.Product;
//import spring.framework.spring5Framework.domain.Supplier;
//import spring.framework.spring5Framework.repositories.ProductRepository;
//import spring.framework.spring5Framework.repositories.SupplierRepository;
//
//import java.time.LocalDate;
//
//@Component
//public class BootsStrap  implements ApplicationListener<ContextRefreshedEvent> {
//    private final ProductRepository productRepository;
//    private final SupplierRepository supplierRepository;
//
//    public BootsStrap(ProductRepository productRepository, SupplierRepository supplierRepository) {
//        this.productRepository = productRepository;
//        this.supplierRepository = supplierRepository;
//    }
//
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Product product1 = new Product();
//        product1.setCode("JNILIB");
//        product1.setName("DOLL");
//        product1.setBatch("12ee");
//        product1.setStock(7);
//        product1.setDeal(3);
//        product1.setFree(1);
//        product1.setMrp(2.5);
//        product1.setRate(3.5);
//        product1.setExp(LocalDate.parse("2/3/2001"));
//        product1.setCompany("DOLO CO.");
//
//        productRepository.save(product1);
//
//        Product product2 = new Product();
//        product2.setCode("HBJSHJB");
//        product2.setName("COLDACT");
//        product2.setBatch("@HHJ");
//        product2.setStock(3);
//        product2.setDeal(1);
//        product2.setFree(4);
//        product2.setMrp(4.5);
//        product2.setRate(5.5);
//        product2.setCompany("COLD CO.");
//
//        productRepository.save(product2);
//
//        Supplier supplier1 = new Supplier();
//        supplier1.setName("json");
//
//        supplierRepository.save(supplier1);
//
//        Supplier supplier2 = new Supplier();
//        supplier2.setName("Spring");
//
//        supplierRepository.save(supplier2);
//
//
//        supplier1.getProducts().add(product1);
//        product1.getSuppliers().add(supplier1);
//
//        supplierRepository.save(supplier1);
//        productRepository.save(product1);
//
//        supplier2.getProducts().add(product2);
//        product2.getSuppliers().add(supplier2);
//
//        supplierRepository.save(supplier2);
//        productRepository.save(product2);
//    }
//}
