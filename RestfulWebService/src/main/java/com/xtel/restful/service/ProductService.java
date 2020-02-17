package com.xtel.restful.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.xtel.restful.authentication.Authen;
import com.xtel.restful.entity.Product;
import com.xtel.restful.model.ProductModel;

@Path("webservice/restful/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ProductService {
	private Gson gson = new Gson();
	@Context
	HttpServletRequest request;

	@GET
	@Path("/products")
	public Response findAll(@DefaultValue("0") @QueryParam("page") String pageIndex) {
		String author = request.getHeader("author");
//		System.out.println(author);
		if (!Authen.getInstance().authentication(author, "ADMIN")) {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized!").build();
		}
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

	@GET
	@Path("/products/search")
	public Response findByName(@QueryParam("q") String keyword) {
		ProductModel model = new ProductModel();
		return Response.ok().entity(gson.toJson(model.findByName(keyword))).build();
	}

	@POST
	@Path("/products")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response save(String body) {
		Product product = parseBody(body, Product.class);
		ProductModel model = new ProductModel();
		int a = model.insert(product);
		return buildResponse(a);
	}

	@PUT
	@Path("/products")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response update(String body) {
		Product product = parseBody(body, Product.class);
		ProductModel model = new ProductModel();
		return Response.ok().entity(model.update(product)).build();
	}

	@DELETE
	@Path("/products/{id}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response delete(@PathParam("id") int id) {
		ProductModel model = new ProductModel();
		return Response.ok().entity(model.delete(id)).build();
	}

	private Response buildResponse(Object objResponse) {
		return Response.status(Response.Status.OK).entity(objResponse).build();
	}

	private <T> T parseBody(String body, Class<T> t) {
		return gson.fromJson(body, t);
	}
}
