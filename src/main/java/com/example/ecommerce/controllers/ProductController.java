package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entities.*;
import com.example.ecommerce.repositories.ProductOrderDetailsRepository;
import com.example.ecommerce.services.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductOrderDetailsRepository productOrderDetailsRepository;
//    @Autowired
//    private TemplateEngine templateEngine;

//    @GetMapping("/allProducts")
//    public ResponseEntity<?> getAllProducts()
//    {
//        try {
//            List<Product> listOfProducts = productService.getAllProducts();
//            return new ResponseEntity<List<Product>>(listOfProducts, HttpStatus.OK);
//        }
//        catch (BusinessException businessException)
//        {
//            ErrorResponse errorResponse=new ErrorResponse(businessException.getErrorMessage(),HttpStatus.NOT_FOUND.value());
//            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
//        }
//
//    }

    //    @GetMapping("/category/{categoryid}")
//    public List<Product> getProductsByCategoryId(@PathVariable int categoryid)
//    {
//        return productService.getProductsByCategoryId(categoryid);
//    }

    @PostMapping("/saveProductDetails")
    public ResponseEntity<?> saveProducts(@RequestParam("image") MultipartFile multipartFile,@RequestParam String productData) throws IOException {

        ProductDetailsDTO productDetailsDTO;
        try {
            productDetailsDTO = objectMapper.readValue(productData, ProductDetailsDTO.class);
        }
        catch(JsonProcessingException jsonProcessingException)
        {
         return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        ProductImages productImages=productService.saveImage(multipartFile);
        productService.saveProducts(productImages,productDetailsDTO);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/allProducts")
    public ResponseEntity<?> getAllProducts()
    {

            List<Product> listOfProducts = productService.getAllProducts();
            return new ResponseEntity<List<Product>>(listOfProducts, HttpStatus.OK);
    }

    @GetMapping("/images")
    public ResponseEntity<?> getAllImages()
    {

        List<ProductImages> productImages = productService.getAllImages();
        return new ResponseEntity<List<ProductImages>>(productImages, HttpStatus.OK);
    }

//    @GetMapping("/getByCategory")
//    public ResponseEntity<?> getProductsByCategory(@RequestParam String category)
//    {
//        try {
//            List<Product> productList = productService.getProductsByCategory(category);
//            return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
//        }
//        catch (BusinessException businessException)
//        {
//            ErrorResponse errorResponse=new ErrorResponse(businessException.getErrorMessage(),HttpStatus.NOT_FOUND.value());
//            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
//        }
//
//    }

    @GetMapping("/getByCategory")
    public ResponseEntity<?> getProductsByCategory(@RequestParam String category)
    {

            List<Product> productList = productService.getProductsByCategory(category);
            return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);

    }


    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam(value = "minPrice",required = false) Double minPrice,
                                                 @RequestParam(value = "maxPrice",required = false) Double maxPrice) {
        PriceRange priceRange = new PriceRange(minPrice, maxPrice);
        List<Product> products = productService.getProductsByPriceRange(priceRange);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


    @PostMapping("/saveProductsByUserId/{userid}")
    public ResponseEntity<Void> saveProductsByUserId(@PathVariable Integer userid, @RequestBody List<ProductDto> productDtos) {

            productService.saveProductsByUserId(userid,productDtos);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/orderedProductsByUserId/{userid}")
    public ResponseEntity<?> showOrderedProducts(@PathVariable Integer userid)
    {
            List<ProductOrderItems> productOrderItems = productService.showOrderedProductsByUserID(userid);
            return new ResponseEntity<List<ProductOrderItems>>(productOrderItems, HttpStatus.OK);

    }

    @PostMapping("/addProductsToCartByUserId/{userid}")
    public ResponseEntity<Void> addProductsToCartByUserId(@PathVariable Integer userid, @RequestBody List<ProductDto> productDtos) {

        productService.addProductsToCartByUserId(userid,productDtos);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

//    @PostMapping("/addProductsToCartByUserId/{userid}/{productid}")
//    public ResponseEntity<Void> addProductToCartByUserId(@PathVariable int userid, @PathVariable("productid") int productid) {
//        productService.addProductToCartByUserId(userid,productid);
//        return new ResponseEntity<Void>(HttpStatus.CREATED);
//    }

    @GetMapping("/showCartItemsByUserId/{userid}")
    public ResponseEntity<?> showCartItemsByUserId(@PathVariable Integer userid)
    {
        List<ProductCartItems> productCartItems = productService.showCartItemsByUserID(userid);
        return new ResponseEntity<List<ProductCartItems>>(productCartItems, HttpStatus.OK);

    }

    @DeleteMapping("/deleteCartItemByUserId/{cartid}")
    public ResponseEntity<Void> deleteCartItemByCartId(@PathVariable Integer cartid) {
        productService.deleteCartItem(cartid);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/generatePdf/{userid}/{orderid}")
    public ResponseEntity<byte[]> generatePDF(@PathVariable Integer userid,@PathVariable Integer orderid)
    {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

//        List<ProductOrderItems> productOrderItems =productService.showOrderItemsByUserIDAndOrderId(userid,orderid);
//
//        List<String> imagess=new ArrayList<>();
//        for(ProductOrderItems productOrderItems1:productOrderItems)
//        {
////      imagess.add(Base64.getEncoder().encodeToString(productOrderItems1.getProduct().getProductImages().getImageData()));
//            imagess.add(productService.getImageByteDataAsBase64Format(productOrderItems1.getProduct().getProductImages().getImageData()));
//        }

        List<OrderInvoiceDto> orderInvoiceDtos=productService.getOrderInvoiceDetails(userid,orderid);

        Context context=new Context();
        context.setVariable("orderItems", orderInvoiceDtos);
        ProductOrderDetails productOrderDetails=productOrderDetailsRepository.findById(orderid).orElse(null);
        String orderNo = productOrderDetails.getOrderId().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        String orderDate= formatter.format(productOrderDetails.getOrderDate());
        String orderAmount=productOrderDetails.getOrderAmount().toString();
        context.setVariable("orderNo", orderNo);
        context.setVariable("orderDate", orderDate);
        context.setVariable("orderAmount", orderAmount);
//        context.setVariable("pimage", imagess.get(0));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        String html = templateEngine.process("newOrderInvoice", context);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime= dateFormat.format(new Date());
        String pdfName="invoice-"+currentDateTime+".pdf";
        headers.setContentDispositionFormData("inline", pdfName);

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }



//    @GetMapping("/sample")
//    public String hello(@RequestParam("price") BigDecimal d, @RequestBody Sample sample)
//    {
//        System.out.println("------------"+d);
//        return "Hello ";
//    }


//    @GetMapping("/getProductsByPrice")
//    public List<Product> getProductsByPrice(@RequestParam int price)
//    {
//      return productService.getProductsByPrice(price);
//    }

//    @PostMapping("/saveProductsByUserId/{userid}")
//    public ResponseEntity<?> saveProductsByUserId(@PathVariable int userid, @RequestBody List<ProductDto> productDtos) {
//        try {
//            productService.saveProductsByUserId(userid,productDtos);
//            return new ResponseEntity<Void>(HttpStatus.CREATED);
//        }
//        catch (BusinessException businessException)
//        {
//            ErrorResponse errorResponse=new ErrorResponse(businessException.getErrorMessage(),HttpStatus.BAD_REQUEST.value());
//            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
//        }
//
//    }

//    @GetMapping("/orderedProductsByUserId/{userid}")
//    public ResponseEntity<?> showOrderedProducts(@PathVariable int userid)
//    {
//        try {
//            List<ProductOrderItems> productOrderItems = productService.showOrderedProductsByUserID(userid);
//            return new ResponseEntity<List<ProductOrderItems>>(productOrderItems, HttpStatus.OK);
//        }
//        catch (BusinessException businessException)
//        {
//            ErrorResponse errorResponse=new ErrorResponse(businessException.getErrorMessage(),HttpStatus.NOT_FOUND.value());
//            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
//        }
//
//    }



}
