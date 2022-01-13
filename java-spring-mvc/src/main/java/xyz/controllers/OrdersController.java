package xyz.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

	@GetMapping("/orders")
	public String index(HttpServletRequest request) {
		
		request.setAttribute("message", "Hello World");
		
		return "orders/index";
	}
}
