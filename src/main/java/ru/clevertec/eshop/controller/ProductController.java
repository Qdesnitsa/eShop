package ru.clevertec.eshop.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.ProductService;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    ProductService productService;

    public ProductController(@Qualifier("productServiceImpl") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() throws ServiceException {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ServiceException {
        Product product = (Product) productService.findByID(id).get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
