package com.CarsApplication.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.CarsApplication.domain.dto.CarDTO;

public interface CarRepository extends JpaRepository<Car, Long> {

	List<Car> findByTipo(String tipo);

	void save(CarDTO db);

}
