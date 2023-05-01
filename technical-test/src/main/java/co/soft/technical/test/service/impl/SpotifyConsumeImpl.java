package co.soft.technical.test.service.impl;

import co.soft.technical.test.exception.ObjectNotFoundException;
import co.soft.technical.test.pojo.SpotifyGenres;
import co.soft.technical.test.pojo.SpotifyToken;
import co.soft.technical.test.service.IRestServiceConsume;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Qualifier("spotifyService")
public class SpotifyConsumeImpl implements IRestServiceConsume<SpotifyGenres> {

    // ~ Attributes
    // ====================================================================
    @Value("${spotify.client_id}")
    private String clientId;
    @Value("${spotify.client_secret}")
    private String clientSecret;
    @Value("${spotify.token_url}")
    private String tokenUrl;
    @Value("${spotify.consult_url}")
    private String consultUrl;

    private SpotifyToken getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(this.clientId, this.clientSecret);
        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);
        ResponseEntity<SpotifyToken> response = restTemplate.postForEntity(tokenUrl, request, SpotifyToken.class);
        return response.getBody();
    }
    @Override
    public SpotifyGenres getConsultInformation() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(getAccessToken().getAccessToken());
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<SpotifyGenres> response = restTemplate.exchange(consultUrl, HttpMethod.GET, request, SpotifyGenres.class);
            return response.getBody();
        }catch(Exception e){
           throw new ObjectNotFoundException("spotify.service.information.not.found");
        }
    }
}
