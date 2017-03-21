package UI;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author mamour on 17/03/17.
 */
class WindowUtils {

    static JPanel header() {
        JPanel test = new JPanel();
        test.setLayout(new BorderLayout());
        test.setBackground(new Color(59, 89, 152));
        JLabel label = new JLabel("Association des Anciens de l'ESP\n");
        label.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
        test.add(label, BorderLayout.WEST);
        label.setForeground(Color.WHITE);
        return test;
    }

    static JPanel footer() {
        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footer.add(new JLabel("Parametres"));
        footer.add(new JLabel("Confidentialite"));
        footer.add(new JLabel("A propos"));
        footer.add(new JLabel("Aide"));
        footer.add(new JLabel("@Copyright AMAM 2017"));
        footer.setBackground(Color.WHITE);
        return footer;
    }

    static JLabel textAccueil() {
        return new JLabel("<html>Et quia Mesopotamiae tractus omnes crebro inquietari <br>" +
                "sueti praetenturis et stationibus servabantur agrariis, laevorsum flexo itinere <br>" +
                "Osdroenae subsederat extimas partes, novum parumque aliquando temptatum commentum <br>" +
                "adgressus. quod si impetrasset, <br>" +
                "fulminis modo cuncta vastarat. erat autem quod cogitabat huius modi.<br>" +
                "Inter has ruinarum varietates a Nisibi quam tuebatur accitus Vrsicinus, cui nos <br>" +
                "obsecuturos iunxerat imperiale praeceptum, dispicere litis exitialis certamina <br></html>", SwingConstants.CENTER);
    }

    static Border myBorder(String title, Color color, int epaisseur) {
        Border lineBorder = BorderFactory.createLineBorder(color, epaisseur);
        return BorderFactory.createTitledBorder(lineBorder,
                title,
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Helvetica Neue", Font.BOLD, 15), Color.BLACK);
    }
}