package BaseDonnees;

import java.sql.*;

/**
 * Created by moussa on 21/03/2017.
 */
public class Operation implements VAriableBaseDonnees{
      static Connection conn;
    static Statement st;
    static ResultSet rs ;
   //bloc permettant de réaliser la connexion vers la base de données de l'utilisateur
    static {

        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(myUrl,user,password);
                st=conn.createStatement();
        }

         catch (Exception e)
        {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
    }
    //methode permettant de tester l'authentification en parcourant latable des admins et vérifiant si les parametres saisis sont valides
    public static boolean connection(String login,String mdpasse) throws Exception{
        String requete="select count(*) from Admins where login=\""+login+"\"and mdpasse=\""+mdpasse+"\"";
        rs=st.executeQuery(requete);
        int nb;
        rs.next();
        nb=rs.getInt(1);
        return (nb==1);
    }
//methode permettant de verifier si un membre existe dans la table membre

    public static boolean existe (String idMembre)throws Exception{
        String requete="select count(*) from membres where Tel=\""+idMembre+"\"";
        rs=st.executeQuery(requete);
        int nb=0 ;
        rs.next();
        nb=rs.getInt(1);
        return (nb==1);
    }
//methode permettant d'ajouter un membre dans la base de données
public static void ajouterMembre(Membre pers) throws Exception{
    //nous verfions d'abord si la personne n'est pas déja présente
        if (!existe(pers.getTel())){
        Formation [] formation=pers.getFormation();
        int idFormation,i=formation.length - 1;
//insertion des informations personnelles dans la base
        String requete="insert into membres values(\""
            +pers.getTel()+"\",\""
            +pers.getPrenom()+"\",\""
            +pers.getNom()+"\",\""
            +pers.getDateNaiss()+"\",\""
            +pers.getEmail()+"\",\""
            +pers.getAdresse()+"\",\""
            +pers.getTelBureau()+"\",\""
            +pers.getFaxe()+"\")";
    st.executeUpdate(requete);
    //insertion dans la table de jointure des formations du membres
    while(i>=0){
        requete = "select idFormations from Formation where Departement=\""+formation[i].getDepartement()+"\" and niveau=\""+formation[i].getNiveau()+"\" and `option`=\""+formation[i].getOption()+"\"";
        rs=st.executeQuery(requete);
        rs.next();
        idFormation=rs.getInt("idFormations");
        requete="insert into membre_formation values(\""
            +pers.getTel()+"\","
            +idFormation+",\""
            +formation[i].getAnnee()+"\")" ;
        st.executeUpdate(requete);
        i--;
    }
    }
}
//methode pour rechercher un membre dans la base de données
 public static Membre chercherMembre(String idMembre) throws Exception
    {
        int nbFormations=1,i=0;
        Formation [] formation ;
//requete pour connaitre le nombre de formations suivies par le membre
        String requete = "select count(*) from membre_formation where membres_Tel = \""+idMembre+"\"";
        rs = st.executeQuery(requete);
        rs.next();
        nbFormations=rs.getInt(1);

        formation=new Formation[nbFormations];

// selection des formations du membre
        requete = "select Departement,niveau,`option`,annee from membre_formation,Formation where membres_Tel = \""+idMembre+"\" and idFormations=Formation_idFormations";
        rs = st.executeQuery(requete);
        while(rs.next()){
            formation[i]=new Formation(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            i++;
        }
        //selection des infos personnelles du membres
        requete = "select * from membres where tel = \""+idMembre+"\"";
        rs = st.executeQuery(requete);
        rs.next();
        //on retourne un objet de type membre ayant les formations et les infos personnelles
        return (new Membre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
            rs.getString(6),rs.getString(7),rs.getString(8),formation));
    }
    //methode permettant de supprimer un membre de la base de données
    public static boolean supprimerMembre(String idMembre) throws Exception
    {
        String requete = "delete from membres where tel = \""+idMembre+"\"";
        return (st.executeUpdate(requete)==1) ;

    }
//méthode permettant de modifier les infos personnelles d'un membre
    public static void modifyMembre(Membre pers) throws Exception{
        String requete="update membres set prenom=\""
                +pers.getPrenom()+"\",nom=\""
                +pers.getNom()+"\",datenaiss=\""
                +pers.getDateNaiss()+"\",email=\""
                +pers.getEmail()+"\",adresse=\""
                +pers.getAdresse()+"\",tel=\""
                +pers.getTel()+"\",telbureau=\""
                +pers.getTelBureau()+"\",faxe=\""
                +pers.getFaxe()+"\" where tel=\""+pers.getTel()+"\"";
        st.executeUpdate(requete);

    }
//methode permettant de modifier les formations d'un membre
    public static void modifyFormation(Formation[] formation,String tel ) throws Exception {
        int idFormation,i=(formation.length)-1;
        String requete ;
        requete="delete from membre_formation where membres_tel=\""+tel+"\" " ;
        st.executeUpdate(requete);
        while(i>=0){
            requete = "select idFormations from Formation where Departement=\""+formation[i].getDepartement()+"\" and niveau=\""+formation[i].getNiveau()+"\" and `option`=\""+formation[i].getOption()+"\"";
            rs=st.executeQuery(requete);
            rs.next();
            idFormation=rs.getInt("idFormations");
            requete="insert into membre_formation values(\""
                    +tel+"\","
                    +idFormation+",\""
                    +formation[i].getAnnee()+"\")" ;
            st.executeUpdate(requete);
            i--;
        }
    }
    //methode d'afficher tous les membres
    public static Membre [] lister () throws Exception {
        String requete = "select count(*) from membres" ;
        rs=st.executeQuery(requete);
        rs.next();
        int nbMembres=rs.getInt(1),i=0;
        Membre [] membres = new Membre[nbMembres];
        requete = "select * from membres ";
        rs=st.executeQuery(requete);
        while(rs.next()){
            membres[i]=new Membre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getString(7),rs.getString(8),null);
            i++;
        }
        return membres;

    }


}