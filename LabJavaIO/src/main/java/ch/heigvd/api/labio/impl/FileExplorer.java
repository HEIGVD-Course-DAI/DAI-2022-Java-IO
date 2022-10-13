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

        if(!rootDirectory.exists()) return;

        // Transforme if it's a file
        if(rootDirectory.isFile()) {
            transformer.transform(rootDirectory);
            return;
        }

        // Else explore
        File[] contents = rootDirectory.listFiles();
        if(contents == null) return;
        for (File content : contents) {
            explore(content);
        }

    }
}