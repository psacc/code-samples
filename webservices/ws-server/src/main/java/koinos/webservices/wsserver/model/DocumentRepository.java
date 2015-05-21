package koinos.webservices.wsserver.model;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;

@WebService(name = "DocumentRepository", targetNamespace = "http://wsserver.webservices.koinos/")
public interface DocumentRepository {
    public @XmlMimeType("application/octet-stream") DataHandler getContent(@WebParam(name="id") String id);
    public Document download(@WebParam(name="id") String id);
    public void upload(@WebParam(name="document") Document document);
}
