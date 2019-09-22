package com.klapertart;

import com.klapertart.service.ProductService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
    private static SessionFactory factory;
    
    public static void main( String[] args ){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
            System.err.println("Gagal membuat sessionFactory object." + ex); 
        }
			
        //buat service
        ProductService SP = new ProductService(factory);

        //tambah pegawai & mengembalikan id pegawainya
        Integer productID = SP.addProduct("Macbook", 15000000.0, 70);
        System.out.println("Saved - ProductID : " + productID);
        
        factory.close();
    }
}
