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
 *             |             |             |                                   *
 *             |             |             |                                   *
 *             |             |             |                                   *
 *             |             |             |                                   *
 *             |             |             |                                   *
 *             |             |             |                                   *
 ******************************************************************************/

package project_a;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
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
    double displayWidth, displayHeight;
            
/******************************************************************************/
    
    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setFullScreen(true);
        
        // Remove 'Exit Fullscreen' function
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        
        playIntro(primaryStage);
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public void playIntro(Stage primaryStage)
    {
        // Get intro file
        File f=new File("src/project_a/intro.mp4");
        Media m=new Media(f.toURI().toString());
        MediaPlayer mp=new MediaPlayer(m);
        MediaView mv=new MediaView(mp);
        
        // Prepare scene
        StackPane root = new StackPane();
        Scene scene = new Scene(root, displayWidth, displayHeight);   
        
        // Add player and play
        root.setBackground(Background.EMPTY);
        root.getChildren().add(mv);
        mp.play();
        
        // Set scene and show
        primaryStage.setScene(scene);
        primaryStage.show();
        
        mp.setOnEndOfMedia(new Runnable() 
        {
            @Override
            public void run() {
                primaryStage.close();
            }
        });
    }
    
    
}


