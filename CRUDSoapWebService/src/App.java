import com.xtel.crudservice.implement.Implement;

import javax.xml.ws.Endpoint;

public class App {
        public static final String WS_URL = "http://localhost:8080/ws/customers";
        public static void main(String[] args) {
            Endpoint.publish(WS_URL, new Implement());
        }
}
