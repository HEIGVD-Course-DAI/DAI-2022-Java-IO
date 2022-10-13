package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.Arrays;

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
        //get files and directories of rootDirectory
        File[] item = rootDirectory.listFiles();
        //sort the items alphabetically
        if(item != null) {
            Arrays.sort(item);
            //check if an item is a file or a directory
            for (File file : item) {
                if (file.isFile()) {
                    transformer.transform(file);
                } else {
                    explore(file);
                }
            }
        }




    }
}