package koinos.webservices.wsclient.handlers;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

public class SoapMessageLogger implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        debugMessage(context);
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        debugMessage(context);
        return true;
    }

    @Override
    public void close(MessageContext context) {

    }

    private void debugMessage(SOAPMessageContext smc) {
        if (isLogEnabled(smc)) {
            // if needed, single constants can be found in MessageContext class
            for (String key : smc.keySet()) {
                System.out.println("### " + key + "-->" + smc.get(key));
            }
            // very risky but sometimes useful operation:
            // smc.getMessage().writeTo(outputStream);
        }
    }

    protected boolean isLogEnabled(SOAPMessageContext messageContext) {
        return Boolean.parseBoolean(System.getProperty("koinos.wsclient.log.enabled"));
    }

}
