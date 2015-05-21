package koinos.webservices.wsclient.handlers;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.handler.soap.SOAPHandler;
import java.util.ArrayList;
import java.util.List;

public class ListHandlerResolver implements javax.xml.ws.handler.HandlerResolver {
    private List<SOAPHandler> handlers;

    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {
        return handlers == null ? new ArrayList<Handler>() : new ArrayList<Handler>(handlers);
    }

    public void setHandlers(List<SOAPHandler> handlers) {
        this.handlers = handlers;
    }
}
