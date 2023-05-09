package com.example.ecommerce.services;

import com.example.ecommerce.dto.PriceRange;
import com.example.ecommerce.dto.ProductDetailsDTO;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.ProductCartItems;
import com.example.ecommerce.entities.ProductImages;
import com.example.ecommerce.entities.ProductOrderItems;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface ProductService {

    void saveProducts(ProductImages productImages, ProductDetailsDTO productDetailsDTO) throws IOException;

    ProductImages saveImage(MultipartFile file) throws IOException;
    List<Product> getAllProducts();

    List<ProductImages> getAllImages();

    ProductImages getImagesByName(String imagename);
//    List<Product> getProductsByCategoryId(int categoryId);

    List<Product> getProductsByCategory(String category);

//    List<Product> getProductsByPrice(int price);

    public List<Product> getProductsByPriceRange(PriceRange priceRange);

    void saveProductsByUserId(Integer userid,List<ProductDto> productDtos);


     List<ProductOrderItems> showOrderedProductsByUserID(Integer userid);

    List<ProductOrderItems> showOrderItemsByUserIDAndOrderId(Integer userid,Integer orderid);

     void addProductsToCartByUserId(Integer userid,List<ProductDto> productDtos);

    List<ProductCartItems> showCartItemsByUserID(Integer userid);

    void deleteCartItem(Integer cartid);
}
