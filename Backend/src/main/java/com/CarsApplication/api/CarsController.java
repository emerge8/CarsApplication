package com.CarsApplication.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.CarsApplication.domain.Car;
import com.CarsApplication.domain.CarService;
import com.CarsApplication.domain.dto.CarDTO;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

	@Autowired
	private CarService service;

	@SuppressWarnings("rawtypes")
	@GetMapping()
	public ResponseEntity get() {
		return ResponseEntity.ok(service.getCars());
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) throws ObjectNotFoundException {
		CarDTO car = service.getCarById(id);

		return ResponseEntity.ok(car);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarsByTipo(@PathVariable("tipo") String tipo) {
		List<CarDTO> cars = service.getCarsByTipo(tipo);

		return cars.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cars);
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@PostMapping
	public ResponseEntity post(@RequestBody Car car) {

		CarDTO c = service.insert(car);

		URI location = getUri(c.getId());
		return ResponseEntity.created(location).build();
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Car car) {

		car.setId(id);

		CarDTO c = service.update(car, id);

		return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
