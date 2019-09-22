package com.klapertart;

import com.klapertart.service.ProductService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    private static SessionFactory factory;
    
    public static void main( String[] args ){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
            System.err.println("Gagal membuat sessionFactory object." + ex); 
        }
			
        ProductService SP = new ProductService(factory);

        Integer productID = SP.addProduct("Macbook", 15000000.0, 70);
        
        SP.listProduct();
        
        SP.updateProduct(productID, "Pokcoy");
        
        SP.listProduct();

        SP.deleteProduct(productID);
        
        SP.listProduct();
        
        factory.close();
    }
}
