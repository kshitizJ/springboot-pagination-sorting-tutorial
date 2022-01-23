package pagination.tut;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pagination.tut.dto.APIResponse;
import pagination.tut.entity.Product;
import pagination.tut.service.ProductService;

@SpringBootApplication
@RestController
@RequestMapping("/products")
public class TutApplication {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(TutApplication.class, args);
	}

	@GetMapping
	private APIResponse<List<Product>> getProducts() {
		List<Product> products = productService.findAllProduct();
		return new APIResponse<>(products.size(), products);
	}

	@GetMapping("/{field}")
	private APIResponse<List<Product>> getProductsWithSort(@PathVariable String field) {
		List<Product> products = productService.findProductsWithSorting(field);
		return new APIResponse<>(products.size(), products);
	}

	/**
	 * 
	 * To convert Page to List type, use {.getContent()} method from Page instance.
	 * 
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/pagination/{offset}/{pageSize}")
	private APIResponse<Page<Product>> getProductsWithPagination(@PathVariable Integer offset,
			@PathVariable Integer pageSize) {
		Page<Product> products = productService.findProductsWithPagination(offset, pageSize);
		return new APIResponse<>(products.getSize(), products);
	}

	@GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
	private APIResponse<Page<Product>> getProductsWithPaginationAndSort(@PathVariable Integer offset,
			@PathVariable Integer pageSize, @PathVariable String field) {
		Page<Product> products = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
		return new APIResponse<>(products.getSize(), products);
	}

}
