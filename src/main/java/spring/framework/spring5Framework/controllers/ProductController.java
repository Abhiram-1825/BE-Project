package spring.framework.spring5Framework.controllers;


import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.framework.spring5Framework.domain.Product;
import spring.framework.spring5Framework.execptions.ResponseMessage;
import spring.framework.spring5Framework.repositories.ProductRepository;
import spring.framework.spring5Framework.services.ProductService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        String message;
        try{
            productService.upload(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }catch (Exception e){
            message = "Could not upload the file: " + file.getOriginalFilename() + "!" + e;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/show")
    public ResponseEntity<Map<String, Object>> getAllTutorials(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "50") int size) {
        try {
            Pageable pagination = PageRequest.of(page, size);

            List<Product> products = productRepository.findAllByNotExpired();

            return getMapResponseEntity(pagination, products);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Map<String, Object>> getMapResponseEntity(Pageable pagination, List<Product> products) {
        int s1 = (int) pagination.getOffset();
        int e1 = Math.min((s1 + pagination.getPageSize()), products.size());
        Page<Product> pageResult = new PageImpl<>(products.subList(s1, e1), pagination, products.size());

        if (products.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Map<String, Object> response = new HashMap<>();
        response.put("products", pageResult.getContent());
        response.put("currentPage", pageResult.getNumber());
        response.put("totalItems", pageResult.getTotalElements());
        response.put("totalPages", pageResult.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getProductsByName(@RequestParam String supplierName ,
                                                                 @RequestParam(required = false) String productName,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "50") int size){
        try {
            Pageable paging = PageRequest.of(page, size);

            List<Product> products = productRepository.findBySupplierNameAndNotExpired(supplierName);
            if(productName != null && !productName.isEmpty()){
                products = products.stream()
                                    .filter(product -> product.getName().toLowerCase().contains(productName.toLowerCase()))
                                    .collect(Collectors.toList());
            }
            return getMapResponseEntity(paging, products);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
