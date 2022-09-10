package org.acme;

import static org.hamcrest.Matchers.oneOf;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@QuarkusTest
class GreetingResourceTest {

    @ParameterizedTest
    @MethodSource("paths")
    void malformedAccept(final String path) {
        RestAssured
                .given()
                .accept("*/*, a/b; q=MALFORMED")

                .when()
                .get(path)

                .then()
                .statusCode(oneOf(200, 204));
    }

    @ParameterizedTest
    @MethodSource("paths")
    void wellFormedAccept(final String path) {
        RestAssured
                .given()
                .accept("*/*, a/b; q=1")

                .when()
                .get(path)

                .then()
                .statusCode(oneOf(200, 204));
    }

    @ParameterizedTest
    @MethodSource("paths")
    void noAccept(final String path) {
        RestAssured
                .given()

                .when()
                .get(path)

                .then()
                .statusCode(oneOf(200, 204));
    }

    static Stream<Arguments> paths() {
        return Stream.of(
                /* FAIL */
                Arguments.of("multipleResponseNonNullOtherMimes"),
                Arguments.of("multipleResponseNonNull"),
                Arguments.of("multipleStringNonNull"),
                Arguments.of("multipleUniNonNull"),

                /* SUCCEED */
                Arguments.of("multipleResponseNoEntity"),
                Arguments.of("multipleResponseNull"),
                Arguments.of("multipleStringNull"),
                Arguments.of("multipleUniNullItem"),
                Arguments.of("multipleUniNull"),
                Arguments.of("singleResponseNonNull"),
                Arguments.of("singleStringNonNull"),
                Arguments.of("singleStringNull"),
                Arguments.of("singleUniNonNull"),
                Arguments.of("singleUniNull")
        );
    }
}
