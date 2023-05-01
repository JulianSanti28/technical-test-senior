package co.soft.technical.test.service;

import co.soft.technical.test.dto.PlayListDto;
import java.util.List;

public interface IPlayListService {
    PlayListDto savePlayList(final PlayListDto playListDto);
    List<PlayListDto> findAllPlayList();
    PlayListDto findPlayListByName(String name);
    void deleteByName(String name);
}
