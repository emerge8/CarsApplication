package com.CarsApplication.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.CarsApplication.domain.dto.CarDTO;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarService {

	@Autowired
	private CarRepository rep;

	public List<CarDTO> getCars() {
		return rep.findAll().stream().map(CarDTO::create).collect(Collectors.toList());
	}

	public CarDTO getCarById(Long id) throws ObjectNotFoundException {
		Optional<Car> car = rep.findById(id);
		return car.map(CarDTO:: create).orElseThrow(()-> new ObjectNotFoundException("The car was not found"));
	}

	public List<CarDTO> getCarsByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarDTO::create).collect(Collectors.toList());
	}

	public CarDTO insert(Car car) {
		Assert.isNull(car.getId(), " It was not possible find the register");

		return CarDTO.create(rep.save(car));
	}

	public CarDTO update(Car car, Long id) {
		Assert.notNull(id, "It was not possible to update");

		Optional<Car>optional = rep.findById(id);
		if (optional.isPresent()) {
			Car db = optional.get();
			
			db.setName(car.getName());
			db.setTipo(car.getTipo());
			System.out.println("Car id " + db.getId());

			rep.save(db);

			return CarDTO.create(db);
		} else {
			return null;
			//throw new RuntimeException("It was not possible to update");
		}

	}

	public void delete(Long id) {
			rep.deleteById(id);
	}
}
