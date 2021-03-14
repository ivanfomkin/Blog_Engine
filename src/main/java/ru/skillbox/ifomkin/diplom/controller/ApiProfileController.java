package ru.skillbox.ifomkin.diplom.controller;

import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.ifomkin.diplom.dto.pofile.request.ProfileEditRequest;
import ru.skillbox.ifomkin.diplom.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/api/profile")
public class ApiProfileController {
    private final UserService service;

    @Autowired
    public ApiProfileController(UserService service) {
        this.service = service;
    }

    @PostMapping("/my")
    public ResponseEntity<?> editProfile(@ModelAttribute ProfileEditRequest request, Principal principal) {
        return ResponseEntity.ok(service.editUser(request, principal));
    }
}
