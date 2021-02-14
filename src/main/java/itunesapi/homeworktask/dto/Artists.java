package itunesapi.homeworktask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import itunesapi.homeworktask.entity.Artist;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artists {

    @JsonProperty(value = "results")
    private List<Artist> artistList;

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }
}
