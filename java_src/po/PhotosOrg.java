package po;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author cslew
 * @version 0.1
 */
public class PhotosOrg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("You need to pass in the folder name and person/camera model!\nApp terminating...");
			System.exit(0);
		}
	
		String name = args[0];
		String personOrCameraModel = args[1];
	
        File imgDir = new File("img");
        String[] filesAndDirs = imgDir.list();

        for (String fd : filesAndDirs) {
            File f = new File("img//" + fd.toString());
            long lastMod = f.lastModified();
            Date d = new Date(lastMod);
            String dateInString = convertDateToString(d);
			String folderName = dateInString + "-" + name + "-[" + personOrCameraModel + "]" ;
            createNewDir(folderName);
            CopyFile.copyfile("img//" + fd, "img//" + folderName + "//" + fd);
        }
    }

    public static String convertDateToString(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(d);
    }

    public static void createNewDir(String dirName) {
        File newDir = new File("img//" + dirName);

        if (!newDir.exists()) {
            newDir.mkdir();
        }
    }

    public static boolean copyToFolder(File f) {
        return true;
    }
}
