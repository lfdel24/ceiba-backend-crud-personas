package com.lfdel24.crud.dominio.puerto.repositorio;

import com.lfdel24.crud.dominio.modelo.Identificacion;
import com.lfdel24.crud.dominio.modelo.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPersona extends JpaRepository<Persona, Long> {

    Optional<Persona> findByIdentificacion(Identificacion identificacion);

}
