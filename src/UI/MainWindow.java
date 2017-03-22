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
            submitModify /* Bouton pour modifier un membre*/,
            submitDelete/* Bouton pour supprimer un membre*/,
            addFormation /* Permet d'ajouter une ou plusieurs autres formations*/;

    /**
     * Label utilises
     */
    private final JLabel
            textEcranConnexion /* Text de l'ecran de Connexion*/;
    private JLabel cover /* Photo de couverture*/;

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
            passwdMember /*Mot de passe du membre choisi lors de l'ajout*/,
             numToSearch /* Numero du membre a chercher*/,
            dateBirth /* Date de naisance du membre */;

    private JFormattedTextField
            numPhone /* Numero telephone du membre */,
            faxe /* Faxe du memebre */,
            telOffice /* Telephone bureau du membre*/;
    /**
     * Zone de choix
     */
    private JComboBox<String>
            departements /*Departements */,
            niveau /*Niveau */,
            options /*Options */,
            annee /* Annee formation suivie*/;

    private String[]
            selectedDepartement,
            selectedOption,
            selectedNiveau,
            selectedAnnee;
    private static int nbFormation = 0;

    private static final Font myFont = new Font("Helvetica Neue", Font.BOLD, 15);


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
        panelSearch = new JPanel();
        panelSearch.setLayout(new BoxLayout(panelSearch, BoxLayout.PAGE_AXIS));
    }

    /**
     * Retourne le menu de notre application
     *
     * @return JPanel
     */
    private JPanel menu() {
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.LEFT));
        menu.setBackground(new Color(59, 89, 152));

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
        panelContent.setBackground(new Color(216, 223, 234));
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

    /**
     * Retourne le panel qui va contenir le
     * contenu de la page d'accueil du membre
     *
     * @return JPanel
     */
    private JPanel accueilContent() {
        JPanel accueilContent = new JPanel();
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBackground(new Color(216, 223, 234));
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
        accueilContent.setBackground(new Color(216, 223, 234));
        return accueilContent;
    }

    private JPanel formFormation() {
        JPanel formation = new JPanel();
        String[] libDep = {"", "Genie Informatique", "Genie Mecanique", "Genie Electrique",
                "Genie Civil", "Genie Chimique et BA", "Gestion"},
                libNiveau = {"", "DUT", "DST", "DIC", "DESCAF", "DEC", "DIT"},
                libOption = {"", "Informatique", "Telecoms et Reseaux",
                        "Biologie Appliquee", "Civil", "Mecanique"};
        formation.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        departements = new JComboBox<>();
        niveau = new JComboBox<>();
        options = new JComboBox<>();
        annee = new JComboBox<>();

        selectedDepartement = new String[3];
        selectedOption = new String[3];
        selectedNiveau = new String[3];
        selectedAnnee = new String[3];

        for (int i = 0; i < 6; i++)
            departements.addItem(libDep[i]);
        for (int i = 0; i < 6; i++)
            niveau.addItem(libNiveau[i]);
        for (int i = 0; i < 5; i++)
            options.addItem(libOption[i]);
        for (int i = 1960; i < 2017; i++)
            annee.addItem(String.valueOf(i));
        new JScrollPane(annee);

        departements.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedDepartement[nbFormation] = (String) e.getItem();
                switch (selectedDepartement[nbFormation]) {
                    case "Genie Informatique":
                        options.removeItem("Biologie Appliquee");
                        options.removeItem("Civil");
                        options.removeItem("Mecanique");
                        break;

                    case "Genie Civil":
                        options.addItem("Civil");
                        options.removeItem("Informatique");
                        options.removeItem("Telecoms et Reseaux");
                        break;

                    case "Genie Mecanique":
                        options.addItem("Mecanique");
                        options.removeItem("Informatique");
                        options.removeItem("Telecoms et Reseaux");
                }
            }
        });

        niveau.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                selectedNiveau[nbFormation] = (String) e.getItem();
        });

        options.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                selectedOption[nbFormation] = (String) e.getItem();
        });

        annee.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                selectedAnnee[nbFormation] = (String) e.getItem();
        });
        formation.add(new JLabel("Departements"));
        formation.add(departements);
        formation.add(new JLabel("Options"));
        formation.add(options);
        formation.add(new JLabel("Niveau"));
        formation.add(niveau);

        formation.add(new JLabel("Annee"));
        formation.add(annee);

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
        infoAuth.add(new JLabel("Login"));
        infoAuth.add(loginMember = new JTextField(15));
        loginMember.setPreferredSize(new Dimension(15, 30));
        infoAuth.add(new JLabel("Mot de passe"));
        infoAuth.add(passwdMember = new JTextField(15));
        passwdMember.setPreferredSize(new Dimension(15, 30));
        infoAuth.setBorder(WindowUtils.myBorder("Login et Mot de passe",
                new Color(59, 89, 152), 2));
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
        formSearch.add(numToSearch = new JTextField(15));
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

    /**
     * Efface l'ecran d'authentification
     * si l'authentification reussit
     */
    private void cleanEcranAuth() {
        header.remove(panelLogin);
        header.repaint();
        menu = this.menu();
        header.add(menu, BorderLayout.EAST);
        panelContent.remove(textEcranConnexion);
        panelContent.remove(cover);
        try {
            BufferedImage myPicture =
                    ImageIO.read(getClass().getResource("./img.jpg"));
            Image myPictureScaled = myPicture.getScaledInstance(header.getWidth(),
                    header.getHeight() + 100, Image.SCALE_SMOOTH);
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
            try {
                if (Operation.connection(login.getText(), passwd.getText()) == true) {
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
            header.remove(menu);
            header.repaint();
            mainPanel.remove(cover);
            mainPanel.repaint();
            this.authentification();
        }
        if (e.getSource() == addFormation) {
            nbFormation++;
            formation.add(this.formFormation());
            formation.remove(addFormation);
            formation.repaint();
            formation.add(addFormation);
        }
        if (e.getSource() == submitSearch) {

            try{
                    if (!(numToSearch.getText().equals("")) && Operation.existe(numToSearch.getText())==true){
                       
                        panelResultat = new JPanel();
                        panelResultat.setLayout(new BoxLayout(panelResultat, BoxLayout.PAGE_AXIS));
                        Membre membre= Operation.chercherMembre(numToSearch.getText());
                        panelResultat.add(new JLabel("Nom : "+membre.getNom()));
                        panelResultat.add(new JLabel("Prenom : "+membre.getPrenom()));
                        panelResultat.add(new JLabel("Date de Naissance : "+membre.getDateNaiss()));
                        panelResultat.add(new JLabel("Telephone :"+membre.getTel()));
                        panelResultat.add(new JLabel("Email : "+membre.getEmail()));
                        panelResultat.add(new JLabel("Adresse : "+membre.getAdresse()));
                        panelResultat.add(new JLabel("Fixe : "+membre.getTelBureau()));
                        panelResultat.add(new JLabel("Faxe : "+membre.getFaxe()));
                        panelResultat.add(new JLabel("Formations : "));
                        int i =(membre.getFormation().length)-1 ;
                        while(i>=0){
                                panelResultat.add(new JLabel(membre.getFormation()[i].getDepartement()+" "+membre.getFormation()[i].getOption()+" "
                                    +membre.getFormation()[i].getNiveau()+" "+membre.getFormation()[i].getAnnee()));
                                i--;
                        }
                        


                        panelResultat.setBorder(WindowUtils.myBorder("Resultat",
                                new Color(59, 89, 152), 2));
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
            		if(loginMember.getText().equals("") || firstName.getText().equals("") || lastName.getText().equals("")
            			|| passwdMember.getText().equals("")||
            			departements.getSelectedIndex()==0||niveau.getSelectedIndex()==0||options.getSelectedIndex()==0)

            				JOptionPane.showMessageDialog(panelLogin,"Veuillez remplir tous les champs relatifs obligatoires");

            		else {
							if (dateBirth.getText().equals(""))
			            		dateBirth.setText("0000-00-00");

			                if (Operation.existe(loginMember.getText())==false){

			                    Formation [] formation=new Formation[nbFormation+1];

			                    for(int i=0 ;i<nbFormation+1;i++){
			                        formation[i]=new Formation(departements.getSelectedItem().toString(),niveau.getSelectedItem().toString(),
			                        options.getSelectedItem().toString(),annee.getSelectedItem().toString()) ;
			                        Operation.ajouterMembre(new Membre(loginMember.getText(),passwdMember.getText(),firstName.getText(),lastName.getText(),
			                        dateBirth.getText(),email.getText(),adress.getText(),numPhone.getText(),telOffice.getText(),faxe.getText(),formation));
			                    }
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
        this.pack();
    }

}
