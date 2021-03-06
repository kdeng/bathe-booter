package bathe;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class BatheBooterTest {

  private void testResolveJarFile(String input, String expected) throws Exception {
    final File resolvedFile = new BatheBooter().resolveJarFile(new URL(input));
    Assert.assertEquals(new File(expected), resolvedFile);
  }

  public String prefixWhenNotOnWindows() {
    return ":".equals(File.pathSeparator) ? "/" : "";
  }

  @Test
  public void testResolveJarFileWithSpaces() throws Exception {
    testResolveJarFile("jar:file:/C:/test/test%20file.jar!/",
      prefixWhenNotOnWindows() + "C:/test/test file.jar");
  }

  @Test
  public void testResolveJarFileWithoutSpaces() throws Exception {
    testResolveJarFile("jar:file:/C:/test/test-file.jar!/",
      prefixWhenNotOnWindows() + "C:/test/test-file.jar");
  }

}
