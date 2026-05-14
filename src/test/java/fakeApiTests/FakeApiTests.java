package fakeApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FakeApiTests {

    static Integer bookingId;

    @BeforeAll
    static void setup() {

        RestAssured.baseURI = "http://localhost:4000/api";
    }

    // =========================================================
    // HEALTH CHECK
    // =========================================================

    @Test
    @Order(1)
    public void shouldReturn200HealthCheck() {

        given()

                .when()
                .get("/bookings")

                .then()
                .statusCode(200)
                .time(lessThan(2000L));

        System.out.println("✅ HEALTH CHECK OK");
    }

    // =========================================================
    // GET ALL BOOKINGS
    // =========================================================

    @Test
    @Order(2)
    public void shouldGetAllBookings() {

        given()

                .when()
                .get("/bookings")

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        System.out.println("✅ GET ALL BOOKINGS OK");
    }

    // =========================================================
    // CREATE BOOKING
    // =========================================================

    @Test
    @Order(3)
    public void shouldCreateBooking() {

        String body = """
        {
          "firstname": "Maria",
          "lastname": "QA",
          "totalprice": 700,
          "depositpaid": true,
          "bookingdates": {
            "checkin": "2026-07-01",
            "checkout": "2026-07-10"
          },
          "additionalneeds": "Lunch"
        }
        """;

        bookingId =

                given()
                        .contentType(ContentType.JSON)
                        .body(body)

                        .when()
                        .post("/bookings")

                        .then()
                        .statusCode(201)
                        .body("firstname", equalTo("Maria"))
                        .body("lastname", equalTo("QA"))
                        .body("totalprice", equalTo(700))
                        .extract()
                        .path("id");

        Assertions.assertNotNull(bookingId);

        System.out.println("✅ BOOKING CREATED ID: " + bookingId);
    }

    // =========================================================
    // GET BOOKING BY ID
    // =========================================================

    @Test
    @Order(4)
    public void shouldGetBookingById() {

        given()

                .when()
                .get("/bookings/" + bookingId)

                .then()
                .statusCode(200)
                .body("id", equalTo(bookingId))
                .body("firstname", equalTo("Maria"));

        System.out.println("✅ GET BOOKING BY ID OK");
    }

    // =========================================================
    // UPDATE BOOKING - PUT
    // =========================================================

    @Test
    @Order(5)
    public void shouldUpdateBooking() {

        String body = """
        {
          "firstname": "Maria Updated",
          "lastname": "Automation",
          "totalprice": 900,
          "depositpaid": true,
          "bookingdates": {
            "checkin": "2026-08-01",
            "checkout": "2026-08-20"
          },
          "additionalneeds": "Dinner"
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(body)

                .when()
                .put("/bookings/" + bookingId)

                .then()
                .statusCode(200)
                .body("firstname", equalTo("Maria Updated"))
                .body("lastname", equalTo("Automation"))
                .body("totalprice", equalTo(900));

        System.out.println("✅ PUT BOOKING OK");
    }

    // =========================================================
    // PATCH BOOKING
    // =========================================================

    @Test
    @Order(6)
    public void shouldPatchBooking() {

        String body = """
        {
          "firstname": "Maria PATCH"
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(body)

                .when()
                .patch("/bookings/" + bookingId)

                .then()
                .statusCode(200)
                .body("firstname", equalTo("Maria PATCH"));

        System.out.println("✅ PATCH BOOKING OK");
    }

    // =========================================================
    // DELETE BOOKING
    // =========================================================

    @Test
    @Order(7)
    public void shouldDeleteBooking() {

        given()

                .when()
                .delete("/bookings/" + bookingId)

                .then()
                .statusCode(200);

        System.out.println("✅ DELETE BOOKING OK");
    }

    // =========================================================
    // NEGATIVE TEST - INVALID ID
    // =========================================================

    @Test
    @Order(8)
    public void shouldReturn404ForInvalidBookingId() {

        given()

                .when()
                .get("/bookings/999999")

                .then()
                .statusCode(404);

        System.out.println("✅ INVALID ID TEST OK");
    }

    // =========================================================
    // NEGATIVE TEST - INVALID PAYLOAD
    // =========================================================

    @Test
    @Order(9)
    public void shouldCreateBookingWithInvalidPayload() {

        String body = """
        {
          "firstname": 123
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(body)

                .when()
                .post("/bookings")

                .then()
                .statusCode(anyOf(is(400), is(201)));

        System.out.println("✅ INVALID PAYLOAD TEST OK");
    }

    // =========================================================
    // NEGATIVE TEST - INVALID ENDPOINT
    // =========================================================

    @Test
    @Order(10)
    public void shouldReturn404ForInvalidEndpoint() {

        given()

                .when()
                .get("/invalid-endpoint")

                .then()
                .statusCode(404);

        System.out.println("✅ INVALID ENDPOINT TEST OK");
    }

    // =========================================================
    // PERFORMANCE TEST
    // =========================================================

    @Test
    @Order(11)
    public void shouldRespondInLessThan2Seconds() {

        given()

                .when()
                .get("/bookings")

                .then()
                .time(lessThan(2000L));

        System.out.println("✅ PERFORMANCE TEST OK");
    }

    // =========================================================
    // CONTENT TYPE VALIDATION
    // =========================================================

    @Test
    @Order(12)
    public void shouldValidateContentType() {

        given()

                .when()
                .get("/bookings")

                .then()
                .contentType(ContentType.JSON);

        System.out.println("✅ CONTENT TYPE VALIDATION OK");
    }

    // =========================================================
    // SCHEMA BASIC VALIDATION
    // =========================================================

    @Test
    @Order(13)
    public void shouldValidateBookingSchema() {

        String body = """
        {
          "firstname": "Schema",
          "lastname": "Validation",
          "totalprice": 1000,
          "depositpaid": true,
          "bookingdates": {
            "checkin": "2026-09-01",
            "checkout": "2026-09-10"
          },
          "additionalneeds": "Breakfast"
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(body)

                .when()
                .post("/bookings")

                .then()
                .statusCode(201)
                .body("$", hasKey("id"))
                .body("$", hasKey("firstname"))
                .body("$", hasKey("lastname"))
                .body("$", hasKey("bookingdates"));

        System.out.println("✅ SCHEMA VALIDATION OK");
    }
}