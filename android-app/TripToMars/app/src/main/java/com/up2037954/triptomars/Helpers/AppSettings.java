package com.up2037954.triptomars.Helpers;

import android.content.Context;
import android.content.res.Resources;

import com.up2037954.triptomars.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppSettings {

    public static String nodesFilePath;
    public static String usersFilePath;

    public static void init(Context context) throws IOException {

        Resources resources = context.getResources();

        InputStream rawResource = resources.openRawResource(R.raw.app);
        Properties properties = new Properties();
        properties.load(rawResource);

        nodesFilePath = properties.getProperty("filesystem.path.nodes");
        usersFilePath = properties.getProperty("filesystem.path.users");
    }
}
