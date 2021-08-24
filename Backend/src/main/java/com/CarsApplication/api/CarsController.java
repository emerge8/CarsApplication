package com.CarsApplication.api;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CarsApplication.domain.Car;
import com.CarsApplication.domain.CarService;


@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {
	private CarService service = new CarService();

	@GetMapping()
	public List<Car> get() {
		return service.getCars();
	}
}


