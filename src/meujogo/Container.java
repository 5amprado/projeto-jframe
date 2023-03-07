package meujogo;

import meujogo.model.Fase;

import javax.swing.*;

public class Container extends JFrame {
    public Container(){
        add(new Fase());
        setTitle("O Jogo");
        setSize(1024,728);
        //Para a aplicação para quando fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Para a apicação aparecer no meio da tela
        setLocationRelativeTo(null);
        //Para nao poder minimizar ou maximizar a tela
        this.setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}
