package test.api_tests.books.dtos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfoDto {
	public String title;
	public String subtitle;
	public ArrayList<String> authors;
	public String publisher;
	public int pageCount;
}
