package demo.isbn.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import demo.isbn.client.IsbnClient;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@ApplicationScoped
@Path("/books")
public class BookRest {
	private IsbnClient client = new IsbnClient();

	@GET
	@Path(("/{isbn}"))
	@Produces(MediaType.APPLICATION_JSON)
	public Response findBookByIsbn(@PathParam("isbn") String isbn) {
	    return Response.ok( client.fetchBookInfo(isbn) ).build();
	}
}
