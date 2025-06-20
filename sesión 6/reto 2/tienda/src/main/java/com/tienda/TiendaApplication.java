
package com.tienda;

import com.tienda.modelo.*;
import com.tienda.repositorio.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class TiendaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(MarcaRepository marcaRepo, ProductoRepository productoRepo) {
        return args -> {
            Marca apple = new Marca("Apple");
            Marca samsung = new Marca("Samsung");
            marcaRepo.saveAll(List.of(apple, samsung));

            productoRepo.save(new Producto("iPhone 15", apple));
            productoRepo.save(new Producto("iPad Pro", apple));
            productoRepo.save(new Producto("Galaxy S23", samsung));
            productoRepo.save(new Producto("Smart TV", samsung));

            System.out.println("📚 Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println("🏷️ " + marca.getNombre() + ":");
                productoRepo.findAll().stream()
                    .filter(p -> p.getMarca().getId().equals(marca.getId()))
                    .forEach(p -> System.out.println("   - " + p.getNombre()));
            });
        };
    }
}
