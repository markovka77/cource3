package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    @Value("${path.to.avatars.folder}")
    private Path avatarsDir;



    public Long uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Files.createDirectories(avatarsDir);
        String originalFileName = avatarFile.getOriginalFilename();
        int dotIndex = originalFileName.lastIndexOf(".");
        String extension = originalFileName.substring(dotIndex);
        String fileName = studentId + extension;
        FileOutputStream fos = new FileOutputStream (avatarsDir.toAbsolutePath() + "/" + fileName);
        String absolutPath = avatarsDir.toAbsolutePath() + "/" + fileName;
        avatarFile.getInputStream().transferTo(fos);
        fos.close();



        Student studentReference= studentRepository.getReferenceById(studentId);
        Avatar avatar= avatarRepository.findAvatarByStudent(studentReference)
                .orElse(new Avatar());

        avatar.setStudent(studentRepository.getReferenceById(studentId));
        avatar.setFilePath(absolutPath);
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);

        return avatar.getId();



    }
    public Avatar getById(Long id){
        return avatarRepository.findById(id).orElseThrow();
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
