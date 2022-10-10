package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.Objects;

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
        if (rootDirectory.exists()) {
            FileTransformer transformer = new FileTransformer();
            getNextLevel(Objects.requireNonNull(rootDirectory.listFiles()), transformer);
        }



        /* TODO: implement the logic to explore the rootDirectory.
         *  Use the Java JDK documentation to see:
         *  - how to get the files and directories of rootDirectory (which is of class File)
         *  - to sort the items (files and directories) alphabetically
         *  - to check if an item is a file or a directory
         *  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         */
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    }

    /**
     * Recursive function that goes down the directory/files tree. Check all files/directory in the same level and
     * call the function again if it's a directory and apply transformers if it's a file.
     * @param files all file and directory in current level
     * @param transformer transformer to apply to all files
     */
    private void getNextLevel(File[] files,FileTransformer transformer){
        for (File file : files){
            if(file.isDirectory()){
                getNextLevel(Objects.requireNonNull(file.listFiles()),transformer);
            }else{
                transformer.transform(file);
            }
        }
    }
}