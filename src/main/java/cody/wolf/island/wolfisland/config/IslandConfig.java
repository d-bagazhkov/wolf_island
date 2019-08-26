package cody.wolf.island.wolfisland.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("island")
public class IslandConfig {
    private Integer countHorizontalCeil;
    private Integer countVerticalCeil;
    private Integer sizeCeil;
}
