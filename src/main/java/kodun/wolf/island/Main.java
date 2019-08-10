package kodun.wolf.island;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kodun.wolf.island.css.Styles;

import static kodun.wolf.island.Configuration.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Styles styles = new Styles();

    @Override
    public void start(Stage primaryStage) {
        GridPane rootGridPane = new GridPane();
        rootGridPane.setGridLinesVisible(true);
        for (int i = 0; i < countCeilHeight; i++) {
            RowConstraints rowConstraints = new RowConstraints(widthCeil);
            rootGridPane.getRowConstraints().add(rowConstraints);
        }
        for (int i = 0; i < countCeilWidth; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(widthCeil);
            rootGridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < countCeilWidth; i++) {
            for (int j = 0; j < countCeilHeight; j++) {
                Pane pane = new Pane();
                pane.setBorder(styles.blackBorder());
                rootGridPane.add(pane, i, j);
            }
        }

        System.out.printf("Width %d; Height: %d. Count row ceil %d; count column ceil %d\n", width, height, countCeilWidth, countCeilHeight);
        primaryStage.setTitle("Wolf island");
        Scene scene = new Scene(rootGridPane, width, height);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
