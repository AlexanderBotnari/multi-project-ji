package entities;

import instruments.AddConstructor;

@AddConstructor
public class Box {

	private int id;

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Box [id=" + id + "]";
	}
	
	
}
