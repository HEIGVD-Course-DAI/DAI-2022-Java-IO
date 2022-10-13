package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    try {
      FileInputStream fis = new FileInputStream(inputFile);
      InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
      FileOutputStream fos = new FileOutputStream(inputFile.getPath() + ".out");
      OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
      UpperCaseCharTransformer u = new UpperCaseCharTransformer();
      LineNumberingCharTransformer l = new LineNumberingCharTransformer();
      while (isr.ready()) {
        String c = String.valueOf((char) isr.read());
        c = u.transform(c);
        c = l.transform(c);
        osw.write(c);
      }
      isr.close();
      fis.close();
      osw.close();
      fos.close();
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}