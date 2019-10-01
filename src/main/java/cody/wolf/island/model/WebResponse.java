package cody.wolf.island.model;

import cody.wolf.island.service.Island;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class WebResponse {

    private Island island;
    private Map<String, String> stats;


}
