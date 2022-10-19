package ch.heigvd.api.labio.impl;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;
import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
// Used to import UTF-8 Charset
import java.nio.charset.StandardCharsets;
import java.io.File;


/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger
 */
public class FileTransformer {
  private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());

  public void transform(File inputFile) throws IOException {
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
      // Create Input/Output Stream
      // Source: https://stackoverflow.com/questions/6698354/where-to-get-utf-8-string-literal-in-java
      InputStreamReader inputReader = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);
      //String extension = ".out";
      OutputStreamWriter outputWriter = new OutputStreamWriter(new FileOutputStream(inputFile+ ".out"), StandardCharsets.UTF_8);

      // Instantiate uppercasechar and linenumberingchar transformers
      UpperCaseCharTransformer uppercaseFileTransform = new UpperCaseCharTransformer();
      LineNumberingCharTransformer linenumberingFileTransform = new LineNumberingCharTransformer();

      // Read first character
      // Return -1 if the end has been reached
      int nchar = inputReader.read();
      while (nchar != -1) {
        String s = String.valueOf((char) nchar);
        nchar = inputReader.read();
        outputWriter.write(linenumberingFileTransform.transform(uppercaseFileTransform.transform(s)));
      }

      // Close input/output reader/writer
      outputWriter.close();
      inputReader.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}