package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Repository
public class ProductRepository {
    private List <Product> productData = new ArrayList<>();
    private Map <String, Product> productMap = new HashMap<>();

    public Product create (Product product) {
        productData.add(product);
        productMap.put(product.getProductId(), product);
        return product;
    }

    public Product edit (Product product) {
        Product productToEdit = productMap.get(product.getProductId());
        if (productToEdit != null) {
            productToEdit.setProductName(product.getProductName());
            productToEdit.setProductQuantity(product.getProductQuantity());
            return productToEdit;
        }
        return null;
    }

    public void delete (String productId) {
        Product product = productMap.get(productId);
        productData.remove(product);
        productMap.remove(productId);
    }

    public Product findById (String productId) {
        return productMap.get(productId);
    }

    public Iterator <Product> findAll() {
        return productData.iterator();
    }
}