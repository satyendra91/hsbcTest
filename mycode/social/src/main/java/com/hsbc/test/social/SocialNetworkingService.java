package com.hsbc.test.social;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Satyendra
 * 
 * This is our service class it has all the business logic for different endpoints.
 *
 */
@Component
public class SocialNetworkingService {

	private User user = null;

	@Autowired
	private SocialNetworkingDAO dao;

	/**
	 * This method is used to store the user post in inMemoryclass
	 * SocialNetworkingDAO variable allUsers's user object. If the length of message
	 * is greater than 140 then it will throw Exception
	 * 
	 * @param post   , which need to be posted
	 * @param userId , user who is posting message
	 * @return successful message or exception if length of message is greater than 140 charcters
	 */
	public String postOnWall(String post, String userId) {

		if (post.length() > 140) {

			throw new MaximumLenghException("Lengh of message can't be greater than 140 characters");
		}

		long currentTimeMillis = System.currentTimeMillis();

		Post newPost = new Post(post, currentTimeMillis);

		if (dao.allUsers.containsKey(userId)) {
			user = dao.allUsers.get(userId);
			user.addPost(newPost);
		} else {
			ArrayList posts = new ArrayList<Post>();
			posts.add(newPost);

			user = new User(userId, posts, null);

			dao.addNewUser(user);
		}
		//return dao.allUsers.get(userId);
		return "message has posted succesfully";

	}

	/**
	 * This method is used to show all the post which user has done in reverse
	 * chronicle order Logic behind this code is we have user object is present in
	 * our DAO static object allUsers and it get updated whenever user post any
	 * message, so we took that object and put all message in LIFO format
	 * 
	 * @param userId
	 * @return list of post in LIFO order , exception if user not found
	 */
	public List<String> showWall(String userId) {

		if (!dao.allUsers.containsKey(userId)) {
			throw new UserNotFountException(userId);
		} else {
			user = dao.allUsers.get(userId);
			List<String> posts = new ArrayList<String>();
			for (int i = user.getPosts().size() - 1; i >= 0; i--) {
				posts.add(user.getPosts().get(i).getPost());
			}

			return posts;
		}
	}

	/**
	 * @param follow , userId of user which need to be followed by userId
	 * @param userId
	 * @return a success message if follow has done successfully, exception if user
	 *         or follower user not found.
	 */
	public String followUser(String follow, String userId) {
		if (!dao.allUsers.containsKey(userId) || !dao.allUsers.containsKey(follow)) {
			throw new UserNotFountException(userId);
		} else {
			user = dao.allUsers.get(userId);
			user.addToFollow(dao.allUsers.get(follow));
			return "now you are following " + follow;
		}
	}

	/**
	 * This method will show you posts of all the followers. Logic behind the
	 * method, we check users all followers and put it on list and to sort it in
	 * reverse chronicle order we have store all post from list to TreeMap. And Key
	 * we have used as postTimeinMillis ( it has time of storing post in long).
	 * 
	 * @param userId
	 * @return List of messages or Exception if user not found
	 */
	public List<String> showTimeLine(String userId) {

		if (!dao.allUsers.containsKey(userId)) {
			throw new UserNotFountException(userId);
		} else {
			user = dao.allUsers.get(userId);
			List<User> follows = user.getFollows();
			TreeMap<Long, String> map = new TreeMap<Long, String>(Collections.reverseOrder());
			for (User fUser : follows) {
				List<Post> posts = fUser.getPosts();
				for (Post post : posts) {
					map.put(post.getPostTimeinMillis(), post.getPost());
				}
			}
			ArrayList<String> list = new ArrayList<String>();

			for (Map.Entry<Long, String> m : map.entrySet()) {

				list.add(m.getValue());

			}

			return list;
		}

	}

}
