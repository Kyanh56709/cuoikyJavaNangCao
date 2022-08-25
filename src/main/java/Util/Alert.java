package Util;

import io.github.palexdev.materialfx.controls.MFXDialog;
import io.github.palexdev.materialfx.controls.factories.MFXAnimationFactory;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

import java.util.List;

public class Alert {
    private static MFXDialog dialog;

    public enum DialogType {
        ERROR, SUCCESS, CONFIRMATION
    }

    private static StackPane header;
    private static MFXFontIcon icon;

    private static Label content;

    private static HBox buttonBox;

    public static MFXDialog getInstance() {
        if (dialog == null) {
            new Alert();
        }
        return dialog;
    }

    public static void showDialog(DialogType dialogType, String message, List<Button> action) {
        buildHeader(dialogType);
        content.setText(message);
        buttonBox.getChildren().setAll(action);
        for (Button button : action) {
            dialog.setCloseButtons(button);
        }//nut dong
        dialog.show();
    }

    private static void buildHeader(DialogType dialogType) {
        switch (dialogType) {
            case ERROR:
                header.setStyle("-fx-background-color: #f44336;");
                icon.setDescription("mfx-x-circle-light");
                break;
            case SUCCESS:
                header.setStyle("-fx-background-color: #00c853;");
                icon.setDescription("mfx-variant8-mark");
                break;
            case CONFIRMATION:
                header.setStyle("-fx-background-color: #00c853;");
                icon.setDescription("mfx-info-circle");
                break;
        }
    }

    private Alert() {
        dialog = new MFXDialog();
        dialog.setPrefHeight(200);  //200
        dialog.setPrefWidth(300);   //300

        dialog.setVisible(false);

        header = new StackPane();
        header.setAlignment(Pos.CENTER);
        header.getStyleClass().add("header-node"); //header-node
        icon = new MFXFontIcon();
        icon.setSize(80);
        icon.setColor(Paint.valueOf("white"));
        header.getChildren().add(icon);
        header.setPrefHeight(120);
        dialog.setTop(header);

        content = new Label();
        content.getStyleClass().add("content-label");
        dialog.setCenter(content);

        buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getStyleClass().add("buttons-box");
        dialog.setBottom(buttonBox);

        // chạy lên giữa rồi sang phải
        dialog.setAnimateIn(true);
        dialog.setAnimateOut(true);
        dialog.setInAnimationType(MFXAnimationFactory.SLIDE_IN_TOP);
        dialog.setOutAnimationType(MFXAnimationFactory.SLIDE_OUT_RIGHT);

    }



}
