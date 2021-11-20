package com.up2037954.triptomars.Helpers.Standard;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {

    /**
     * Returns the content of a file indicated - If the file does not exist, it will be created
     *
     * @param path        Path of the file to get
     * @param initContent Content of the file to create if the file does not exist
     */
    public static String getOrCreate(String path, String initContent, Context context) throws IOException {

        File file = new File(context.getFilesDir(), path);

        // Make sure file exist
        if (!file.exists())
            if (file.createNewFile()) // If it doesn't exist, create it
                save(path, initContent, context);

        int length = (int) file.length();

        byte[] bytes = new byte[length];

        try (FileInputStream in = new FileInputStream(file)) {
            in.read(bytes);
        }
        return new String(bytes);
    }

    public static void save(String path, String content, Context context) throws IOException {

        File file = new File(context.getFilesDir(), path);

        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write(content.getBytes());
        }
    }
}
