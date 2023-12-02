package ru.netology.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.dto.FileResponse;
import ru.netology.entities.File;
import ru.netology.repository.StorageFileRepository;
import ru.netology.jwt.JwtTokenUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StorageFileService {
    private final StorageFileRepository storageFileRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public StorageFileService(StorageFileRepository storageFileRepository, JwtTokenUtil jwtTokenUtil) {
        this.storageFileRepository = storageFileRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public List<FileResponse> getFiles(String authToken, int limit) {
        String owner = jwtTokenUtil.getUsernameFromToken(authToken.substring(7));
        Optional<List<File>> fileList = storageFileRepository.findAllByOwner(owner);
        return fileList.get().stream().map(fr -> new FileResponse(fr.getFilename(), fr.getSize()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void uploadFile(String authToken, String filename, MultipartFile file) throws IOException {
        String owner = jwtTokenUtil.getUsernameFromToken(authToken.substring(7));
        storageFileRepository.save(new File(filename, file.getContentType(), file.getSize(), file.getBytes(), owner));
    }

    public void deleteFile(String authToken, String filename) {
        String owner = jwtTokenUtil.getUsernameFromToken(authToken.substring(7));
        storageFileRepository.removeByFilenameAndOwner(filename, owner);
    }

    public File downloadFile(String authToken, String filename) {
        String owner = jwtTokenUtil.getUsernameFromToken(authToken.substring(7));
        return storageFileRepository.findByFilenameAndOwner(filename, owner);
    }

    public void renameFile(String authToken, String filename, String newFilename) {
        String owner = jwtTokenUtil.getUsernameFromToken(authToken.substring(7));
        storageFileRepository.renameFile(filename, newFilename, owner);
    }
}