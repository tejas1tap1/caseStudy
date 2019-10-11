package com.beehyv.shoppingcart.controller;



import com.beehyv.shoppingcart.dto.UserCredentialsDTO;
import com.beehyv.shoppingcart.entity.UserCredentials;
import com.beehyv.shoppingcart.mapper.UserCredentialsMapper;
import com.beehyv.shoppingcart.services.UserCredentialsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserCredentialsController {
 @Autowired
 private UserCredentialsServices userCredentialsServices;
	@GetMapping("/login")
	public Boolean login()
	{

		return true;
	}
//	@PostMapping("/login-user")
//	public ResponseEntity<?> Login(UserCredentials userCredentials) {
//		//return userCredentialsServices.Login(userCredentials);
//
//	}

	@PostMapping("/add-user")
	public ResponseEntity<?> SignUp(UserCredentialsDTO userCredentialsDTO) {
		return  userCredentialsServices.SignUp(UserCredentialsMapper.INSTANCE.toUserCredentials(userCredentialsDTO));
	}
//	@PostMapping("/logout-user")
//     public ResponseEntity<String> Logout() {
//		return null;
//	}

}
