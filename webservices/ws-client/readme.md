## Generazione client jax-ws
* configurare l'apposito plugin maven (vedi il plugin **jaxws-maven-plugin** nel pom.xml)
* `mvn jaxws:wsimport`

## Definizione dei proxy ai servizi jax-ws come bean di spring
* vedi la definizione del bean di tipo `org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean` in spring-main.xml
* è consigliabile puntare ai wsdl memorizzati internamente al progetto (`p:wsdlDocumentUrl="classpath:external-wsdl/DocumentService.wsdl"`)

## Streaming documenti
- aggiungere la dipendenza da `jaxws-rt`

        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-rt</artifactId>
            <version>2.2.10</version>
            <scope>compile</scope>
        </dependency>

- verificare che, nel client jax-ws generato, i dati da inviare in streaming 
    * siano stati dichiarati di tipo `javax.activation.DataHandler`
    * siano stati annotati con `@XmlMimeType("application/octet-stream")`
        
- è possibile testare il comportamento del client eseguendo la classe `koinos.webservices.wsclient.TestStreaming`      
    * per i test impostare la dimensione massima dell'heap molto bassa (es. -Xmx64m)
    * impostare la cartella temporanea -Djava.io.tmpdir=&lt;cartella temporanea&gt;
    * i file scaricati dal server verranno scritti nella cartella temporanea
    * il file in upload è presente nel pacchetto (`resources/testdata/largedoc.sample`)
