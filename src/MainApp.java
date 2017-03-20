import UI.MainWindow;

import javax.swing.*;

/**
 * @author mamour on 16/03/17.
 */
public class MainApp {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainWindow.authentification();

    }
}