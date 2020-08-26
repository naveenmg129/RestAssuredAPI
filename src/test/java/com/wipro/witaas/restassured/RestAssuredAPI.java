package com.wipro.witaas.restassured;


 

import org.testng.Assert;
import org.testng.annotations.Test;

 

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

 

public class RestAssuredAPI {
    
    @Test(priority=0,description="Authentication Method")
    public void AuthenticationRequestMethod()
    {
        String uriBase = "https://postman-echo.com";
        String username="postman";
        String password="password";
        
        RestAssured.baseURI ="https://postman-echo.com/put";
         RequestSpecification request = RestAssured.given();
        
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("postman");
        authScheme.setPassword("password");
        RestAssured.authentication = authScheme;
        
        Response response=
                given()
                .contentType("text/plain")
                .when()
                .get(uriBase);
        
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(),200);    
        
        System.out.println("Get Data Value: "+response.getBody().path("data"));
                
                
               
    }

 

    @Test(priority=1,description="Get Method")
    public void getRequestMethod()
    {
        String uriBase = "https://postman-echo.com/get";
        
        given()
          .relaxedHTTPSValidation()
          .param("foo1","bar1")
          .param("foo2","bar2")
        .when()
          .get(uriBase)
        .then()
          .assertThat().statusCode(200).and().
           body("args.foo1",containsString("bar1")).and().
           body("args.foo2",containsString("bar2")).
           //header("Content-Type","application/json; charset=utf-8");
           contentType(ContentType.JSON);
        
      
        
        
    }

 

    @Test(priority=2,description="Post Method")
    public void postRequestMethod() throws ParseException
    {
        String uriBase = "https://postman-echo.com/post";
        
        JSONObject jsonparameter=new JSONObject();
        jsonparameter.put("employee","Naveen kumar");
        jsonparameter.put("designation","Senior Project Engineer");
        
        Response response=given()
                .contentType("text/plain")
                .body(jsonparameter.toJSONString())
                .when()
                .post(uriBase);
        
        System.out.println("POST response\n"+ response.asString());
        Assert.assertEquals(response.statusCode(),200);    
        
        //System.out.println("Get Data Value: "+response.getBody().path("data"));
        
    }

 

    
    @Test(priority=3,description="Put Method")
    public void putMethod() throws ParseException
    {
        RestAssured.baseURI ="https://postman-echo.com/put";
         RequestSpecification request = RestAssured.given();
         
         JSONObject requestParams = new JSONObject();
         requestParams.put("employ", "Mike"); // Cast
         requestParams.put("designation", "Manager");
         
         
         request.body(requestParams.toJSONString());
         Response response = given().contentType("text/plain")
                    .body(requestParams.toJSONString())
                    .when()
                    .put(baseURI);
         
         int statusCode = response.getStatusCode();
         System.out.println(response.asString());
         Assert.assertEquals(statusCode, 200); 
         
         System.out.println("Put response\n"+ response.asString());
         
        
                    
            System.out.println("Get Data Value: "+response.getBody().path("data"));
    }
    
    @Test(priority=4,description="Get the List of user")
    public void DeleteMethod() 
    {
        RestAssured.baseURI ="https://postman-echo.com";
         RequestSpecification request = RestAssured.given();
         
         Headers header=given().when()
                 .delete("/delete").thenReturn().getHeaders();
         
         for(Header h:header)
             System.out.println(h.getName()+h.getValue());
         
         int status=given().delete("/delete").thenReturn().getStatusCode();
         System.out.println(status);
         
        
                     
     
    }
    

 

    
}