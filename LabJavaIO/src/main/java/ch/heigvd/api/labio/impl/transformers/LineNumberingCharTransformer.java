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
  int lineNumber = 1;
  int characterNumber = 0;
  public String transform(String c) {
    char character = c.charAt(0);

    if (character == '\r') {
      c = "";
    } else if (character == '\n') {
      if(characterNumber == 0) {
        c = lineNumber++ + ". " + c;
        c += lineNumber++ + ". ";
      } else {
        c = "\n"+ lineNumber++ +". ";
      }
    } else if (characterNumber == 0) {
      c = lineNumber++ + ". " + c;
    }
    ++characterNumber;
    return c;
  }
}