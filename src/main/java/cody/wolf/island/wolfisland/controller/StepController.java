package cody.wolf.island.wolfisland.controller;

import cody.wolf.island.wolfisland.model.Entity;
import cody.wolf.island.wolfisland.service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
