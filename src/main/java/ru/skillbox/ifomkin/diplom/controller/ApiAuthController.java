package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.ifomkin.diplom.dto.security.request.LoginsRequest;
import ru.skillbox.ifomkin.diplom.dto.security.response.LoginResponse;
import ru.skillbox.ifomkin.diplom.dto.security.response.builder.LoginResponseFactory;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.service.AuthService;
import ru.skillbox.ifomkin.diplom.service.UserService;

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
}
