package sn.niayetch.niayetech.util;

import java.io.File;

public class FileDelete {
    public static String delete(String path){
        File file
                = new File(path);

        if (file.delete()) {
            return  "File deleted successfully";
        }
        else {
            return  "Failed to delete the file";
        }

    }
}
