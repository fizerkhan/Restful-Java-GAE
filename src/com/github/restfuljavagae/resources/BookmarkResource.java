package com.github.restfuljavagae.resources;

import java.io.IOException;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.github.restfuljavagae.models.Bookmark;
import com.github.restfuljavagae.models.ModelManager;

import flexjson.JSONSerializer;

/**
 * Resource which handles all Bookmarks related request.
 * 
 */
public class BookmarkResource extends ServerResource {

	static int count = 1;

	/**
	 * Handle GET requests.
	 * 
	 * @throws IOException
	 */
	@Get("json")
	public Representation getBookmark() {

		PersistenceManager pm = ModelManager.get().getPersistenceManager();

		try {
			String idStr = (String) getRequestAttributes().get("id");

			if (idStr != null) {
				// Get bookmark by ID
				int id = Integer.parseInt(idStr);
				Bookmark bookmark = pm.getObjectById(Bookmark.class, id);

				ResourceResponse resp = new ResourceResponse();
				resp.setData("bookmark", bookmark);
				resp.setSuccess(true);

				return (resp.asJSon());

			} else {
				// Get all bookmarks if no id is specified
				String query = "SELECT FROM " + Bookmark.class.getName();
				List<Bookmark> bookmarks = (List<Bookmark>) pm.newQuery(query)
						.execute();

				String jsonData = new JSONSerializer().serialize(bookmarks);
				Representation rep = new StringRepresentation(jsonData);
				getResponse().setStatus(Status.SUCCESS_OK);

				return (rep);
			}

		} catch (JDOObjectNotFoundException e) {
			ErrorResourceResponse resp = new ErrorResourceResponse(
					"Bookmark not found");
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return (resp.asJSon());

		} catch (Exception e) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			ErrorResourceResponse resp = new ErrorResourceResponse(
					"There was an error getting the bookmark.");
			return (resp.asJSon());
		} finally {
			pm.close();
		}

	}

	/**
	 * Handle POST requests.
	 * 
	 * @throws IOException
	 */
	@Post("json")
	public Representation addBookmark(String request) throws IOException {
		PersistenceManager pm = ModelManager.get().getPersistenceManager();

		try {
			// Parse POST JSON data input
			JSONObject requestObject = new JSONObject(request);

			String url = requestObject.getString("url");

			// create the model, update its fields and save to the datastore
			Bookmark bookmark = new Bookmark(url);
			bookmark.setName(requestObject.getString("name"));
			bookmark.setDescription(requestObject.getString("description"));

			// Store bookmark
			pm.makePersistent(bookmark);

			ResourceResponse resp = new ResourceResponse();
			resp.setData("bookmark", bookmark);
			resp.setSuccess(true);

			return (resp.asJSon());
		} catch (Exception e) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			ErrorResourceResponse resp = new ErrorResourceResponse(
					"There was an error saving the bookmark.");
			return (resp.asJSon());
		} finally {
			pm.close();
		}

	}

	/**
	 * Handle PUT requests.
	 * 
	 * @throws IOException
	 */
	@Put("json")
	public Representation updateBookmark(String request) throws IOException {
		PersistenceManager pm = ModelManager.get().getPersistenceManager();

		try {
			// Parse PUT JSON data input
			JSONObject requestObject = new JSONObject(request);

			String idStr = (String) getRequestAttributes().get("id");
			int id = Integer.parseInt(idStr);
			Bookmark bookmark = pm.getObjectById(Bookmark.class, id);

			// Update bookmark
			bookmark.setUrl(requestObject.getString("url"));
			bookmark.setName(requestObject.getString("name"));
			bookmark.setDescription(requestObject.getString("description"));

			// Store bookmark
			pm.makePersistent(bookmark);

			ResourceResponse resp = new ResourceResponse();
			resp.setData("bookmark", bookmark);
			resp.setSuccess(true);

			return (resp.asJSon());

		} catch (JDOObjectNotFoundException e) {
			ErrorResourceResponse resp = new ErrorResourceResponse(
					"Bookmark not found");
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return (resp.asJSon());

		} catch (Exception e) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			ErrorResourceResponse resp = new ErrorResourceResponse(
					"There was an error saving the bookmark.");
			return (resp.asJSon());
		} finally {
			pm.close();
		}
	}

	/**
	 * Handle DELETE requests.
	 * 
	 * @throws IOException
	 */
	@Delete("json")
	public Representation deleteBookmark() {
		PersistenceManager pm = ModelManager.get().getPersistenceManager();
		try {
			String idStr = (String) getRequestAttributes().get("id");
			int id = Integer.parseInt(idStr);
			Bookmark bookmark = pm.getObjectById(Bookmark.class, id);

			// delete the entity
			pm.deletePersistent(bookmark);

			ResourceResponse resp = new ResourceResponse();
			resp.setSuccess(true);
			resp.setData("message", "Bookmark is deleted.");

			return (resp.asJSon());

		} catch (JDOObjectNotFoundException e) {
			ErrorResourceResponse resp = new ErrorResourceResponse(
					"Bookmark not found");
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return (resp.asJSon());

		} catch (Exception e) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			ErrorResourceResponse resp = new ErrorResourceResponse(
					"There was an error deleting the bookmark.");
			return (resp.asJSon());
		} finally {
			pm.close();
		}
	}
}
