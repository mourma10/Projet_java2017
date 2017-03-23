package BaseDonnees;
import java.util.*;
import java.sql.Date;
import java.text.*;

public class Formation {
	
	private String departement ;
	private String niveau ;
	private String option ;
	private String annee ;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy");


	public Formation (String departement , String niveau ,
					String option , String annee ) throws Exception{


		this.departement=departement;
		this.niveau=niveau;
		this.option=option ;
		this.annee=annee;
	}

	public String getDepartement(){
		return this.departement ;
	} 

	public String getNiveau(){
		return this.niveau ;
	} 

	public String getOption(){
		return this.option ;
	} 

	public String getAnnee(){
		return this.annee ;
	} 




}