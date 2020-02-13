package com.xtel.restful;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.xtel.restful.authentication.AuthenticationFilter;


public class Main {
	public static void main(String[] args) {
		ResourceConfig config = new ResourceConfig();
		config.packages("com.xtel.restful.service");
		config.register(AuthenticationFilter.class);
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));

		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(server, "/*");
		context.addServlet(servlet, "/*");

		try {
			server.start();
			server.join();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
