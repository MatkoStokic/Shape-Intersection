package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Shape intersection");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(3, 2, 5, 7));
        shapes.add(new Rectangle(1, 1, 0.5f, 0.5f));
        shapes.add(new Circle(5, 1, 10));
        shapes.add(new Circle(11, 11, 1));

        for (int i = 0; i < shapes.size(); i++) {
            for (int j = 0; j < shapes.size(); j++){
                if (i == j)
                    continue;
                System.out.println(i + " : " + j + " " + shapes.get(i).isColliding(shapes.get(j)));
            }
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
