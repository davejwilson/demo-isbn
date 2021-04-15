package demo.isbn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.jupiter.api.Test;

public class BookEndpointIT {

  @Test
  public void testGetProperties() throws JsonProcessingException {

      // system properties
      String port = System.getProperty("http.port");
      String contextRoot = System.getProperty("context.root", "/");
      String url = "http://localhost:" + port + contextRoot;

      // client setup
      Client client = ClientBuilder.newClient();
      client.register(JsrJsonpProvider.class);

      // request
      WebTarget target = client.target(url + "api/books/ISBN:1931498717");
      Response response = target.request().get();

      // response
      assertEquals(200, response.getStatus(), "Incorrect response code from " + url);

      JsonObject obj = response.readEntity(JsonObject.class);
      System.out.println(new ObjectMapper().writeValueAsString(obj));

      response.close();
  }

}
