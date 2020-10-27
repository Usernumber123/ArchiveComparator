import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Archive {
    private List<Files> files;
    private int size;


    public Archive(ZipFile zipFile) throws IOException {
        Enumeration zipFiles = zipFile.entries();
        files = new ArrayList<>();
        while (zipFiles.hasMoreElements()) {
            ZipEntry element = (ZipEntry) zipFiles.nextElement();
            this.files.add(new Files(element.getName(), element.getSize()));

        }
        this.size = this.files.size();

        zipFile.close();
    }

    public List<Files> getFiles() {
        return files;
    }


    public int getSize() {
        return size;
    }


}
