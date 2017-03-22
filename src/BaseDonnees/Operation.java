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
			conn = DriverManager.getConnection(myUrl, user, password);
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
        String requete="select count(*) as nombre from membres where id=\""+idMembre+"\"";
        rs=st.executeQuery(requete);
        int nb=0 ;
        rs.next();
        nb=rs.getInt("nombre");
        return (nb==1);
    }
/*
//methode permettant de rechercher un membre selon son id

    public static Membre rechercher (String idMembre){
        if(existe(idMembre)){

            String requete="select * from membres where id=\""+idMembre+"\"";
            rs=st.executeQuery(requete);
            rs.next()
            return new Membre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)
                ,rs.getString(10));

            }
        else 
            return null ;
        }

    }
*/
public static void ajouterMembre(Membre pers) throws SQLException{
    String requete="insert into user values(\""
            +pers.getLogin()+"\","
            +pers.getmdpasse()+"\","
            +pers.getPrenom()+"\","
            +pers.getNom()+"\","
            +pers.getDateNaiss()+"\","
            +pers.getEmail()+"\","
            +pers.getAdresse()+"\","
            +pers.getTel()+"\","
            +pers.getTelBureau()+"\","
            +pers.getFaxe()+")";
    int a =st.executeUpdate(requete);
    if(a == 1)
    {
        System.out.println("Update reussie");
    }
    else
    {
        System.out.println("Insertion echouee");
    }
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