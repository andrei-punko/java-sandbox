package by.andd3dfx.interview.workfusion.hard;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import org.junit.Test;

public class FoldersTest {

  @Test
  public void folderNames() throws Exception {
    String xml =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<folder name=\"c\">" +
            "<folder name=\"program files\">" +
            "<folder name=\"uninstall information\" />" +
            "</folder>" +
            "<folder name=\"users\" />" +
            "</folder>";

    Collection<String> result = Folders.folderNames(xml, 'u');

    assertThat("Unexpected size", result.size(), is(2));
    assertThat("Unexpected result", result, hasItems("uninstall information", "users"));
  }
}