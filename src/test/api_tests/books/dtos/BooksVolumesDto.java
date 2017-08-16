package test.api_tests.books.dtos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksVolumesDto {
	public String kind;
	public int totalItems;
	public ArrayList<BookVolumeDto> items;
}
