package com.example.ecommerce.services;

import com.example.ecommerce.customexceptions.BusinessException;
import com.example.ecommerce.dto.OrderInvoiceDto;
import com.example.ecommerce.dto.PriceRange;
import com.example.ecommerce.dto.ProductDetailsDTO;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entities.*;
import com.example.ecommerce.exceptions.EmptyInputException;
import com.example.ecommerce.exceptions.NullValueException;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.repositories.*;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

import com.example.ecommerce.constants.Status;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ProductServiceImpl implements ProductService{

   public static final String ACTIVE="YES";
//    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductOrderDetailsRepository productOrderDetailsRepository;

    @Autowired
    private ProductOrderItemsRepository productOrderItemsRepository;

    @Autowired
    private ProductPaymentRepository productPaymentRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private ProductCartRepository productCartRepository;

    @Autowired
    private ProductCartItemsRepository productCartItemsRepository;

    @Autowired
    private ProductImagesRepository productImagesRepository;


//    @Override
//    public List<Product> getAllProducts() {
//        List<Product> productList;
//        try
//        {
//            productList=productRepository.findAll();
//        }
//        catch (Exception e)
//        {
//            throw new BusinessException("Something went wrong while fetching all the products");
//        }
//        if(productList.isEmpty())
//        {
//            throw new BusinessException("Empty!!! No products are available");
//        }
//        return productList;
//    }


    /*
    @Override
    public List<Product> getProductsByCategoryId(int categoryid) {
        ProductCategory productCategory=productCategoryRepository.findById(categoryid).get();
//        ProductCategory productCategory=productCategoryRepository.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category not found"));
        return productRepository.findByProductCategory(productCategory);
    }

     */


    @Override
    public void saveProducts(ProductImages productImages, ProductDetailsDTO productDetailsDTO) throws IOException {
        Product product=new Product();
        product.setProductId(productDetailsDTO.getProductId());
        product.setProductName(productDetailsDTO.getProductName());
        product.setProductCategory(productDetailsDTO.getProductCategory());
        product.setProductPrice(productDetailsDTO.getProductPrice());
        product.setProductDescription(productDetailsDTO.getProductDescription());
        product.setProductDiscount(productDetailsDTO.getProductDiscount());
        product.setProductInventory(productDetailsDTO.getProductInventory());
        product.setProductImages(productImages);
        productRepository.save(product);
    }

    @Override
    public ProductImages saveImage(MultipartFile file) throws IOException {

        ProductImages productImages=new ProductImages();
        productImages.setName(file.getOriginalFilename());
        productImages.setImageData(file.getBytes());
        return productImagesRepository.save(productImages);
    }

    @Override
    public List<Product> getAllProducts() {
        logger.info("In getAllProducts method");
        List<Product> productList =productRepository.findAll();
        if(productList.isEmpty())
        {
            throw new ResourceNotFoundException("Empty!!! No products are available");
        }

        return productList;

    }

    @Override
    public List<ProductImages> getAllImages() {
        return productImagesRepository.findAll();
    }

    @Override
    public ProductImages getImagesByName(String imagename) {
        return productImagesRepository.findByName(imagename);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        if(category==null || category.isEmpty())
        {
         throw new EmptyInputException("Category is empty. Please provide category name");
        }
        ProductCategory productCategory=productCategoryRepository.findByCategoryName(category);
        if(productCategory==null)
        {
            throw new ResourceNotFoundException(category+" category not found");
        }
        return productRepository.findByProductCategory(productCategory);
    }

    @Override
    public List<Product> getProductsByPriceRange(PriceRange priceRange) {


        if(priceRange.getMinPrice()==null || priceRange.getMaxPrice()==null) {
             throw new EmptyInputException("Please provide the price range");
        }
        List<Product> productList = productRepository.findByProductPriceBetween(priceRange.getMinPrice(), priceRange.getMaxPrice());
        if(productList.isEmpty())
        {
            throw new ResourceNotFoundException("No products are not found between the range");
        }
        return productList;
    }

    @Override
    public void saveProductsByUserId(Integer userid,List<ProductDto> productDtos) {

        UserInfo userInfo = userInfoRepository.findById(userid).get();
//        UserInfo userInfo;
//        try {
//             userInfo = userInfoRepository.findById(userid).get();
//        }
//        catch (NoSuchElementException NoSuchElementException)
//        {
//          throw new BusinessException("No such user id found");
//        }
        if(productDtos.isEmpty())
        {
            throw new EmptyInputException("Empty!! Add products");
        }
      ProductOrderDetails productOrderDetails1=new ProductOrderDetails();

//      if(userInfo!=null)
//      {
          productOrderDetails1.setUserInfo(userInfo);
          productOrderDetails1.setProductPayment(getPaymentDetails(productDtos));
          productOrderDetails1.setOrderAmount(calculateOrderAmount(productDtos));
          productOrderDetails1.setOrderStatus(Status.SUCCESS.toString());
          productOrderDetails1.setOrderDate(LocalDate.now());

          productOrderDetailsRepository.save(productOrderDetails1);

          ProductInventory productInventory;

          for(ProductDto productDto:productDtos)
          {
              ProductOrderItems productOrderItems=new ProductOrderItems();
              Product product=new Product();
              product.setProductId(productDto.getProductId());
              product.setProductCategory(productDto.getProductCategory());
              product.setProductName(productDto.getProductName());
              product.setProductDescription(productDto.getProductDescription());
              product.setProductPrice(productDto.getProductPrice());
              product.setProductInventory(productDto.getProductInventory());
              product.setProductDiscount(productDto.getProductDiscount());
              product.setProductImages(productDto.getProductImages());

             productOrderItems.setProductOrderDetails(productOrderDetails1);
             productOrderItems.setProduct(product);
             productOrderItems.setQuantity(productDto.getProductQuantity());

             productOrderItemsRepository.save(productOrderItems);


             productInventory=productInventoryRepository.findById(product.getProductInventory().getInventoryId()).orElse(null);
             if(productInventory!=null)
             {
                 int stock=productInventory.getQuantity();
                 int updatedStock=0;
                 if(stock>productOrderItems.getQuantity()) {
                     updatedStock = stock - productOrderItems.getQuantity();
                 }
                 productInventory.setQuantity(updatedStock);
                 productInventoryRepository.save(productInventory);
             }

          }
//      }
    }

    @Override
    public List<ProductOrderItems> showOrderedProductsByUserID(Integer userid) {

        UserInfo userInfo = userInfoRepository.findById(userid).get();

//        UserInfo userInfo;
//        try {
//            userInfo = userInfoRepository.findById(userid).get();
//        }
//        catch (NoSuchElementException noSuchElementException)
//        {
//            throw new BusinessException("User Not found");
//        }

        List<ProductOrderDetails> productOrderDetails=productOrderDetailsRepository.findByUserInfo(userInfo);
        if(productOrderDetails.isEmpty())
        {
            throw new ResourceNotFoundException("No orders placed");
        }
        List<ProductOrderItems> productOrderItems=productOrderItemsRepository.findByListOfProductOrderDetails(productOrderDetails);
        return productOrderItems;
    }

    @Override
    public List<ProductOrderItems> showOrderItemsByUserIDAndOrderId(Integer userid, Integer orderid) {

        UserInfo userInfo = userInfoRepository.findById(userid).get();

        ProductOrderDetails productOrderDetails=productOrderDetailsRepository.findByOrderIdAndUserInfo(orderid,userInfo);
        if(productOrderDetails==null)
        {
            throw new ResourceNotFoundException("No orders placed");
        }
        List<ProductOrderItems> productOrderItems=productOrderItemsRepository.findByProductOrderDetails(productOrderDetails);
        return productOrderItems;
    }

    @Override
    public void addProductsToCartByUserId(Integer userid, List<ProductDto> productDtos) {
        UserInfo userInfo = userInfoRepository.findById(userid).get();
        if(productDtos.isEmpty())
        {
            throw new EmptyInputException("Cart is Empty!! Add products");
        }
//        ProductCart productCart=new ProductCart();

        ProductCart productCart=productCartRepository.findByUserInfo(userInfo);
        double cartAmount;
        if(productCart==null) {
            productCart.setUserInfo(userInfo);
            productCart.setTotalAmount(calculateOrderAmount(productDtos));
        }
        else
        {
            cartAmount=productCart.getTotalAmount() + calculateOrderAmount(productDtos);
            productCart.setTotalAmount(cartAmount);
        }
        productCartRepository.save(productCart);
        logger.info("ProductCart"+productCart.getCartId()+" "+productCart.getUserInfo()+" "+productCart.getTotalAmount());


//        ProductCartItems productCartItem;
        for(ProductDto productDto:productDtos) {
            ProductCartItems productCartItems;
            Product product=new Product();
            product.setProductId(productDto.getProductId());
            product.setProductCategory(productDto.getProductCategory());
            product.setProductName(productDto.getProductName());
            product.setProductDescription(productDto.getProductDescription());
            product.setProductPrice(productDto.getProductPrice());
            product.setProductInventory(productDto.getProductInventory());
            product.setProductDiscount(productDto.getProductDiscount());
            product.setProductImages(productDto.getProductImages());

            productCartItems=productCartItemsRepository.findByProductCartAndProduct(productCart,product);
            int updatedQuantity;

            if(productCartItems==null) {
                ProductCartItems productCartItems1=new ProductCartItems();
                productCartItems1.setProductCart(productCart);
                productCartItems1.setProduct(product);
                productCartItems1.setQuantity(productDto.getProductQuantity());
                productCartItemsRepository.save(productCartItems1);
            }
            else
            {
                updatedQuantity=productCartItems.getQuantity() + productDto.getProductQuantity();
                productCartItems.setQuantity(updatedQuantity);
                productCartItemsRepository.save(productCartItems);
            }

        }



    }

    @Override
    public List<ProductCartItems> showCartItemsByUserID(Integer userid) {

        UserInfo userInfo=null;

        userInfo = userInfoRepository.findById(userid).get();

        ProductCart productCart=productCartRepository.findByUserInfo(userInfo);
        if(productCart==null)
        {
            throw new ResourceNotFoundException("Cart is Empty!!");
        }

        List<ProductCartItems> productCartItems=productCartItemsRepository.findByProductCart(productCart);
        return productCartItems;
    }

    @Override
    public void deleteCartItem(Integer cartid) {
        productCartItemsRepository.deleteById(cartid);
    }

    public ProductPayment getPaymentDetails(List<ProductDto> productDtos)
    {
        ProductPayment productPayment1=new ProductPayment();
        productPayment1.setPaymentType("UPI");
        productPayment1.setPaymentAmount(calculateOrderAmount(productDtos));
        productPayment1.setPaymentStatus(Status.SUCCESS.toString());
        productPaymentRepository.save(productPayment1);
        return productPayment1;
    }

    public Double calculateOrderAmount(List<ProductDto> productDtos)
    {

        Double totalAmount=0.00;
        for(ProductDto productDto:productDtos) {
            ProductDiscount productDiscount=productDto.getProductDiscount();
            int quantity=productDto.getProductQuantity();
            if(productDiscount.getActive().equalsIgnoreCase(ACTIVE))
            {

                totalAmount+=quantity*calculateAmountAfterDiscount(productDiscount.getDiscountPercentage(),productDto.getProductPrice());
            }
            else{
                totalAmount += quantity*productDto.getProductPrice();
            }
        }
        return totalAmount;
    }

    public Double calculateAmountAfterDiscount(Integer discountPercent,Double price)
    {
        Integer discount=discountPercent;
        Double discountAmount=(discount*price)/100;
        return price-discountAmount;

    }

    public String getImageByteDataAsBase64Format(byte[] imageInBytes) {
        String imageUri = null;
        try {
            imageUri = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageInBytes);
        } catch (Exception e) {
            System.out.println("Error while converting the Image Byte Data as BASE 64 format {} " + e.getCause());
        }
        return imageUri;

    }

