package com.xtel.restful.service;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.xtel.restful.entity.Product;
import com.xtel.restful.model.ProductModel;

@Path("webservice/restful/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ProductService {
	private Gson gson = new Gson();

	@GET
	@Path("/products")
	public Response findAll(@DefaultValue("0") @QueryParam("page") String pageIndex) {
		ProductModel model = new ProductModel();
		int count = new ProductModel().numberOfPage();
		int index = Integer.parseInt(pageIndex);
		if (index < 1) {
			index = 1;
		} else if (index > count) {
			index = count;
		}
		return Response.ok().entity(gson.toJson(model.findAll(index - 1))).build();
	}

	@GET
	@Path("/products/{id}")
	public Response findById(@PathParam("id") int id) {
		ProductModel model = new ProductModel();
		return Response.ok().entity(gson.toJson(model.findById(id))).build();
	}

	@PermitAll
	@POST
	@Path("/products")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response save(String body) {
		Product product = parseBody(body, Product.class);
		ProductModel model = new ProductModel();
		int a = model.save(product);
		return buildResponse(a);
	}

	private Response buildResponse(Object objResponse) {
		return Response.status(Response.Status.OK).entity(objResponse).build();
	}

	private <T> T parseBody(String body, Class<T> t) {
		return gson.fromJson(body, t);
	}
}
