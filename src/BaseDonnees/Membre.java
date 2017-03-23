package BaseDonnees;
import java.sql.Date;
import java.text.*;

public class Membre {

	private String tel ;
	private String prenom ;
	private String nom ;
	private Date dateNaiss ;
	private String email ;
	private String adresse ;
	private String telBureau ;
	private String faxe ;
	private Formation [] formation ;

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

	public Membre (String tel , String prenom ,
		String nom , String dateNaiss , String email, String adresse ,
		 String telBureau , String faxe , Formation[] formation ) throws Exception {

		this.tel=tel ;
		this.nom=nom ;
		this.prenom=prenom ;
		this.dateNaiss= new Date (this.formatter.parse(dateNaiss).getTime());
		this.email=email ;
		this.adresse = adresse ;
		this.telBureau=telBureau ;
		this.faxe=faxe ;
		this.formation=formation;

	}

   // les getters

	public String getTel() { return this.tel ; }

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

	public void setNom(String nom){
		this.nom=nom ;
	}

	public void setPrenom(String prenom){
		this.prenom=prenom ;
	}

	public void setDateNaiss(String dateNaiss){
		try {
			this.dateNaiss = new Date (this.formatter.parse(dateNaiss).getTime()) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public void setFormation(Formation[] formation){
		/*boucle pour remplir les différentes formations d'un membre */
		for (int i =0 ; i<formation.length ; i++)
			this.formation[i]=formation[i];
}
}
