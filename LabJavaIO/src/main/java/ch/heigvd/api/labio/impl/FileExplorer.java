package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The FileExplorer performs an exploration of the file system. It
 * visits the files and directory in alphabetic order.
 * When the explorer sees a directory, it recursively explores the directory.
 * When the explorer sees a file, it invokes the transformation on it.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class FileExplorer {

    public void explore(File rootDirectory) {
        FileTransformer transformer = new FileTransformer();

        /* TODO: implement the logic to explore the rootDirectory.
         *  Use the Java JDK documentation to see:
         *  - how to get the files and directories of rootDirectory (which is of class File)
         *  - to sort the items (files and directories) alphabetically
         *  - to check if an item is a file or a directory
         *  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         */
        List<File> listOfFolder = new ArrayList<File>();
        listOfFolder.add(rootDirectory);

        //Iterate in the root folder and all added folder
        for (int i = 0; i < listOfFolder.size(); ++i) {
            File[] files = listOfFolder.get(i).listFiles();
            if (files == null) {
                continue;
            }

            Arrays.sort(files);
            for (File file : files) {
                System.out.println(file.getName());
                if (file.isFile()) {
                    transformer.transform(file);
                } else {
                    listOfFolder.add(file);
                }
            }
        }
    }
}