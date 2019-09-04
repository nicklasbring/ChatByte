package request;

import java.io.File;
import java.io.Serializable;

public class Request implements Serializable {

    private String sender;
    private String msg;
    private RequestType type;

    private File file = null;

    public Request(String sender, String msg, RequestType type) {
        this.sender = sender;
        this.msg = msg;
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RequestType getType() {
        return type;
    }
    public void setType(RequestType type) {
        this.type = type;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
