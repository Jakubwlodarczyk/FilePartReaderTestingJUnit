import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.filePath = "";
        this.fromLine = 1;
        this.toLine = 10;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {
        if(fromLine > toLine || fromLine < 1) {
            throw new IllegalArgumentException("Wrong fromLine and/or toLine parameters!");
        }
    }

    private String read() throws IOException {
        return String.join("\n", Files.readAllLines(Paths.get(this.filePath)));
    }

    public String readLines() throws IOException {
        String[] lines = this.read().split("\\n");
        StringBuilder stringToReturn = new StringBuilder();
        Integer linesToReturn = this.toLine - this.fromLine;
        if (linesToReturn == 0) {
            return lines[this.fromLine-1];
        } else {
            while (this.fromLine <= this.toLine) {
                stringToReturn.append(lines[this.fromLine -1]);
                stringToReturn.append("\n");
                this.fromLine ++;
            }
        }
        return stringToReturn.toString().substring(0, stringToReturn.length()-1);
    }

    public Integer getToLine() {
        return toLine;
    }

    public void setToLine(Integer toLine) {
        this.toLine = toLine;
    }

    public Integer getFromLine() {

        return fromLine;
    }

    public void setFromLine(Integer fromLine) {
        this.fromLine = fromLine;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
