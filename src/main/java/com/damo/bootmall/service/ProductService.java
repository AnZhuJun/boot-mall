package com.damo.bootmall.service;

import com.damo.bootmall.bean.Product;
import com.damo.bootmall.dao.IProduct;
import com.damo.bootmall.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

//    jdbc

    @Autowired
    private IProduct productDaos;


    public int createProduct(Product product){
        return productDaos.insert(product);
    }

    public int updateProduct(Product product){
        return productDaos.updateById(product);
    }

    public int deleteProduct(int id){
        return productDaos.deleteById(id);
    }

    public Product getById(int id)
    {
        return productDaos.getById(id);
    }

    public Product getByName(String name){
        return productDaos.getByName(name);
    }



    //jpa
    @Autowired
    private ProductDao productDao;

    public List<Product> tickAll(){
        return productDao.findAll();
    }

    public Product tickById(int id){
        return productDao.findById(id).orElse(new Product(-1,"null",0.0));
    }

    public Product tickByNameAndPrice(String name,Double price){
        return productDao.findByNameAndPrice(name,price);
    }

    public Product renewProduct(Product product){
        return productDao.save(product);
    }

    public Product removeProduct(int id) {
        Product current = tickById(id);
        if (current.getId() > -1) {
            productDao.deleteById(id);
        }
        return current;
    }
}
