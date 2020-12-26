package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.ifomkin.diplom.dto.security.request.LoginsRequest;
import ru.skillbox.ifomkin.diplom.dto.security.response.LoginResponse;
import ru.skillbox.ifomkin.diplom.dto.security.response.builder.LoginResponseFactory;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.service.AuthService;
import ru.skillbox.ifomkin.diplom.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("api/auth")
public class ApiAuthController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public ApiAuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginsRequest loginsRequest) {
        User user = userService.isExists(loginsRequest.getEMail()) ?
                userService.findByEmail(loginsRequest.getEMail()) :
                null;

        Authentication auth = authService.authenticate(loginsRequest.getEMail(), loginsRequest.getPassword());

        return ResponseEntity.status(HttpStatus.OK)
                .body(LoginResponseFactory.getLoginResponse(auth.isAuthenticated(), user));
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkAuth(Principal principal) {
        boolean authorized = authService.checkAuthorisation(principal);
        return ResponseEntity.ok(
                LoginResponseFactory.getLoginResponse(
                        authorized,
                        authorized ? userService.findByEmail(principal.getName()) : null
                ));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return ResponseEntity.ok(LoginResponseFactory.getLoginResponse(true, null));
    }
}
