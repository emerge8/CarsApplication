package com.CarsApplication.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarService {

	@Autowired
	private CarRepository rep;

	public Iterable<Car> getCars() {
		return rep.findAll();
	}

	public Optional<Car> getCarById(Long id) {
		return rep.findById(id);
	}

	public List<Car> getCarsByTipo(String tipo) {
		return rep.findByTipo(tipo);
	}

	public Car insert(Car car) {
		Assert.isNull(car.getId(), " It was not possible find the register");

		return rep.save(car);
	}

	public Car update(Car car, Long id) {
		Assert.notNull(id, "It was not possible to update");

		Optional<Car> optional = getCarById(id);
		if (optional.isPresent()) {
			Car db = optional.get();
			db.setName(car.getName());
			db.setTipo(car.getTipo());
			System.out.println("Car id " + db.getId());

			rep.save(db);

			return db;
		} else {
			throw new RuntimeException("It was not possible to update");
		}

	}

	public List<Car> getCarsFake() {
		List<Car> cars = new ArrayList<>();

		cars.add(new Car(1L, "Fusca"));
		cars.add(new Car(2L, "Opala"));
		cars.add(new Car(3L, "Variante"));
		return cars;
	}

	
	public void delete(Long id) {
		Optional<Car> car = getCarById(id);
		if (car.isPresent()) {
			rep.deleteById(id);
		}
	}

}
