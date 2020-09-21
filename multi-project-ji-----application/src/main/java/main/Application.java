package main;

import entities.Box;
import entities.Car;
import mobile.Iphone;

public class Application {

	public static void main(String[] args) {
		

		Box b = new Box();
		
		System.out.println("Application Running!");
		System.out.println(b);
		
		Iphone i = new Iphone();
		System.out.println(i);
		
		Car c = new Car();
		System.out.println(c);

	}

}
