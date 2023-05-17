package com.exceptions;

public class UnableToLoadDvdsException extends RuntimeException {

	public String toString() {
		return "Unable to read DVDs from database.";
	}
	
}
