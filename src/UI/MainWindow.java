package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.NumberFormat;
import BaseDonnees.* ;
import java.util.* ;
import java.text.*;
import java.sql.Date ;

/**
 * @author mamour on 17/03/17.
 */
public class MainWindow extends JFrame implements ActionListener {
    /**
     * Panneaux qui seront utilises dans notre
     * aplication
     */
    private JPanel
            mainPanel /*Panneau principal*/;
    private JPanel header /* Header*/;
    private JPanel menu /* Menu*/;
    private final JPanel footer /* Footer*/;
    private JPanel panelLogin /* Panneau pour le formulaire d'authentification*/;
    private JPanel panelContent/* Panneau qui va gerer le contenu de notre app*/;
    private JPanel accueilContent /*Panneau qui va contenir les elements de la page d'accueil*/;
    private JPanel activity /* Panneau qui va contenir les activites de la personne connecte*/;
    private JPanel formAdd /* Panneau qui va contenir le formulaire d'ajout*/;
    private JPanel formSearch/*Panneau qui va contenir le formulaire de recherche*/;
    private JPanel mainFormation;
    private JPanel formation;
    private JPanel panelSearch;
    private JPanel panelResultat;

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
            submitSearch /* Bouton de validation pour la recherche*/,
            submitModifyMember,/*Bouton validation de modif des infos */
            submitModifyFormation,/*Bouton validation de modif des formations */
            addFormation /* Permet d'ajouter une ou plusieurs autres formations*/;

    /**
     * Label utilises
     */
    private final JLabel
            textEcranConnexion /* Text de l'ecran de Connexion*/;
    private JLabel cover /* Photo de couverture*/;
    private JLabel logo /* logo UCAD*/;


    /**
     * Zone de saisies
     */
    private JTextField
            login /* Login du membre  a l'authentification*/,
            firstName /* Prenom du membre */,
            lastName /* Nom du membre */,
            adress /* Adresse du membre */,
            email /* Email du membre */,
            dateBirth /* Date de naisance du membre */;
    private JPasswordField passwd /* Mot de passe du membre  a l'authentification*/;

    private JFormattedTextField
            numToSearch /* Numero du membre a chercher*/,
            numPhone /* Numero telephone du membre */,
            faxe /* Faxe du memebre */,
            telOffice /* Telephone bureau du membre*/;
    /**
     * Zone de choix
     */
    private JComboBox  <String> []
            departements=new JComboBox[3] /*Departements */,
            niveau=new JComboBox[3] /*Niveau */,
            options=new JComboBox[3] /*Options */,
            annee=new JComboBox[3] /* Annee formation suivie*/;

    private String[]
            selectedDepartement,
            selectedOption,
            selectedNiveau;
    private String [] selectedAnnee = new String[6];
    private static int nbFormation = 0;

    private static final Font myFont = new Font("DecoType Naskh", Font.BOLD, 15);
//    Helvetica Neue
//    les polices
//    -Times New Roman
//    -Lucida Sans Typewriter
//    -DecoType Naskh



