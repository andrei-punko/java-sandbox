package by.andd3dfx.interview.wf.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paragraph {

  private static final String PATTERN = "\\d[\n]*\\d[\n]*\\d[\n]*-[\n]*\\d[\n]*\\d[\n]*-[\n]*\\d[\n]*\\d[\n]*\\d[\n]*\\d";

  public static String changeFormat(String paragraph) {
    Pattern p = Pattern.compile(PATTERN);
    Matcher m = p.matcher(paragraph);
    while (m.find()) {
      String s = m.group(0);
      String[] parts = s.split("-");
      String result = parts[0] + "/" + parts[2] + "/" + parts[1];
      paragraph = paragraph.replaceAll(m.group(0), result);
    }
    return paragraph;
  }

  public static void main(String[] args) {
    System.out.println(changeFormat("Please quote your policy number: 112-39-8552."));
    System.out.println(changeFormat("Please quote your policy number: 112-39-\n8552."));
  }
}
