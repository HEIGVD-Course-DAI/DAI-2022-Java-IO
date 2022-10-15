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
    FileTransformer ft = new FileTransformer();

    public void explore(File rootDirectory) {
        if (rootDirectory.exists()) {
            File[] files = rootDirectory.listFiles();
            if (files != null){
                for (File file : files) {
                    if (file.isFile())
                        ft.transform(file);
                    else
                        explore(file);
                }
            }
        }
    }
}
