package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentsRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentsRepository studentsRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentsRepository studentsRepository) {
        this.avatarRepository = avatarRepository;
        this.studentsRepository = studentsRepository;
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

        Avatar avatar = new Avatar();
        avatar.setStudent(studentsRepository.getReferenceById(studentId));
        avatar.setFilePath(absolutPath);
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);

        return avatar.getId();



    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
