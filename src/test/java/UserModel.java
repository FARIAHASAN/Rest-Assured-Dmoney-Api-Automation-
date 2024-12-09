import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {
    private String email ,phone_number,nid, role,password, name, from_account,to_account;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom_account() {
        return from_account;
    }

    public void setFrom_account(String from_account) {
        this.from_account = from_account;
    }

    public String getTo_account() {
        return to_account;
    }

    public void setTo_account(String to_account) {
        this.to_account = to_account;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    private Integer amount;


    public UserModel(String from_account, String to_account, int amount) {
        setFrom_account(from_account);
        setTo_account(to_account);
        setAmount(amount);
    }

    UserModel(String email, String password)
    {
        setEmail(email);
        setPassword(password);
    }
    UserModel(String name,String email, String password,String phone_number,String nid,String role)
    {
        setEmail(email);
        setPassword(password);
        setName(name);
        setPhone_number(phone_number);
        setNid(nid);
        setRole(role);
    }


}
