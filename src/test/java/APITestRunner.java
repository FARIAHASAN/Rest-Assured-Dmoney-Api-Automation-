import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class APITestRunner extends  SetUp{
    @BeforeTest
    public void doResetUserFile() throws IOException, ParseException {
        Utils.resetUserFile();
    }
    @Test(priority = 1,description= "Admin Login into the system with valid credentials")
    public  void doLogIn() throws IOException, ConfigurationException {
        String email = "admin@roadtocareer.net";
        String password = "1234";
        UserController userController = new UserController();
        userController.doLogIn(email,password);

    }
    @Test(priority =2, description = "Admin creates a customer")
    public void doCreateFirstCustomer() throws IOException, ConfigurationException, ParseException {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress();
        String nid=String.valueOf( Utils.randomNumber(1000,9999))+"23"+String.valueOf( Utils.randomNumber(1000,9999));
        String role = "Customer";
        String phone_number = String.valueOf( Utils.randomNumber(1000,9999))+"523"+String.valueOf( Utils.randomNumber(1000,9999));
        UserController userController = new UserController();
        userController.createFirstCustomer(name,email,password,phone_number,nid,role);

    }
    @Test(priority =3, description = "Admin creates another customer")
    public void doCreateSecondCustomer() throws IOException, ConfigurationException, ParseException {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress();
        String nid=String.valueOf( Utils.randomNumber(1000,9999))+"23"+String.valueOf( Utils.randomNumber(1000,9999));
        String role = "Customer";
        String phone_number = String.valueOf( Utils.randomNumber(1000,9999))+"523"+String.valueOf( Utils.randomNumber(1000,9999));
        UserController userController = new UserController();
        userController.createSecondCustomer(name,email,password,phone_number,nid,role);

    }
    @Test(priority =4, description = "Admin creates an agent")
    public void doCreateAgent() throws IOException, ConfigurationException, ParseException {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress();
        String nid=String.valueOf( Utils.randomNumber(1000,9999))+"23"+String.valueOf( Utils.randomNumber(1000,9999));
        String role = "Agent";
        String phone_number = String.valueOf( Utils.randomNumber(1000,9999))+"523"+String.valueOf( Utils.randomNumber(1000,9999));
        UserController userController = new UserController();
        userController.createAgent(name,email,password,phone_number,nid,role);

    }
    @Test(priority =5,description = "Admin creates a merchant")
    public void doCreateMerchant() throws IOException, ConfigurationException, ParseException {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress();
        String nid=String.valueOf( Utils.randomNumber(1000,9999))+"23"+String.valueOf( Utils.randomNumber(1000,9999));
        String role = "Merchant";
        String phone_number = String.valueOf( Utils.randomNumber(1000,9999))+"523"+String.valueOf( Utils.randomNumber(1000,9999));
        UserController userController = new UserController();
        userController.createMerchant(name,email,password,phone_number,nid,role);

    }
    @Test(priority =6, description = "Admin(System) deposits money to agent")
    public void doDepositToAgent() throws IOException, ConfigurationException, ParseException {


        String from_account ="SYSTEM";
        String to_account = (String) Utils.getInfo(2).get("agent_phone_number");
        Integer amount=2000;
        UserController userController = new UserController();
        userController.depositToAgent(from_account,to_account,amount);
    }

    @Test(priority =7,description = "Agent checks for balance")
    public void checkAgentBalance() throws IOException, ParseException {
        UserController userController = new UserController();
        String message ="Agent Balance Check";
        userController.checkBalance(message, (String) Utils.getInfo(2).get("agent_phone_number"));
    }

    @Test(priority =8, description = "Agent deposits money to customer")
    public void doDepositToCustomer() throws IOException, ConfigurationException, ParseException {


        String from_account =(String) Utils.getInfo(2).get("agent_phone_number");
        String to_account = (String) Utils.getInfo(0).get("cus1_phone_number");
        Integer amount=1500;
        UserController userController = new UserController();
        userController.depositToCustomer(from_account,to_account,amount);
    }
    @Test(priority =9, description = "Customer checks balance")
    public void checkCustomerBalance() throws IOException, ParseException {
        UserController userController = new UserController();
        String message ="Customer Balance Check";
        userController.checkBalance(message, (String) Utils.getInfo(0).get("cus1_phone_number"));
    }
    @Test(priority =10,description = "Customer withdraw money")
    public void withdrawByCustomer() throws IOException, ConfigurationException, ParseException {


        String to_account =(String) Utils.getInfo(2).get("agent_phone_number");
        String from_account = (String) Utils.getInfo(0).get("cus1_phone_number");
        Integer amount=500;
        UserController userController = new UserController();
        userController.withdrawByCustomer(from_account,to_account,amount);
    }
    @Test(priority =11, description = "Customer checks balance after withdraw")
    public void checkBalanceAfterWithdraw() throws IOException, ParseException {
        UserController userController = new UserController();
        String message ="Customer Balance After withdraw: ";
        userController.checkBalance(message, (String) Utils.getInfo(0).get("cus1_phone_number"));
    }
    @Test(priority =12,description = "Customer performs sendmoney")
    public void sendmoneyByCustomer() throws IOException, ConfigurationException, ParseException {


        String from_account  =(String) Utils.getInfo(0).get("cus1_phone_number");
        String to_account = (String) Utils.getInfo(1).get("cus2_phone_number");
        Integer amount=500;
        UserController userController = new UserController();
        userController.sendMoneyByCustomer(from_account,to_account,amount);
    }
    @Test(priority =13,description = "Customer checks balance after sendmoney")
    public void checkBalanceAfterSendMoney() throws IOException, ParseException {
        UserController userController = new UserController();
        String message ="Customer Balance After send money: ";
        userController.checkBalance(message, (String) Utils.getInfo(0).get("cus1_phone_number"));
    }
    @Test(priority =14,description = "Recipient customer checks balance")
    public void checkBalanceOfRecipient() throws IOException, ParseException {
        UserController userController = new UserController();
        String message ="Customer2 Balance After receiving money from customer 1: ";
        userController.checkBalance(message, (String) Utils.getInfo(1).get("cus2_phone_number"));
    }

    @Test(priority =15,description = "Customer makes payment")
    public void paymentByCustomer() throws IOException, ConfigurationException, ParseException {
        String from_account  =(String) Utils.getInfo(1).get("cus2_phone_number");

        String to_account = (String) Utils.getInfo(3).get("merchant_phone_number");
        Integer amount=100;
        UserController userController = new UserController();
        userController.paymentByCustomer(from_account,to_account,amount);

    }
    @Test(priority =16,description = "Customer checks balance after payment")
    public void checkBalanceAfterPayment() throws IOException, ParseException {
        UserController userController = new UserController();
        String message ="Customer2 Balance After making payment to Merchant: ";
        userController.checkBalance(message, (String) Utils.getInfo(1).get("cus2_phone_number"));
    }
}
