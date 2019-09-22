/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klapertart.service;

import com.klapertart.model.Product;
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
}
