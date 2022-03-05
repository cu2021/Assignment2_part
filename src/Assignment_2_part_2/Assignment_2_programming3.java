package Assignment_2_part_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Assignment_2_programming3 extends Application {

    Stage stage;
    private Label labelTitle, labelError;
    private TextField textFieldLoginName;
    private PasswordField passwordField;
    private Button buttonSubmit, buttonCancel, clearButton;

    @Override
    public void start(Stage primaryStage) throws Exception {

        labelTitle = new Label("Login Information");

        textFieldLoginName = new TextField();
        textFieldLoginName.setPromptText("Login Name");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        labelError = new Label("Enter your username and password.");
        labelError.setId("label-error");

        VBox vBox1 = new VBox(10, labelTitle, textFieldLoginName, passwordField, labelError);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setPadding(new Insets(10));

        buttonSubmit = new Button("Submit");
        buttonCancel = new Button("Cancel");
        clearButton = new Button("Clear");

        MyEventHandler eventHandler = new MyEventHandler();
        buttonSubmit.setOnAction(eventHandler);
        buttonCancel.setOnAction(eventHandler);
        clearButton.setOnAction(eventHandler);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(buttonSubmit, clearButton, buttonCancel);
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPadding(new Insets(10));

        VBox vBox2 = new VBox(10, vBox1, hBox1);
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setStyle("-fx-border-color: blue;");
        vBox2.setPadding(new Insets(10, 20, 10, 20));

        FlowPane flowPane = new FlowPane(vBox2);
        flowPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(flowPane, 500, 350);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Login Screen");
        stage = primaryStage;
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class MyEventHandler implements EventHandler<ActionEvent> {

        FileConnection connection = FileConnection.getUserConnection();

        @Override

        public void handle(ActionEvent event) {
            String button = ((Button) event.getSource()).getText();
            switch (button) {
                case "Submit":
                    if (connection.verifyUser(textFieldLoginName.getText(), passwordField.getText())) {
                        labelError.setText("Valid User!");
                        labelError.setStyle("-fx-text-fill: green;");
                        stage.close();
                        new FileControllers().show();

                    } else {
                        labelError.setText("Your username or password is wrong,\n\t\t\ttry Again!");
                        labelError.setStyle("-fx-text-fill: red;");
                    }

                    break;

                case "Clear":
                    textFieldLoginName.setText("");
                    passwordField.setText("");
                    break;
                default:
                    System.exit(0);

            }

        }
    }

}
