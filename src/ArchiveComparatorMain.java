import java.io.*;

public class ArchiveComparatorMain {
    static public void main(String arg[]) {
        try {
            File file = new File(arg[0]);
            File file1 = new File(arg[1]);
            new FileWrite(file, file1);
        } catch (ArrayIndexOutOfBoundsException e) {
            Frame frame = new Frame();
            new FileWrite(frame.getFirstFile(), frame.getSecondFile());
        }
    }
}


