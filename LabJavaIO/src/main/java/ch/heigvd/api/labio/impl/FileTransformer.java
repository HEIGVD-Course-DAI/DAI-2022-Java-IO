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
    try{
      InputStreamReader input = new FileReader(inputFile, StandardCharsets.UTF_8);
      OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(inputFile + ".out"), StandardCharsets.UTF_8);
      int i=0;
      UpperCaseCharTransformer ucTransform = new UpperCaseCharTransformer();
      LineNumberingCharTransformer lnTransform = new LineNumberingCharTransformer();
      while((i=input.read())!=-1){
        String firstTransform = ucTransform.transform(((char)i) + "");
        String secondTransform = lnTransform.transform(firstTransform);
        output.write(secondTransform);
      }
      output.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}