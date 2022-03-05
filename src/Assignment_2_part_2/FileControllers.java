package Assignment_2_part_2;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileControllers extends Stage {

    private Stage stage;
    private MenuBar menuBar;
    private Menu menuFile, menuColor, menuAbout;
    private MenuItem menuItemOpen, menuItemClear, menuItemSave, menuItemExit,
            menuItemGold, menuItemRed, menuItemCyan,
            menuItemAbout;
    private TextArea textArea;
    private Slider fontSizeBehavior;
    String path = null;

    public FileControllers() {
        menuBar = new MenuBar();
//        menues
        menuFile = new Menu("File");
        menuColor = new Menu("Color");
        menuAbout = new Menu("About");

//       File items.
        menuItemOpen = new MenuItem("open");
        menuItemSave = new MenuItem("Save");
        menuItemClear = new MenuItem("Clear");
        menuItemExit = new MenuItem("Exit");

//      Color Items.
        menuItemGold = new MenuItem("Gold");
        menuItemRed = new MenuItem("Red");
        menuItemCyan = new MenuItem("Cyan");

//      About Items.
        menuItemAbout = new MenuItem("About me...");

//      Adding the menues to the menu bar.
        menuBar.getMenus().addAll(menuFile, menuColor, menuAbout);

//      Adding the menue items.
        menuFile.getItems().addAll(menuItemOpen, menuItemSave, menuItemClear, menuItemExit);
        menuColor.getItems().addAll(menuItemGold, menuItemRed, menuItemCyan);
        menuAbout.getItems().addAll(menuItemAbout);

//        Text Area
        textArea = new TextArea("Playing with JavaFX Advanced Controls");
        textArea.setMaxSize(900, 300);
        textArea.setMinSize(900, 300);
        textArea.setWrapText(true);

//        Slider
        fontSizeBehavior = new Slider(5, 72, 36);
        fontSizeBehavior.setMajorTickUnit(5);
        fontSizeBehavior.setMinorTickCount(4);
        fontSizeBehavior.setShowTickLabels(true);
        fontSizeBehavior.setShowTickMarks(true);
        fontSizeBehavior.setSnapToTicks(true);

//        handling the event on slider
        fontSizeBehavior.valueProperty().addListener(e -> {
            textArea.setFont(Font.font(fontSizeBehavior.getValue()));
        });
//        Combo Box
        ComboBox< String> comboBoxLinkes = new ComboBox<>();
        comboBoxLinkes.getItems().addAll("Instructor Git Hub",
                "JavaFX Guide", "My Git Hub");
        comboBoxLinkes.getSelectionModel().selectFirst();
        //        Web View
        WebView webView = new WebView();
        webView.getEngine().load("https://github.com/javafx-tutorial");

//        handling the combo box items
        comboBoxLinkes.setOnAction(e -> {
            if (comboBoxLinkes.getSelectionModel().getSelectedItem().equalsIgnoreCase("Instructor Git Hub")) {
                webView.getEngine().load("https://github.com/aashgar");
            } else if (comboBoxLinkes.getSelectionModel().getSelectedItem().equalsIgnoreCase("JavaFX Guide")) {
                webView.getEngine().load("https://www.javatpoint.com/javafx-tutorial");
            } else {
                webView.getEngine().load("https://github.com/cu2021");
            }
        });

//        aligning items
        HBox hBox1 = new HBox(30, comboBoxLinkes, webView);
        hBox1.setAlignment(Pos.TOP_CENTER);
        VBox vBox1 = new VBox(15, textArea, fontSizeBehavior, hBox1);
        vBox1.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane(vBox1);
        borderPane.setTop(menuBar);
        borderPane.setCenter(vBox1);

//        Handling the menu
        menuItemGold.setOnAction(e -> {
            textArea.setStyle("-fx-background-color : gold;-fx-text-fill:gold;");
        });
        menuItemCyan.setOnAction(e -> {
            textArea.setStyle("-fx-background-color : cyan;-fx-text-fill:cyan;");
        });
        menuItemRed.setOnAction(e -> {
            textArea.setStyle("-fx-background-color : red; -fx-text-fill:red;");
        });
        MyEventHandler eventHandler = new MyEventHandler();
        menuItemOpen.setOnAction(eventHandler);
        menuItemSave.setOnAction(eventHandler);

        menuItemClear.setOnAction(e -> {
            textArea.setText("");
        });
        menuItemExit.setOnAction(e -> {
            System.exit(0);
        });

        menuItemAbout.setOnAction(eventHandler);

        //*********************************
        Scene scene = new Scene(borderPane, 1100, 700);
        scene.getStylesheets().add(getClass().getResource("styles_1.css").toExternalForm());
        this.setScene(scene);
        this.setTitle("Advanced JavaFX Controllers Screen");
        stage = this;
    }

    private class MyEventHandler implements EventHandler<ActionEvent> {

        @Override

        public void handle(ActionEvent event) {
            File selectedFile = null;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(
                    new File("src/Assignment_2_part_2"));
            if (event.getSource() == menuItemOpen) {
                selectedFile = fileChooser.showOpenDialog(stage);
                path = selectedFile.getAbsolutePath();
                textArea.setText(FileConnection.
                        getUserConnection().openFile(selectedFile));

            } else if (event.getSource() == menuItemSave) {
                if (path != null) {
                    try {
                        FileConnection.getUserConnection().
                                saveFile(path, textArea.getText());

                        System.out.println("Done");
                    } catch (IOException ex) {

                    }//end of catch}
                } else {
                    try {
                        selectedFile = fileChooser.showSaveDialog(stage);
                        File file = new File(selectedFile.getAbsolutePath());
                        file.createNewFile();
                        FileConnection.getUserConnection().
                                saveFile(selectedFile.getAbsolutePath(), textArea.getText());
                        System.out.println("Done");

                    } catch (IOException ex) {
                        Logger.getLogger(FileControllers.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }//end of else

            } else if (event.getSource() == menuItemAbout) {
//                stage.close();
                new Profile();
            }
//end of if-else

        }//end of big if-else
    }

}
