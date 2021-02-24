package com.example.petridelivery.wrappers.base.abs;

public class File {
    private String fileName;

    public File(String fileName) {
        setFileName(fileName);
    }

    public void setFileName(String fileName) {
        if(fileName.contains("/")){
            throw new IllegalArgumentException("file cannot have forward slash");
        }
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return fileName;
    }
}
