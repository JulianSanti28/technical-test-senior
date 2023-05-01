package co.soft.technical.test.mapper;

import co.soft.technical.test.dto.SongDto;
import co.soft.technical.test.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper extends IEntityMapper<SongDto, Song> {
    @Mapping(target = "genre", source = "genre")
    Song toDomain(SongDto dto);
    @Mapping(target = "genre", source = "genre")
    SongDto toDto(Song entity);

}
