import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FilePartReaderTest {

    private FilePartReader filePartReader = new FilePartReader();

    @Test
    public void testReadLinesBeforeSetup() {
        assertThrows(IOException.class, ()-> {
            filePartReader.readLines();
        });
    }

    @Test
    public void testSetupFromLineLT1() {
        assertThrows(IllegalArgumentException.class, ()-> {
            filePartReader.setFilePath("src/data/test_data.txt");
            filePartReader.setFromLine(-1);
            filePartReader.setToLine(2);
            filePartReader.setup(filePartReader.getFilePath(), filePartReader.getFromLine(), filePartReader.getToLine());
        });
    }

    @Test
    public void testSetupToLineLTFromLine() {
        assertThrows(IllegalArgumentException.class, ()-> {
            filePartReader.setFilePath("src/data/test_data.txt");
            filePartReader.setFromLine(5);
            filePartReader.setToLine(1);
            filePartReader.setup(filePartReader.getFilePath(), filePartReader.getFromLine(), filePartReader.getToLine());
        });
    }

    @Test
    public void testReadLines1_2() throws IOException {
        String expected = "1a1\n" +
                "2b 2a";
        filePartReader.setFilePath("src/data/test_data.txt");
        filePartReader.setFromLine(1);
        filePartReader.setToLine(2);
        assertEquals(expected, filePartReader.readLines());
    }

    @Test
    public void testReadLines2_4() throws IOException {
        String expected = "2b 2a\n" +
                "3c 3b 3a\n" +
                "4d 4cr 4bb4 4a";
        filePartReader.setFilePath("src/data/test_data.txt");
        filePartReader.setFromLine(2);
        filePartReader.setToLine(4);
        assertEquals(expected, filePartReader.readLines());
    }

    @Test
    public void testReadLinesAll() throws IOException {
        String expected = "1a1\n" +
                "2b 2a\n" +
                "3c 3b 3a\n" +
                "4d 4cr 4bb4 4a\n" +
                "5e 5d 5c 5b 5ax\n" +
                "6f 6ea 6d 6ca 6bb 6a\n" +
                "7g 7f 7ea";
        filePartReader.setFilePath("src/data/test_data.txt");
        filePartReader.setFromLine(1);
        filePartReader.setToLine(7);
        assertEquals(expected, filePartReader.readLines());
    }

    @Test
    public void testReadLinesPastEof() {
        filePartReader.setFilePath("src/data/test_data.txt");
        filePartReader.setFromLine(1);
        filePartReader.setToLine(20);
        assertThrows(IndexOutOfBoundsException.class , ()-> {
            filePartReader.readLines();
        });
    }
}
