package spring.framework.spring5Framework.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.framework.spring5Framework.poihelper.ExcelReader;
import spring.framework.spring5Framework.repositories.ProductRepository;
import spring.framework.spring5Framework.repositories.SupplierRepository;

import java.io.IOException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public void upload(MultipartFile file){
        try{
        ExcelReader.excelToProducts(file.getInputStream() , productRepository , supplierRepository);
        } catch (IOException e){
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
