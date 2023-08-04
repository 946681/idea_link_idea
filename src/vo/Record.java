package vo;

public class Record {
    private String time;
    private String operation;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "时间："+time+" , 操作："+operation;
    }
}
