package com.example.rest.angular;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.dto.login.Login;
import com.example.dto.login.LoginBackend;
import com.example.dto.login.Token;
import com.example.exception.ResourceNotFoundException;

@RestController
public class LoginBackendController {

	@Value("${rest.url}")
    private String restUrl;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public Token loginProcess(@RequestBody Login login, Model model) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(login);
        
        LoginBackend loginBackend = new LoginBackend();
        loginBackend.setUsername(login.getLogin());
        loginBackend.setPassword(login.getPassword());
        loginBackend.setStoreId(login.getStore());
        System.out.println(loginBackend);
        
        String url = restUrl + "/v1/auth";
        
        Token token;
        try {
        	token = restTemplate.postForObject(url, loginBackend, Token.class);
        	System.out.println(token);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException("Bad Credentials");
		}

        return token;
    }
}
