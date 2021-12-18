package service;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author mayk
 */
public class Block {
    
    private int x;
    private int y;
    private int width;
    private int height;
    private int life;
    private Color color;
    private Bloque blok;

    public Block(int x, int y, int width, int height, int life, Color color, Bloque blok) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.life = life;
        this.color = color;
        this.blok = blok;
    }
    
    public Block() {
        
    }
    //getter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLife() {
        return life;
    }

    public Color getColor() {
        return color;
    }

    public Bloque getBlok() {
        return blok;
    }
    
    //setter

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBlok(Bloque blok) {
        this.blok = blok;
    }
    //tostring

    @Override
    public String toString() {
        return "Nodo{" + "x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", life=" + life + ", color=" + color + '}';
    }
}
