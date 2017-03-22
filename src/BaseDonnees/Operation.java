package BaseDonnees;

import java.sql.*;

/**
 * Created by moussa on 21/03/2017.
 */
public class Operation {
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

}