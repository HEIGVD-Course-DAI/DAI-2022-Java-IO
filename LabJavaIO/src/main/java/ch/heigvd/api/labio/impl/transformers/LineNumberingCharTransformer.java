package ch.heigvd.api.labio.impl.transformers;

import java.util.logging.Logger;

/**
 * This class applies a transformation to the input character (a string with a single character):
 *   1. Convert all line endings to Unix-style line endings, i.e. only '\n'.
 *   2. Add a line number at the beginning of each line.
 * Example:
 *   Using this character transformer, the following file :
 *      This is the first line.\r\n
 *      This is the second line.
 *   will be transformed to:
 *      1. This is the first line.\n
 *      2. This is the second line.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class LineNumberingCharTransformer {
  private static final Logger LOG = Logger.getLogger(LineNumberingCharTransformer.class.getName());
  private int lineNumber = 0;
  private boolean newString = true;
  public String transform(String c) {
    /* TODO: implement the transformation here.
     */
    StringBuilder res = new StringBuilder("");

    if(newString) {
      newString = false;
      res.append(++lineNumber).append(". ") ;
    }

    if(c.charAt(0) == '\r') {

    } else if (c.charAt(0) == '\n') {
      res.append(c);
      res.append(++lineNumber).append(". ") ;
    } else  if(c.charAt(0) == '\0') {
      lineNumber = 0;
      newString = true;
    } else {
      res.append(c);
    }

    return res.toString();
  }
}