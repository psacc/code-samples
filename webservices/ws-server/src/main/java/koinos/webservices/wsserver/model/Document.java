package koinos.webservices.wsserver.model;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlMimeType;

public class Document {
    private String id;
    private DataHandler content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlMimeType("application/octet-stream")
    public DataHandler getContent() {
        return content;
    }

    public void setContent(@XmlMimeType("application/octet-stream") DataHandler content) {
        this.content = content;
    }
}
