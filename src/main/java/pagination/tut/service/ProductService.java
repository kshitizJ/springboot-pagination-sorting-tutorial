package pagination.tut.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import pagination.tut.entity.Product;
import pagination.tut.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 
     * @PostConstruct will run only once when the application start..
     *                It is used to populate database when the application starts
     * 
     *                Here we will add 100 products to the database when the
     *                application starts
     * 
     */
    @PostConstruct
    public void initDB() {

        List<Product> products = IntStream.rangeClosed(1, 200)
                .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextLong(5000L)))
                .collect(Collectors.toList());

        productRepository.saveAll(products);

    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    /**
     * 
     * {@link Sort} will sort by any field that is passed
     * {@link Direction} will tell how to sort. Sort in ascending order or
     * descending order.
     * 
     */
    public List<Product> findProductsWithSorting(String field) {
        return productRepository.findAll(Sort.by(Direction.ASC, field));
    }

    /**
     * 
     * {@link PageRequest} this will short down the number of objects which is
     * coming from database.
     * 
     * For example if we pass 1 as offset and 10 as pageSize than
     * 
     * @param offset
     *                 it means 1st 10 elements or 2nd 10 elements, etc.
     * @param pageSize
     *                 it is the number of products that should be diplayed at a
     *                 time
     * 
     * @return
     */
    public Page<Product> findProductsWithPagination(Integer offset, Integer pageSize) {

        Page<Product> productsPerPage = productRepository.findAll(PageRequest.of(offset, pageSize));

        return productsPerPage;

    }

    /**
     * 
     * Pagination with sorting.
     * 
     * @param offset
     * @param pageSize
     * @param field
     * @return
     */
    public Page<Product> findProductsWithPaginationAndSorting(Integer offset, Integer pageSize, String field) {

        Page<Product> productsPerPage = productRepository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Direction.ASC, field)));

        return productsPerPage;

    }

}
