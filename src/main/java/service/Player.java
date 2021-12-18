/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import view.Menu;

/**
 *
 * @author mayk
 */
public class Player {
    private  Clip musica;
    private  AudioInputStream ais;
    public Player() {
        
    }
    public  Clip setPlayer(InputStream is){
        try {
            ais = AudioSystem.getAudioInputStream(is);
            
            musica = AudioSystem.getClip();
            musica.open(ais);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return musica;
    }
    public  void play(){
        musica.start();

    }
     public  void stop(){
         musica.stop();

    }
    
}
