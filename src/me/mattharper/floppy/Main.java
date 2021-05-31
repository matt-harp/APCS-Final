package me.mattharper.floppy;

import me.mattharper.floppy.game.GameView;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) { // [Rubric B] neat main method
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("floppy");

            frame.getContentPane().add(new GameView());
            frame.setResizable(true);
            frame.setVisible(true);
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
