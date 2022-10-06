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

  // Used as flag
  boolean firstChar = true;

  int lineNumber = 0;
  public String transform(String c) {
    /* TODO: implement the transformation here. */

    String lines[] = c.split("\\r");
    String temp;
    String temp2 = c;


    c = "";

    for (int i = 0; i < lines.length; ++i)
    {
      if (temp2.equals("\n")){
        firstChar = true;
        lineNumber++;
      }

      if(firstChar){
        temp = (i+1) + ". ";
        temp += lines[i];
        temp.replace('\r','\n');
        c += temp;
        firstChar = false;
      }
      else return temp2;
    }

    return c;



    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }
}