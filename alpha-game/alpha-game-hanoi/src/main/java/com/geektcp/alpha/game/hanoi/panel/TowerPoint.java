package com.geektcp.alpha.game.hanoi.panel;

import java.awt.Component;
import java.awt.Container;

public class TowerPoint {

    private int x, y;
    private boolean existDisc;
    private Disc disc = null;

    public boolean equals(TowerPoint p) {
        return p.getX() == this.x && p.getY() == this.getY();
    }

    public void putDisc(Component com, Container con) {
        disc = (Disc) com;
        con.setLayout(null);
        con.add(disc);
        int w = disc.getBounds().width;
        int h = disc.getBounds().height;
        disc.setBounds(x - w / 2, y - h / 2, w, h);
        existDisc = true;
        disc.setPoint(this);
        con.validate();
    }

    public Disc getDiscOnPoint() {
        return disc;
    }

    public void removeDisc(Component com, Container con) {
        if (com != null) {
            con.remove(com);
        }
        con.validate();
    }

    public void removeAllDisc(Container con){
        con.removeAll();
        con.validate();
    }

    public TowerPoint(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isExistDisc() {
        return existDisc;
    }

    public boolean isNotExistDisc() {
        return !existDisc;
    }

    public void setExistDisc(boolean existDisc) {
        this.existDisc = existDisc;
    }

}