    /**
     * Creation de la fenetre principale
     * L'affichage se fera en plein ecran
     */
    public MainWindow() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.getContentPane().add(mainPanel);
        this.setPreferredSize(new Dimension(1200, 700));
        this.pack();
        header = WindowUtils.header();
        footer = WindowUtils.footer();
        textEcranConnexion = WindowUtils.textAccueil();
        textEcranConnexion.setFont(myFont);
        this.formAdd = this.formAdd();
        this.formSearch = this.formSearch();
        this.accueilContent = this.accueilContent();
        panelSearch = new JPanel();
        panelSearch.setLayout(new BoxLayout(panelSearch, BoxLayout.PAGE_AXIS));
        selectedDepartement = new String[6];
        selectedOption = new String[6];
        selectedNiveau = new String[6];
    }

    /**
     * Retourne le menu de notre application
     *
     * @return JPanel
     */
    private JPanel menu() {
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(5,1));
        menu.setBackground(new Color(59, 89, 152));

        accueil = new JButton("Accueil");
        searchMember = new JButton("Rechercher");
        addMember = new JButton("Ajouter");
        deconnexion = new JButton("Deconnexion");
        accueil.setFont(new Font("Arial", Font.PLAIN, 20));
        searchMember.setFont(new Font("Arial", Font.PLAIN, 20));
        addMember.setFont(new Font("Arial", Font.PLAIN, 20));
        deconnexion.setFont(new Font("Arial", Font.PLAIN, 20));


        accueil.addActionListener(this);
        searchMember.addActionListener(this);
        addMember.addActionListener(this);
        deconnexion.addActionListener(this);

        accueil.setForeground(new Color(59,89,152));
        searchMember.setBackground(new Color(59,89,152));
        addMember.setBackground(new Color(59,89,152));
        deconnexion.setBackground(new Color(59,89,152));

        try {
            BufferedImage mylogo =
                    ImageIO.read(getClass().getResource("./Images/logo_ucad.png"));
            logo = new JLabel(new ImageIcon(mylogo));
            menu.add(logo, BorderLayout.NORTH);
        } catch (IOException io) {
            io.printStackTrace();
        }
        menu.add(accueil);
        menu.add(addMember);
        menu.add(searchMember);
        menu.add(deconnexion);

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
        panelLogin.setBackground(new Color(59, 89, 152));

        JLabel labelLogin = new JLabel("Login ");
        JLabel labelPasswd = new JLabel("Mot de passe ");
        labelLogin.setForeground(Color.WHITE);
        labelPasswd.setForeground(Color.WHITE);
        labelLogin.setFont(myFont);
        labelPasswd.setFont(myFont);

        panelLogin.add(labelLogin);
        panelLogin.add(login = new JTextField(10));
        panelLogin.add(labelPasswd);
        panelLogin.add(passwd = new JPasswordField(10));

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
                    ImageIO.read(getClass().getResource("./Images/img6.png"));
            cover = new JLabel(new ImageIcon(myPicture));
            panelContent.add(cover, BorderLayout.WEST);
        } catch (IOException io) {
            io.printStackTrace();
        }
        panelContent.setBackground(new Color(216, 223, 234));
        mainPanel.add(panelContent, BorderLayout.CENTER);
        this.pack();
    }


    /**
     * Retourne le panel qui va contenir le
     * contenu de la page d'accueil du membre
     *
     * @return JPanel
     */
    private JPanel accueilContent() {
        JPanel accueilContent = new JPanel();
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.setBackground(new Color(216, 223, 234));
        JLabel pic,pic1,pic2;
        accueilContent.setLayout(new BorderLayout());
        try {
            BufferedImage myPicture =
                    ImageIO.read(getClass().getResource("./Images/img1.jpeg"));
            pic1 = new JLabel(new ImageIcon(myPicture));
            content.add(pic1);
            BufferedImage myPictures =
                    ImageIO.read(getClass().getResource("./Images/img2.jpg"));
            pic2 = new JLabel(new ImageIcon(myPictures));
            content.add(pic2);
            accueilContent.add(content, BorderLayout.CENTER);
        } catch (IOException io) {
            io.printStackTrace();
        }
        accueilContent.setBackground(new Color(216, 223, 234));
        return accueilContent;
    }

    private JPanel formFormation() {
        JPanel formation = new JPanel();
        String[] libDep = {"", "Genie Informatique", "Genie Mecanique", "Genie Electrique",
                "Genie Civil", "Genie Chimique et BA", "Gestion"},
                libNiveau = {"", "DUT", "DST", "DIC", "DESCAF", "DEC", "DIT"},
                libOption = {"", "Informatique", "Telecom",
                        "Biologie Appliquee", "Civil", "Mecanique"};
        formation.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        departements[nbFormation] = new JComboBox<>();
        niveau[nbFormation] = new JComboBox<>();
        options[nbFormation] = new JComboBox<>();
        annee[nbFormation] = new JComboBox<>();

        for (int i = 0; i < 6; i++)
            departements[nbFormation].addItem(libDep[i]);
        for (int i = 0; i < 6; i++)
            niveau[nbFormation].addItem(libNiveau[i]);
        for (int i = 0; i < 5; i++)
            options[nbFormation].addItem(libOption[i]);
        for (int i = 1960; i < 2017; i++)
            annee[nbFormation].addItem(String.valueOf(i));
        new JScrollPane(annee[nbFormation]);
        switch(nbFormation) {
            case 0 :
                departements[0].addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedDepartement[0] = (String) e.getItem();
                    switch ((String) departements[0].getSelectedItem()) {
                        case "Genie Informatique":
                            options[0].removeAllItems();
                            options[0].addItem("");
                            options[0].addItem("Informatique");
                            options[0].addItem("Telecom");
                            break;

                        case "Genie Civil":
                            options[0].removeAllItems();
                            options[0].addItem("Civil");
                            break;

                        case "Genie Mecanique":
                            options[0].removeAllItems();
                            options[0].addItem("Mecanique");
                            break;

                        case "Genie Electrique":
                            options[0].removeAllItems();
                            options[0].addItem("Electrique");
                            break;
                        case "Gestion":
                            options[0].removeAllItems();
                            options[0].addItem("Gestion");
                            break;

                        case "Genie Chimique et BA":
                            options[0].removeAllItems();
                            options[0].addItem("");
                            options[0].addItem("Analyse Biologique");
                            options[0].addItem("Chimie");
                            options[0].addItem("Intustrie Alimentaire");
                            break;
                }


                }
        });
                break;
            case 1 :
                departements[1].addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        selectedDepartement[1] = (String) e.getItem();
                        switch ((String) departements[1].getSelectedItem()) {
                            case "Genie Informatique":
                                options[1].removeAllItems();
                                options[1].addItem("");
                                options[1].addItem("Informatique");
                                options[1].addItem("Telecom");
                                break;

                            case "Genie Civil":
                                options[1].removeAllItems();
                                options[1].addItem("Civil");
                                break;

                            case "Genie Mecanique":
                                options[1].removeAllItems();
                                options[1].addItem("Mecanique");
                                break;

                            case "Genie Electrique":
                                options[1].removeAllItems();
                                options[1].addItem("Electrique");
                                break;
                            case "Gestion":
                                options[1].removeAllItems();
                                options[1].addItem("Gestion");
                                break;

                            case "Genie Chimique et BA":
                                options[1].removeAllItems();
                                options[1].addItem("");
                                options[1].addItem("Analyse Biologique");
                                options[1].addItem("Chimie");
                                options[1].addItem("Intustrie Alimentaire");
                                break;
                        }


                    }
                });
                break;
                case 2 :
                departements[2].addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        selectedDepartement[2] = (String) e.getItem();
                        switch ((String) departements[2].getSelectedItem()) {
                            case "Genie Informatique":
                                options[2].removeAllItems();
                                options[2].addItem("");
                                options[2].addItem("Informatique");
                                options[2].addItem("Telecom");
                                break;

                            case "Genie Civil":
                                options[2].removeAllItems();
                                options[2].addItem("Civil");
                                break;

                            case "Genie Mecanique":
                                options[2].removeAllItems();
                                options[2].addItem("Mecanique");
                                break;

                            case "Genie Electrique":
                                options[2].removeAllItems();
                                options[2].addItem("Electrique");
                                break;
                            case "Gestion":
                                options[2].removeAllItems();
                                options[2].addItem("Gestion");
                                break;

                            case "Genie Chimique et BA":
                                options[2].removeAllItems();
                                options[2].addItem("");
                                options[2].addItem("Analyse Biologique");
                                options[2].addItem("Chimie");
                                options[2].addItem("Intustrie Alimentaire");
                                break;
                        }


                    }
                });
                break;
    }
