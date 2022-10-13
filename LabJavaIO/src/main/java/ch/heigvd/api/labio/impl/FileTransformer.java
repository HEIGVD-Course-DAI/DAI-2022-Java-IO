package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.charset.StandardCharsets;

/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger
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
    // ... transformer = ...

    try {
      FileReader fr = new FileReader(inputFile, StandardCharsets.UTF_8);
      FileWriter fw = new FileWriter(inputFile.getAbsoluteFile() + ".out", StandardCharsets.UTF_8);
      BufferedReader br = new BufferedReader(fr);
      BufferedWriter bw = new BufferedWriter(fw);

      UpperCaseCharTransformer upTransf = new UpperCaseCharTransformer();
      LineNumberingCharTransformer linTransf = new LineNumberingCharTransformer();

      int b = br.read();
      while(b != -1) {
        bw.write(linTransf.transform(upTransf.transform(Character.toString(b))));
        b = br.read();
      }
      bw.flush();
      bw.close();

      fw.close();

      br.close();
      fr.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}