public List<OrderInvoiceDto>  getOrderInvoiceDetails(Integer userid,Integer orderid)
{

    List<ProductOrderItems> productOrderItems=showOrderItemsByUserIDAndOrderId(userid,orderid);
    List<OrderInvoiceDto> orderInvoiceDtos=new ArrayList<>();
    if(!productOrderItems.isEmpty())
    {
        for(ProductOrderItems productOrderItems1:productOrderItems)
        {
            OrderInvoiceDto orderInvoiceDto=new OrderInvoiceDto();
            orderInvoiceDto.setProductName(productOrderItems1.getProduct().getProductName());
            orderInvoiceDto.setProductDescription(productOrderItems1.getProduct().getProductDescription());
            orderInvoiceDto.setProductImages(getImageByteDataAsBase64Format(productOrderItems1.getProduct().getProductImages().getImageData()));
            orderInvoiceDto.setProductPrice(productOrderItems1.getProduct().getProductPrice());
            orderInvoiceDto.setProductQuantity(productOrderItems1.getQuantity());
            orderInvoiceDto.setPriceAfterDiscount(productOrderItems1.getProduct().getProductDiscount().getActive().equalsIgnoreCase(ACTIVE) ? calculateAmountAfterDiscount( productOrderItems1.getProduct().getProductDiscount().getDiscountPercentage(),productOrderItems1.getProduct().getProductPrice())*productOrderItems1.getQuantity():productOrderItems1.getProduct().getProductPrice()*productOrderItems1.getQuantity());
            orderInvoiceDtos.add(orderInvoiceDto);
        }
    }
    return orderInvoiceDtos;
}
}
