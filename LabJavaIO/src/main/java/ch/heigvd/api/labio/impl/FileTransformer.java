package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
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

    // ... transformer = ...
    try {
      String filePath = inputFile.getPath();
      File output = new File(filePath + ".out");
      FileReader fr = new FileReader(inputFile, StandardCharsets.UTF_8);
      FileWriter fw = new FileWriter(output, StandardCharsets.UTF_8);
      int c = fr.read();
      UpperCaseCharTransformer transformer = new UpperCaseCharTransformer();
      LineNumberingCharTransformer secondtransformer = new LineNumberingCharTransformer();
      while (c != -1){
        String s = transformer.transform(Character.toString(c));
        s = secondtransformer.transform(s);
        fw.write(s);
        c = fr.read();
      }
      fw.flush();
      fw.close();
      fr.close();
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}