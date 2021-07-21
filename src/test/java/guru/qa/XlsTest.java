package guru.qa;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class XlsTest {
    @Test
    void simpleXlsTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("xls_school.xlsx");

        XLS xlsFile = new XLS(stream);
        Assertions.assertEquals("Александрова Ксения Евгеньевна", xlsFile.excel.getSheetAt(1).getRow(2).getCell(1).getStringCellValue());
    }
    @Test
    void csvTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("table.csv");
        List<String[]> result;
        try (CSVReader reader = new CSVReader(new InputStreamReader(stream))) {
            result = reader.readAll();
        }
        assertThat(result).contains(
                new String[]{"title", "author"},
                new String[]{"Война и мир", "Толстой"}
        );
    }
}

