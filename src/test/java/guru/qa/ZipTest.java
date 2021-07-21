package guru.qa;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTest {
    @Test
    void simpleZipTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String entryAsString = null;
        try (ZipInputStream stream = new ZipInputStream(classLoader.getResourceAsStream("zip/sample-zip-file.zip"))) {
            ZipEntry entry;
            while ((entry = stream.getNextEntry()) != null) {
                entryAsString = IOUtils.toString(stream, StandardCharsets.UTF_8);
            }
        }
        Assertions.assertTrue(entryAsString.contains("I would love to try or hear the sample audio your app can produce. I do not want to purchase"));
    }
}
