package cody.wolf.island.controller;

import cody.wolf.island.model.Entity;
import cody.wolf.island.service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StepController {

    private final StepService stepService;

    @CrossOrigin
    @PostMapping("/handle")
    public List<Entity> handleStep() {
        return stepService.handle();
    }

    @CrossOrigin
    @GetMapping("/reset")
    public List<Entity> reset() {
        return stepService.reset();
    }

}
