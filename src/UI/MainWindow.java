package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.NumberFormat;

/**
 * @author mamour on 17/03/17.
 */
public class MainWindow extends JFrame implements ActionListener {
    /**
     * Panneaux qui seront utilises dans notre
     * aplication
     */
    private JPanel
            mainPanel /*Panneau principal*/,
            header /* Header*/,
            footer /* Footer*/,
            panelLogin /* Panneau pour le formulaire d'authentification*/,
            panelContent/* Panneau qui va gerer le contenu de notre app*/,
            accueilContent /*Panneau qui va contenir les elements de la page d'accueil*/,
            activity /* Panneau qui va contenir les activites de la personne connecte*/,
            formAdd /* Panneau qui va contenir le formulaire d'ajout*/,
            formSearch/*Panneau qui va contenir le formulaire de recherche*/;

    /**
     * Boutons de notre application
     */
    private JButton
            connexion /* Bouton de connexion*/,
            deconnexion /* Bouton de deconnexion*/,
            accueil /* Bouton de navigation du menu qui charge la page d'accueil*/,
            addMember /* Bouton de navigation du menu qui charge
                         le formulaire d'ajout*/,
            editMember /*Modification des infos d'un membre*/,
            rmMember /*Suppression d'un membre*/,
            searchMember /* Bouton de navigation du menu qui charge
                            le formulaire de recherche*/,
            submitAddMember /* Bouton de validation pour l'ajout*/,
            submitSearch /* Bouton de validation pour la recherche*/;

    /**
     * Label utilises
     */
    private JLabel
            textEcranConnexion /* Text de l'ecran de Connexion*/,
            cover /* Photo de couverture*/;

    /**
     * Zone de saisies
     */
    private JTextField
            login /* Login du membre  a l'authentification*/,
            passwd /* Mot de passe du membre  a l'authentification*/,
            firstName /* Prenom du membre */,
            lastName /* Nom du membre */,
            adress /* Adresse du membre */,
            email /* Email du membre */,
            loginMember /*Login du membre choisi lors de l'ajout*/,
            passwdMember /*Mot de passe du membre choisi lors de l'ajout*/;

    private JFormattedTextField
            numPhone /* Numero telephone du membre */,
            dateBirth /* Date de naisance du membre */,
            annee /* Annee formation suivie*/,
            faxe /* Faxe du memebre */,
            telOffice /* Telephone bureau du membre*/,
            numToSearch /* Numero du membre a chercher*/;
    /**
     * Zone de choix
     */
    private JComboBox<String>
            departements /*Departements */,
            niveau /*Niveau */,
            options /*Options */;

    private static Font myFont = new Font("Helvetica Neue", Font.BOLD, 15);


