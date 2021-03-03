package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private ComboBox shapeType;

    @FXML
    private TextField positionX;

    @FXML
    private TextField positionY;

    @FXML
    private TextField width;

    @FXML
    private TextField height;

    @FXML
    private TextField radius;

    @FXML
    private Button addShape;

    @FXML
    private Text text;

    private List<Shape> shapes = new ArrayList<>();

    public void initialize() {
        ObservableList<Type> options = FXCollections.observableArrayList();
        for (Type type : Type.values()) {
            options.add(type);
        }

        shapeType.setItems(options);
    }


    public void addShape() {
        String notice = "";

        if (positionX.getText().isBlank())
            notice += "Shape X position is blank!\n";

        if (positionY.getText().isBlank())
            notice += "Shape Y position is blank!\n";

        if (shapeType.getSelectionModel().isEmpty())
            notice += "Shape type has to be selected!\n";

        if (!notice.equals("")) {
            showWarning(notice);
            return;
        }

        if (shapeType.getValue() == Type.RECTANGLE) {
            if (width.getText().isBlank())
                notice += "Shape width is blank!\n";

            if (height.getText().isBlank())
                notice += "Shape width is blank!\n";

            if (!notice.equals("")) {
                showWarning(notice);
                return;
            }

            float _posX = Float.parseFloat(positionX.getText());
            float _posY = Float.parseFloat(positionY.getText());
            float _width = Float.parseFloat(width.getText());
            float _height = Float.parseFloat(height.getText());

            shapes.add(new Rectangle(_posX, _posY, _width, _height));
        }
        else if (shapeType.getValue() == Type.CIRCLE) {
            if (radius.getText().isBlank())
                notice += "Shape radius is blank!\n";

            if (!notice.equals("")) {
                showWarning(notice);
                return;
            }

            float _posX = Float.parseFloat(positionX.getText());
            float _posY = Float.parseFloat(positionY.getText());
            float _radius = Float.parseFloat(radius.getText());

            shapes.add(new Circle(_posX, _posY, _radius));
        }

        updateText();
    }

    private void showWarning(String notice) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("");
        alert.setContentText(notice);
        alert.showAndWait();
    }


    private void updateText() {
        String newText = "";

        if (shapes.size() > 1) {
            for (int i = 0; i < shapes.size(); i++) {
                newText += "Shape_" + i + " is colliding with: ";
                for (int j = 0; j < shapes.size(); j++) {
                    if (i == j)
                        continue;

                    if (shapes.get(i).isColliding(shapes.get(j))) {
                        newText += "Shape_" + j + ", ";
                    }
                }
                newText = newText.substring(0, newText.length() - 2);
                newText += ".\n";
            }
        }

        text.setText(newText);
    }
}
