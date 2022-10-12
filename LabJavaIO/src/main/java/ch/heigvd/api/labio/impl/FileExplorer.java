package ch.heigvd.api.labio.impl;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

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
        File root = new File(".");

        String[] extensions = {"utf8"};
        Collection<File> files = FileUtils.listFiles(root, extensions, true);

        for (File file : files) {
            transformer.transform(file);
        }
    }
}