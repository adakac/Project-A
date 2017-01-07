// <editor-fold defaultstate="collapsed" desc="Copyright Disclaimer">
//      
//        Copyright (C)
//        This program is free software: you can redistribute it and/or modify
//        it under the terms of the GNU General Public License as published by
//        the Free Software Foundation, either version 3 of the License, or
//        (at your option) any later version.
//        This program is distributed in the hope that it will be useful,
//        but WITHOUT ANY WARRANTY; without even the implied warranty of
//        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//        GNU General Public License for more details.
//        You should have received a copy of the GNU General Public License
//        along with this program.  If not, see http://www.gnu.org/licenses/
// </editor-fold>
/*******************************************************************************
 *                                                                             *
 * File Name:   Project_A.java                                                 *
 *                                                                             *
 * Description: This is the main funktion of the project. It contains various  *
 *              Functions, the intro etc.                                      *
 *                                                                             *
 *******************************************************************************
 * Change Type | Contributer | Change Date | Description                       *
 *-------------+-------------+-------------+-----------------------------------*
 * Milestone   | adakac      | 01.10.2016  | Added Intro Sequence              *
 * Milestone   | adakac      | 02.10.2016  | Added Main Menu Buttons w/o Func. *
 * Milestone   | adakac      | 02.10.2016  | Added function to Quit button     *
 * Milestone   | adakac      | 20.10.2016  | Added Settings GUI w/o Audio Func.*
 * Milestone   | heralc15    | 05.01.2017  | Adjusted button size              *
 * Milestone   | adakac      | 07.01.2017  | Fixed the exit alert              *
 * Milestone   | adakac      | 07.01.2017  | Added German lang, settings work  *
 *             |             |             |                                   *
 *             |             |             |                                   *
 *             |             |             |                                   *
 *             |             |             |                                   *
 ******************************************************************************/

package project_a;

