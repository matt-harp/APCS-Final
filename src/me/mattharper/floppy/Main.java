package me.mattharper.floppy;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("a");

            frame.getContentPane().add(new GameView());
            frame.setResizable(true);
            frame.setVisible(true);
            frame.setSize(1600, 900);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
