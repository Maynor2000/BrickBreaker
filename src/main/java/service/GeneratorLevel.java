package service;

import Exceptions.IndexOutLineBlock;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * @author mayk
 */
public class GeneratorLevel {
    
   private ArrayList<Block> blocks;
   private ScreenControl screen1;
   private Ball ball;
   private Bar bar;
   
    public GeneratorLevel() {
        screen1 = new ScreenControl();
        blocks = new ArrayList();
        ball = new Ball();
        bar = new Bar();
    }
    /**
     * le genera un tamano a los bloques con respecto a los numeros de bloques que se 
     * quierean por cada linea, el primer parametro es la cantidad de block por linea
     * genera un Jframe con las caracteristicas dadas
     * el segundo parametro hace referencia a la cantidad de bloques totales tomar en cuanta para
     * cuantas lineas se queran crear.
     * @param numblocks
     * @param  cant
     **/
    public void createLevel(int numblocks,int cant){
        
        for(int i=0;i<cant;i++){//crea la cantidad de nodos deseados
            Block firstblock = setSizeBlock(numblocks,new Block());
        
            firstblock = setLifeBlock(firstblock);
            firstblock = setColorBlock(firstblock);
            firstblock = setLocationBlock(firstblock);
            firstblock = setNewBlock(firstblock);
            blocks.add(firstblock);//anade los nodos a la lista
        }
        
        ball.setVisible(true);
        //centra el balon
        ball.setLocation((screen1.getSize('w')/2)-(ball.getSize().width/2),(screen1.getSize('h'))-(ball.getSize().height)-(bar.getSize().height));
        
        bar.setVisible(true);
        //centra la barra
        bar.setLocation((screen1.getSize('w')/2)-(bar.getSize().width/2),(screen1.getSize('h'))-(bar.getSize().height));
        //llama a la clase para ejecutar los movimientos en otro hilo de ejecucion
        new ControlThread().start();
    }
    /**
     * inicia el movimiento de el balon
     **/
    public void play(){
        moveBall(ball,bar);
    }
    /**
     * crea una instancia MoveToWindows para controlar el movimiento de la barra y el balon
     **/
    private void moveBall(JFrame frame,JFrame frame2){
      MoveToWindows a =  new MoveToWindows(frame,frame2,blocks);
      a.starMove();
    }
    /**
     * le genera un tamano a los bloques con respecto a los numeros de bloques que se 
     * quierean por cada linea, el primer parametro es la cantidad de block por linea
     * @param numblocks
     * @param  block
     * @return  block con el tamano indicado
     **/
    private Block setSizeBlock(int numblocks,Block block){
        
        int Swidth = screen1.getSize('w');
        
        int Bwidth = Swidth/numblocks;
        int Bheight = Swidth/numblocks;
        
        block.setWidth(Bwidth);
        block.setHeight(Bheight);
        
        return block;
    }
    /**
     * genera una localizacion para cada bloque con respecto al anterior
     * @param block
     * @return block con la hubicacion en la pantalla
     **/
    private Block setLocationBlock(Block block){
        
        int Swidth = screen1.getSize('w');
        boolean y = false;
        
        if(blocks.isEmpty()){//si no hay un primer nodo genera uno con la posicion (0,0)
            block.setX(0);
            block.setY(0);
            
            return block;
        }else{//pregunta al ultimo nodo cual es su localizacion para generar una nueva
            Block blockLatest = blocks.get(blocks.size()-1);
            
            if((blockLatest.getX()+blockLatest.getWidth()*2)>=Swidth){
                blockLatest.setY(blockLatest.getY()+blockLatest.getHeight());
                blockLatest.setX(0);
            }else{
                blockLatest.setX(blockLatest.getX()+blockLatest.getWidth());
            }
            block.setX(blockLatest.getX());
            block.setY(blockLatest.getY());
            return block;
        }
    }
    /**
     * genera un cuatro colores y se los asigna a cada bloque dependiendo de el nivel de vida
     * para ello pimero se debe asignar un nivel de vida
     * @param  block
     **/
    private Block setColorBlock(Block block){

        if(block.getLife()<3)block.setColor(Color.RED);
        else if(block.getLife()>2 && block.getLife()<6)block.setColor(Color.ORANGE);
        else if(block.getLife()>5 && block.getLife()<9)block.setColor(Color.YELLOW);
        else if(block.getLife()>8 && block.getLife()<12)block.setColor(Color.BLUE);
        
        return block;
    }
    /**
     * genera un numero del 1 al 9 que es la vida que puede tener un bloque 
     * @param  block
     * @return block con la vida indicada
     **/
    private Block setLifeBlock(Block block){
        double life = Math.random()*10;
        
        if(life<1)life=1;
        
        block.setLife((int)life);
        
        return block;
    }
    /**
     * genera un frame de la clase bloque y se lo coloca al nodo Block
     *@param block
     * @return block con el frame 
     **/
    public Block setNewBlock(Block block){
        
        Bloque bloc = new Bloque(block);
        block.setBlok(bloc);
        
        return block;
    }
   //esta es una clase privada para controlar la llamada de los hilos 
    private class ControlThread extends Thread{
        
       public void run(){
           play();
       }
           
    }
}
