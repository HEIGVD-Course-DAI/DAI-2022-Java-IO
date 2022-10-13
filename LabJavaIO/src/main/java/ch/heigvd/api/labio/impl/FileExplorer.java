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
        File temp[] = rootDirectory.listFiles();
        if (temp != null) {
            Arrays.sort(temp);
            for (File currentfile : temp) {
                if (currentfile.isFile()) {
                    transformer.transform(currentfile);
                } else if (currentfile.isDirectory()) {
                    explore(currentfile);
                }
            }
        }

        // throw new UnsupportedOperationException("The student has not implemented this method yet.");

    }
}