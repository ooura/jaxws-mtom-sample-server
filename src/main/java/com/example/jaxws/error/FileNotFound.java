package com.example.jaxws.error;

public class FileNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public FileNotFound(String path) {
		super(path + " is not found.");
	}
}
