package co.soft.technical.test.service.impl;

import co.soft.technical.test.dto.PlayListDto;
import co.soft.technical.test.dto.SongDto;
import co.soft.technical.test.exception.BusinessRuleException;
import co.soft.technical.test.exception.ObjectNotFoundException;
import co.soft.technical.test.mapper.PlayListMapper;
import co.soft.technical.test.model.PlayList;
import co.soft.technical.test.pojo.SpotifyGenres;
import co.soft.technical.test.repository.IPlayListRepository;
import co.soft.technical.test.service.IPlayListService;
import co.soft.technical.test.service.IRestServiceConsume;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayListServiceImpl implements IPlayListService {

    // ~ Dependencies
    // ====================================================================

    private final IPlayListRepository playListRepository;
    private final PlayListMapper playListMapper;

    private final IRestServiceConsume spotifyRestServiceConsume;

    // ~ Dependency Injection
    // ====================================================================
    public PlayListServiceImpl(IPlayListRepository playListRepository, PlayListMapper playListMapper, @Qualifier("spotifyService") IRestServiceConsume spotifyRestServiceConsume) {
        this.playListRepository = playListRepository;
        this.playListMapper = playListMapper;
        this.spotifyRestServiceConsume = spotifyRestServiceConsume;
    }

    @Override
    public PlayListDto savePlayList(PlayListDto playListDto) {
        Optional<PlayList> playListOpt = this.playListRepository.findByName(playListDto.getName());
        if(playListOpt.isPresent()) throw new BusinessRuleException("bad.request.play.list.name.duplicate");
        Set<SongDto> songDtoSet = playListDto.getSongs();
        SpotifyGenres genresResponse =  (SpotifyGenres) this.spotifyRestServiceConsume.getConsultInformation();
        List<String> genreList = genresResponse.getGenres();
        /*Check the genre for each song, if the genre is valid then it will be added*/
        Set<SongDto> filteredSongs = songDtoSet.stream()
                .filter(song -> genreList.stream().anyMatch(song.getGenre()::equalsIgnoreCase))
                .collect(Collectors.toSet());
        /*Replace songs list*/
        playListDto.setSongs(filteredSongs);
        /*Continue*/
        return this.playListMapper.toDto(this.playListRepository.save(this.playListMapper.toDomain(playListDto)));
    }

    @Override
    public List<PlayListDto> findAllPlayList() {
        List<PlayList> playListList = this.playListRepository.findAll();
        return playListList
                .stream()
                .map(this.playListMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public PlayListDto findPlayListByName(String name) {
        Optional<PlayList> playListOpt = this.playListRepository.findByName(name);
        if(playListOpt.isEmpty()) throw new ObjectNotFoundException("bad.request.play.list.name.not.found");
        return this.playListMapper.toDto(playListOpt.get());
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<PlayList> playListOpt = this.playListRepository.findByName(name);
        if(playListOpt.isEmpty()) throw new ObjectNotFoundException("bad.request.play.list.name.not.found");
        this.playListRepository.deleteByName(name);
    }
}
