package com.crazyworld;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.crazyworld.in.dao.CountryLanguageRepository;
import com.crazyworld.in.service.CountryLanguageServiceImpl;

@SpringBootTest
public class CountryLanguageServiceImplTest {
	
	@Mock
	CountryLanguageRepository countrylanguageRepository;
	
	
	@InjectMocks
	CountryLanguageServiceImpl countryLanguageImpl;
	
	// here write your test methods
	// test methods are annotated with @Test
//	
//	@Test
//	public void testGetAllBooks() {
//		// create the mockito rule
//		List<BookEntity> stubAllBooks = new ArrayList<BookEntity>();
//		stubAllBooks.add(new BookEntity(101, "HarryPotter","", 222, "Fantasy", new AuthorEntity(4,"Rowling", "J.K", null)));
//		
//		when(bookRepository.findAll()).thenReturn(stubAllBooks);
//		
//		// get the expected output
//		List<BookPojo> expectedAllBooks = new ArrayList<BookPojo>();
//		expectedAllBooks.add(new BookPojo(101, "HarryPotter","", 222, "Fantasy", new AuthorPojo(4,"Rowling", "J.K", null)));
//		
//		// get the actual output
//		// here we have call bookService.getAllBooks()
//		List<BookPojo> actualAllBooks = bookServiceImpl.getAllBooks();
//		
//		// assert the expected and actual output
//		assertEquals(expectedAllBooks.size(), actualAllBooks.size());
//		verify(bookRepository).findAll(); // this line verifies whether the findAll method on bookRepositgory was called or not
//		
//	}
//	
//	@Test
//	public void testGetAllBooksException() {
//		// create mockito rule
//		List<BookEntity> stubAllBooks = new ArrayList<BookEntity>();
//		when(bookRepository.findAll()).thenReturn(stubAllBooks);
//		
//		// get the expected output
//		String expectedMessage = "No Books Available!";
//		
//		// get actual output
//		NoBooksAvailableException actualException = assertThrows(NoBooksAvailableException.class, ()->bookServiceImpl.getAllBooks());
//		String actualMessage = actualException.getMessage();
//		
//		// assert the expected and actual output
//		assertEquals(expectedMessage, actualMessage);
//		verify(bookRepository).findAll();
//		
	}


