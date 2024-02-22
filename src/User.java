import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    public String login;
    public String password;
    public String firstName;
    public String secondName;

    public User(String login, String password, String firstName, String secondName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public boolean save() throws SQLException {
        String sql = "SELECT Login FROM Users WHERE Login = " + "'" + login + "'";
        PreparedStatement s = DataBase.con.prepareStatement(sql);
        ResultSet resultSet = s.executeQuery();

        if (resultSet.next()){
            System.out.println("Данный логин уже занят.");
        }
        else{
            if (password.length() <= 3){
                System.out.println("Введите другой пароль. Минимальный размер пароля 3 символа");
            }
            else if (login.isEmpty()){
                System.out.println("Введите другой логин. Минимальный размер логина 1 символ");
            }
            else{
                password = DataBase.hex(password, 5);
                login = login.replaceAll(" ", "");

                sql = String.format("INSERT INTO Users (login, password, firstName, secondName) VALUES ('%s', '%s', '%s', '%s')", login, password, firstName, secondName);
                PreparedStatement st = DataBase.con.prepareStatement(sql);
                st.execute();
                return true;
            }
        }
        return false;
    }

}
