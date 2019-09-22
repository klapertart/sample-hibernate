/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klapertart.service;

import com.klapertart.model.Product;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author kurakuraninja
 */
public class ProductService {
    private SessionFactory factory;

    public ProductService(SessionFactory factory) {
        super();
        this.factory = factory;
    }

    public Integer addProduct(String name, Double price, int stock){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer productID = null;
        try{
           tx = session.beginTransaction();
           Product product = new Product(name, price, stock);
           productID = (Integer) session.save(product); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        return productID;
    }    
    
    public void listProduct(){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
           tx = session.beginTransaction();
           // in create query mention class name not table name
           List<Product> productlis = session.createQuery("FROM Product").list(); 
           for(Product product : productlis){
               System.out.println(product.toString());
           }
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }    
    
    public void updateProduct(Integer id, String name){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
           tx = session.beginTransaction();
           Product product = (Product)session.get(Product.class, id); 
           product.setName(name);
           session.update(product); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }    
    
    
    public void deleteProduct(Integer id){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
           tx = session.beginTransaction();
           Product product = (Product)session.get(Product.class, id); 
           session.delete(product); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }
        
}
