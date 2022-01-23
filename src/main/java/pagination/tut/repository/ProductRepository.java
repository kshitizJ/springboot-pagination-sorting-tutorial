package pagination.tut.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pagination.tut.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
