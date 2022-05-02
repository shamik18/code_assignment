package com.mycomp.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FIleUtils {
    private static final String CSV = "csv";
    private FIleUtils(){ }

    public static boolean isValidFile(String fileName){
        if(StringUtils.blankOrEmpty(fileName))
            return false;
        Path path = Path.of(fileName);

        if(!Files.exists(path) || !Files.isRegularFile(path) || !Files.isReadable(path) || Files.isDirectory(path))
            return false;

        boolean validExt ;
        try {
            String contentType = Files.probeContentType(path);
            validExt = contentType != null && contentType.contains(CSV);
        } catch (IOException e) {
            String extension = getFileExtension(path);
            validExt = extension.equalsIgnoreCase(CSV);
        }
        return validExt;
    }

    private static String getFileExtension(Path path) {
        String fileName = path.toFile().getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

}
