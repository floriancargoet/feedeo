package serveur;


import java.util.Date;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

public class Article{
	//ID de l'article
	private Long idArticle;
	//Tittre de l'article
	private String title;
	//Lien de l'article
	private String link;
	//Date de l'article
	private Date date;
	//Cat�gories de l'article /tags
	private List<String> categories;
	
	//R�sum� de l'article()
	private String summary;
	//LIEN AVEC LES PROPRIETES D'UN ARTICLES lien one to many avec la classe Articl_Properties
	private Set<Articles_Properties> articles_properties=new HashSet<Articles_Properties>();
	//LIEN MANY TO MANY AVEC USER SET DE PROPRIETES
	private Set<Articles_Properties> article_properties=new HashSet<Articles_Properties>();
	
	
	
	//PROPRIETE DE L'ARTICLE 
	//private Set<Articles_User>
	
	
	//Auteur de l'article
	private String author;
	//Lien article au dossier many to many
	private Set<Directory> listDir;
	//proprio de l'article
	private String owner;
	
	public Article(){}
	
	public Article(String title,String url,Date date,String summary,String author,List<String> categories, boolean read,String owner){
		this.title=title;
		this.link=url;
		this.date=date;
		this.summary=summary;
		this.author=author;
		this.owner=owner;
		//this.read=read;
		this.categories=categories;
	}
	
	@SuppressWarnings("unchecked")
	public Article(SyndEntry se,Feed feedOrig) {
		this.title=se.getTitle();
        this.link=se.getLink();
        try{
        	this.date=se.getPublishedDate();
        	if (this.date==null) {
        		this.date=new Date(0);
        	}
    	}
        catch(Exception e){
    		this.date=new Date(0);
    		e.printStackTrace();
    	}
        try{
        	this.categories=se.getCategories();
    	}
        catch(Exception e){
    		e.printStackTrace();
    	}
        try{
        	this.summary=se.getDescription().getValue();
        }
        catch(Exception e){
        	
        	try{
        	this.summary=((SyndContent)se.getContents().get(0)).getValue();
        	}catch(Exception ex){
        		ex.printStackTrace();
        	}
        	
        }
       // this.read=(boolean)false;
        try{
        	this.author=se.getAuthor();
    	}
        catch(Exception e){
    		e.printStackTrace();
    	}
        //this.feedOrig=feedOrig;
        
    }
	
// DEBUT SET GET
	public void setId(Long i){
		idArticle=i;
	}
	public Long getIdArticle(){
		return idArticle;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setURL(String link){
		this.link= link;
	}
	
	public String getURL(){
		return link;
	}
	
	public void setDate(Date date){
		this.date= date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setCategories(List<String> cat){
		categories=cat;
	}
	public List<String> getCategories(){
		return categories;
		
		
	}
	public void addCategory(String newCat){
		categories.add(newCat);	
	}
	public void setSummary(String sum){
		summary=sum;
	}
	public String getSummary(){
		return summary;
	}
public String getSummaryLight() {
		
		String summary=this.getSummary();
		summary=summary.replaceAll("<img.*?/.{0,4}?>", "");
		summary=summary.replaceAll("<embed.*?>", "");
		return summary;
}

/*
 * public boolean getread(){
	return read;
}
public void setread(boolean read){
	this.read=read;
}
*/

public String getOwner() {
	return owner;
}

public void setOwner(String owner) {
	this.owner = owner;
}

public void setAuthor(String author){
	this.author=author;
}
	
public String getAuthor(){
	return author;
}

public void setArticlesProperties(Set<Articles_Properties> articles_properties){
	this.articles_properties=articles_properties;
}
public Set<Articles_Properties> getUserArticles(){
	return articles_properties;

}


public Set<Directory> getlistDir(){
	return this.listDir;
}

public void setlistDir(Set<Directory> listDir){
	this.listDir=listDir;
}

public Set<Articles_Properties> getArticle_user(){
	return this.article_properties;
}

public void setArticle_user(Set<Articles_Properties> article_properties){
	this.article_properties=article_properties;
}
//FIN SET GET

//GESTION DES PIECES JOINTES ENCLOSURES

//FIN GESTION DES PIECES JOINTES ENCLOSURES

//GESTION DES FONCTION ISREAD

//FIN GESTION DES FONCTION ISREAD ET SETREAD VIA L'APPLICATION EXTEND

}
