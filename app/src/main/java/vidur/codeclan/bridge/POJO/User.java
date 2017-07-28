package vidur.codeclan.bridge.POJO;

/**
 * Created by vidur on 7/28/2017.
 */

public class User {
    String email;
    String user_id;
    String username;
    long mobile_number;

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMobile_number(long mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail() {

        return email;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public long getMobile_number() {
        return mobile_number;
    }

    public User(String email, String user_id, String username, long mobile_number) {

        this.email = email;
        this.user_id = user_id;
        this.username = username;
        this.mobile_number = mobile_number;
    }
}
