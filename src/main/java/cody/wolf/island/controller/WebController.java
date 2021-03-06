package cody.wolf.island.controller;

import cody.wolf.island.config.IslandConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    private static final String CELL_SIZE = "sizeCell";
    private static final String PATH_TO_INDEX = "pathToIndex";
    private final IslandConfig islandConfig;

    @RequestMapping(value = {"/", "home", "index"})
    public String index(Model model) {
        model.addAttribute(CELL_SIZE, islandConfig.getSizeCell());
        model.addAttribute(PATH_TO_INDEX, islandConfig.getPathToIsland());
        return "index";
    }
}
