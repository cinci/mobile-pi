package pi4droid.server.runner;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

import java.lang.management.ManagementFactory;

/**
 * Starts an embedded Jetty web server
 * <p>
 * Created by jc on 14. 5. 2014.
 */
public class JettyPiRunner {
    public static void main(String[] args) throws Exception {

        Server server = new Server();
        SocketConnector connector = new SocketConnector();

        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(8080);
        server.setConnectors(new Connector[]{connector});

        WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath("/");
        bb.setWar("webapp");

        // START JMX SERVER
        ManagementFactory.getPlatformMBeanServer();
        server.addHandler(bb);
        try {
            System.out.println(">>> STARTING EMBEDDED JETTY SERVER");
            server.start();
            while (System.in.available() == 0) {
                Thread.sleep(5000);
            }

            server.stop();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(100);
        }
    }
}
