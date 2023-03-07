package meujogo.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int x,y,dx,dy,altura,largura;
    private Image imagem;
    private List<Tiro> tiros;
    private boolean isVisivel;

    public Player(){
        this.x = 100;
        this.y = 100;
        isVisivel = true;

        tiros = new ArrayList<Tiro>();
    }

    public void load(){
        ImageIcon referencia = new ImageIcon("res\\New Piskel.png");
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update(){
        x += dx;
        y += dy;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }

    public void tiroSimples(){
        this.tiros.add(new Tiro(x+largura, y+(altura/2)));
    }

    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        if(codigo == KeyEvent.VK_A){
            tiroSimples();
        }
        if(codigo == KeyEvent.VK_UP){
            dy=-3;
        }
        if(codigo == KeyEvent.VK_DOWN){
            dy=3;
        }
        if(codigo == KeyEvent.VK_LEFT){
            dx=-3;
        }
        if(codigo == KeyEvent.VK_RIGHT){
            dx=3;
        }
    }
    public void keyRelease(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        if(codigo == KeyEvent.VK_UP){
            dy=0;
        }
        if(codigo == KeyEvent.VK_DOWN){
            dy=0;
        }
        if(codigo == KeyEvent.VK_LEFT){
            dx=0;
        }
        if(codigo == KeyEvent.VK_RIGHT){
            dx=0;
        }
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }
}
