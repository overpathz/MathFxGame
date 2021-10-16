package util;

import javafx.stage.Stage;

public final class Buffer {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Buffer.stage = stage;
    }
}
