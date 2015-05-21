package koinos.webservices.wsclient;

import com.sun.xml.ws.developer.StreamingDataHandler;
import com.sun.xml.ws.encoding.DataSourceStreamingDataHandler;
import koinos.webservices.wsclient.generated.Document;
import koinos.webservices.wsclient.generated.DocumentRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestStreaming {
    public static final String URL = "http://docs.oracle.com/javase/7/docs/api/javax/xml/ws/WebServiceFeature.html";
//    public static final String URL = "http://download.jetbrains.com/idea/ideaIC-14.1.3.exe";

    private final ApplicationContext context;

    public TestStreaming() {
        context = new ClassPathXmlApplicationContext("config/spring-main.xml");
    }

    private DocumentRepository getDocumentRepository() {
        return context.getBean(DocumentRepository.class);
    }

    private void testDownload() throws Exception {
        DocumentRepository ws = getDocumentRepository();

        Document document = ws.download(URL);

        printHeap("document stream received");

        writeToFile(document.getContent(), getTempDir() + "/output.download");

        printHeap("document saved");
    }

    private void testGetContent() throws Exception {
        DocumentRepository ws = getDocumentRepository();

        DataHandler content = ws.getContent(URL);

        printHeap("content stream received");

        writeToFile(content, getTempDir() + "/output.getcontent");

        printHeap("content saved");
    }

    private void testUpload() throws IOException {
        DocumentRepository ws = getDocumentRepository();

        printHeap("upload: before creating stream");

        Document document = new Document();
        document.setId("prova.upload");

//        final DataHandler dataHandler = new DataSourceStreamingDataHandler(new FileDataSource(getTempDir() + "/largedoc.sample"));
        final StreamingDataHandler dataHandler = new DataSourceStreamingDataHandler(new URLDataSource(new URL(URL)));
        document.setContent(dataHandler);

        printHeap("before upload");
        ws.upload(document);

        printHeap("after upload");
    }

    public static void main(String[] args) throws Exception {
        printHeap("starting...", true);
        final TestStreaming main = new TestStreaming();
        main.testUpload();
        main.testGetContent();
        main.testDownload();
    }

    public static void printHeap(String message) {
        printHeap(message, false);
    }

    public static void printHeap(String message, boolean wait) {
        System.out.printf("[%50s] - %s - %s%n", message, new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()), ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
        try {
            if (wait) {
                System.out.print("press enter...");
                System.in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(DataHandler content, String fileName) throws FileNotFoundException {
        final FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        try {
            content.writeTo(fileOutputStream);
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception ex) { /* ignore it */ }
        }
    }

    private static String getTempDir() {
        return System.getProperty("java.io.tmpdir");
    }

}
