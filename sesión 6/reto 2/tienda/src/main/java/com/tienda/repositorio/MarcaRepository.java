
package com.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.modelo.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {}
