package mobile;

import instruments.AddConstructor;

@AddConstructor
public class Iphone {

	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Iphone [id=" + id + ", name=" + name + "]";
	}

	
	
}
