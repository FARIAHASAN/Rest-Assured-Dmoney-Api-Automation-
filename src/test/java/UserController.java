import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserController extends SetUp {
    String url;
    UserController() throws IOException {
        initConfig();
       url= property.getProperty("baseUrl");
    }

public void doLogIn(String email, String password) throws ConfigurationException {
    UserModel model = new UserModel(email,password);
    Response res =given().baseUri(url).contentType("application/json").body(model).when().post("/user/login");
    JsonPath jsonpath = res.jsonPath();
    String token = jsonpath.getString("token");
    Utils.setEnvVar("token",token);
   // System.out.println(res.asString());
}

public void createFirstCustomer(String name,String email, String password,String phone_number,String nid,String role) throws ConfigurationException, IOException, ParseException {
    UserModel model = new UserModel( name,email,password,phone_number,nid,role);
    Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).body(model).when().post("/user/create");
    //System.out.println("hei customer"+res.asString());
    JsonPath jpath = res.jsonPath();
   // System.out.println(String.valueOf(jpath.get("id")));
    String cus1_email = jpath.getString("user.email");
    String cus1_password = jpath.getString("user.password");
    String cus1_phone_number = jpath.getString("user.phone_number");
/*    Utils.setEnvVar("cus1_email",cus1_email);
    Utils.setEnvVar("cus1_password",cus1_password);
    Utils.setEnvVar("cus1_phone_number",cus1_phone_number);*/

    JSONObject object = new JSONObject();
    object.put("cus1_email",cus1_email);
    object.put("cus1_password",cus1_password);
    object.put("cus1_phone_number",cus1_phone_number);
    Utils.setInfo(object);
   // System.out.println(res.asString());
}
    public void createSecondCustomer(String name,String email, String password,String phone_number,String nid,String role) throws ConfigurationException, IOException, ParseException {
        UserModel model = new UserModel( name,email,password,phone_number,nid,role);
        Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).body(model).when().post("/user/create");
        // System.out.println(res.asString());
        JsonPath jpath = res.jsonPath();
        // System.out.println(String.valueOf(jpath.get("id")));
        String cus2_email = jpath.getString("user.email");
        String cus2_password = jpath.getString("user.password");
        String cus2_phone_number = jpath.getString("user.phone_number");
     /*   Utils.setEnvVar("cus2_email",cus2_email);
        Utils.setEnvVar("cus2_password",cus2_password);
        Utils.setEnvVar("cus2_phone_number",cus2_phone_number);*/
      //  System.out.println(res.asString());
        JSONObject object = new JSONObject();
        object.put("cus2_email",cus2_email);
        object.put("cus2_password",cus2_password);
        object.put("cus2_phone_number",cus2_phone_number);
        Utils.setInfo(object);
    }
    public void createAgent(String name,String email, String password,String phone_number,String nid,String role) throws ConfigurationException, IOException, ParseException {
        UserModel model = new UserModel( name,email,password,phone_number,nid,role);
        Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).body(model).when().post("/user/create");
        // System.out.println(res.asString());
        JsonPath jpath = res.jsonPath();
        // System.out.println(String.valueOf(jpath.get("id")));
        String agent_email = jpath.getString("user.email");
        String agent_password = jpath.getString("user.password");
        String agent_phone_number = jpath.getString("user.phone_number");
       /* Utils.setEnvVar("agent_email",agent_email);
        Utils.setEnvVar("agent_password",agent_password);
        Utils.setEnvVar("agent_phone_number",agent_phone_number);*/
        // System.out.println(res.asString());
        JSONObject object = new JSONObject();
        object.put("agent_email",agent_email);
        object.put("agent_password",agent_password);
        object.put("agent_phone_number",agent_phone_number);
        Utils.setInfo(object);

    }

    public void createMerchant(String name,String email, String password,String phone_number,String nid,String role) throws ConfigurationException, IOException, ParseException {
        UserModel model = new UserModel( name,email,password,phone_number,nid,role);
        Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).body(model).when().post("/user/create");
        // System.out.println(res.asString());
        JsonPath jpath = res.jsonPath();
        // System.out.println(String.valueOf(jpath.get("id")));
        String  merchant_email = jpath.getString("user.email");
        String merchant_password = jpath.getString("user.password");
        String merchant_phone_number = jpath.getString("user.phone_number");
        /*Utils.setEnvVar("merchant_email",merchant_email);
        Utils.setEnvVar("merchant_password",merchant_password);
        Utils.setEnvVar("merchant_phone_number",merchant_phone_number);*/
       // System.out.println(res.asString());
        JSONObject object = new JSONObject();
        object.put("merchant_email",merchant_email);
        object.put("merchant_password",merchant_password);
        object.put("merchant_phone_number",merchant_phone_number);
        Utils.setInfo(object);

    }
    public void depositToAgent(String from_account, String to_account,Integer amount) throws ConfigurationException {
        UserModel model = new UserModel(from_account,to_account,amount);
        Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).body(model).when().post("/transaction/deposit");
        System.out.println(res.asString());

    }
    public void checkBalance(String message,String phone_number)
    {
        Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).when().get("/transaction/balance/"+phone_number);
        JsonPath jsonPath = res.jsonPath();
        System.out.println(res.asString());
        Integer balance = jsonPath.getInt("balance");
        System.out.println(message +"  "+ balance);

    }
    public void depositToCustomer(String from_account, String to_account,Integer amount) throws ConfigurationException {
        UserModel model = new UserModel(from_account,to_account,amount);
        Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).body(model).when().post("/transaction/deposit");
        System.out.println(res.asString());
    }
    public void withdrawByCustomer(String from_account, String to_account,Integer amount) throws ConfigurationException {
        UserModel model = new UserModel(from_account,to_account,amount);
        Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).body(model).when().post("/transaction/withdraw");
        System.out.println(res.asString());

    }
    public void sendMoneyByCustomer(String from_account, String to_account,Integer amount) throws ConfigurationException {
        UserModel model = new UserModel(from_account,to_account,amount);
        Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).body(model).when().post("/transaction/sendmoney");
       System.out.println(res.asString());
    }
    public void paymentByCustomer(String from_account, String to_account,Integer amount) throws ConfigurationException {
        UserModel model = new UserModel(from_account,to_account,amount);
        Response res =given().baseUri(url).contentType("application/json").headers("authorization","Bearer "+property.getProperty("token"),"X-AUTH-SECRET-KEY",property.getProperty("X-AUTH-SECRET-KEY")).body(model).when().post("/transaction/payment");
         System.out.println(res.asString());
    }



}
