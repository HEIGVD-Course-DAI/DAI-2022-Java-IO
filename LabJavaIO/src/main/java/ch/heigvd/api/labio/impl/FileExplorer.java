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
        FileTransformer transformer = new FileTransformer();
        if (rootDirectory.isDirectory()) {
            for (File child : Objects.requireNonNull(rootDirectory.listFiles())) {
                if (child.isDirectory()) explore(child);
                if (child.isFile()) transformer.transform(child);
            }
        }
        if (rootDirectory.isFile()) {
            transformer.transform(rootDirectory);
        }

    }
}