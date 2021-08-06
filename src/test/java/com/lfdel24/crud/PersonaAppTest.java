package com.lfdel24.crud;

import com.lfdel24.crud.dominio.modelo.Identificacion;
import com.lfdel24.crud.dominio.modelo.Persona;
import com.lfdel24.crud.dominio.modelo.TipoIdentificacion;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonaAppTest {

	@Test
	void contextLoads() {
	}

	@Test
	void validarCrearObjetoPersona() {
		Identificacion identificacion = Identificacion.crear(TipoIdentificacion.CEDULA, "1094911832");
		Persona persona = Persona.crear(identificacion, "Leonardo De la cruz", LocalDate.now());
		Assertions.assertNotNull(persona);
	}

}
