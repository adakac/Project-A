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
 *             |             |             |                                   *
 *             |             |             |                                   *
 *             |             |             |                                   *
 *             |             |             |                                   *
 *             |             |             |                                   *
 ******************************************************************************/

package project_a;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Project_A extends Application 
{
/******************************************************************************/
//                              Variables                                     //    
/******************************************************************************/
    // Debug Variables, DEFAULT = FALSE
    static boolean debug_skipIntro = false;
    
    // Variables
    double displayWidth, displayHeight;
    String lastLocation;
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
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Button btPlay = new Button("Play");
        btPlay.setMinWidth(80);
        grid.add(btPlay, 0, 1);

        Button btLoad = new Button("Load");
        btLoad.setMinWidth(80);
        grid.add(btLoad, 0, 2);

        Button btSettings = new Button("Settings");
        btSettings.setMinWidth(80);
        grid.add(btSettings, 0, 3);
        
        Button btCredits = new Button("Credits");
        btCredits.setMinWidth(80);
        grid.add(btCredits, 0, 4);
        
        Button btExit = new Button("Exit Game");
        btExit.setMinWidth(80);
        grid.add(btExit, 0, 5);
        
        // Prepare scene
        StackPane root = new StackPane();
        Scene sceneMenu = new Scene(root, displayWidth, displayHeight);  
        
        root.setStyle("-fx-background: #000000;");
        root.getChildren().add(grid);
        
        primaryStage.setScene(sceneMenu);
        primaryStage.show();
    }
    
    public static void main(String[] args) 
    {
        launch(args);
    }
}


