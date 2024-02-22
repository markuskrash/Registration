import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;

public class DataBase {
    public static Connection con = null;

    public void connect () throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:users.sqlite");
        String sql = """
                CREATE TABLE IF NOT EXISTS Users(
                    id         INTEGER PRIMARY KEY AUTOINCREMENT,
                    Login      TEXT    UNIQUE,
                    Password   TEXT,
                    FirstName  TEXT,
                    SecondName TEXT
                )""";
        PreparedStatement st = con.prepareStatement(sql);
        st.execute();
    }

    public String login() throws SQLException {
        String sql = "SELECT password FROM Users WHERE login = " + "'" + MainWindow.instance.firstJTF.getText() +  "'";
        PreparedStatement s = con.prepareStatement(sql);
        ResultSet resultSet = s.executeQuery();
        if (!resultSet.next()) {
            return "ERR";
        }
        else if (Objects.equals(resultSet.getString("password"), hex(MainWindow.instance.secondJTF.getText(), 5))){
            if (MainWindow.instance.firstJTF.getText().equals("admin")){
                return "admin";
            }
            return "OK";
        }
        else{
            return "ERR";
        }
    }

    public static String hex(String str, int k){

        byte[] messageDigest = new byte[0];
        str += "markuskrash";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(str.getBytes());
            messageDigest = md.digest();

        }catch (NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
        }
        BigInteger bigInt = new BigInteger(1, messageDigest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));
        while (md5Hex.length() < 32){
            md5Hex.insert(0, "0");
        }
        if (k == 0)return md5Hex.toString().toUpperCase();
        else return hex(md5Hex.toString(), k - 1);
    }


}
