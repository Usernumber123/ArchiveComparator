import java.util.HashMap;
import java.util.Objects;

public class ArchiveComparator {
    private Archive newArc;
    private Archive oldArc;


    ArchiveComparator(Archive oldArc, Archive newArc) {
        this.oldArc = oldArc;
        this.newArc = newArc;
    }

    public void compare() {
        this.findDelFiles();
        this.findNewFiles();
        this.findUpFiles();
        this.findReFiles();
    }

    private void findDelFiles() {
        for (int i = 0; i < oldArc.getSize(); i++) {
            boolean check = true;
            for (int j = 0; j < newArc.getSize(); j++) {
                if (Objects.equals(newArc.getFiles().get(j).getName(), oldArc.getFiles().get(i).getName())) {
                    check = false;
                    break;
                }
            }
            if (check) {
                oldArc.getFiles().get(i).setStatus(Status.DELETED);
            }
        }
    }


    private void findNewFiles() {
        for (int i = 0; i < newArc.getSize(); i++) {
            boolean check = true;
            for (int j = 0; j < oldArc.getSize(); j++) {
                if (Objects.equals(newArc.getFiles().get(i).getName(), oldArc.getFiles().get(j).getName())) {
                    check = false;
                    break;
                }
            }
            if (check) {
                newArc.getFiles().get(i).setStatus(Status.NEW);
            }
        }
    }

    private void findUpFiles() {
        for (int i = 0; i < oldArc.getSize(); i++) {
            for (int j = 0; j < newArc.getSize(); j++) {
                if (Objects.equals(newArc.getFiles().get(j).getName(), oldArc.getFiles().get(i).getName())
                        && newArc.getFiles().get(j).getSize() != oldArc.getFiles().get(i).getSize()) {
                    newArc.getFiles().get(j).setStatus(Status.UPDATED);
                    oldArc.getFiles().get(i).setStatus(Status.UPDATED);
                    break;
                }
            }
        }
    }


    private void findReFiles() {
        for (int i = 0; i < oldArc.getSize(); i++) {
            for (int j = 0; j < newArc.getSize(); j++) {
                if (!Objects.equals(newArc.getFiles().get(j).getName(), oldArc.getFiles().get(i).getName())
                        && newArc.getFiles().get(j).getSize() == oldArc.getFiles().get(i).getSize()) {
                    newArc.getFiles().get(j).setStatus(Status.RENAMED);
                    oldArc.getFiles().get(i).setStatus(Status.RENAMED);
                    break;
                }
            }
        }
    }

    public HashMap<Status, String> getHashMap() {
        HashMap<Status, String> hashMap = new HashMap<>();
        for (Files file : oldArc.getFiles()) {
            if (file.getStatus() == Status.RENAMED) {
                for (Files file1 : newArc.getFiles()) {
                    if (file.getSize() == file1.getSize()) {
                        hashMap.put(file.getStatus(), file.getName() + '/' + file1.getName());
                        break;
                    }
                }
            } else {
                hashMap.put(file.getStatus(), file.getName());
            }
        }
        for (Files file : newArc.getFiles()) {
            if (Objects.equals(file.getStatus(), Status.NEW)) {
                hashMap.put(file.getStatus(), file.getName());
            }
        }
        return hashMap;
    }

}
