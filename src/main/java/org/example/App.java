package org.example;

import org.example.services.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
//        Service service = new Service();
//        System.out.println(service
//                .hashing_password("admin"));
        SpringApplication.run(App.class, args);
    }
}
