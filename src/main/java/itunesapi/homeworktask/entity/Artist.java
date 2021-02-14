package itunesapi.homeworktask.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "ARTIST")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {

    public Artist() {
    }

    public Artist(Long amgArtistId, String artistName, String genre) {
        this.amgArtistId = amgArtistId;
        this.artistName = artistName;
        this.genre = genre;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "AMG_ARTIST_ID")
    @JsonProperty(value = "amgArtistId")
    private Long amgArtistId;

    @Column(name = "ARTIST_NAME")
    @JsonProperty(value = "artistName")
    private String artistName;

    @Column(name = "GENRE")
    @JsonProperty(value = "primaryGenreName")
    private String genre;

    @JsonProperty(value = "collectionName")
    private String collectionName;

    @JsonProperty(value = "collectionPrice")
    private String collectionPrice;

    @OneToOne(mappedBy = "artist")
    private User user;

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(Long amgArtistId) {
        this.amgArtistId = amgArtistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", amgArtistId=" + amgArtistId +
                ", artistName='" + artistName + '\'' +
                ", genre='" + genre + '\'' +
                ", user=" + user +
                '}';
    }
}
