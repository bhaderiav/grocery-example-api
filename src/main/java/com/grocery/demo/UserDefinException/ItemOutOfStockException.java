package com.grocery.demo.UserDefinException;

public class ItemOutOfStockException extends RuntimeException {

	 public ItemOutOfStockException(String message) {
	        super(message);
	    }
}
