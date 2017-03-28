package UI;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author AmaM on 17/03/17.
 */
class WindowUtils {
    static Color colorForm = new Color(1, 157, 228);

    static JPanel header() {
        JPanel test = new JPanel();
        test.setLayout(new FlowLayout(FlowLayout.CENTER));
        test.setBackground(new Color(29, 32, 34));
        JLabel label = new JLabel("Association des Anciens de l'ESP\n");
        label.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
        test.add(label, BorderLayout.CENTER);
        label.setForeground(Color.WHITE);
        return test;
    }

    static JPanel footer() {
        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footer.add(new JLabel("@Copyright AMAM 2017"));
        footer.setBackground(Color.WHITE);
        return footer;
    }

    static JLabel textAccueil() {
        return new JLabel("<html>Le but de cette association d'anciens élèves est :<br>" +
                "<br>" +
                " d'aider son établissement à poursuivre sa tâche éducative :<br>" +
                " aide financière, apport d'expertise et de compétence, <br>" +
                " aide à l'entrée dans la vie active, <br>" +
                " aides pour les fêtes de l'établissement…<br>" +
                "Elle permet de prolonger les liens de camaraderie, <br>" +
                "de fédérer les étudiants actuels et anciens de l'école autour <br>" +
                "d'un réseau pour favoriser les échanges et l'insertion professionnelle.<br>" +
                " Les anciens élèves sont la « mémoire » de l'établissement !!! </html>", SwingConstants.CENTER);
    }

    static Border myBorder(String title, Color color, int epaisseur) {
        Border lineBorder = BorderFactory.createLineBorder(color, epaisseur);
        return BorderFactory.createTitledBorder(lineBorder,
                title,
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Helvetica Neue", Font.BOLD, 15), Color.BLACK);
    }
}