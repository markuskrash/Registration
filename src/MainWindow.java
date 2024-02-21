import javax.swing.*;
import java.awt.*;

import java.sql.SQLException;

public class MainWindow extends JFrame {
    public static MainWindow instance;
    public DataBase dataBase = new DataBase();

    public JTextField firstJTF = new JTextField();
    public JTextField secondJTF = new JTextField();

    public JButton logInBtn = new JButton("Log in");
    public JButton signUpBtn = new JButton("Sign up");

    public MainWindow() throws SQLException {
        instance = this;
        dataBase.connect();


        setSize(500, 300);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Log in or sign up");

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(firstJTF, gbc);
        gbc = new GridBagConstraints(
                0, 1, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(secondJTF, gbc);

        gbc = new GridBagConstraints(
                0, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(signUpBtn, gbc);

        gbc = new GridBagConstraints(
                1, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(logInBtn, gbc);

        logInBtn.addActionListener(e -> {
            try{
                switch (dataBase.login()){
                    case "admin":
                        AdminWindow wnd = new AdminWindow();
                        wnd.setVisible(true);
                        dispose();
                        break;
                    case "OK":
                        UserWindow wndUser = new UserWindow(firstJTF.getText());
                        wndUser.setVisible(true);
                        dispose();
                        break;
                    case "ERR":
                        System.out.println("Вы неверно ввели имя пользователя или пароль, попробуй ещё раз.");
                       secondJTF.setText("");
                        secondJTF.setText("");
                        break;
                }
            }catch (SQLException l){
                System.out.println(l.getMessage());
            }
        });

        signUpBtn.addActionListener(e -> {
            SignUpWindow wndSignUp = new SignUpWindow();
            wndSignUp.setVisible(true);
        });
    }

    public static void main(String[] args) throws InterruptedException, SQLException {
        MainWindow wnd = new MainWindow();

        wnd.setVisible(true);
    }

}
