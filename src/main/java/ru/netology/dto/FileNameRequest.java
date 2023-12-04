package ru.netology.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class FileNameRequest {

    public String getFilename() {
        return filename;
    }

    private String filename;

    @JsonCreator
    public FileNameRequest(String filename) {
        this.filename = filename;
    }
}
