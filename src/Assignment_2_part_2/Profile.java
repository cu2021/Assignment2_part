package Assignment_2_part_2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Profile extends Stage {

    Label nameLabel, gpaLabel, ageLabel, nameInfoLabel, gpaInfoLabel, ageInfoLabel;
    Button closeButton;

    public Profile() {
        nameLabel = new Label("Full Name: ");
        gpaLabel = new Label("GPA: ");
        ageLabel = new Label("Age: ");

        nameInfoLabel = new Label("Uthman Mohammed Uthman Shbeir.");
        gpaInfoLabel = new Label("98.4.");
        ageInfoLabel = new Label("19-year-old.");

        VBox vBox1 = new VBox(20, nameLabel, ageLabel, gpaLabel);
        vBox1.setAlignment(Pos.CENTER_RIGHT);

        VBox vBox2 = new VBox(20, nameInfoLabel, ageInfoLabel, gpaInfoLabel);

        HBox hBox3 = new HBox(20, vBox1, vBox2);
        hBox3.setAlignment(Pos.CENTER);

        closeButton = new Button("Close");
        closeButton.setPadding(new Insets(10));

        VBox container = new VBox(30, hBox3, closeButton);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(10, 20, 10, 20));

        FlowPane flowPane = new FlowPane(container);
        flowPane.setAlignment(Pos.CENTER);

        Scene s = new Scene(flowPane, 530, 500);
        s.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        this.setScene(s);
        this.setTitle("Profile");
        this.show();

        closeButton.setOnAction(e -> {
            this.close();
        });
    }

}
