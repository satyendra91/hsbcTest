package com.hsbc.test.social;

import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


 /**
 * @author Satyendra
 * 
 * This is our controller class, for each endpoint flow comes to this class.
 * It has done the mapping of different endpoints with respestive method
 *
 */
@RestController 
public class socialNetworkingController {

	@Autowired
	SocialNetworkingService service;

	@PostMapping("/socialNetworking/{userId}/post")
	public String post(@RequestBody String post, @PathVariable String userId) {

		return service.postOnWall(post, userId);

	}

	@GetMapping("/socialNetworking/{userId}/wall")
	public List<String> showWall(@PathVariable String userId) {

		return service.showWall(userId);
	}

	@PostMapping("/socialNetworking/{userId}/follow")
	public String followuser(@RequestBody String follow, @PathVariable String userId) {

		return service.followUser(follow, userId);

	}

	@GetMapping("/socialNetworking/{userId}/timeline")
	public List<String> showTimeline(@PathVariable String userId) {

		return service.showTimeLine(userId);
	}

}
