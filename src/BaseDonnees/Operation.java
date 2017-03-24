package BaseDonnees;

import java.sql.*;

/**
 * Created by moussa on 21/03/2017.
 */
public class Operation implements VAriableBaseDonnees{
      static Connection conn;
    static Statement st;
    static ResultSet rs ;
    static {
        try {
               Class.forName("com.mysql.jdbc.Driver");

        }
         catch (Exception e)
        {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(myUrl,user,password);
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }

        try {
            st=conn.createStatement();
        }
         catch (Exception e)
        {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
    }
    //methode permettant de tester l'authentification
    public static boolean connection(String login,String mdpasse) throws Exception{
        String requete="select count(*) from Admins where login=\""+login+"\"and mdpasse=\""+mdpasse+"\"";
        rs=st.executeQuery(requete);
        int nb;
        rs.next();
        nb=rs.getInt(1);
        return (nb==1);
    }
//methode permettant de verifier si un membre existe dans la bdd 

    public static boolean existe (String idMembre)throws Exception{
        String requete="select count(*) from membres where Tel=\""+idMembre+"\"";
        rs=st.executeQuery(requete);
        int nb=0 ;
        rs.next();
        nb=rs.getInt(1);
        return (nb==1);
    }

public static void ajouterMembre(Membre pers) throws Exception{
    if (!existe(pers.getTel())){
        Formation [] formation=pers.getFormation();
        int idFormation,i=formation.length - 1;
        System.out.println("i = "+i);

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

 public static Membre chercherMembre(String idMembre) throws Exception
    {
        int nbFormations=1,i=0;
        Formation [] formation ;

        String requete = "select count(*) from membre_formation where membres_Tel = \""+idMembre+"\"";
        rs = st.executeQuery(requete);
        rs.next();
        nbFormations=rs.getInt(1);

        formation=new Formation[nbFormations];


        requete = "select Departement,niveau,`option`,annee from membre_formation,Formation where membres_Tel = \""+idMembre+"\" and idFormations=Formation_idFormations";
        rs = st.executeQuery(requete);
        while(rs.next()){
            formation[i]=new Formation(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            i++;
        }
        
        requete = "select * from membres where tel = \""+idMembre+"\"";
        rs = st.executeQuery(requete);
        rs.next();
        return (new Membre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
            rs.getString(6),rs.getString(7),rs.getString(8),formation));
    }
    public static boolean supprimerMembre(String idMembre) throws Exception
    {
        String requete = "delete from membres where tel = \""+idMembre+"\"";
        return (st.executeUpdate(requete)==1) ;

    }

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


    }