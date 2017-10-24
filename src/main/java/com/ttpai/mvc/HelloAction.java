package com.ttpai.mvc;

import javax.servlet.http.HttpServletRequest;

@Action
public class HelloAction {

	
	@Request
	public String test1(HttpServletRequest req,User user,String newPwd,int id){
		req.setAttribute("username", user.getUsername());
		req.setAttribute("password", user.getPassword());
		req.setAttribute("newPwd", newPwd);
		req.setAttribute("id", id);
		return "/a";
	}
	
	@Request
	public User test2(){
		User user = new User();
		user.setUsername("MMM");
		return user;
	}
	
	@Request
	public String test3(){
		
		return "@test3";
	}
	
}
