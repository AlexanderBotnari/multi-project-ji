package entities;

import instruments.AddConstructor;

@AddConstructor
public class Car {

	private int id;
	private String model;
	
	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + "]";
	}
	
	
}
