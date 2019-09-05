package cody.wolf.island.controller;

import cody.wolf.island.service.TableService;
import cody.wolf.island.service.impl.TableServiceImpl;
import cody.wolf.island.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MoveController {

    private final GameService gameService;
    private final ObjectMapper objectMapper;

    @CrossOrigin
    @PostMapping("/handle")
    public TableService handleStep() {
        long start = new Date().getTime();
        try {
            log.debug("Start handle move");
            return gameService.handle();
        } finally {
            log.debug("Resolve all move. Time: {}ms", new Date().getTime() - start);
        }
    }

    @CrossOrigin
    @GetMapping("/reset")
    public TableService reset() {
        return gameService.reset();
    }

    @CrossOrigin
    @GetMapping("/socket/handle/{interval}")
    public Flux<ServerSentEvent<TableService>> socket(@PathVariable("interval") Integer interval) {
        return Flux.interval(Duration.ofMillis(interval))
                .map(sequence -> ServerSentEvent.<TableService>builder()
                        .id(String.valueOf(sequence))
                        .data(handleStep())
                        .build());
    }

}
