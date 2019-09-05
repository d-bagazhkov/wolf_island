package cody.wolf.island.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("island")
public class IslandConfig {
    private Integer countHorizontalCell;
    private Integer countVerticalCell;
    private Integer sizeCell;

    private Map<String, ThingCharacteristic> characteristic;

    public ThingCharacteristic getWolfConfig() {
        return characteristic.get("wolf");
    }

    public ThingCharacteristic getRabbitConfig() {
        return characteristic.get("rabbit");
    }

    @Data
    public static class ThingCharacteristic {
        private int count;
        private int bornAge;
        private int decEnergy;
        private int incEnergy;
    }
}