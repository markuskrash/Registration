import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SignUpWindow extends JFrame {
    public JTextField firstNameJTF = new JTextField();
    public JTextField secondNameJTF = new JTextField();

    public JButton signUpBtn = new JButton("Sign up");
    public SignUpWindow(String login, String password){
        setSize(500, 300);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Sign up");

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(firstNameJTF, gbc);
        gbc = new GridBagConstraints(
                0, 1, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(secondNameJTF, gbc);

        gbc = new GridBagConstraints(
                0, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(signUpBtn, gbc);

        signUpBtn.addActionListener(e -> {
            User user = new User(login, password, firstNameJTF.getName(), secondNameJTF.getName());
            try {
                user.save();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
    }
}
