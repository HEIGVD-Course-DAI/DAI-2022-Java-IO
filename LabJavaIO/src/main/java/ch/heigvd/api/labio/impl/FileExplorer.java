package ch.heigvd.api.labio.impl;

import java.io.File;

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

        // Check if the file we were given actually exists
        if(!rootDirectory.exists()) {
            return;
        }

        File[] fileList =  rootDirectory.listFiles();
        if(fileList == null)
            return;

        for (File f : fileList) {
            if(f.isFile()) {
                transformer.transform(f);
            }
            else {
                explore(f);
            }
        }
    }
}