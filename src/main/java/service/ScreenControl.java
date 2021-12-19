package service;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author mayk
 */
public class ScreenControl {
    
    private Toolkit toolkit;
    private Dimension screen;
    
    public ScreenControl() {
        this.toolkit = Toolkit.getDefaultToolkit();//obtiene el default toolkit
        this.screen = this.toolkit.getScreenSize();//obtiene la pantalla del equipo utilizado
    }
    /**
     * retorna un int de las dos posibles posiciones con los tamanos de la pantalla
     * que se le halla especificado por parametro 'w' para anchura y 'h' para la altura.
     * @param axis
     * @return un int con la altura o anchura de la pantalla segun se especifique o 0 si no se entiende lo solicitado
     **/
    public int getSize(char axis){
        if(axis=='w')return screen.width;
        else if(axis=='h')return screen.height;
        else return 0;
    } 
    
}
