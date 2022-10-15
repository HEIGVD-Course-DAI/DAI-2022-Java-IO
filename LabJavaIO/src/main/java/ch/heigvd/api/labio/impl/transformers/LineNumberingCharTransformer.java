package ch.heigvd.api.labio.impl.transformers;

import java.util.logging.Logger;

/**
 * This class applies a transformation to the input character (a string with a single character):
 * 1. Convert all line endings to Unix-style line endings, i.e. only '\n'.
 * 2. Add a line number at the beginning of each line.
 * Example:
 * Using this character transformer, the following file :
 * This is the first line.\r\n
 * This is the second line.
 * will be transformed to:
 * 1. This is the first line.\n
 * 2. This is the second line.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class LineNumberingCharTransformer {

    private int lNum = 1;
    private static final Logger LOG = Logger.getLogger(LineNumberingCharTransformer.class.getName());
    private static final String LINE_PREFIX = ". ";

    public String transform(String c) {

        //Remove carriage return
        if (c.equals("\r"))
            return "";

        if (lNum == 1) {
            String newC = lNum + LINE_PREFIX + c;
            if (c.equals("\n"))
                newC = newC + ++lNum + LINE_PREFIX;
            lNum++;
            return newC;
        }

        if (c.equals("\n"))
            return "\n" + lNum++ + LINE_PREFIX;

        return c;
    }
}
