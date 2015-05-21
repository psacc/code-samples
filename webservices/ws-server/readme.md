## Streaming dati binari
- Per lo streaming di un dato binario è necessario:
    * dichiararlo come DataHandler
    * annotarlo con @XmlMimeType("application/octet-stream")
    
    
##### Note
1. Per eseguire: mvn [clean install] jetty:run-war -Djava.io.tmpdir=&lt;cartella temporanea&gt;
2. I documenti verranno uploadati nella cartella temporanea, con il nome indicato nell'id documento
3. I documenti verranno downloadati leggendo i file dall'url indicato nell'id documento
