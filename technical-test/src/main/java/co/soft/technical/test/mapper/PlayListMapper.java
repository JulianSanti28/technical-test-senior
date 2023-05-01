package co.soft.technical.test.mapper;

import co.soft.technical.test.dto.PlayListDto;
import co.soft.technical.test.model.PlayList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SongMapper.class})
public interface PlayListMapper extends IEntityMapper<PlayListDto, PlayList> {

    @Mapping(target = "songs", source = "songs")
    PlayList toDomain(PlayListDto dto);
    @Mapping(target = "songs", source = "songs")
    PlayListDto toDto(PlayList entity);

}
