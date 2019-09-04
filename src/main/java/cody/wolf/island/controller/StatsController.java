package cody.wolf.island.controller;

import cody.wolf.island.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/stats")
    public Map<String, String> getStatsPolling() {
        return new HashMap<String, String>() {{
            put("Count wolf", String.valueOf(statsService.getCountWolf()));
            put("Count rabbit", String.valueOf(statsService.getCountRabbit()));
            put("Count step", String.valueOf(statsService.getCountSteps()));
        }};
    }

    @GetMapping("/socket/stats/{interval}")
    public Flux<ServerSentEvent<Map<String, String>>> getStatsSocket(@PathVariable("interval") Integer interval) {
        return Flux.interval(Duration.ofMillis(interval))
                .map(sequence -> ServerSentEvent.<Map<String, String>>builder()
                        .id(String.valueOf(sequence))
                        .data(getStatsPolling())
                        .build());
    }

}
