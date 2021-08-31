package com.CarsApplication.domain.dto;

import org.modelmapper.ModelMapper;

import com.CarsApplication.domain.Car;

public class CarDTO {

		private Long id;
		private String name;
		private String tipo;
		
		public static CarDTO create (Car c) {
			ModelMapper modelMapper = new ModelMapper();
			return modelMapper.map(c, CarDTO.class);
		}
		
		@Override
		public String toString() {
			return "CarDTO [id=" + id + ", name=" + name + ", tipo=" + tipo + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CarDTO other = (CarDTO) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (tipo == null) {
				if (other.tipo != null)
					return false;
			} else if (!tipo.equals(other.tipo))
				return false;
			return true;
		
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		
}