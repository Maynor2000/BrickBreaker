
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
    
    //constructor
    public MoveToWindows() {
        screen1 = new ScreenControl();
    }
    //constructor
    public MoveToWindows(JFrame frame, JFrame frame2,ArrayList<Block> blocks) {
        //inicializa los objetos necesarios
        this.frame = frame;
        this.frame2 = frame2;
        frame2.addKeyListener(this);
        screen1 = new ScreenControl();
        barX = frame2.getLocation().x;
        this.blocks = blocks;
        player = new Player();
        player2 = new Player();//no utilizado
        //crea las rutas a los sonidos con un flijo de datos
        try {
            musica = getClass().getResource("/eq56zdb-wind-impact_2ccMwdCf.wav").openStream();
            player.setPlayer(musica);
            musica2 = getClass().getResource("/golpe.wav").openStream();
            
        } catch (IOException ex) {
            Logger.getLogger(MoveToWindows.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * inicia el movimiento del balon y se queda a la escucha de eventos del teclado para mover la barra
     **/
    public void starMove(){
        location = frame.getLocation();
        boolean limitX = false;//true si el balon llego al limite de la pantalla
        boolean limitY = false;//true si el balon llego al limite de la pantalla
        
        int x = location.x;//localizacion del balon
        int y = location.y;
        
        while(true){//inicia los movimientos de forma indefinida
            try {
                Thread.sleep(5);//duerme al hilo para alentar el movimiento de el balon(si es demasiado rapido la pantalla crashea)
                location = frame.getLocation();//la posicion de el balon
                
            } catch (InterruptedException ex) {
                Logger.getLogger(MoveToWindows.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(y >= screen1.getSize('h')){//si el valon esta en el suelo limite
                limitY = true;//le dara paso al metodo de decremento de la y(recuerda que el decremento lo mueve hacia arriva por el uso de el cuarto cuadrante)
                // si el balon no callo en una posicion donde esta la barra manda un mensaje y termina el juego
                if(frame.getLocation().x < frame2.getLocation().x || frame.getLocation().x > frame2.getLocation().x+frame2.getSize().width ){
                    JOptionPane.showMessageDialog(null, "game over", "has perdido", 2);
                    exit(0);
                }
            }
     
            if(y <= 0){ limitY = false;}//si el balon esta en y 0 incrementara para simular los rebotes
            
            if(x >= screen1.getSize('w')){limitX = true;}//decrementa x si esta en el borde
            if(x <= 0){ limitX = false;}//lo mismo pero para el otro borde
            //valida que si la caida del balon es sobre la barra este rebote hacia arriba
            if(frame.getLocation().x > frame2.getLocation().x && frame.getLocation().x < frame2.getLocation().x+frame2.getSize().width 
                    && frame.getLocation().y == frame2.getLocation().y){
                limitY = true;
            }
            //busca si el balon colisiono en una posicion donde se encuentra un nodo esto con alluda de la
            //lista de nodos que guardamos
            for(Block a:blocks){
                //valida si hubo una colicion y A quien fue
                if(frame.getLocation().x > a.getX()&&frame.getLocation().x<a.getX()+a.getWidth()
                        && frame.getLocation().y == a.getY()){
                    a.setLife(a.getLife()-1);//resta uno de vida al bloque colisionado
                    a.getBlok().jLabel1.setText(String.valueOf(a.getLife()));//coloca la nueva vida al texto del frame
                    
                    limitY = false;//hace que rebote al contrario porque colisiono con un bloque
                    
                }
                if(a.getLife()==0){//si el blqoue quedo en cero se cierra el frame y se reproduce un sonido de ruptura
                    a.getBlok().dispose();
                    
                    player.play();
                    
                }
            }
            blocks.removeIf((data)->{return data.getLife()==0;});//remueve el nodo de la lsita que ya no tiene vida
            
            if(limitX){frame.setLocation(x--, y);}//decrementa en x
            else {frame.setLocation(x++, y);}//iincrementa
            
            if(limitY){frame.setLocation(x, y--);}//decrementa en y
            else {frame.setLocation(x, y++);}//incrementa
            
        }
    }

    @Override
    //controla si se preciono una tecla
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()=='d'){//si se preciona d se mueve ala derecha pero con limite de la pantalla
            if(barX<screen1.getSize('w'))frame2.setLocation(barX+=10, frame2.getLocation().y);
            if(barX+frame2.getSize().width>screen1.getSize('w')) barX = screen1.getSize('w')-frame2.getSize().width;frame2.setLocation(barX, frame2.getLocation().y);
        }
        if(e.getKeyChar()=='a'){//si se preciona a se mueve ala izquierda pero con limite de la pantalla
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
