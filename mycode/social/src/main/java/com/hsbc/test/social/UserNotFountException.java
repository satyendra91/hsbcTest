package com.hsbc.test.social;

public class UserNotFountException extends RuntimeException {

	public UserNotFountException(String userId) {
		super("User ---> " + userId + " not found");
	}

}
