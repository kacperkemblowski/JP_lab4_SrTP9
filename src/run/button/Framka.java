package run.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Framka extends JFrame{
    private int x,y;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Framka frame = new Framka();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        });
    }

    public Framka() throws HeadlessException {
        this("Running button and drawing shapes");
    }

    public Framka(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);

        // stworzenie panelu
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,800,800);
        panel.setFocusable(true); // nadanie focusu
        panel.requestFocus();     // dla panelu aby dzialal keylistener


        //Przycisk run
        JButton runButton = new JButton("Run");
        runButton.setBounds(1,712,200,50);
        //Przycisk cancel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(585,712,200,50);

        Random random = new Random();
        //Obsługa przycisku run
        runButton.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if (e.getX() < 180) { // wjechanie na przycisk 20 pixeli z prawej strony nie przesuwa go
                    runButton.setLocation(random.nextInt(585) + 1, random.nextInt(712) + 1); // przesunięcie przycisku na losowe miejsce w obrębie panelu
                }
            }
        });

        //Nasłuchiwanie kliknięcia przycisku cancel, który powraca przycis run do miejsca pierwotnego
        cancelButton.addActionListener(e-> runButton.setLocation(1,712));

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                x = e.getX();
                y = e.getY();
                }
        });

        //Obsługi naciskania przycisków do rysowania kwadratu i elipsy
        Kanwa kanwa = new Kanwa();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_K){
                    System.out.println("Rysuje kwadrat na koordynatach x = " + x + ", y = " + y);
                }
                if (key == KeyEvent.VK_E){
                    System.out.println("Rysuje elipsę na koordynatach x = " + x + ", y = " + y);
                }
            }
        });

        //Dodanie wszystkich komponentów do paneli
        add(panel);
        add(kanwa);
        panel.add(runButton);
        panel.add(cancelButton);
        panel.add(kanwa);
    }

}
