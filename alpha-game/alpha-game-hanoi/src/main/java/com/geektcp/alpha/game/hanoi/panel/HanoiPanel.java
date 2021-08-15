package com.geektcp.alpha.game.hanoi.panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.*;

public class HanoiPanel extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static Tower tower = null;
    private int towerSize = 3;
    private static char[] towerName = {'A', 'B', 'C'};
    private JButton autoButton = null;
    private JTextField towerNumText;

    public HanoiPanel() {
        initTower();

        JButton renew = new JButton("开始");
        renew.addActionListener(this);

        autoButton = new JButton("自动");
        autoButton.addActionListener(this);

        JPanel north = new JPanel();

        JLabel towerLabel = new JLabel("盘子的数目");
        towerNumText = new JTextField(8);
        north.add(towerLabel);
        north.add(towerNumText);
        north.add(renew);
        north.add(autoButton);
        String mess = "将全部盘子从" + towerName[0] + "座搬运到" +
                towerName[1] + "座或" + towerName[2] + "座";

        JLabel hintMess = new JLabel(mess, JLabel.CENTER);
        north.add(hintMess);

        add(north, BorderLayout.NORTH);
        setResizable(false);
        setVisible(true);
        setBounds(60, 60, 500, 600);
        validate();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.isNull(towerNumText.getText())||towerNumText.getText().length()==0){
            towerSize = 3;
        }else {
            towerSize = Integer.parseInt(towerNumText.getText());
        }

        if (e.getSource() == autoButton) {
            tower.setAmountOfDisc(this.towerSize);
            tower.putDiscOnTower();
            int x = this.getBounds().x + this.getBounds().width + 10;
            int y = this.getBounds().y;
            AutoPanel autoPanel = AutoPanel.getInstance(this);
            autoPanel.setLocation(x, y);
            autoPanel.setTower(tower);
            autoPanel.autoRun();
            autoPanel.setVisible(true);   // will sleep util listen something
        }else {
            tower.setAmountOfDisc(this.towerSize);
            tower.putDiscOnTower();
        }
        validate();
    }

    private void initTower(){
        tower = new Tower(towerName);
        tower.setAmountOfDisc(towerSize);
        tower.setMaxDiscWidth(120);
        tower.setMinDiscWidth(50);
        tower.setDiscHeight(16);
        tower.putDiscOnTower();
        this.add(tower, BorderLayout.CENTER);
    }


}
