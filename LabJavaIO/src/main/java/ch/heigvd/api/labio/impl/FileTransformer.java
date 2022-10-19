package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.*;
import org.glassfish.jersey.internal.inject.ReferenceTransformingFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger, Stéphane Nascimento Santos
 */
public class FileTransformer {
    private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());
    public void transform(File inputFile) {
        /*
         * This method opens the given inputFile and copies the
         * content to an output file.
         * The output file has a file name <inputFile-Name>.out, for example:
         *   quote-2.utf --> quote-2.utf.out
         * Both files must be opened (read or write) with encoding "UTF-8".
         * Before writing each character to the output file, the transformer calls
         * a character transformer to transform the character before writing it to the output.
         */

        LineNumberingCharTransformer lineNumberTr = new LineNumberingCharTransformer();
        UpperCaseCharTransformer upCaseTr = new UpperCaseCharTransformer();

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(inputFile), StandardCharsets.UTF_8));

            File outputFile = new File(inputFile.getPath() + ".out");
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8));

            while (br.ready()) {
                String currentChar = (char) br.read() + "";

                bw.write(
                        lineNumberTr.transform(
                                upCaseTr.transform(
                                        currentChar)));

                if (currentChar.equals("\n")) {
                    bw.write(lineNumberTr.transform(""));
                }
            }
            br.close();
            bw.flush();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /* TODO: implement the following logic here:
         *  - open the inputFile and an outputFile
         *    Use UTF-8 encoding for both.
         *    Filename of the output file: <inputFile-Name>.out (that is add ".out" at the end)
         *  - Copy all characters from the input file to the output file.
         *  - For each character, apply a transformation: start with NoOpCharTransformer,
         *    then later replace it with a combination of UpperCaseFCharTransformer and LineNumberCharTransformer.
         */
        try {

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
        }
    }
}