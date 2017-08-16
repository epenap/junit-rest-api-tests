package test.api_tests.books.dtos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookVolumeDto {
	public String kind;
	public VolumeInfoDto volumeInfo;
}
