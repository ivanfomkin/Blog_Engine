package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping(value = "/my", consumes = "multipart/form-data")
    public ResponseEntity<?> editProfileWithPhoto(
            @ModelAttribute ProfileEditRequest requestWithPhoto,
            @RequestPart(required = false) MultipartFile photo,
            Principal principal) {
        return ResponseEntity.ok(service.editUser(requestWithPhoto, principal, photo));
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping(value = "/my", consumes = "application/json")
    public ResponseEntity<?> editProfile(
            @RequestBody ProfileEditRequest request,
            @RequestPart(required = false) String photo,
            Principal principal) {
        return ResponseEntity.ok(service.editUser(request, principal, null));
    }
}
