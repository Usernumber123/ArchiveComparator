public class Files {
    private String name;
    private long size;
     private Status status;

    public Files(String name, long size) {
        this.name = name;
        this.size = size;
        this.status = Status.DEFAULT;
    }

    public Status getStatus() {
        return status;
    }


    public String getName() {
        return name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getSize() {
        return size;
    }


}