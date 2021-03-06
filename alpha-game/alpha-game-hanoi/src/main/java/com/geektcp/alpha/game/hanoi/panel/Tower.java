package com.geektcp.alpha.game.hanoi.panel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Tower extends JPanel {

    private static final long serialVersionUID = 1L;

    private int amountOfDisc = 3;
    private int maxDiscWidth, minDiscWidth, discHeight;
    private char[] towerName;
    private TowerPoint[] pointA, pointB, pointC;

    private HandleMouse handleMouse;
    private AutoPanel autoPanel;

    public Tower(char[] towerName) {
        handleMouse = new HandleMouse(this);
        this.towerName = towerName;
        setLayout(null);
        setBackground(new Color(200, 226, 226));
    }

    public void putDiscOnTower() {
        removeDisc();
        int n = (maxDiscWidth - minDiscWidth) / amountOfDisc;
        Disc[] disc = new Disc[amountOfDisc];
        for (int i = 0; i < disc.length; i++) {
            disc[i] = new Disc();
            disc[i].setNumber(i);
            int discWidth = minDiscWidth + i * n;
            disc[i].setSize(discWidth, discHeight);
            disc[i].addMouseListener(handleMouse);
            disc[i].addMouseMotionListener(handleMouse);
        }
        pointA = new TowerPoint[amountOfDisc];
        pointB = new TowerPoint[amountOfDisc];
        pointC = new TowerPoint[amountOfDisc];

        int verticalDistance = discHeight;
        for (int i = 0; i < pointA.length; i++) {
            pointA[i] = new TowerPoint(maxDiscWidth, verticalDistance + 100);
            verticalDistance = verticalDistance + discHeight;
        }
        verticalDistance = discHeight;
        for (int i = 0; i < pointB.length; i++) {
            pointB[i] = new TowerPoint(2 * maxDiscWidth, verticalDistance + 100);
            verticalDistance = verticalDistance + discHeight;
        }
        verticalDistance = discHeight;
        for (int i = 0; i < pointC.length; i++) {
            pointC[i] = new TowerPoint(3 * maxDiscWidth, verticalDistance + 100);
            verticalDistance = verticalDistance + discHeight;
        }

        for (int i = 0; i < pointA.length; i++) {
            pointA[i].putDisc(disc[i], this);
        }

        handleMouse.setPointA(pointA);
        handleMouse.setPointB(pointB);
        handleMouse.setPointC(pointC);

        initAutoPanel();
        validate();
        repaint();
    }

    public void removeDisc() {
        if (pointA != null) {
            for (int i = 0; i < pointA.length; i++) {
                pointA[i].removeDisc(pointA[i].getDiscOnPoint(), this);
                pointB[i].removeDisc(pointB[i].getDiscOnPoint(), this);
                pointC[i].removeDisc(pointC[i].getDiscOnPoint(), this);
            }
        }
    }

    public void removeAllDisc() {
        if (pointA != null) {
            for (int i = 0; i < pointA.length; i++) {
                pointA[i].removeAllDisc(this);
                pointB[i].removeAllDisc(this);
                pointC[i].removeAllDisc(this);
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x1, y1, x2, y2;
        x1 = pointA[0].getX();
        y1 = pointA[0].getY() - discHeight / 2;
        x2 = pointA[amountOfDisc - 1].getX();
        y2 = pointA[amountOfDisc - 1].getY() + discHeight / 2;
        g.drawLine(x1, y1, x2, y2);

        x1 = pointB[0].getX();
        y1 = pointB[0].getY() - discHeight / 2;
        x2 = pointB[amountOfDisc - 1].getX();
        y2 = pointB[amountOfDisc - 1].getY() + discHeight / 2;
        g.drawLine(x1, y1, x2, y2);

        x1 = pointC[0].getX();
        y1 = pointC[0].getY() - discHeight / 2;
        x2 = pointC[amountOfDisc - 1].getX();
        y2 = pointC[amountOfDisc - 1].getY() + discHeight / 2;
        g.drawLine(x1, y1, x2, y2);
        g.setColor(Color.blue);

        x1 = pointA[amountOfDisc - 1].getX() - maxDiscWidth / 2;
        y1 = pointA[amountOfDisc - 1].getY() + discHeight / 2;
        x2 = pointC[amountOfDisc - 1].getX() + maxDiscWidth / 2;
        y2 = pointC[amountOfDisc - 1].getY() + discHeight / 2;

        int length = x2 - x1, height = 6;
        g.fillRect(x1, y1, length, height);
        int size = 5;
        for (int i = 0; i < pointA.length; i++) {
            g.fillOval(pointA[i].getX() - size / 2, pointA[i].getY() - size / 2, size, size);
            g.fillOval(pointB[i].getX() - size / 2, pointB[i].getY() - size / 2, size, size);
            g.fillOval(pointC[i].getX() - size / 2, pointC[i].getY() - size / 2, size, size);
        }
        g.drawString(towerName[0] + "???", pointA[amountOfDisc - 1].getX(), pointA[amountOfDisc - 1].getY() + 50);
        g.drawString(towerName[1] + "???", pointB[amountOfDisc - 1].getX(), pointB[amountOfDisc - 1].getY() + 50);
        g.drawString(towerName[2] + "???", pointC[amountOfDisc - 1].getX(), pointC[amountOfDisc - 1].getY() + 50);
    }

    public void setAmountOfDisc(int number) {
        if (number <= 1)
            amountOfDisc = 1;
        else
            amountOfDisc = number;
    }

    public void setMaxDiscWidth(int maxDiscWidth) {
        this.maxDiscWidth = maxDiscWidth;
    }

    public void setMinDiscWidth(int minDiscWidth) {
        this.minDiscWidth = minDiscWidth;
    }

    public void setDiscHeight(int discHeight) {
        this.discHeight = discHeight;
    }

    public void setAutoMoveDisc(AutoPanel autoPanel) {
        this.autoPanel = autoPanel;
    }

    public AutoPanel getAutoPanel() {
        return autoPanel;
    }

    public void initAutoPanel(){
        autoPanel = AutoPanel.getInstance(this);
        autoPanel.setTowerName(towerName);
        autoPanel.setAmountOfDisc(amountOfDisc);
        autoPanel.setPointA(pointA);
        autoPanel.setPointB(pointB);
        autoPanel.setPointC(pointC);
    }

}