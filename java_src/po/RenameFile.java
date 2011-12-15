package po;

import java.io.*;

/**
 *
 * @author cslew
 * @version 0.1 
*/
public class RenameFile {

    public static void main(String[] args) {
        File imgDir = new File("img");
        String[] filesAndDirs = imgDir.list();

        for (String fd : filesAndDirs) {

            File f = new File("img//" + fd);

            if (f.isDirectory()) {
                renameFilesInDir(f, fd);
            }
        }
    }

    public static void renameFilesInDir(File dir, String dirDay) {
        System.out.println("Currently: " + dir.toString());

        String[] images = dir.list();

        int counter = 1;

        for (String img : images) {


            File oldF = new File(dir.toString() + "//" + img);

            String fileExt = getFileExt(oldF.toString());

            File newF = new File(dir.toString() + "//" + dirDay + "-" + convertToThree(counter) + "." + fileExt);

            System.out.println("Current image: " + oldF.toString());
            System.out.println("New file name: " + dir.toString() + "//" + dirDay + "-" + convertToThree(counter) + "." + fileExt);

            // Rename file (or directory)
            boolean success = oldF.renameTo(newF);
            if (!success) {
                System.out.println("File was not successfully renamed");
            } else {
                System.out.println("File was successfully renamed");
            }

            counter++;
        }
    }

    public static String convertToThree(int value) {
        String strValue = String.valueOf(value);

        if (value < 10) {
            return "00" + strValue;
        } else if (value >= 10 && value < 100) {
            return "0" + strValue;
        }

        return "err";
    }

    public static String getFileExt(String filename) {
        String fname = "";
        String ext = "";
        int mid = filename.lastIndexOf(".");

        fname = filename.substring(0, mid);
        ext = filename.substring(mid + 1, filename.length());

        return ext;
    }
}
