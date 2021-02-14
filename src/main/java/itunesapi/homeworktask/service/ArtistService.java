package itunesapi.homeworktask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itunesapi.homeworktask.dto.Artists;
import itunesapi.homeworktask.entity.Artist;
import itunesapi.homeworktask.entity.User;
import itunesapi.homeworktask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service is used to retrieve data related to artists from itunes using users favorite artist data
 */
@Service
public class ArtistService {

    @Autowired
    private UserRepository userRepository;

    private String URL_ARTISTS = "https://itunes.apple.com/search?entity=allArtist";
    private String URL_TOP_ALBUMS = "https://itunes.apple.com/lookup?";

    public void saveOrUpdate(User user, Artist artist) {
        artist.setUser(user);
        user.setArtist(artist);
        userRepository.save(user);
    }

    public List<Artist> getTopAlbums(Long amgArtistId) {
        try {
            String url = buildUrlTopAlbums(amgArtistId);
            String response = createArtistsJson(url);
            System.out.println(response);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, Artists.class).getArtistList();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to parse value");
        }
    }

    public List<Artist> getArtists(String artist) {
        try {
            String url = buildUrlArtists(artist);
            String response = createArtistsJson(url);
            System.out.println(response);

            ObjectMapper objectMapper = new ObjectMapper();
            Artists artists = objectMapper.readValue(response, Artists.class);
            return artists.getArtistList().stream()
                    .filter(a -> a.getAmgArtistId() != null)
                    .collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to parse value");
        }
    }

    private String createArtistsJson(String link) {
        try {
            URL url = new URL(link);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openConnection().getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            return sb.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong, please try again later");
        }
    }

    private String buildUrlArtists(String artist) {
        return URL_ARTISTS + appendArtist(artist);
    }

    private String buildUrlTopAlbums(Long amgArtistId) {
        return URL_TOP_ALBUMS + appendAmgArtistId(amgArtistId) + appendAlbum("album") + appendLimit(5);
    }

    private String appendLimit(int limit) {
        return "&limit=" + limit;
    }

    private String appendAlbum(String album) {
        return "&entity=" + album;
    }

    private String appendAmgArtistId(Long amgArtistId) {
        return "amgArtistId=" + amgArtistId;
    }

    private String appendArtist(String artist) {
        return "&term=" + artist;
    }
}
