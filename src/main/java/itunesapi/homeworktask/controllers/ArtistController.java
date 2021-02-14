package itunesapi.homeworktask.controllers;

import itunesapi.homeworktask.entity.Artist;
import itunesapi.homeworktask.entity.User;
import itunesapi.homeworktask.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping(value = "/searchApi", produces = "application/json")
    public String findArtists(@SessionAttribute("user") User user, Model model, @RequestParam("artist") String artist) {
        List<Artist> artists = artistService.getArtists(artist);
        model.addAttribute("artists", artists);
        model.addAttribute("user", user);
        return "search-page";
    }

    @PostMapping(value = "/favoriteArtist", produces = "application/json")
    public String saveArtist(@SessionAttribute("user") User user,
                             @RequestParam("amgArtistId") Long amgArtistId,
                             @RequestParam("artistName") String artistName,
                             @RequestParam("genre") String genre,
                             Model model) {
        artistService.saveOrUpdate(user, new Artist(amgArtistId, artistName, genre));
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping(value = "/topAlbums", produces = "application/json")
    public String topAlbums(@SessionAttribute("user") User user, Model model) {
        if (user.getArtist() == null) {
            model.addAttribute("user", user);
            return "profile";
        }

        List<Artist> topAlbums = artistService.getTopAlbums(user.getArtist().getAmgArtistId());
        model.addAttribute("user", user);
        model.addAttribute("topAlbums", topAlbums);
        return "top-albums";
    }

}
