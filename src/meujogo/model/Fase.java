package meujogo.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Player player;
    private Timer timer;
    private List<Enemy> enemy;
    private boolean emJogo;

    public Fase(){
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon("res\\rua.png");
        fundo = referencia.getImage();

        player = new Player();
        player.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);
        timer.start();

        inicializarInimigos();
        emJogo = true;
    }

    public void inicializarInimigos(){
        int cooordenadas[] = new int[40];
        enemy = new ArrayList<>();

        for (int i = 0 ; i < cooordenadas.length; i++){
            int x = (int)(Math.random() * 8000 + 1024);
            int y = (int)(Math.random() * 650 + 30);
            enemy.add(new Enemy(x,y));
        }
    }


    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        if(emJogo == true){

            graficos.drawImage(fundo, 0, 0, null);
            graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

            List<Tiro> tiros = player.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                Tiro m = tiros.get(i);
                m.load();
                graficos.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
            for (int o = 0; o < enemy.size(); o++) {
                Enemy in = enemy.get(o);
                in.load();
                graficos.drawImage(in.getImage(), in.getX(), in.getY(), this);
            }
        } else{
            ImageIcon fimJogo = new ImageIcon("res\\endgame.png");
            graficos.drawImage(fimJogo.getImage(),0,0,null);

        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        List<Tiro> tiros = player.getTiros();
        for(int i = 0; i < tiros.size(); i++){
            Tiro m = tiros.get(i);
            if(m.isVisivel()){
                m.update();
            } else {
                tiros.remove(i);
            }
        }
        for(int o = 0; o < enemy.size() ; o++){
            Enemy in = enemy.get(o);
            if(in.isVisivel()){
                in.update();
            }else{
                enemy.remove(o);
            }
        }
        checarColisoes();
        repaint();
    }
    public void checarColisoes(){
        Rectangle formaNave = player.getBounds();
        Rectangle formaEnemy;
        Rectangle formaTiro;

        for(int i = 0; i < enemy.size(); i++){
            Enemy tempEnemy = enemy.get(i);
            formaEnemy = tempEnemy.getBounds();
            if(formaNave.intersects(formaEnemy)){
                player.setVisivel(false);
                tempEnemy.setVisivel(false);
                emJogo = false;
            }
        }
        List<Tiro> tiros = player.getTiros();
        for(int j = 0; j<tiros.size(); j++){
            Tiro tempTiro = tiros.get(j);
            formaTiro = tempTiro.getBounds();
            for(int o = 0; o < enemy.size(); o++){
                Enemy tempEnemy = enemy.get(o);
                formaEnemy = tempEnemy.getBounds();
                if(formaTiro.intersects(formaEnemy)){
                    tempEnemy.setVisivel(false);
                    tempTiro.setVisivel(false);
                }
            }
        }
    }

    private class TecladoAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyRelease(e);
        }
    }
}
