package cody.wolf.island.controller;

import cody.wolf.island.config.IslandConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final IslandConfig islandConfig;

    @RequestMapping(value = {"/", "home", "index"})
    public String index(Model model) {
        model.addAttribute("sizeCeil", islandConfig.getSizeCeil());
        return "index";
    }
}