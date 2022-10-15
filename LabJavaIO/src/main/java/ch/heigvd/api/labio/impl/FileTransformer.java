package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.nio.charset.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger
 */
public class FileTransformer {
    private static final String EXT = ".out";
    private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());

    public void transform(File file) {
        Charset utf8 = StandardCharsets.UTF_8;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), utf8));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file.getPath() + EXT), utf8));

            UpperCaseCharTransformer ucTrans = new UpperCaseCharTransformer();
            LineNumberingCharTransformer lnTrans = new LineNumberingCharTransformer();

            int c;
            while ((c = reader.read()) != -1) {
                String newC = ucTrans.transform(Character.toString((char)c));
                writer.write(lnTrans.transform(newC));
            }

            reader.close();
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading, writing or transforming " +
                    "file.", ex);
        }
    }
}