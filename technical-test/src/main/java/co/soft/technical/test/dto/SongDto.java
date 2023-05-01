package co.soft.technical.test.dto;

import co.soft.technical.test.model.PlayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongDto {
    private Long id;
    @JsonProperty("titulo")
    @NotNull(message = "{song.title.not.null}")
    @NotEmpty(message = "{song.title.not.empty}")
    private String title;
    @JsonProperty("artista")
    @NotNull(message = "{song.artist.not.null}")
    @NotEmpty(message = "{song.artist.not.empty}")
    private String artist;
    @JsonProperty("album")
    @NotNull(message = "{song.album.not.null}")
    @NotEmpty(message = "{song.album.not.empty}")
    private String album;
    @JsonProperty("anno")
    @Min(value = 1900, message = "{song.year.min}")
    private Integer year;
    @JsonProperty("genero")
    private String genre;
}
