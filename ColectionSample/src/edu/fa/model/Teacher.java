package edu.fa.model;

public class Teacher {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher(String name) {
		super();
		this.name = name;
	}

	public Teacher() {
		super();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	
}
