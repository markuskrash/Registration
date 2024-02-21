import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserWindow extends JFrame {
    public UserWindow(String user) {
        JLabel welcomeLabel = new JLabel(String.format("Здравствуйте, %s. Вы успешно вошли в систему", user));
        JLabel nameUser = new JLabel(user);
        JButton logOutBtn = new JButton("Log Out");


        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameUser.setHorizontalAlignment(SwingConstants.CENTER);


        setSize(500, 300);
        setLayout(new GridBagLayout());
        setLocation(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 2, 1, 25, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(nameUser, gbc);

        gbc = new GridBagConstraints(
                2, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(logOutBtn, gbc);

        gbc = new GridBagConstraints(
                0, 1, 3, 1, 1, 7,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(welcomeLabel, gbc);

        logOutBtn.addActionListener(e -> {
            try {
                MainWindow wnd = new MainWindow();
                wnd.setVisible(true);
                dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });

    }
}
