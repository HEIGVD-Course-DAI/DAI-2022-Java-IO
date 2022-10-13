package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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

    /* TODO: first start with the NoOpCharTransformer which does nothing.
     *  Later, replace it by a combination of the UpperCaseCharTransformer
     *  and the LineNumberCharTransformer.
     */
    // ... transformer = ...

    /*
    Line by line could have been done with that (notes from online research):
    https://www.baeldung.com/java-char-encoding
    https://docs.oracle.com/javase/7/docs/api/java/nio/charset/Charset.html
    https://stackoverflow.com/questions/1006276/what-is-the-default-encoding-of-the-jvm
    https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
    https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
     */

    /*
    Charset encoding = Charset.forName("UTF-8");
    Path filePath = Path.of("c:/temp/demo.txt");
    try (Stream<String> lines = Files.lines(inputFile.getAbsolutePath(), encoding)) {
      lines.forEach(System.out::println);
    }

    String test[] = Files.lines(inputFile.getAbsolutePath());*/

    /* TODO: implement the following logic here:
     *  - open the inputFile and an outputFile
     *    Use UTF-8 encoding for both.
     *    Filename of the output file: <inputFile-Name>.out (that is add ".out" at the end)
     *  - Copy all characters from the input file to the output file.
     *  - For each character, apply a transformation: start with NoOpCharTransformer,
     *    then later replace it with a combination of UpperCaseFCharTransformer and LineNumberCharTransformer.
     */
    try {

      FileReader fr = new FileReader(inputFile, StandardCharsets.UTF_8);
      FileWriter fw = new FileWriter(inputFile.getPath() + ".out", StandardCharsets.UTF_8);
      LineNumberingCharTransformer lnct = new LineNumberingCharTransformer();
      UpperCaseCharTransformer ucct = new UpperCaseCharTransformer();

      int c = fr.read();

      while ( c != -1 ) {
        fw.write(lnct.transform(ucct.transform(Character.toString(c))));
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