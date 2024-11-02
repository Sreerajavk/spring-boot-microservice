package com.sreers.microservice.order;

import com.sreers.microservice.order.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port =port;
	}
	static {
		mySQLContainer.start();
	}

	@Test
	void createSuccessOrders() {

		String payload = """
				{
				    "skuCode": "MacBook",
				    "price": 90,
				    "quantity": 20
				}
				""";
		InventoryClientStub.stubInventorySuccessCall("MacBook",20);
		RestAssured.given().contentType("application/json")
				.body(payload)
				.when()
				.post("/api/order")
				.then()
				.statusCode(201);
	}

	@Test
	void createFailedOrders() {

		String payload = """
				{
				    "skuCode": "MacBook",
				    "price": 90,
				    "quantity": 200
				}
				""";
		InventoryClientStub.stubInventoryFailCall("MacBook",200);
		RestAssured.given().contentType("application/json")
				.body(payload)
				.when()
				.post("/api/order")
				.then()
				.statusCode(400);
	}

	@Test
	void getOrders() {
		RestAssured.given()
				.get("/api/order")
				.then()
				.statusCode(200);
	}

}