package co.soft.technical.test.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayListDto {
    private Long id;
    @JsonProperty("nombre")
    @NotNull(message = "{playlist.name.not.null}")
    @NotEmpty(message = "{playlist.name.not.empty}")
    private String name;

    @JsonProperty("descripcion")
    @NotNull(message = "{playlist.description.not.null}")
    @NotEmpty(message = "{playlist.description.not.empty}")
    private String description;
    @JsonProperty("canciones")
    @NotNull(message = "{playlist.songs.not.null}")
    @Size(min = 1, message = "{playlist.songs.not.empty}")
    @Valid
    private Set<SongDto> songs;
}
