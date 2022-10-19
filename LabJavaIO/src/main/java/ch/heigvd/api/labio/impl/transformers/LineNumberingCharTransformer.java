package ch.heigvd.api.labio.impl.transformers;

import java.util.Objects;
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
  private int counter = 1;

  public String transform(String c) {
    // 0. First we have to check if its the first char
    String copie = c;
    if(counter == 1) {
      StringBuilder sb = new StringBuilder(c);
      sb.insert(0, counter++ + ". ");
      c = sb.toString();
    }

    // 1. If the char is a \r we remove it
    if(Objects.equals(copie, "\r"))
    {
      c = "";
    }

    // 2. If the char is a \n we have to add the line number
    if(Objects.equals(copie, "\n"))
    {
      c += counter++ + ". ";
    }

    // If nothing correspond -> we return the char unchanged
   return c;
  }
}