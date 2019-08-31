package cody.wolf.island.controller;

import cody.wolf.island.model.IslandTable;
import cody.wolf.island.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MoveController {

    private final TableService tableService;

    @CrossOrigin
    @PostMapping("/handle")
    public IslandTable handleStep() {
        return tableService.handle();
    }

    @CrossOrigin
    @GetMapping("/reset")
    public IslandTable reset() {
        return tableService.reset();
    }

}
