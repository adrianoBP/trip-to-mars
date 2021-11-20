package Helpers.Standard;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileHelper {

    /**
     * Returns the content of a file indicated - If the file does not exist, it will be created
     * @param path Path of the file to get
     * @param initContent Content of the file to create if the file does not exist
     */
    public static String getOrCreate(String path, String initContent) throws IOException {

        Path filePath = Paths.get(path);

        // Make sure file exist
        if (!Files.exists(filePath))
            if (filePath.toFile().createNewFile()) // If it doesn't exist, create it
                Files.write(filePath, initContent.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        return Files.readString(filePath);
    }

    public static void save(String path, String content) throws IOException {

        Path filePath = Paths.get(path);

        if (Files.exists(filePath))
            Files.writeString(filePath, content, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
    }
}
