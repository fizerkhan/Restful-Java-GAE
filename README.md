#Application base prototype for RESTful-Java-GAE

This is a base prototype demo for setting up an infrastructure for a RESTful Java application, with a Google App Engine backend API talking to DataStore.

## Why

When I initially work with Google App Engine with RESTful API, I walk through lot of resources.
Unfortunately I could not find out any concrete code to explain RESTful in GAE with Java.

Finally, I found [resftul-webshop](https://github.com/oscarrenalias/restful-webshop) which provides me a sample code to work with RESTful APIs in GAE. Thanks to [Oscar Renalias](https://github.com/oscarrenalias).
Then I made some changes to make my application to work with RESTful APIs. Here, I just put my learnings in template code. 

## USAGE

To start,
 
	1. Just clone this repository
	2. From Eclipse, Import this repository as 'Existing Projects into Workspace'. 

##Bookmark Application

This is simple Bookmarking application where you create, retrieve, update, delete bookmarks.

There are five RESTful APIs provided

	1. POST /bookmark				-	Create new bookmark
	2. GET /bookmarks				-	Get all bookmarks
	3. GET /bookmarks/:id			-	Get particular bookmark
	4. PUT /bookmarks/:id			-	Update particular bookmark
	5. DELETE /bookmarks:id			-	Delete particular bookmark

I haven't done any pages to test above APIs. You have to use `curl` tool or your browser to test the APIs.
While testing with JSON data, I highly recommend you to use PrettyJSON tool. Simply you can pipe the output to Python JSON tool `| python -mjson.tool`.
In below sample, I use default GAE url as http://localhost:8888.

###POST /bookmark

	$ curl -H 'Content-Type: application/json' -d '{"url":"http://github.com", "name":"GitHub", "description" : "Long Live Opensource" }' http://localhost:8888/api/v1/bookmarks
	
	$ curl -H 'Content-Type: application/json' -d '{"url":"http://google.com", "name":"Google", "description" : "Do not be Evil" }' http://localhost:8888/api/v1/bookmarks

###GET /bookmarks

	$ curl http://localhost:8888/api/v1/bookmarks

###GET /bookmarks/:id

	$ curl http://localhost:8888/api/v1/bookmarks/1590
	
Note: I assume, the id is 1590. You have to place the ID which you got from GET all bookmarks.
	
###PUT /bookmarks/:id

	$ curl -X PUT -H 'Content-Type: application/json' -d '{"url":"http://github.com", "name":"GitHub(Public)", "description" : "Long Live GitHub Public Repositories" }' http://localhost:8888/api/v1/bookmarks/1590

###DELETE /bookmarks:id

	$ curl -X DELETE http://localhost:8888/api/v1/bookmarks/1590


##Appendix
It is under MIT License.
Feel free to fork it and give pull requests.

Long Live Open Source
