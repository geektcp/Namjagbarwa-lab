package com.geektcp.alpha.game.hanoi.panel;

import java.awt.Color;
import javax.swing.JButton;

public class Disc extends JButton {
    private static final long serialVersionUID = 1L;

    private int number;
    private TowerPoint point;

    public Disc() {
        setBackground(Color.cyan);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TowerPoint getPoint() {
        return point;
    }

    public void setPoint(TowerPoint point) {
        this.point = point;
    }

}
