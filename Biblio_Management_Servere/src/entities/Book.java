package entities;

import java.io.Serializable;

public class Book implements Serializable {


	private static final long serialVersionUID = 7597629736125245082L;
	
	public  String id ; 
	public  String name ; 
	public  String author ; 
	public  String price ; 
	public  String editions;
	
	
	public Book(String id , String name, String author, String price, String editions) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.editions = editions;
	} 
	
	public Book( ) {
		super();
	 
	} 
	
	
	
}
