package co.soft.technical.test.rest;

import co.soft.technical.test.dto.PlayListDto;
import co.soft.technical.test.service.IPlayListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lists")
public class PlayListController {

    // ~ Dependencies
    // ====================================================================
    private final IPlayListService playListService;

    // ~ Dependency Injection
    // ====================================================================
    public PlayListController(IPlayListService playListService) {
        this.playListService = playListService;
    }

    // ~ API Methods
    // ====================================================================
    @PostMapping
    public ResponseEntity<PlayListDto> saveEmployee(@Valid @RequestBody final PlayListDto playListDto){
        return new ResponseEntity<>(this.playListService.savePlayList(playListDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlayListDto>> findAllPlayList(){
        return new ResponseEntity<>(this.playListService.findAllPlayList(), HttpStatus.OK);
    }

    @GetMapping("/{listName}")
    public ResponseEntity<PlayListDto> findByName(@PathVariable("listName") String name){
        return new ResponseEntity<>(this.playListService.findPlayListByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<Map<String, String>> deleteByName(@PathVariable("listName") String name){
        this.playListService.deleteByName(name);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Registro eliminado exitosamente");
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

}
