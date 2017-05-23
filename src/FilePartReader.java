import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.filePath = "src/data/test_data.txt";
        this.fromLine = 1;
        this.toLine = 10;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {
        if(fromLine < toLine || fromLine < 1) {
            throw new IllegalArgumentException("Wrong fromLine and/or toLine parameters!");
        }
    }

    private String read() throws IOException {
        return String.join("\n", Files.readAllLines(Paths.get(this.filePath)));
    }

    public String readLines() throws IOException {
        String[] lines = this.read().split("\\n");
        StringBuilder stringToReturn = new StringBuilder();
        if (fromLine == 1 && toLine == 1){
            stringToReturn = new StringBuilder(lines[0]);
            return stringToReturn.toString();
        } else {
            Integer index = 1;
            for (String line : lines) {
                if (index >= this.fromLine && index <= this.toLine) {
                    stringToReturn.append(line);
                    stringToReturn.append("\n");
                    index ++;
                }
            }
            return stringToReturn.toString().substring(0, stringToReturn.length()-1);
        }
    }

}
