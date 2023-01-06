package com.neco.es;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	public static void main (String[] args) {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String rawPAssword = "admin";
	String encodedPassword = encoder.encode(rawPAssword);
	
	System.out.println(encodedPassword);
	
	}
	
	
}
