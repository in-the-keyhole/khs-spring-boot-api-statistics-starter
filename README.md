# khs-spring-boot-api-statistics-starter
A Spring Boot starter for publishing api statistics

Installation
------------
Add this dependency to your pom.xml:

	<dependency>
		<groupId>com.keyholesoftware</groupId>
		<artifactId>khs-spring-api-statistics-starter</artifactId>
		<version>1.0.0</version>
	</dependency>	

Annotate your Spring Boot main class:

	@SpringBootApplication
	@EnableApiStatistics
	public class MySpringBootApp {

		public static void main(String[] args) {
			SpringApplication.run(MySpringBootApp.class, args);
		}
	}

Add configuration in application.yml:

api:
  statistics:
    name: someName
    pattern-match: /.*
    publish-url: http://someHost/someApi
    token: someSecurityToken