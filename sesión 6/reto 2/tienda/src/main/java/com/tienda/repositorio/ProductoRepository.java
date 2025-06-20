
package com.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}
