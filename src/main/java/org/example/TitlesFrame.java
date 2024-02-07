package org.example;

import javax.swing.*;
import java.awt.*;

public class TitlesFrame extends JFrame {
    public TitlesFrame() {
        this.initUI();
    }

    private void initUI() {
        this.setTitle("Криві фігури");
        Color fillColor = Color.blue;
        Color strokeColor = Color.red;
        int strokeWidth = 5;

        this.add(new TitlesPanel(38, fillColor, strokeColor, strokeWidth));
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            TitlesFrame ps = new TitlesFrame();
            ps.setVisible(true);
        });
    }
}
