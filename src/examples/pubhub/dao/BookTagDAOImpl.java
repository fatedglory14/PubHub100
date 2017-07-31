package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

//Implementation for the BookTagDAO

public class BookTagDAOImpl implements BookTagDAO {
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	//END OF CONNECTION TO DATABASE STATEMENTS
	

//--------------------------------------------------------------------
	
	
	@Override
	public List<BookTag> getAllBookTags() {
		
		List<BookTag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT tags FROM book_tags";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
			
				BookTag tag = new BookTag();
				
				tag.setTag(rs.getString("tags"));

				tags.add(tag);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}

	
//----------------------------------------------------------------------
	
	
	@Override
	public List<BookTag> getAllBooks() {
		
		List<BookTag> books = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT isbn_13 FROM book_tags";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				BookTag book = new BookTag();
				
				book.setIsbn13(rs.getString("isbn_13"));
				
				books.add(book);

			}
			
			rs.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;	
	}

//----------------------------------------------------------------------
	
	
	@Override
	public List<BookTag> getTagsByBook(String isbn13) {
		List<BookTag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn13);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				BookTag tag = new BookTag();
				
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag(rs.getString("tags"));
				
				tags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tags;
	}
	
	
//----------------------------------------------------------------------
	
	
	@Override
	public List<Book> getBooksByTag(String tag) {
		List<Book> books = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM books WHERE isbn_13 IN (SELECT isbn_13 FROM book_tags WHERE tags = ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));

				books.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return books;
	}
	
	
//----------------------------------------------------------------------

	@Override
	public BookTag getBookByISBN(String isbn) {
		BookTag book = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				book = new BookTag();
				book.setIsbn13(rs.getString("isbn_13"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return book;
	}
	
	
	//----------------------------------------------------------------
	
	
	@Override
	public boolean addBookTag(BookTag Booktag) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (?, ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, Booktag.getIsbn13());
			stmt.setString(2, Booktag.getTag());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}	
			
	}
	
	
//----------------------------------------------------------------------
	
	
	@Override
	public boolean deleteBookTag(BookTag Booktag) {

		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE isbn_13=? AND tags=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, Booktag.getIsbn13());
			stmt.setString(2, Booktag.getTag());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}	
	}
	
	
//----------------------------------------------------------------------
	
	
	//Method for closing resources to prevent memory leaks.
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

	
//-----END OF Implementation--------------------------------------------	


}
