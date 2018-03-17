package com.bootcamp;

import com.jayway.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * @author ibrahim
 * @see InfoDebatIntegrationTest is the integration test for this service
 * if every thing goes well,it will return 200 httpStatus code
 */
public class InfoDebatIntegrationTest {
    private static Logger logger = LogManager.getLogger( InfoDebatIntegrationTest.class );
    /**
     * The Base URI of debat use case service,
     * it can be change with the online URI of this service.
     */
    private String BASE_URI = "http://localhost:8095/debat";

    /**
     * The base path of the use case, according to this controller implementation
     */
    private String INFO_DEBAT_PATH = "/infodebats";

    /**
     * @see InfoDebatIntegrationTest#getInfoDebatWS() is the method whitch test
     * @see com.bootcamp.controllers.InfoDebatController#getDebatInfoByEntityId(String, int) method
     * a 200 httpStatus code will be raise if OK
     * @throws Exception
     */
    @Test(priority = 0, groups = {"ProjetTest"})
    public void getInfoDebatWS() throws Exception {
        String entityType = "PROJET";
        int entityId = 1;
        String getURI = BASE_URI + INFO_DEBAT_PATH + "/" + entityType + "/" + entityId;
        Response response = given()
                .log().all()
                .contentType( "application/json" )
                .expect()
                .when()
                .get( getURI );
        logger.debug( response.getBody().prettyPrint() );
        Assert.assertEquals( response.statusCode(), 200 );

    }

}
