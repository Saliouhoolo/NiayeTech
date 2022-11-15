package sn.niayetch.niayetech.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.niayetch.niayetech.config.JwtTokenUtil;
import sn.niayetch.niayetech.entity.AuthRequest;
import sn.niayetch.niayetech.entity.AuthResponse;
import sn.niayetch.niayetech.entity.User;
import sn.niayetch.niayetech.entity.dto.UserDto;
import sn.niayetch.niayetech.exception.ResourceNotFoundException;
import sn.niayetch.niayetech.service.impl.UserServiceImpl;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.Map;

/**
 *  - POST /api/auth/login
 *
 * 	- POST /api/auth/register
 *
 * 	- GET /api/auth/reset/{email}
 */
@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private AuthenticationManager authManager;
    private JwtTokenUtil jwtUtil;
    private UserServiceImpl userService;

    public AuthController(AuthenticationManager authManager, JwtTokenUtil jwtUtil, UserServiceImpl userService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);

                UserDto userDto = new UserDto(user.getId(),user.getPrenom(),user.getNom(),user.getEmail(),user.getAuthorities().toString());
                AuthResponse response = new AuthResponse(userDto, accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public  Map<String ,String>  register(@RequestBody User user) throws ValidationException {
        return userService.createUser(user);
    }
    @GetMapping("reset/{email}")
    public Map<String ,String> reset(@PathVariable(value="email") String email) throws ResourceNotFoundException {
        return userService.resetPassword(email);
    }
}
