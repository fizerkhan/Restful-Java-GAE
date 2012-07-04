package com.github.restfuljavagae;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.github.restfuljavagae.resources.BookmarkResource;

public class RestfulApplication extends Application {

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());

		// Defines sample routes
		// Route for GET all bookmarks and POST new bookmark
		// It handles following request
		//	GET /bookmarks		- Return all available bookmarks
		// 	POST /bookmarks		- new bookmark is passed as JSON data and created.
		router.attach("/bookmarks", BookmarkResource.class);
		
		// Route for GET/PUT/DELETE specific bookmark(by id)
		// It handles following request
		//	GET /bookmarks/1		- Get bookmark 1
		// 	PUT /bookmarks/1		- Update bookmark 1 with the passed JSON data. 
		// 	DELETE /bookmarks/1		- Delete bookmark 1
		router.attach("/bookmarks/{id}", BookmarkResource.class);

		// You have to create your own ServerResource and attach it in the router as
		// above.

		return router;
	}
}
