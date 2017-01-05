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
 *             |             |             |                                   *
 *             |             |             |                                   *
 ******************************************************************************/

package project_a;

import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    int     audioVolume,            //Volume in percent
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
        
        mp.setOnEndOfMedia(new Runnable() 
        {
            @Override
            public void run() 
            {
                primaryStage.close();
                startMenu(primaryStage);
            }
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
        
        Button btPlay = new Button("Play");
        btPlay.setMinWidth(100);
        buttonGrid.add(btPlay, 0, 1);

        Button btLoad = new Button("Load");
        btLoad.setMinWidth(100);
        buttonGrid.add(btLoad, 0, 2);

        Button btSettings = new Button("Settings");
        btSettings.setMinWidth(100);
        buttonGrid.add(btSettings, 0, 3);
        btSettings.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
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
                
                Label lbVolumeText = new Label();
                lbVolumeText.setAlignment(Pos.CENTER_RIGHT);
                lbVolumeText.setMinWidth(100);
                
                Button btLeaveSettings = new Button("Back to the menu");
                btLeaveSettings.setMinWidth(150);
                btLeaveSettings.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        primaryStage.close();
                        startMenu(primaryStage);
                    }
                });
                
                Slider volumeSlider = new Slider();
                volumeSlider.setMin(0);
                volumeSlider.setMax(100);
                volumeSlider.setValue(50);
                lbVolumeValue.setText(((int)volumeSlider.getValue())+"");
                volumeSlider.setShowTickLabels(true);
                volumeSlider.setShowTickMarks(true);
                volumeSlider.setMajorTickUnit(50);
                volumeSlider.setMinorTickCount(5);
                volumeSlider.setBlockIncrement(10);
                volumeSlider.valueProperty().addListener(new ChangeListener<Number>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
                    {
                        audioVolume = (int) volumeSlider.getValue();
                        lbVolumeValue.setText(audioVolume+"");
                    }
                });
                
                ChoiceBox languageBox = new ChoiceBox(FXCollections.observableArrayList("English", "German"));
                languageBox.setMinWidth(150);
                if(language == 1)
                {
                    lbVolumeText.setText("Volume");
                    languageBox.setValue("English");
                }
                    
                if(language == 2)
                {
                    languageBox.setValue("German");
                    lbVolumeText.setText("Lautstärke");
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
                                            break;
                                            
                            case "German":  language=2;
                                            lbVolumeText.setText("Lautstärke");
                                            break;
                        }
                        rootMenu.requestLayout();
                    }

                });
                
                settingsGrid.add(lbVolumeText, 0, 0);
                settingsGrid.add(volumeSlider, 1, 0);
                settingsGrid.add(lbVolumeValue, 2, 0);
                settingsGrid.add(languageBox, 1, 1);
                settingsGrid.add(btLeaveSettings,1,2);
                rootMenu.getChildren().add(settingsGrid);
                rootMenu.setVisible(true);
            }
        });
                
        Button btCredits = new Button("Credits");
        btCredits.setMinWidth(100);
        buttonGrid.add(btCredits, 0, 4);
        
        Button btExit = new Button("Exit Game");
        btExit.setMinWidth(100);
        buttonGrid.add(btExit, 0, 5);
        btExit.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(primaryStage);
                alert.setHeaderText("Quit Game");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.exit(1);
                }else 
                {
                    alert.close();
                }
                
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


