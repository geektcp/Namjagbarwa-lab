package com.geektcp.alpha.game.tetris;

import com.geektcp.alpha.game.tetris.block.Tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application extends JFrame {

    private static final long serialVersionUID = 8995729671326316569L;
    Tetris tetris = new Tetris();

    public Application() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(280, 350);
        this.setTitle("Tetris Remake");
        this.setResizable(false);

        JMenuBar menu = new JMenuBar();
        this.setJMenuBar(menu);
        JMenu gameMenu = new JMenu("游戏");
        JMenuItem newGameItem = gameMenu.add("新游戏");
        newGameItem.addActionListener(this.NewGameAction);
        JMenuItem pauseItem = gameMenu.add("暂停");
        pauseItem.addActionListener(this.PauseAction);
        JMenuItem continueItem = gameMenu.add("继续");
        continueItem.addActionListener(this.ContinueAction);
        JMenuItem exitItem = gameMenu.add("退出");
        exitItem.addActionListener(this.ExitAction);
        JMenu modeMenu = new JMenu("模式");
        JMenuItem v4Item = modeMenu.add("4方块");
        v4Item.addActionListener(this.v4Action);
        JMenuItem v6Item = modeMenu.add("6方块");
        v6Item.addActionListener(this.v6Action);
        JMenu helpMenu = new JMenu("帮助");
        JMenuItem aboutItem = helpMenu.add("关于");
        aboutItem.addActionListener(this.AboutAction);
        menu.add(gameMenu);
        menu.add(modeMenu);
        menu.add(helpMenu);

        this.add(this.tetris);
        this.tetris.setFocusable(true);
    }

    static public void main(String... args) {
        Application application = new Application();
        application.setVisible(true);
    }

    ActionListener NewGameAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            Application.this.tetris.initial();
        }
    };

    ActionListener PauseAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            Application.this.tetris.setPause(true);
        }
    };

    ActionListener ContinueAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            Application.this.tetris.setPause(false);
        }
    };

    ActionListener ExitAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            System.exit(0);
        }
    };

    ActionListener AboutAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            JOptionPane.showMessageDialog(Application.this, "Tetris Remake Ver 1.0", "关于", JOptionPane.WARNING_MESSAGE);
        }
    };

    ActionListener v4Action = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            Application.this.tetris.setMode("v4");
        }
    };

    ActionListener v6Action = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            Application.this.tetris.setMode("v6");
        }
    };
}
