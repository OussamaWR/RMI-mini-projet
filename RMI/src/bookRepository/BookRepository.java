package bookRepository;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import entities.Book;

public interface BookRepository extends Remote {
	 public Connection Connect() throws RemoteException;
	
	 public int AddBook(String name , String edition , String price , String Author ) throws RemoteException;
	 public int UpdateBook(String id ,String name , String edition , String price , String Author ) throws RemoteException;
	 public Book  GetById(String id) throws RemoteException;
	 public int DeleteById(String id)throws RemoteException;
	 public Vector<Book> GetAll() throws RemoteException;
	 
	 
	

}
