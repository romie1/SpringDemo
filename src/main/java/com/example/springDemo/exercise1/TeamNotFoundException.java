package com.example.springDemo.exercise1;

public class TeamNotFoundException extends RuntimeException{
	 private static final long serialVersionUID = 1L;

	public TeamNotFoundException(Long id) {
		super("Team " + id + " not found");
	}
	
	public TeamNotFoundException(String name) {
		super("Team " + name + " not found");
	}
	 
	 
	 
}
