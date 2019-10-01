package cody.wolf.island.controller;

import cody.wolf.island.model.WebResponse;
import cody.wolf.island.service.GameService;
import cody.wolf.island.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final GameService gameService;
    private final StatsService statsService;

    @PostMapping
    public WebResponse handle() {
        return new WebResponse(
                gameService.handle(),
                statsService.getStats()
        );
    }

}
