package test.api_tests.books;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import test.api_tests.books.dtos.BooksVolumesDto;

public class GetBooks {
	private static String BOOKS_ENDPOINT = "https://www.googleapis.com/books/v1/volumes";
	
    private WebTarget target;

    @Before
    public void setup(){
    	Client client = ClientBuilder.newClient();
        target = client.target(BOOKS_ENDPOINT);
    }

    @Test
    public void whenIUseAValidISBNAsQuery_thenIGetAValidBooksAsAResponse(){
    	// arrange
        target = target.queryParam("q", "0345341465");

        // act
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        // assert
        assertEquals(200, response.getStatus());
        
        BooksVolumesDto dto = response.readEntity(BooksVolumesDto.class);
        assertNotEquals(null, dto);
        assertEquals("books#volumes", dto.kind);
        assertTrue(dto.totalItems > 1);
        
        assertTrue(dto.items.stream().anyMatch(bookVolume -> 
        	bookVolume.volumeInfo != null &&
    		bookVolume.volumeInfo.title.equals("Star Wars") &&
    		bookVolume.volumeInfo.publisher.equals("Lucasbooks") &&
    		bookVolume.volumeInfo.pageCount == 224));
    }

    @Test
    public void whenIUseANonMatchingQuery_thenIGetAResponseWithNoItems(){
    	// arrange
        target = target.queryParam("q", "nonmatchingqueryforbooks");

        // act
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        // assert
        assertEquals(200, response.getStatus());
        
        BooksVolumesDto dto = response.readEntity(BooksVolumesDto.class);
        assertNotEquals(null, dto);
        assertEquals("books#volumes", dto.kind);
        assertEquals(0, dto.totalItems);
        assertEquals(null, dto.items);
    }

    @Test
    public void whenIDoNotProvideAnyQuery_thenIGetABadRequestResponse(){
        // act
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        // assert
        assertEquals(400, response.getStatus());
    }
}
