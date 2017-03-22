package BaseDonnees;

import java.sql.Date;
import java.text.*;

public class Membre {

	private String login ;
	private String mdpasse ;
	private String prenom ;
	private String nom ;
	private Date dateNaiss ;
	private String email ;
	private String adresse ;
	private String tel ;
	private String telBureau ;
	private String faxe ;
	private Formation [] formation ;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

	public Membre (String login , String mdpasse , String prenom ,
		String nom , String dateNaiss , String email, String adresse , String tel ,
		 String telBureau , String faxe , Formation... formation ) throws Exception {

		this.login=login ;
		this.mdpasse=mdpasse ;
		this.nom=nom ;
		this.prenom=prenom ;
		this.dateNaiss= new Date (formatter.parse(dateNaiss).getTime());
		this.email=email ;
		this.adresse = adresse ;
		this.tel=tel;
		this.telBureau=telBureau ;
		this.faxe=faxe ;
		this.formation = new Formation [formation.length] ;

		/*boucle pour remplir les différentes formations d'un membre */
		for (int i =0 ; i<formation.length ; i++)
			this.formation[i]=formation[i];

	}

   // les getters

	public String getLogin(){
		return this.login ;
	}

	public String getmdpasse(){
		return this.mdpasse ;
	}

	public String getNom(){
		return this.nom ;
	}

	public String getPrenom(){
		return this.prenom ;
	}

	public java.sql.Date getDateNaiss(){
		return this.dateNaiss ;
	}

	public String getEmail(){
		return this.email ;
	}

	public String getAdresse(){
		return this.adresse ;
	}

	public String getTel(){
		return this.tel;
	}

	public String getTelBureau(){
		return this.telBureau ;
	}

	public String getFaxe(){
		return this.faxe ;
	}

	public Formation [] getFormation(){
		return this.formation ;
	}




// les setteurs

	public void setLogin(String login){
		this.login=login ;
	}

	public void setmdpasse(String mdpasse){
		this.mdpasse=mdpasse ;
	}

	public void setNom(String nom){
		this.nom=nom ;
	}

	public void setPrenom(String prenom){
		this.prenom=prenom ;
	}

	public void setDateNaiss(Date dateNaiss){
		this.dateNaiss= dateNaiss ;
	}

	public void setEmail(String email){
		this.email=email ;
	}

	public void setAdresse(String adresse){
		this.adresse=adresse ;
	}

	public  void setTel(String tel){
		this.tel=tel;
	}

	public void setTelBureau(String telBureau){
		this.telBureau=telBureau ;
	}

	public void setFaxe(String faxe){
		this.faxe=faxe ;
	}

	public void setFormation(Formation... formation){
		/*boucle pour remplir les différentes formations d'un membre */
		for (int i =0 ; i<formation.length ; i++)
			this.formation[i]=formation[i];


}
}
