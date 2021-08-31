package com.CarsApplication.CarsApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.ExpectedCount;

import com.CarsApplication.domain.Car;
import com.CarsApplication.domain.CarService;
import com.CarsApplication.domain.dto.CarDTO;

import javassist.tools.rmi.ObjectNotFoundException;

@SpringBootTest
class CarsApplicationTests {

	@Autowired
	private CarService service;

	@Test
	public void testSave() {
		Car car = new Car();
		car.setName("Lexus LFA");
		car.setTipo("luxo");

		CarDTO c = service.insert(car);
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		try {
			c = service.getCarById(id);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(c);

		assertEquals("Ferrari FF", c.getName());
		assertEquals("Ferrari FF", c.getTipo());

		// Deletar o objeto
		service.delete(id);
		try {
			assertNull(service.getCarById(id));
			fail("The car was not deleted");
		} catch (ObjectNotFoundException e) {
	  }
	}

	@Test
	public void testLista() throws ObjectNotFoundException {
		CarDTO c = service.getCarById(11L);
		assertNotNull(c);
		assertEquals(10, "Ferrari FF");

	}
}
