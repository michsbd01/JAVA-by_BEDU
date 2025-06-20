package com.ejemplo.inventario;

import com.ejemplo.inventario.model.Producto;
import com.ejemplo.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.validation.ConstraintViolationException;

@SpringBootApplication
public class InventarioApplication implements CommandLineRunner {

    @Autowired
    private ProductoRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            repo.save(new Producto("Laptop Lenovo", "Laptop de alto rendimiento", 12500));
            repo.save(new Producto("Mouse Logitech", "Mouse óptico", 350));
            repo.save(new Producto("Teclado Mecánico", "Con luces RGB", 950));
            repo.save(new Producto("Monitor", "Monitor LED 24 pulgadas", 3200));
        } catch (ConstraintViolationException e) {
            System.err.println("Validación fallida: " + e.getMessage());
        }

        System.out.println("📦 Productos con precio mayor a 500:");
        repo.findByPrecioGreaterThan(500).forEach(System.out::println);

        System.out.println("\n🔍 Productos que contienen 'lap':");
        repo.findByNombreContainingIgnoreCase("lap").forEach(System.out::println);

        System.out.println("\n🎯 Productos con precio entre 400 y 1000:");
        repo.findByPrecioBetween(400, 1000).forEach(System.out::println);

        System.out.println("\n📘 Productos cuyo nombre empieza con 'm':");
        repo.findByNombreStartingWithIgnoreCase("m").forEach(System.out::println);
    }
}