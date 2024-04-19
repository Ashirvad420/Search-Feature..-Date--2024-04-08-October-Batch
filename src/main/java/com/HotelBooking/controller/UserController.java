package com.HotelBooking.controller;


import com.HotelBooking.dto.JWTResponse;
import com.HotelBooking.dto.LoginDto;
import com.HotelBooking.dto.PropertyUserDto;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // http://localhost:8080/api/v1/users/addUser
    @PostMapping("/addUser")  // this is for SignUp
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto dto)
    {
       PropertyUser user = userService.addUser(dto);
       if (user != null)
       {
           return new ResponseEntity<>("sign up successfully", HttpStatus.CREATED);
       }
       return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @PostMapping("/login")  // this is for SignIn
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto)
    {

        // JWT Token
        String jwtToken = userService.verifyLogin(loginDto);

        if (jwtToken!=null)
        {
            JWTResponse jwtResponse = new JWTResponse();
            jwtResponse.setToken(jwtToken);
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }



    // how to get users details from jwt token
    @GetMapping("/profile")
    public PropertyUser getCurrentProfile(@AuthenticationPrincipal PropertyUser propertyUser)
    {
        return propertyUser;
    }


}


// hum postmaping ko replace kr ke " @RequestMapping(name = "/addUser", method = RequestMethod.POST) " de skte hai