    /**
     * Creation de la fenetre principale
     * L'affichage se fera en plein ecran
     */
    public MainWindow() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.getContentPane().add(mainPanel);
        this.setExtendedState(MainWindow.MAXIMIZED_BOTH);
        header = WindowUtils.header();
        footer = WindowUtils.footer();
        textEcranConnexion = WindowUtils.textAccueil();
        textEcranConnexion.setFont(myFont);
        this.formAdd = this.formAdd();
        this.formSearch = this.formSearch();
        this.accueilContent = this.accueilContent();
    }

    /**
     * Retourne le menu de notre application
     *
     * @return JPanel
     */
    private JPanel menu() {
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.LEFT));
        menu.setBackground(new Color(50, 50, 100));

        accueil = new JButton("Accueil");
        searchMember = new JButton("Rechercher");
        addMember = new JButton("Ajouter");
        deconnexion = new JButton("Deconnexion");

        accueil.addActionListener(this);
        searchMember.addActionListener(this);
        addMember.addActionListener(this);
        deconnexion.addActionListener(this);

        accueil.setContentAreaFilled(false);
        searchMember.setContentAreaFilled(false);
        addMember.setContentAreaFilled(false);
        deconnexion.setContentAreaFilled(false);

        accueil.setForeground(Color.WHITE);
        searchMember.setForeground(Color.WHITE);
        addMember.setForeground(Color.WHITE);
        deconnexion.setForeground(Color.WHITE);


        menu.add(accueil);
        menu.add(addMember);
        menu.add(searchMember);
        menu.add(deconnexion);

        menu.setBorder(WindowUtils.myBorder("Menu", Color.WHITE, 1));

        return menu;
    }

    /**
     * Retourne le panel d'authentification
     *
     * @return JPanel
     */
    private JPanel panelLogin() {
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelLogin.setBackground(new Color(50, 50, 100));

        JLabel labelLogin = new JLabel("Login ");
        JLabel labelPasswd = new JLabel("Mot de passe ");
        labelLogin.setForeground(Color.WHITE);
        labelPasswd.setForeground(Color.WHITE);
        labelLogin.setFont(myFont);
        labelPasswd.setFont(myFont);

        panelLogin.add(labelLogin);
        panelLogin.add(login = new JTextField(10));
        panelLogin.add(labelPasswd);
        panelLogin.add(passwd = new JTextField(10));

        connexion = new JButton("connexion");
        connexion.addActionListener(this);
        panelLogin.add(connexion);

        panelLogin.setBorder(WindowUtils.myBorder("connexion", Color.WHITE, 1));

        return panelLogin;
    }

    /**
     * Ecran de connexion
     */
    public void authentification() {
        panelContent = new JPanel();
        panelLogin = this.panelLogin();
        panelContent.setLayout(new BorderLayout());
        header.add(panelLogin, BorderLayout.EAST);
        panelContent.add(header, BorderLayout.NORTH);
        panelContent.add(textEcranConnexion, BorderLayout.EAST);
        panelContent.add(footer, BorderLayout.SOUTH);
        try {
            BufferedImage myPicture =
                    ImageIO.read(getClass().getResource("./img.jpg"));
            cover = new JLabel(new ImageIcon(myPicture));
            panelContent.add(cover, BorderLayout.WEST);
        } catch (IOException io) {
            io.printStackTrace();
        }
        panelContent.setBackground(Color.CYAN);
        JScrollPane bar = new JScrollPane(panelContent);
        bar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(bar);
        mainPanel.add(panelContent, BorderLayout.CENTER);
        this.pack();
    }

    private JPanel accueilActivity() {
        JPanel activity = new JPanel();
        JPanel contentActivity = new JPanel();
        contentActivity.setBackground(Color.WHITE);
        activity.setLayout(new BorderLayout());
        contentActivity.setLayout(new BoxLayout(contentActivity, BoxLayout.PAGE_AXIS));
        JLabel activity1 = new JLabel("Activity");
        activity1.setFont(new Font("Arial", Font.BOLD, 38));
        contentActivity.add(activity1);
        contentActivity.add(new JLabel("Text1"));
        contentActivity.add(new JLabel("Text2"));
        contentActivity.add(new JLabel("Text3"));
        contentActivity.add(new JLabel("Test4"));
        activity.add(contentActivity, BorderLayout.CENTER);
        activity.setBorder(WindowUtils.myBorder("Activite", Color.BLACK, 1));
        return activity;
    }

    private JPanel accueilContent() {
        JPanel accueilContent = new JPanel();
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBackground(Color.CYAN);
        JLabel pic;
        accueilContent.setLayout(new BorderLayout());
        try {
            BufferedImage myPicture =
                    ImageIO.read(getClass().getResource("./img.jpg"));
            pic = new JLabel(new ImageIcon(myPicture));
            content.add(pic);
            accueilContent.add(content, BorderLayout.WEST);
        } catch (IOException io) {
            io.printStackTrace();
        }
        accueilContent.setBackground(Color.CYAN);
        return accueilContent;
    }

    /**
     * Retourne le formulaire d'ajout
     *
     * @return JPanel
     */
    private JPanel formAdd() {
        /*Mise en place des sections et du JPanel qui sera retourne*/
        JPanel formAdd, privacy, formation, contact, infoAuth;
        formAdd = new JPanel();
        infoAuth = new JPanel();
        privacy = new JPanel();
        contact = new JPanel();
        formation = new JPanel();
        formAdd.setLayout(new BoxLayout(formAdd, BoxLayout.PAGE_AXIS));
        infoAuth.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        privacy.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contact.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formation.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        /*Section login et mot de passe*/
        infoAuth.add(new JLabel("Login"));
        infoAuth.add(loginMember = new JTextField(15));
        loginMember.setPreferredSize(new Dimension(15, 30));
        infoAuth.add(new JLabel("Mot de passe"));
        infoAuth.add(passwdMember = new JTextField(15));
        passwdMember.setPreferredSize(new Dimension(15, 30));
        infoAuth.setBorder(WindowUtils.myBorder("Login et Mot de passe", new Color(50, 50, 100), 5));
        infoAuth.setBackground(Color.WHITE);

        /*Section informations personnelles*/
        privacy.add(new JLabel("Telephone"));
        privacy.add(numPhone = new JFormattedTextField(NumberFormat.getIntegerInstance()));
        numPhone.setPreferredSize(new Dimension(150, 30));
        privacy.add(new JLabel("Prenom"));
        privacy.add(firstName = new JTextField(15));
        firstName.setPreferredSize(new Dimension(15, 30));
        privacy.add(new JLabel("Nom"));
        privacy.add(lastName = new JTextField(15));
        lastName.setPreferredSize(new Dimension(15, 30));
        privacy.add(new JLabel("Date de Naissance"));
        privacy.add(dateBirth = new JFormattedTextField(NumberFormat.getIntegerInstance()));
        dateBirth.setPreferredSize(new Dimension(150, 30));
        privacy.setBorder(WindowUtils.myBorder("Informations Personnelles",
                new Color(50, 50, 100), 5));
        privacy.setBackground(Color.WHITE);

        /*Section contact*/
        contact.add(new JLabel("Adresse"));
        contact.add(adress = new JTextField(15));
        adress.setPreferredSize(new Dimension(15, 30));
        contact.add(new JLabel("Email"));
        contact.add(email = new JTextField(15));
        email.setPreferredSize(new Dimension(15, 30));
        contact.add(new JLabel("Faxe"));
        contact.add(faxe = new JFormattedTextField(NumberFormat.getIntegerInstance()));
        faxe.setPreferredSize(new Dimension(150, 30));
        contact.add(new JLabel("Tel Bureau"));
        contact.add(telOffice = new JFormattedTextField(NumberFormat.getIntegerInstance()));
        telOffice.setPreferredSize(new Dimension(150, 30));
        contact.setBorder(WindowUtils.myBorder("Contact",
                new Color(50, 50, 100), 5));
        contact.setBackground(Color.WHITE);

        /*Section formations*/
        formation.add(new JLabel("Departements"));
        departements = new JComboBox<>();
        departements.addItem("Genie Informatique");
        departements.addItem("Genie Mecanique");
        departements.addItem("Genie Electrique");
        departements.addItem("Genie Civil");
        departements.addItem("Genie Chimique et BA");
        departements.addItem("Gestion");
        formation.add(departements);
        formation.add(new JLabel("Niveau"));
        niveau = new JComboBox<>();
        niveau.addItem("DUT");
        niveau.addItem("DST");
        niveau.addItem("DIC");
        niveau.addItem("DESCAF");
        niveau.addItem("DEC");
        niveau.addItem("DIT");
        formation.add(niveau);
        formation.add(new JLabel("Options"));
        options = new JComboBox<>();
        options.addItem("Informatique");
        options.addItem("Telecoms et Reseaux");
        options.addItem("Biologie Appliquee");
        options.addItem("Civil");
        options.addItem("Mecanique");
        formation.add(options);
        formation.add(new JLabel("Annee"));
        formation.add(annee = new JFormattedTextField(NumberFormat.getIntegerInstance()));
        annee.setPreferredSize(new Dimension(150, 30));
        formation.setBorder(WindowUtils.myBorder("Formations Suivies", new Color(50, 50, 100), 5));
        formation.setBackground(Color.WHITE);

        /*Bouton de validation*/
        submitAddMember = new JButton("Ajouter");
        submitAddMember.setBackground(new Color(50, 50, 100));
        submitAddMember.setForeground(Color.WHITE);

        /*Ajout des differents panneaux dans le panneau formAdd */
        formAdd.add(infoAuth);
        formAdd.add(privacy);
        formAdd.add(contact);
        formAdd.add(formation);
        formAdd.add(submitAddMember);

        return formAdd;
    }

    /**
     * Formulaire de Recherche
     *
     * @return JPanel
     */
    private JPanel formSearch() {
        /*Mise en place du panel*/
        JPanel formSearch = new JPanel();
        formSearch.setLayout(new FlowLayout(FlowLayout.CENTER));

        /*Ajout des champs*/
        formSearch.add(new JLabel("Numero"));
        formSearch.add(numToSearch = new JFormattedTextField(NumberFormat.getIntegerInstance()));
        numToSearch.setPreferredSize(new Dimension(150, 30));
        submitSearch = new JButton("Rechercher");
        submitSearch.setBackground(new Color(50, 50, 100));
        submitSearch.setForeground(Color.WHITE);
        formSearch.add(submitSearch);
        formSearch.setBorder(WindowUtils.myBorder("Recherche", new Color(50, 50, 100), 5));
        formSearch.setBackground(Color.WHITE);

        return formSearch;
    }

    /**
     * Efface l'ecran d'authentification
     */
    private void cleanEcranAuth() {
        header.remove(panelLogin);
        header.repaint();
        header.add(this.menu(), BorderLayout.EAST);
        panelContent.remove(textEcranConnexion);
        panelContent.remove(cover);
        try {
            BufferedImage myPicture =
                    ImageIO.read(getClass().getResource("./img3.jpg"));
            Image myPictureScaled = myPicture.getScaledInstance(header.getWidth(),
                    header.getHeight() + 200, Image.SCALE_SMOOTH);
            cover = new JLabel(new ImageIcon(myPictureScaled));
        } catch (IOException io) {
            io.printStackTrace();
        }
        mainPanel.add(cover, BorderLayout.NORTH);
        panelContent.repaint();
    }

    /**
     * Routine qui sera executee lors de
     * l'evenement click sur le bouton connexion
     */
    private void actionConnexion() {
        this.accueilContent = this.accueilContent();
        this.activity = this.accueilActivity();
        panelContent.add(this.accueilContent, BorderLayout.WEST);
        panelContent.add(this.activity, BorderLayout.EAST);
    }

    /**
     * Routine qui sera executee lors de
     * l'evenement click sur le bouton addMember
     */
    private void actionAddMember() {
        panelContent.repaint();
        this.formAdd = this.formAdd();
        panelContent.add(formAdd, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connexion) {
            this.cleanEcranAuth();
            this.actionConnexion();
        }
        if (e.getSource() == accueil) {
            if (formAdd.isVisible())
                formAdd.setVisible(false);
            if (formSearch.isVisible())
                formSearch.setVisible(false);
            this.actionConnexion();
        }
        if (e.getSource() == addMember) {
            if (accueilContent.isVisible())
                accueilContent.setVisible(false);
            if (formSearch.isVisible())
                formSearch.setVisible(false);
            this.actionAddMember();
        }
        if (e.getSource() == searchMember) {
            if (accueilContent.isVisible())
                accueilContent.setVisible(false);
            if (formAdd.isVisible())
                formAdd.setVisible(false);
            this.formSearch = this.formSearch();
            panelContent.add(this.formSearch, BorderLayout.CENTER);
        }
        this.pack();
    }
}