import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Project_A extends Application 
{
/******************************************************************************/
//                              Variables                                     //    
/******************************************************************************/
    // Debug Variables, DEFAULT = FALSE
    static boolean debug_skipIntro = true;
    
    // Variables
    double  displayWidth,           //width in px
            displayHeight;          //hight in px
    int     audioVolume=50,         //Volume in percent
            language=1;             //1=english 2=german
    String  lastLocation;           //
    
    
    // Root Stages
    StackPane rootMenu;
/******************************************************************************/
    
    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setFullScreen(true);
        
        // Remove 'Exit Fullscreen' function
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        
        if(debug_skipIntro == true)
            startMenu(primaryStage);
        else
            playIntro(primaryStage);
    }

    public void playIntro(Stage primaryStage)
    {
        lastLocation = "playIntro";
        
        // Get intro file
        File f=new File("src/project_a/intro.mp4");
        Media m=new Media(f.toURI().toString());
        MediaPlayer mp=new MediaPlayer(m);
        MediaView mv=new MediaView(mp);
        
        // Prepare scene
        StackPane root = new StackPane();
        Scene sceneIntro = new Scene(root, displayWidth, displayHeight);   
        
        // Add player and play
        root.setBackground(Background.EMPTY);
        root.getChildren().add(mv);
        mp.play();
        
        // Set scene and show
        primaryStage.setScene(sceneIntro);
        primaryStage.show();
        
        mp.setOnEndOfMedia(() -> {
            primaryStage.close();
            startMenu(primaryStage);
        });
    }
    
    public void startMenu(Stage primaryStage)
    {
        lastLocation = "startMenu";
        
        primaryStage.setFullScreen(true);
        
        // Remove 'Exit Fullscreen' function
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Button btPlay = new Button();
        Button btLoad = new Button();
        Button btSettings = new Button();
        Button btCredits = new Button();
        Button btExit = new Button();
                
        switch(language)
        {
            case 1:
            {
                btPlay.setText("Play");
                btPlay.setMinWidth(100);
                buttonGrid.add(btPlay, 0, 1);

                btLoad.setText("Load");
                btLoad.setMinWidth(100);
                buttonGrid.add(btLoad, 0, 2);

                btSettings.setText("Settings");
                btSettings.setMinWidth(100);
                buttonGrid.add(btSettings, 0, 3);
                
                btCredits.setText("Credits");
                btCredits.setMinWidth(100);
                buttonGrid.add(btCredits, 0, 4);

                btExit.setText("Exit Game");
                btExit.setMinWidth(100);
                buttonGrid.add(btExit, 0, 5);
                
                break;
            }
            case 2:
            {
                btPlay = new Button("Spielen");
                btPlay.setMinWidth(100);
                buttonGrid.add(btPlay, 0, 1);

                btLoad = new Button("Laden");
                btLoad.setMinWidth(100);
                buttonGrid.add(btLoad, 0, 2);

                btSettings = new Button("Einstellungen");
                btSettings.setMinWidth(100);
                buttonGrid.add(btSettings, 0, 3);
                
                btCredits.setText("Mitwirkende");
                btCredits.setMinWidth(100);
                buttonGrid.add(btCredits, 0, 4);

                btExit.setText("Spiel beenden");
                btExit.setMinWidth(100);
                buttonGrid.add(btExit, 0, 5);
                
                break;
            }
            default:
            {
                //TODO Exception handling
                System.exit(1);
            }
        }
        
        btSettings.setOnAction((ActionEvent event) -> {
            lastLocation = "onSettingsKlicked";
            
            primaryStage.close(); 
            primaryStage.setFullScreen(true);
            
            // Remove 'Exit Fullscreen' function
            primaryStage.setFullScreenExitHint("");
            primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            
            // Prepare scene
            rootMenu = new StackPane();
            Scene sceneMenu = new Scene(rootMenu, displayWidth, displayHeight);
            
            rootMenu.setStyle("-fx-background: #000000;");
            
            primaryStage.setScene(sceneMenu);
            primaryStage.show();
            
            GridPane settingsGrid = new GridPane();
            settingsGrid.setAlignment(Pos.CENTER);
            settingsGrid.setHgap(10);
            settingsGrid.setVgap(10);
            settingsGrid.setPadding(new Insets(25, 25, 25, 25));
            
            Label lbVolumeValue = new Label();
            lbVolumeValue.setMinWidth(50);
            
            Label lbLanguageText = new Label();
            lbLanguageText.setAlignment(Pos.CENTER_RIGHT);
            lbLanguageText.setMinWidth(100);
            
            Label lbVolumeText = new Label();
            lbVolumeText.setAlignment(Pos.CENTER_RIGHT);
            lbVolumeText.setMinWidth(100);
            
            Button btLeaveSettings = new Button();
            btLeaveSettings.setMinWidth(150);
            btLeaveSettings.setOnAction((ActionEvent evt) -> {
                primaryStage.close();
                startMenu(primaryStage);
            });
            
            Slider volumeSlider = new Slider();
            volumeSlider.setMin(0);
            volumeSlider.setMax(100);
            volumeSlider.setValue(audioVolume);
            lbVolumeValue.setText(((int)volumeSlider.getValue())+"");
            volumeSlider.setShowTickLabels(true);
            volumeSlider.setShowTickMarks(true);
            volumeSlider.setMajorTickUnit(50);
            volumeSlider.setMinorTickCount(5);
            volumeSlider.setBlockIncrement(10);
            volumeSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                audioVolume = (int) volumeSlider.getValue();
                lbVolumeValue.setText(audioVolume+"");
            });
            
            ChoiceBox languageBox = new ChoiceBox(FXCollections.observableArrayList("English", "German"));
            languageBox.setMinWidth(150);
            if(language == 1)
            {
                lbVolumeText.setText("Volume");
                languageBox.setValue("English");
                lbLanguageText.setText("Language");
                btLeaveSettings.setText("Back to the menu");
            }
            
            if(language == 2)
            {
                languageBox.setValue("German");
                lbVolumeText.setText("Lautstärke");
                lbLanguageText.setText("Sprache");
                btLeaveSettings.setText("Zurück zum Menü");
            }
            
            languageBox.valueProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                    switch((String)languageBox.getValue())
                    {
                        case "English": language=1;
                        lbVolumeText.setText("Volume");
                        lbLanguageText.setText("Language");
                        btLeaveSettings.setText("Back to the menu");
                        break;
                        
                        case "German":  language=2;
                        lbVolumeText.setText("Lautstärke");
                        lbLanguageText.setText("Sprache");
                        btLeaveSettings.setText("Zurück zum Menü");
                        break;
                    }
                    rootMenu.requestLayout();
                }
                
            });
            
            settingsGrid.add(lbVolumeText, 0, 0);
            settingsGrid.add(volumeSlider, 1, 0);
            settingsGrid.add(lbVolumeValue, 2, 0);
            settingsGrid.add(lbLanguageText, 0, 1);
            settingsGrid.add(languageBox, 1, 1);
            settingsGrid.add(btLeaveSettings,1,2);
            rootMenu.getChildren().add(settingsGrid);
            rootMenu.setVisible(true);
        });
                
        
        btExit.setOnAction((ActionEvent event) -> 
        { 
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.getDialogPane().setStyle("-fx-border-color: black;");
            alert.initModality(Modality.APPLICATION_MODAL);

            alert.initStyle(StageStyle.UNDECORATED);

            alert.initOwner(primaryStage);
            switch(language)
            {
                case 1:
                {
                    alert.setHeaderText("Quit Game");
                    alert.setContentText("Are you sure?");
                    break;
                }
                case 2:
                {
                    alert.setHeaderText("Spiel beenden");
                    alert.setContentText("Bist du dir sicher?");
                    break;
                }
                default:
                {
                    //TODO Exception handling
                    System.exit(1);
                }
            }

            Optional<ButtonType> result = alert.showAndWait();
            /* 
             *  To retrieve the value without an error, even if the Optional is 
             *  empty (not 100% sure this can happen in this case, but it does 
             *  not hurt to do it like this).
             */
            if (result.orElse(null) == ButtonType.OK) {
                /* 
                 * Platform.exit shuts down the application more gracefully. 
                 * After all a status code of 1 indicates abnormal termination.
                 */
                Platform.exit();
            }
        });
        
        // Prepare scene
        rootMenu = new StackPane();
        Scene sceneMenu = new Scene(rootMenu, displayWidth, displayHeight);  
        
        rootMenu.setStyle("-fx-background: #000000;");
        rootMenu.getChildren().add(buttonGrid);
        
        primaryStage.setScene(sceneMenu);
        primaryStage.show();
    }
    
    public static void main(String[] args) 
    {
        launch(args);
    }
}


