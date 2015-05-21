package koinos.webservices.wsserver.services;

import com.sun.xml.ws.developer.StreamingAttachment;
import com.sun.xml.ws.encoding.DataSourceStreamingDataHandler;
import koinos.webservices.wsserver.model.Document;
import koinos.webservices.wsserver.model.DocumentRepository;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.MTOM;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@MTOM
@WebService(serviceName = "DocumentService", endpointInterface = "koinos.webservices.wsserver.model.DocumentRepository")
// Non è obbligatorio annotare con StreamingAttachment, ma può essere utile poter impostare dei comportamenti
//@StreamingAttachment(parseEagerly=true, memoryThreshold=1048576L)
@StreamingAttachment(parseEagerly=false)
public class DocumentRepositoryService implements DocumentRepository {

    @Override
    public @XmlMimeType("application/octet-stream") DataHandler getContent(String id) {
//        return new DataSourceStreamingDataHandler(new FileDataSource(getTempDir() + '/' + id));
        try {
            return new DataSourceStreamingDataHandler(new URLDataSource(new URL(id)));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Document download(String id) {
        Document document = new Document();
        document.setId(id);
        document.setContent(getContent(id));
        return document;
    }

    @Override
    public void upload(Document document) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(getTempDir() + "/" + document.getId());
            document.getContent().writeTo(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTempDir() {
        return System.getProperty("java.io.tmpdir");
    }

}
