package pl.edu.pw.ee.tictactoe;

import javafx.scene.image.Image;

import java.util.Objects;

public abstract class Images {
    private Images(){}

    public static final Image CIRCLE_IMAGE = new Image(Objects.requireNonNull(MainController.class.getResource("img/circle.png")).toString());
    public static final Image CROSS_IMAGE = new Image(Objects.requireNonNull(MainController.class.getResource("img/cross.png")).toString());
    public static final Image BLANK_IMAGE = new Image(Objects.requireNonNull(MainController.class.getResource("img/blank.png")).toString());
}
