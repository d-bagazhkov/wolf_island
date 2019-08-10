package kodun.wolf.island.css;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import kodun.wolf.island.Configuration;

public class Styles {

    public Border blackBorder() {
        return new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                new BorderWidths(Configuration.widthCeilBorder)));
    }

}
