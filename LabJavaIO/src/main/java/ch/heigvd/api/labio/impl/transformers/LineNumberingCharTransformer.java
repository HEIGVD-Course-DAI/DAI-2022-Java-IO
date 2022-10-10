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

  private int lineNumber = 1;
  private static final Logger LOG = Logger.getLogger(LineNumberingCharTransformer.class.getName());

  public String transform(String c) {
    String newValue;
    //Remove carriage return
    if(c.equals("\r")){
      return "";
    }

    if(lineNumber == 1){
      //Special condition when it's the first line, and it's empty we need to add 2 line number.
      if(c.equals("\n")){
        newValue = lineNumber + ". " + c + (lineNumber+1) + ". ";
        lineNumber++;
      }else{
        newValue = lineNumber + ". " + c;
      }
      lineNumber++;
    }else{
      //If the char is \n , add new line and return number of the line
      if(c.equals("\n")){
        newValue = "\n" + lineNumber + ". ";
        lineNumber++;
      }else{
        newValue = c;
      }
    }
    return newValue;
  }
}