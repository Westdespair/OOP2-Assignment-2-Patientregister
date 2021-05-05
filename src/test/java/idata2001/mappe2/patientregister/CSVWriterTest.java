package idata2001.mappe2.patientregister;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
public class CSVWriterTest {
    CSVWriter csvWriter;

    @BeforeEach
    public void setUp() throws Exception {
        csvWriter = new CSVWriter();
    }

}