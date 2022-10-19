package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.Objects;
import java.io.*;

/**
 * The FileExplorer performs an exploration of the file system. It
 * visits the files and directory in alphabetic order.
 * When the explorer sees a directory, it recursively explores the directory.
 * When the explorer sees a file, it invokes the transformation on it.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class FileExplorer {

    public void explore(File rootDirectory) throws IOException {

        FileTransformer trf = new FileTransformer();

        // TO DO: File transformer
        // To check if the root directory is a directory
        if(rootDirectory.isDirectory()){
            // To check every child if there are subdir or if this a file
            for (File subDir: Objects.requireNonNull(rootDirectory.listFiles())){
                // Explore subdirectory
                if(subDir.isDirectory()) explore(subDir);
                // Transform subdirectory
                if(subDir.isFile()) trf.transform(subDir);
            }
        }
        if(rootDirectory.isFile()) trf.transform(rootDirectory);
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    }
}