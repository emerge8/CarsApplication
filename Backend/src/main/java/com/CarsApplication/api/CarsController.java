package com.CarsApplication.api;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CarsApplication.domain.Car;
import com.CarsApplication.domain.CarService;


@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {
	
	@Autowired
	private CarService service;

	@GetMapping()
	public Iterable<Car> get() {
		return service.getCars();
	}
	
	@GetMapping("/{id}")
	public Optional<Car> get(@PathVariable("id") Long id) {
		return service.getCarById(id);
	}
	@GetMapping("/tipo/{tipo}")
	public Iterable<Car> getCarsByTipo(@PathVariable("tipo") String tipo) {
		return service.getCarsByTipo(tipo);
	}
	
	@PostMapping
	public String post(@RequestBody Car car) {
		Car c = service.insert(car);
		return "The car have being saved: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Car car) {
		Car c = service.update(car, id);
		return "The car have being updated: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "The car have being deleted";
	}
}


