package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.service.AvatarService;

import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> uploadAvatar(@RequestParam Long studentId, @RequestBody MultipartFile avatarFile) {
       try {
           Long avatarId = avatarService.uploadAvatar(studentId, avatarFile);
           return ResponseEntity.ok(avatarId);
       }catch (IOException e){
           return ResponseEntity.badRequest().build();
       }

    }


}
