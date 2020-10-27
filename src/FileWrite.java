import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class FileWrite {
    private String nameOfOldArch;
    private String nameOfNewArch;
    private HashMap<Status, String> map;

    public void writeToFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("result.txt");
        writer.printf("%-20.20s|%-20.20s\n", nameOfOldArch.substring(nameOfOldArch.lastIndexOf('/') + 1),
                nameOfNewArch.substring(nameOfNewArch.lastIndexOf('/') + 1));

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append('-');
        }
        stringBuilder.append('+');
        for (int i = 0; i < 20; i++) {
            stringBuilder.append('-');
        }
        stringBuilder.append('\n');
        writer.print(stringBuilder.toString());//write line like ----+-----

        for (Map.Entry<Status, String> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case NEW:
                    writer.printf("%-20.20s|+ %-18.18s\n", "", entry.getValue());
                    break;
                case DELETED:
                    writer.printf("- %-18.18s|%-20.20s\n", entry.getValue(), "");
                    break;
                case RENAMED:
                    writer.printf("? %-18.18s|? %-18.18s\n",
                            entry.getValue().substring(0, entry.getValue().indexOf('/')),
                            entry.getValue().substring(entry.getValue().indexOf('/') + 1));
                    break;
                case UPDATED:
                    writer.printf("* %-18.18s|* %-18.18s\n", entry.getValue(), entry.getValue());
                    break;
            }
        }
        writer.close();
    }

    public FileWrite(File oldFile, File newFile) {
        this.nameOfOldArch = newFile.getName();
        this.nameOfNewArch = oldFile.getName();
        try {
            ZipFile zipNewFile = new ZipFile(newFile);
            ZipFile zipOldFile = new ZipFile(oldFile);
            Archive a = new Archive(zipNewFile);
            Archive a1 = new Archive(zipOldFile);
            zipNewFile.close();
            zipOldFile.close();
            ArchiveComparator archComp = new ArchiveComparator(a, a1);
            archComp.compare();
            this.map = archComp.getHashMap();
            this.writeToFile();

            System.out.println("Create file in project directory: result.txt");
        } catch (
                ZipException e) {

            e.printStackTrace();
        } catch (
                IOException e) {

            e.printStackTrace();
        }

    }
}
