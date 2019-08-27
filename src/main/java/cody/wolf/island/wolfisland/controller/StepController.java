package cody.wolf.island.wolfisland.controller;

import cody.wolf.island.wolfisland.model.Entity;
import cody.wolf.island.wolfisland.service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StepController {

    private final StepService stepService;

    @CrossOrigin
    @PostMapping("/handle")
    public List<Entity> handleStep(@RequestBody List<Entity> positions) {
        return stepService.handle(positions);
    }

    @CrossOrigin
    @GetMapping("/reset")
    public List<Entity> reset() {
        return stepService.reset();
    }

}
