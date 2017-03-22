package BaseDonnees;

import java.sql.*;

/**
 * Created by moussa on 21/03/2017.
 */
public class Operation {
    Connection conn;
    static Statement st;

    public Operation() throws ClassNotFoundException , SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String myUrl = "jdbc:mysql://localhost:8889/GestAnciens";
        conn = DriverManager.getConnection(myUrl, "root", "root");
        st=conn.createStatement();
    }
    public  void Connec(String login,String mdpasse) throws Exception{
        ResultSet rs;
        String requete;
        requete="select count(*) as nombre from membres where login=\""+login+"\"and mdpasse=\""+mdpasse+"\"";
        rs=st.executeQuery(requete);
        while (rs.next()){
            int nb=rs.getInt("nombre");
            if (nb==1)
                System.out.println("Connexion authorise");
            else
                System.out.println("Connexion refuse");
        }
    }
}
