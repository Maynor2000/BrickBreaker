
package service;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author mayk
 */
public class MoveToWindows implements KeyListener{
    private JFrame frame;
    private JFrame frame2;
    private Point location;
    private int barX;
    private ArrayList<Block>blocks;
    private final ScreenControl screen1;
    private Player player;
    private Player player2;
    private InputStream musica;
    private InputStream musica2;
    
    public MoveToWindows() {
        screen1 = new ScreenControl();
    }
    public MoveToWindows(JFrame frame, JFrame frame2,ArrayList<Block> blocks) {
        this.frame = frame;
        this.frame2 = frame2;
        frame2.addKeyListener(this);
        screen1 = new ScreenControl();
        barX = frame2.getLocation().x;
        this.blocks = blocks;
        player = new Player();
        player2 = new Player();
        try {
            musica = getClass().getResource("/eq56zdb-wind-impact_2ccMwdCf.wav").openStream();
            player.setPlayer(musica);
            musica2 = getClass().getResource("/golpe.wav").openStream();
            
        } catch (IOException ex) {
            Logger.getLogger(MoveToWindows.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void starMove(){
        location = frame.getLocation();
        boolean limitX = false;
        boolean limitY = false;
        
        int x = location.x;
        int y = location.y;
        
        while(true){
            try {
                Thread.sleep(5);
                location = frame.getLocation();
            } catch (InterruptedException ex) {
                Logger.getLogger(MoveToWindows.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(y >= screen1.getSize('h')){
                limitY = true;
                if(frame.getLocation().x < frame2.getLocation().x || frame.getLocation().x > frame2.getLocation().x+frame2.getSize().width ){
                    JOptionPane.showMessageDialog(null, "game over", "has perdido", 2);
                    exit(0);
                }
            }
     
            if(y <= 0){ limitY = false;}
            
            if(x >= screen1.getSize('w')){limitX = true;}
            if(x <= 0){ limitX = false;}
            
            if(frame.getLocation().x > frame2.getLocation().x && frame.getLocation().x < frame2.getLocation().x+frame2.getSize().width 
                    && frame.getLocation().y == frame2.getLocation().y){
                limitY = true;
            }
            for(Block a:blocks){
                
                if(frame.getLocation().x > a.getX()&&frame.getLocation().x<a.getX()+a.getWidth()
                        && frame.getLocation().y == a.getY()){
                    a.setLife(a.getLife()-1);
                    a.getBlok().jLabel1.setText(String.valueOf(a.getLife()));
                    
                    limitY = false;
                    
                }
                if(a.getLife()==0){
                    a.getBlok().dispose();
                    
                    player.play();
                    
                }
            }
            blocks.removeIf((data)->{return data.getLife()==0;});
            
            if(limitX){frame.setLocation(x--, y);}
            else {frame.setLocation(x++, y);}
            
            if(limitY){frame.setLocation(x, y--);}
            else {frame.setLocation(x, y++);}
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()=='d'){
            if(barX<screen1.getSize('w'))frame2.setLocation(barX+=10, frame2.getLocation().y);
            if(barX+frame2.getSize().width>screen1.getSize('w')) barX = screen1.getSize('w')-frame2.getSize().width;frame2.setLocation(barX, frame2.getLocation().y);
        }
        if(e.getKeyChar()=='a'){
            if(barX>0)frame2.setLocation(barX-=10, frame2.getLocation().y);
        }
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }
    
}
