package baseDonnees;

/**
 * Created by moussa on 21/03/2017.
 */
public class TestAuth {
    public void Connecter(String login,String mdpasse) throws Exception{
        operation d=new operation();
        d.Connec(login,mdpasse);
    }
    public static void main(String[] args)
    {
        TestAuth test=new TestAuth();
        try
        {
            test.Connecter("aliou","repasser");
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
    }
}
