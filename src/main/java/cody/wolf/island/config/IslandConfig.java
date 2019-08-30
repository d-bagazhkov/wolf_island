package cody.wolf.island.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("island")
public class IslandConfig {
    private Integer countHorizontalCeil;
    private Integer countVerticalCeil;
    private Integer sizeCeil;

    @Value("${island.start.count.wolf}")
    private Integer startCountWolf;

    @Value("${island.start.count.rabbit}")
    private Integer startCountRabbit;
}
