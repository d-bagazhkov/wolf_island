package cody.wolf.island.wolfisland.controller;

import cody.wolf.island.wolfisland.config.IslandConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final IslandConfig islandConfig;

    @RequestMapping(value = {"/", "home", "index"})
    public String index(Model model) {
        model.addAttribute("sizeCeil", islandConfig.getSizeCeil());
        model.addAttribute("countVerticalCeil", islandConfig.getCountVerticalCeil());
        model.addAttribute("countHorizontalCeil", islandConfig.getCountHorizontalCeil());
        return "index";
    }
}
