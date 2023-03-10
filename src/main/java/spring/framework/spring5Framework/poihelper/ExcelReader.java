package spring.framework.spring5Framework.poihelper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import spring.framework.spring5Framework.domain.Product;
import spring.framework.spring5Framework.domain.Supplier;
import spring.framework.spring5Framework.repositories.ProductRepository;
import spring.framework.spring5Framework.repositories.SupplierRepository;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ExcelReader {
    public static void excelToProducts(InputStream inputStream , ProductRepository productRepository , SupplierRepository supplierRepository) throws IllegalStateException {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            int index = 0;
            for (Row row : sheet) {
                if(index == 0){
                    index++;
                    continue;
                }
                Product product = new Product();
                for (Cell cell : row) {
                    String cellValue = formatter.formatCellValue(cell);
                    switch (cell.getColumnIndex()) {
                        case 0 -> product.setCode(cellValue);
                        case 1 -> product.setName(cellValue);
                        case 2 -> product.setBatch(cellValue);
                        case 3 -> product.setStock(Integer.valueOf(cellValue));
                        case 4 -> product.setDeal(Integer.valueOf(cellValue));
                        case 5 -> product.setFree(Integer.valueOf(cellValue));
                        case 6 -> product.setMrp(Double.valueOf(cellValue));
                        case 7 -> product.setRate(Double.valueOf(cellValue));
                        case 8 -> {
                            if(CellType.STRING == cell.getCellType()){
                                product.setExp(LocalDate.now());
                            }else{
                                product.setExp(LocalDate.parse(cellValue , dateTimeFormatter));
                            }
                        }
                        case 9 -> product.setCompany(cellValue);
                        case 10 -> {
                            Optional<Supplier> supplier = supplierRepository.findByName(cellValue);

                            if (supplier.isEmpty()) {
                                supplier = Optional.of(new Supplier());
                                supplier.get().setName(cellValue);
                            }
                            supplier.get().getProducts().add(product);
                            product.getSuppliers().add(supplier.get());

                            supplierRepository.save(supplier.get());
                            productRepository.save(product);
                        }

                        default -> throw new IllegalStateException("Unexpected value: " + cell.getColumnIndex());
                        }
                }
            }

            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
