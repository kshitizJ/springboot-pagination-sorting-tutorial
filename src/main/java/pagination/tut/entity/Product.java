package pagination.tut.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer quantity;
    private Long price;

    public Product(String name, Integer quantity, Long price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

}
