package TestCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by verushkat on 9/9/2020
 */

public class TC_CountryCodes {

        @DataProvider(name="dataProvider")
        public static Object[][] zipCodesAndPlaces(){
                    return  new  Object[][]{

                            { "us", "90210", "Beverly Hills" },
                            { "us", "12345", "Schenectady" },
                            { "ca", "B2R", "Waverley"}
                    };
        }
        @Test(dataProvider="dataProvider")
        public void requestZipCodesFromCollection_checkPlaceNameInResponseBody_expectSpecifiedPlaceName(String countryCode,String zipCode, String expectedPlaceName){

                given().
                        pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().
                        get("http://zippopotam.us/{countryCode}/{zipCode}").
                then().
                        assertThat().
                        body("places[0].'place name'", equalTo("Schenectady"));



        }





        }













