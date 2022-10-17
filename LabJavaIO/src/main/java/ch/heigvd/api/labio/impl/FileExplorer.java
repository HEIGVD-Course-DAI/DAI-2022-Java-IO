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

        File[] list = rootDirectory.listFiles();

        if(list == null) {
            return;
        }

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                explore(f);
            }
            else {
                transformer.transform(f);
            }
        }

    }
}