/*
        niveau[nbFormation].addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                selectedNiveau[nbFormation] = (String) e.getItem();
        });*/
/*
        options.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                selectedOption[nbFormatikon] = (String) e.getItem();
        });

      selectedAnnee[nbFormation] = (String) annee.getSelectedItem();

        annee.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                selectedAnnee[nbFormation] = (String) e.getItem();
        });*/
        formation.add(new JLabel("Departements"));
        formation.add(departements[nbFormation]);
        formation.add(new JLabel("Options"));
        formation.add(options[nbFormation]);
        formation.add(new JLabel("Niveau"));
        formation.add(niveau[nbFormation]);

        formation.add(new JLabel("Annee"));
        formation.add(annee[nbFormation]);

        formation.setBackground(Color.WHITE);
        return formation;
    }

    /**
     * Retourne le formulaire d'ajout
     *
     * @return JPanel
     */
    private JPanel formAdd() {
        /*Mise en place des sections et du JPanel qui sera retourne*/
        JPanel formAdd, privacy, contact, infoAuth;
        formAdd = new JPanel();
        infoAuth = new JPanel();
        privacy = new JPanel();
        contact = new JPanel();
        formation = new JPanel();
        mainFormation = new JPanel();
        mainFormation.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formAdd.setLayout(new BoxLayout(formAdd, BoxLayout.PAGE_AXIS));
        infoAuth.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        privacy.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contact.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formation.setLayout(new BoxLayout(formation, BoxLayout.PAGE_AXIS));

        /*Section login et mot de passe*/

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
        privacy.add(dateBirth = new JTextField(15));
        dateBirth.setPreferredSize(new Dimension(150, 30));
        privacy.setBorder(WindowUtils.myBorder("Informations Personnelles",
                new Color(59, 89, 152), 2));
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
                new Color(59, 89, 152), 2));
        contact.setBackground(Color.WHITE);

        /*Section formations*/
        addFormation = new JButton("+Ajouter une formation");
        addFormation.addActionListener(this);
        formation.setBackground(Color.WHITE);
        formation.add(this.formFormation());
        formation.setBorder(WindowUtils.myBorder("Formations Suivies",
                new Color(59, 89, 152), 2));
        addFormation.setBackground(new Color(216, 223, 234));
        formation.add(addFormation);


        /*Bouton de validation*/
        submitAddMember = new JButton("Ajouter");
        submitAddMember.addActionListener(this);
        submitAddMember.setBackground(new Color(59, 89, 152));
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
        submitSearch.setBackground(new Color(59, 89, 152));
        submitSearch.setForeground(Color.WHITE);
        submitSearch.addActionListener(this);
        formSearch.add(submitSearch);
        formSearch.setBorder(WindowUtils.myBorder("Recherche",
                new Color(59, 89, 152), 2));
        formSearch.setBackground(Color.WHITE);
        return formSearch;
    }

    private JPanel formModify() {
        /*Mise en place des sections et du JPanel qui sera retourne*/
        JPanel formAdd, privacy, contact, infoAuth;
        formAdd = new JPanel();
        infoAuth = new JPanel();
        privacy = new JPanel();
        contact = new JPanel();
        formation = new JPanel();
        mainFormation = new JPanel();
        mainFormation.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formAdd.setLayout(new BoxLayout(formAdd, BoxLayout.PAGE_AXIS));
        infoAuth.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        privacy.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contact.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formation.setLayout(new BoxLayout(formation, BoxLayout.PAGE_AXIS));

        /*Section login et mot de passe*/

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
        privacy.add(dateBirth = new JTextField(15));
        dateBirth.setPreferredSize(new Dimension(150, 30));
        privacy.setBorder(WindowUtils.myBorder("Informations Personnelles",
                new Color(59, 89, 152), 2));
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
                new Color(59, 89, 152), 2));
        contact.setBackground(Color.WHITE);

        /*Bouton de validation*/
        submitModifyMember = new JButton("Modifier");
        submitModifyMember.addActionListener(this);
        submitModifyMember.setBackground(new Color(59, 89, 152));
        submitModifyMember.setForeground(Color.WHITE);

        /*Section formations*/
        addFormation = new JButton("+Ajouter une formation");
        addFormation.addActionListener(this);
        formation.setBackground(Color.WHITE);
        formation.add(this.formFormation());
        formation.setBorder(WindowUtils.myBorder("Formations Suivies",
                new Color(59, 89, 152), 2));
        addFormation.setBackground(new Color(216, 223, 234));
        formation.add(addFormation);
        /*Bouton de validation*/
        submitModifyFormation = new JButton("Modifier");
        submitModifyFormation.addActionListener(this);
        submitModifyFormation.setBackground(new Color(59, 89, 152));
        submitModifyFormation.setForeground(Color.WHITE);
        submitModifyMember.addActionListener(this);
        submitModifyFormation.addActionListener(this);
        /*Ajout des differents panneaux dans le panneau formAdd */
        formAdd.add(infoAuth);
        formAdd.add(privacy);
        formAdd.add(contact);
        formAdd.add(submitModifyMember);
        formAdd.add(formation);
        formAdd.add(submitModifyFormation);

        //  formAdd.add(submitAddMember);

        return formAdd;
    }


    /**
     * Efface l'ecran d'authentification
     * si l'authentification reussit
     */
    private void cleanEcranAuth() {
        header.remove(panelLogin);
        header.repaint();
        menu = this.menu();
        panelContent.remove(textEcranConnexion);
        panelContent.remove(cover);
        try {
            BufferedImage myPicture =
                    ImageIO.read(getClass().getResource("./Images/img7.png"));
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
        panelContent.add(this.accueilContent, BorderLayout.CENTER);
        panelContent.add(this.menu, BorderLayout.WEST);
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

    private void actionModifyMember() {
        panelContent.repaint();
        this.formAdd = this.formModify();
        panelContent.add(formAdd, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connexion) {
            try {
                if (Operation.connection(login.getText(), passwd.getText())) {
                    this.cleanEcranAuth();
                    this.actionConnexion();
                } else {
                    JOptionPane.showMessageDialog(panelLogin, "Login ou mot de passe incorrect");
                }
            } catch (Exception ex) {
                System.err.println("Got an exception! ");
                ex.printStackTrace();
            }
        }
        if (e.getSource() == accueil) {
            if (formAdd.isVisible())
                formAdd.setVisible(false);
            if (panelSearch.isVisible())
                panelSearch.setVisible(false);
            if (!accueilContent.isVisible() || accueilContent.getParent() == null)
                this.actionConnexion();
        }
        if (e.getSource() == addMember) {
            if (accueilContent.isVisible())
                accueilContent.setVisible(false);
            if (panelSearch.isVisible())
                panelSearch.setVisible(false);
            if (!formAdd.isVisible() || formAdd.getParent() == null)
                this.actionAddMember();
        }
        if (e.getSource() == searchMember) {
            if (accueilContent.isVisible())
                accueilContent.setVisible(false);
            if (formAdd.isVisible())
                formAdd.setVisible(false);
            if (!panelSearch.isVisible() || panelSearch.getParent() == null) {
                panelSearch.add(formSearch);
                panelSearch.setVisible(true);
                panelContent.add(panelSearch, BorderLayout.CENTER);
            }
        }
        if (e.getSource() == deconnexion) {
            panelContent.removeAll();
            panelContent.repaint();
            mainPanel.remove(cover);
            mainPanel.repaint();
            this.authentification();
        }
        if (e.getSource() == addFormation) {
            if (nbFormation == 2)
                JOptionPane.showMessageDialog(panelContent,"Nombre de formations maximales atteintes");
            else {
                nbFormation++;
                formation.add(this.formFormation());
                formation.remove(addFormation);
                formation.repaint();
                formation.add(addFormation);
            }
        }
        if (e.getSource() == submitSearch) {

            try{
                    if (!(numToSearch.getText().equals("")) && Operation.existe(numToSearch.getText())){
                       
                        panelResultat = new JPanel();
                        panelResultat.setLayout(new BoxLayout(panelResultat, BoxLayout.PAGE_AXIS));
                        Membre membre = Operation.chercherMembre(numToSearch.getText());
                        JPanel panelResInfo = new JPanel();
                        JPanel panelResForm = new JPanel();
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
                        panelResInfo.setLayout(new GridLayout(2, 8));
                        panelResForm.setLayout(new GridLayout(nbFormation+2, 4));

                        String[] labelInfo = {"Numero", "Nom", "Prenom","Date","Adresse","Faxe","TelBureau","Email"};
                        for (int i = 0;i<8;i++)
                            panelResInfo.add(new JLabel(labelInfo[i]));

                        /*String[] labelForm = {"Departement","Option","Niveau","Annee"};
                        for (int i = 0;i<4;i++)
                            panelResForm.add(new JLa bel(labelForm[i]));*/

                        panelResInfo.add(new JLabel(membre.getTel()));
                        panelResInfo.add(new JLabel(membre.getNom()));
                        panelResInfo.add(new JLabel(membre.getPrenom()));
                        panelResInfo.add(new JLabel(""+membre.getDateNaiss()));
                        panelResInfo.add(new JLabel(membre.getAdresse()));
                        panelResInfo.add(new JLabel(membre.getFaxe()));
                        panelResInfo.add(new JLabel(membre.getTelBureau()));
                        panelResInfo.add(new JLabel(membre.getEmail()));

                        panelResInfo.setBorder(WindowUtils.myBorder("Info",
                                new Color(59, 89, 152), 2));
                        panelResForm.setBorder(WindowUtils.myBorder("Formation",
                                new Color(59, 89, 152), 2));

                        int i = (membre.getFormation().length) - 1 ;
                        System.out.println(membre.getFormation().length);
                        while(i>=0){
                                panelResForm.add(new JLabel(membre.getFormation()[i].getDepartement()+" \t\t "+membre.getFormation()[i].getOption()+" \t\t "
                                    +membre.getFormation()[i].getNiveau()+" \t\t "+membre.getFormation()[i].getAnnee()));
                                i--;
                        }

                        panelResultat.setBorder(WindowUtils.myBorder("Resultat",
                                new Color(59, 89, 152), 2));
                        panelResInfo.setBackground(Color.WHITE);
                        panelResForm.setBackground(Color.WHITE);
                        panelResultat.setBackground(Color.WHITE);
                        panel.add(panelResForm);
                        rmMember = new JButton("Supprimer");
                        editMember = new JButton("Modifier");
                        JPanel panelModSup = new JPanel();
                        panelModSup.setLayout(new BoxLayout(panelModSup, BoxLayout.PAGE_AXIS));
                        panelModSup.add(editMember);
                        panelModSup.add(rmMember);
                        rmMember.addActionListener(this);
                        editMember.addActionListener(this);
                        panelModSup.setBackground(Color.WHITE);
                        panel.add(panelModSup);
                        panelResultat.add(panelResInfo);
                        panelResultat.add(panel);

                        panelSearch.add(panelResultat);
                        panelSearch.repaint();
                }
                else
                    JOptionPane.showMessageDialog(panelLogin,"Cet utilisateur n'existe pas");



            }
                catch (Exception ex){

                    System.err.println("Got an exception! ");
                    ex.printStackTrace();
             }
         

            
        }
        if (e.getSource() == submitAddMember) {
            try {
            		if(firstName.getText().equals("") || lastName.getText().equals(""))
            			//departements[0].getSelectedIndex()==0||niveau[0].getSelectedIndex()==0||options[0].getSelectedIndex()==0)

            				JOptionPane.showMessageDialog(panelLogin,"Veuillez remplir tous les champs relatifs obligatoires");

            		else {
							if (dateBirth.getText().equals(""))
			            		dateBirth.setText("0000-00-00");

			                if (!Operation.existe(numPhone.getText())){

			                    Formation [] formation=new Formation[nbFormation+1];
			                    System.out.println("nbformation = "+nbFormation);

			                    for(int i=0 ;i<nbFormation+1;i++){
			                        formation[i]=new Formation((String)departements[i].getSelectedItem(),(String)niveau[i].getSelectedItem(),
                                            (String)options[i].getSelectedItem(),(String)annee[i].getSelectedItem()) ;
			                    }
                                Operation.ajouterMembre(new Membre(numPhone.getText(),firstName.getText(),lastName.getText(),
                                        dateBirth.getText(),email.getText(),adress.getText(),telOffice.getText(),faxe.getText(),formation));
			                 JOptionPane.showMessageDialog(panelLogin,"Ajout effectué avec succès");

	                }
	                else 
	                JOptionPane.showMessageDialog(panelLogin,"Login deja pris");
            		}
	            	
                }
            catch (Exception ex){

                System.err.println("Got an exception! ");
                ex.printStackTrace();
             }
            }
        if (e.getSource() == rmMember) {
            try {

                if(Operation.supprimerMembre(numToSearch.getText()))
                {
                    panelResultat.setVisible(false);
                    JOptionPane.showMessageDialog(panelLogin,"Suppression effectué avec succes");
                }

                else
                    JOptionPane.showMessageDialog(panelLogin,"Erreur de Suppression");
            }
            catch (Exception ex){

                System.err.println("Got an exception! ");
                ex.printStackTrace();
            }
        }
        if (e.getSource() == editMember) {
            try {
                if (accueilContent.isVisible())
                    accueilContent.setVisible(false);
                if (panelSearch.isVisible())
                    panelSearch.setVisible(false);
                if (!formModify().isVisible() || formAdd.getParent() == null)
                    this.actionModifyMember();
                Membre membre= Operation.chercherMembre(numToSearch.getText());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                firstName.setText(membre.getNom());
                lastName.setText(membre.getPrenom());
                dateBirth.setText(formatter.format(membre.getDateNaiss()));
                email.setText(membre.getEmail());
                adress.setText(membre.getAdresse());
                numPhone.setText(membre.getTel());
                telOffice.setText(membre.getTelBureau());
                faxe.setText(membre.getFaxe());
                departements[0].setSelectedItem(membre.getFormation()[0].getDepartement());
                niveau[0].setSelectedItem(membre.getFormation()[0].getNiveau());
                options[0].setSelectedItem(membre.getFormation()[0].getOption());
                annee[0].setSelectedItem(membre.getFormation()[0].getAnnee());
                for(nbFormation=1 ; nbFormation<membre.getFormation().length; nbFormation++){
                    formation.add(this.formFormation());
                    formation.remove(addFormation);
                    formation.repaint();
                    formation.add(addFormation);
                    departements[nbFormation].setSelectedItem(membre.getFormation()[nbFormation].getDepartement());
                    niveau[nbFormation].setSelectedItem(membre.getFormation()[nbFormation].getNiveau());
                    options[nbFormation].setSelectedItem(membre.getFormation()[nbFormation].getOption());
                    annee[nbFormation].setSelectedItem(membre.getFormation()[nbFormation].getAnnee());
                }
                nbFormation--;
                formAdd.remove(submitAddMember);
                formation.repaint();
            }
            catch (Exception ex){

                System.err.println("Got an exception! ");
                ex.printStackTrace();
            }
        }

        if (e.getSource() == submitModifyMember){
            try {
                Membre membre = Operation.chercherMembre(numPhone.getText());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                membre.setNom(firstName.getText());
                membre.setPrenom(lastName.getText());
                membre.setDateNaiss((dateBirth.getText()));
                membre.setEmail(email.getText());
                membre.setAdresse(adress.getText());
                membre.setTel(numPhone.getText());
                membre.setTelBureau(telOffice.getText());
                membre.setFaxe(faxe.getText());
                Operation.modifyMembre(membre);
             //   System.out.println(membre.getFaxe());
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        if (e.getSource() == submitModifyFormation) {
            try {
                Membre membre = Operation.chercherMembre(numPhone.getText());
                System.out.println(nbFormation+"eee");
                Formation[] formation = new Formation[nbFormation+1];
                for(int i=0;i<=nbFormation;i++){
                    formation[i]=new Formation((String)departements[i].getSelectedItem(),(String)niveau[i].getSelectedItem(),
                            (String)options[i].getSelectedItem(),(String)annee[i].getSelectedItem()) ;
                }
                System.out.println(nbFormation+"eee");

                System.out.println(formation[1].getOption());
                Operation.modifyFormation(formation,membre.getTel());
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }


        }
            this.pack();
    }

}
