package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.ProductListDTO;
import com.mercadolibre.dambetan01.dtos.response.BatchStockDTO;
import com.mercadolibre.dambetan01.dtos.response.ProductStockSearchDTO;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.repository.ProductRepository;
import com.mercadolibre.dambetan01.repository.ProductStockRepository;
import com.mercadolibre.dambetan01.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    final private ProductRepository repository;
    final private ProductStockRepository productStockRepository;

    public ProductServiceImpl(ProductRepository repository, ProductStockRepository productStockRepository) {
        this.repository = repository;
        this.productStockRepository = productStockRepository;
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found product with id "+id));
    }

    @Override
    public List<ProductListDTO> findAllProductsList() {

        LocalDate today = LocalDate.now();
        LocalDate threeWeeksAgo = today.minusWeeks(3);

        List<ProductListDTO> productList = repository.findAllProductsList(threeWeeksAgo);

        if (productList.size() == 0) throw new NotFoundException("Not found products");

       return productList;
    }

    @Override
    public List<ProductListDTO> findAllProductsListByCategory(String category) {

        String nameCategory = "";

        if(category.equalsIgnoreCase("FR")){
            nameCategory = "fresco";
        } else if (category.equalsIgnoreCase("RF")){
             nameCategory = "refrigerado";
        } else {
             nameCategory = "congelado";
        }

        LocalDate today = LocalDate.now();
        LocalDate threeWeeksAgo = today.minusWeeks(3);

        String finalNameCategory = nameCategory;
        List<ProductListDTO> productList = repository.findAllProductsList(threeWeeksAgo).stream()
                .filter(data -> data.getCategory().equals(finalNameCategory)).collect(Collectors.toList());

        if (productList.size() == 0) throw new NotFoundException("Not found products");

        return productList;
    }

    @Override
    public ProductStockSearchDTO findAllProductsByIdAndSort(Long idProduct, String order) {
        ProductStockSearchDTO productStockSearchDTO = new ProductStockSearchDTO();
        productStockSearchDTO.setProductId(idProduct);
        List<BatchStockDTO> byProductId = productStockRepository.findByProductId(idProduct, getProductStockSortByOrder(order));
        productStockSearchDTO.setSector(productStockRepository.findByProductBySector(idProduct));
        productStockSearchDTO.setBatchStock(byProductId);
        return productStockSearchDTO;
    }

    private Sort getProductStockSortByOrder(String order) {
        if (order == null)
            return null;
        switch (order){
            case "L":
                return Sort.by(Sort.Direction.DESC, "id");
            case "C":
                return Sort.by(Sort.Direction.DESC, "currentQuantity");
            case "F":
                return Sort.by(Sort.Direction.DESC, "dueDate");
            default:
                return null;
        }
    }


}
