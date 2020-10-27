import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

class Frame extends JFrame {
    private File firstFile;
    private File secondFile;

    Frame() {
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.setFileFilter(new FileNameExtensionFilter("zip & jar", "zip", "jar"));
        int ret1 = fileOpen.showDialog(null, "Open first file");
        if (ret1 == JFileChooser.APPROVE_OPTION) {
            firstFile = fileOpen.getSelectedFile();
        }
        int ret2 = fileOpen.showDialog(null, "Open second file");
        if (ret2 == JFileChooser.APPROVE_OPTION) {
            secondFile = fileOpen.getSelectedFile();
        }
    }

    public File getFirstFile() {
        return firstFile;
    }

    public File getSecondFile() {
        return secondFile;
    }
}