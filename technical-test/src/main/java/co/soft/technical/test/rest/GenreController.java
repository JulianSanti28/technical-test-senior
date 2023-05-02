package co.soft.technical.test.rest;


import co.soft.technical.test.pojo.SpotifyGenres;
import co.soft.technical.test.service.IRestServiceConsume;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private final IRestServiceConsume spotifyRestServiceConsume;
    public GenreController(@Qualifier("spotifyService") IRestServiceConsume spotifyRestServiceConsume) {
        this.spotifyRestServiceConsume = spotifyRestServiceConsume;
    }
    @GetMapping
    public ResponseEntity<SpotifyGenres> findAllGenresSpotify(){
        return ResponseEntity.ok((SpotifyGenres)this.spotifyRestServiceConsume.getConsultInformation());
    }
}
