package org.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeCtrl {

	@RequestMapping(value="/homePage", method = RequestMethod.GET )
	public String showHome( HttpSession session) {
	
		System.out.println("in get method");
/*		Facebook facebook = new FacebookTemplate();
		
		FacebookProfile profile = facebook.userOperations().getUserProfile();
		System.out.println(profile.getFirstName());*/
		return  "home/home";
	}
	
}
