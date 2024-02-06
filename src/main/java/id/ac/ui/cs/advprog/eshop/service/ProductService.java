package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create (Product product);

    public Product edit (Product product);

    public Product findById (String productId);

    public List <Product> findAll();

}