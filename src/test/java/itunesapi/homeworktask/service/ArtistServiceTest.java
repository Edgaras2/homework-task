package itunesapi.homeworktask.service;

import itunesapi.homeworktask.entity.Artist;
import itunesapi.homeworktask.entity.User;
import itunesapi.homeworktask.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ArtistService classUnderTest = new ArtistService();

    @Test
    public void save_user_whenArtistServiceIsCalled() {
        // Given
        User user = new User();
        Artist artist = new Artist();

        // When
        classUnderTest.saveOrUpdate(user, artist);

        // Then
        verify(userRepository).save(user);
    }

    @Test
    public void retrieve_artist_whenArtistNameIsGiven() {
        // Given
        String artists = "Eminem";

        // When
        List<Artist> result = classUnderTest.getArtists(artists);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(347307, (long) result.get(0).getAmgArtistId());
    }

    @Test
    public void retrieve_artistAlbums_whenArtistAmgArtistIdIsGiven() {
        // Given
        Long amgArtistId = 347307L;
        String artists = "Eminem";

        // When
        List<Artist> result = classUnderTest.getTopAlbums(amgArtistId);

        // Then
        assertNotNull(result);
        assertEquals(6, result.size());
        assertEquals(347307, (long) result.get(0).getAmgArtistId());
        assertEquals(artists, result.get(0).getArtistName());
    }
}