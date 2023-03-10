package spring.framework.spring5Framework.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String name;
    private String batch;
    private Integer stock;
    private Integer deal;
    private Integer free;
    private Double mrp;
    private Double rate;
    private LocalDate exp;
    private String company;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_supplier" ,
            joinColumns = @JoinColumn(name = "product_id") ,
            inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    @JsonIgnoreProperties("products")
    private List<Supplier> suppliers = new ArrayList<>();
}
