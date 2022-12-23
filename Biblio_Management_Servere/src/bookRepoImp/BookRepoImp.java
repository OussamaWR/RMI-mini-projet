package bookRepoImp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import bookRepository.BookRepository;
import connection.GetConnection;
import entities.Book;

public class BookRepoImp extends UnicastRemoteObject implements BookRepository  {


public  GetConnection connection = new GetConnection();
public Connection conn=connection.getConnection();
public PreparedStatement pst ;

 
	
	
	public BookRepoImp() throws RemoteException {
		super();
	
	}

	@Override
	public Connection Connect() throws RemoteException {
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	             return  DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud", "root","");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
		 	return null ;
	 

	}

	@Override
	public int AddBook(String name, String edition, String price, String author) throws RemoteException  {
		try {

			pst = conn.prepareStatement("insert into book(name,edition,price ,author)values(?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, edition);
			pst.setString(3, price);
			pst.setString(4, author);
			pst.executeUpdate();
			return 1 ;
		} catch (Exception e) {
			System.out.println("error insert ");
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public int UpdateBook(String id, String name, String edition, String price, String author) throws RemoteException {
	try {
		pst = conn.prepareStatement("update book set name= ?,edition=?,price=? , author=? where id =?");
		pst.setString(1, name);
		            pst.setString(2, edition);
		            pst.setString(3, price);
		            pst.setString(4, author);
		            pst.setString(5, id);
		            pst.executeUpdate();
		            return 1;
	} catch (Exception e) {
		
		System.out.println(e.getMessage());

	}
	return 0 ;
	}

	@Override
	public Book GetById(String id) throws RemoteException {
 
		try {
		pst = conn.prepareStatement("select name,edition,price,author from book where id = ?");
	    pst.setString(1, id);
	    ResultSet rs = pst.executeQuery();
	    if(rs.next()==true)
 	      {
 	         return new Book("" ,rs.getString(1) ,rs.getString(4) ,rs.getString(3) , rs.getString(2));
 	      }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
         
          
		return null;
	}

	
	@Override
	public int DeleteById(String id) throws RemoteException {
		try {
 
		pst = conn.prepareStatement("delete from book where id =?");
	    pst.setString(1, id);
	    pst.executeUpdate();
	    return 1 ;
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return 0;
	}

	@Override
	public Vector<Book> GetAll() throws RemoteException {
		Vector<Book> books = new  Vector() ;
		try {
			 pst = conn.prepareStatement("select * from book");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				books.add(new Book(rs.getString("id"),rs.getString("name"),rs.getString("author"),
						rs.getString("price"),rs.getString("edition")));
			}
			return books;
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		return null;
	}

}
