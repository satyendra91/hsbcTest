package com.hsbc.test.social;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Component;

@Component
public class User {

	private String userName;
	private List<Post> posts;
	private List<User> follows;

	public User() {

	}

	public User(String userName, List<Post> posts, List<User> follows) {
		super();
		this.userName = userName;
		this.posts = posts;
		this.follows = follows;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<User> getFollows() {
		return follows;
	}

	public void setFollows(List<User> follows) {
		this.follows = follows;
	}

	public void addPost(Post post) {

		posts.add(post);
	}

	public void addToFollow(User user) {

		if (follows == null) {
			follows = new ArrayList<User>();
		}
		follows.add(user);
	}

}
