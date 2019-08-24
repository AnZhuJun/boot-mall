package com.damo.bootmall.controller;

import com.damo.bootmall.bean.Product;
import com.damo.bootmall.component.RedisComponent;
import com.damo.bootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService service;

    @Autowired
    RedisComponent redisComponent;

//    public ProductController(RedisComponent redisComponent){
//        this.redisComponent = redisComponent;
//
//    }

    //jdbc

//    @PostMapping
//    public int addProduct(@RequestBody Product product){
//        return service.createProduct(product);
//    }

    @GetMapping("/{name}")
    public Product getProductById(@PathVariable String name) throws IOException{
        Product product = redisComponent.get("product::" + name,Product.class);
        if(Objects.isNull(product)){
            product = service.getByName(name);
            redisComponent.set("product::" + name,product);
        }

        return product;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return service.tickAll();
    }

    @PutMapping
    public Product addProduct(@RequestBody Product product){
        return service.renewProduct(product);
    }

    @GetMapping("/{name}/{price}")
    public Product getByNameAndPrice(@PathVariable String name,@PathVariable Double price){
        return service.tickByNameAndPrice(name,price);
    }

    @CacheEvict(value = "product",allEntries = true)
    @DeleteMapping("/{id}")
    public Product removeProduct(@PathVariable int id){
        return service.removeProduct(id);
    }

    @Cacheable(value = "product" ,key = "#id",unless = "#result.price<20")
    @GetMapping("/id/{id}")
    public Product getById(@PathVariable int id) {
        return service.tickById(id);
    }

    @CachePut(value = "product",key = "#id")
    @PutMapping("/{id}")
    public Product renewProduct(@PathVariable int id,@RequestBody Product product){
        product.setId(id);
        return service.renewProduct(product);
    }
}
