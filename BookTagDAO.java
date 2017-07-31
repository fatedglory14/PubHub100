package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.BookTag;
import examples.pubhub.model.Book;

public interface BookTagDAO {
	
	public List<BookTag> getAllBookTags();
	public List<BookTag> getAllBooks();
	public List<BookTag> getTagsByBook(String isbn13);
	public List<Book> getBooksByTag(String tag);
	public BookTag getBookByISBN(String isbn);

	public boolean addBookTag(BookTag Booktag);
	public boolean deleteBookTag(BookTag Booktag);
}
