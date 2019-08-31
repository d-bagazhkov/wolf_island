package cody.wolf.island.controller;

import cody.wolf.island.model.IslandTable;
import cody.wolf.island.service.TableService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MoveController {

    private final TableService tableService;

    @CrossOrigin
    @PostMapping("/handle")
    public IslandTable handleStep() {
        long start = new Date().getTime();
        try {
            log.debug("Start handle move");
            return tableService.handle();
        }finally {
            log.debug("Resolve all move. Time: {}ms", new Date().getTime() - start);
        }
    }

    @CrossOrigin
    @GetMapping("/reset")
    public IslandTable reset() {
        return tableService.reset();
    }

}
