# khs-spring-boot-api-statistics-starter

A Spring Boot starter for publishing api statistics.

Description
------------
This Spring Boot starter can be used to POST API usage statistics to a publishing target (url) on a configurable interval.  The body of the request will be a JSON array of statistics (see schema below), and a security token can be applied to ensure that only authorized clients have access.

Installation
------------
Add this dependency to your pom.xml:

	<dependency>
		<groupId>com.keyholesoftware</groupId>
		<artifactId>khs-spring-api-statistics-starter</artifactId>
		<version>1.0.1</version>
	</dependency>	

Annotate your Spring Boot main class:

	@SpringBootApplication
	@EnableApiStatistics
	public class MySpringBootApp {

		public static void main(String[] args) {
			SpringApplication.run(MySpringBootApp.class, args);
		}
	}
	
Configurable Properties:
	api.statistics.name - The name under which to group the published statistics
	api.statistics.pattern-match: A regex expression to filter API's in/out
	api.statistics.publish-url: The url to POST the statistics to
	api.statistics.token: A security token to prevent unwanted clients from POSTing stats to the publish-url


Example Configuration(s):
------------

(i.e. application.yml)

	api:
	  statistics:
	    name: apigateway
	    pattern-match: /api/.*
	    publish-url: http://beta.grokola.com/sherpa/api/stats/308
	    token: 9x019749-XXXX-XXXX-XXXX-38090a0ea9g9

Example Payload:
------------

	[
	  {
	    uri: "/api/projects/1",
	    method: "GET",
	    duration: "19",
	    service: "apigateway"
	  },
	  {
	    uri: "/api/projects/1",
	    method: "GET",
	    duration: "16",
	    service: "apigateway"
	  },
	  {
	    uri: "/api/projects/2",
	    method: "GET",
	    duration: "14",
	    service: "apigateway"
	  }
	  ...
	]
