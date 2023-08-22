package pl.zbadajsie.przychodnia.integration.restController;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Map;

public interface AdminControllerTestSupport {

    RequestSpecification requestSpecificationAuthorization(String userName,String password);



    default String deleteUser(String userName) {
        ResponseBody body = requestSpecificationAuthorization("admin", "admin")
                .delete("/api/admin/delete/" + userName)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .response()
                .getBody();

        return body.asString();
    }



}
