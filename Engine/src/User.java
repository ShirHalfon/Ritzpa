import java.util.List;

public class User {

    private String              userName;
    private List<UserItem>      usersStocks;

    public User(String userName, List<UserItem> usersStocks) {
        this.userName = userName;
        this.usersStocks = usersStocks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<UserItem> getUsersStocks() {
        return usersStocks;
    }

    public void setUsersStocks(List<UserItem> usersStocks) {
        this.usersStocks = usersStocks;
    }
}
