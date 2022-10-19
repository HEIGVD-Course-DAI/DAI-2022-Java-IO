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
  int nbLine;

  public String transform(String c) {
    if(Objects.equals(c, "\r"))return "";
    if(nbLine == 0){
      ++nbLine;
      c = nbLine + ". " + c;
    }
    StringBuilder newString = new StringBuilder();
    for (int i=0; i<c.length(); i++) {
      Character cha = c.charAt(i);
      if(!cha.equals('\r'))newString.append(cha);
      if(cha.equals('\n')){
        ++nbLine;
        newString.append(nbLine).append(". ");
      }
    }
    return newString.toString();
  }
}