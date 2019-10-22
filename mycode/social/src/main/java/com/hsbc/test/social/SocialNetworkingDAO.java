package com.hsbc.test.social;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.springframework.stereotype.Component;


/**
 * @author Satyendra
 * This class servers as in memory database and it will store all the user informations
 *
 */
@Component
public class SocialNetworkingDAO {

	static HashMap<String, User> allUsers = new HashMap();
	static {

		allUsers.put("sunny", new User("sunny", new Stack<Post>(), new ArrayList<User>()));

	}

	public void addNewUser(User user) {

		allUsers.put(user.getUserName(), user);
	}
}
