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
            String myUrl = "jdbc:mysql://localhost/GestAnciens";
            conn = DriverManager.getConnection(myUrl, "phpmyadmin", "some_pass");
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
        String requete="select count(*) as nombre from membres where login=\""+login+"\"and mdpasse=\""+mdpasse+"\"";
        rs=st.executeQuery(requete);
        int nb=0 ;
        rs.next();
        nb=rs.getInt("nombre");
        return (nb==1);
    }
//methode permettant de verifier si un membre existe dans la bdd 

    public static boolean existe (String idMembre)throws Exception{
        String requete="select count(*) as nombre from membres where login=\""+idMembre+"\"";
        rs=st.executeQuery(requete);
        int nb=0 ;
        rs.next();
        nb=rs.getInt("nombre");
        return (nb==1);
    }

public static void ajouterMembre(Membre pers) throws Exception,SQLException{
    if (!existe(pers.getLogin())){
        Formation [] formation=pers.getFormation();
        int idFormation,i=(formation.length)-1;

        String requete="insert into membres values(\""
            +pers.getLogin()+"\",\""
            +pers.getmdpasse()+"\",\""
            +pers.getPrenom()+"\",\""
            +pers.getNom()+"\",\""
            +pers.getDateNaiss()+"\",\""
            +pers.getEmail()+"\",\""
            +pers.getAdresse()+"\",\""
            +pers.getTel()+"\",\""
            +pers.getTelBureau()+"\",\""
            +pers.getFaxe()+"\")";
    
    st.executeUpdate(requete);
    while(i>=0){
        requete = "select idFormations from Formation where Departement=\""+formation[i].getDepartement()+"\"and niveau=\""+formation[i].getNiveau()+"\"and `option`=\""+formation[i].getOption()+"\"";
        rs=st.executeQuery(requete);
        rs.next();
        idFormation=rs.getInt(1);
        requete="insert into membre_formation values(\""
            +pers.getLogin()+"\",\""
            +idFormation+"\",\""
            +formation[i].getAnnee()+"\")" ;
        st.executeUpdate(requete);
        i--;
    }
    }
    

}

 public static Membre chercherMembre(String idMembre) throws Exception
    {
        int nbFormations=0,i=0;
        Formation [] formation ;

        String requete = "select count(*) from membre_formation where membres_login = \""+idMembre+"\"";
        rs = st.executeQuery(requete);
        while(rs.next())
            nbFormations++;
        formation=new Formation[nbFormations+1];


        requete = "select Departement,niveau,`option`,annee from membre_formation,Formation where membres_login = \""+idMembre+"\" and idFormations=Formation_idFormations";
        rs = st.executeQuery(requete);
        while(rs.next()){
            formation[i]=new Formation(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            i++;
        }
        
        requete = "select * from membres where login = \""+idMembre+"\"";
        rs = st.executeQuery(requete);
        rs.next();
        return (new Membre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
            rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),formation));
    }
    public static void deleteData(Membre pers) throws Exception
    {
        String requete = "delete from user where login = \""+pers.getLogin()+"\"";
        int a = st.executeUpdate(requete);

        if(a == 1)
        {
            System.out.println("suppression reussie");
        }
        else
        {
            System.out.println("suppression echouee");
        }
    }
}