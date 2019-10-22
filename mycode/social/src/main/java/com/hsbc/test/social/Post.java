package com.hsbc.test.social;

import java.sql.Timestamp;

import javax.validation.Valid;

public class Post {

	
	private String post;
	private long postTimeinMillis;
	
	public Post(String post, long postTimeinMillis) {
		super();
		this.post = post;
		this.postTimeinMillis = postTimeinMillis;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public long getPostTimeinMillis() {
		return postTimeinMillis;
	}
	public void setPostTimeinMillis(long postTimeinMillis) {
		this.postTimeinMillis = postTimeinMillis;
	}
	
}
