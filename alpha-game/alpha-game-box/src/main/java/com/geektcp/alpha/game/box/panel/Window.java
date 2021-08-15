package com.geektcp.alpha.game.box.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * @author tanghaiyang on 2019/9/29.
 */
public class Window extends JFrame implements KeyListener, ActionListener {

    private PicSrc imgSrc;
    private int column;
    private int row;
    private Block[] blocks;
    private Vector<Block> terminals;
    private Block men;
    private Dimension picSize;
    private MyCanvas canvas;
    private Stack<List<String>> backStack;

    public Window(String mapName) {
        backStack = new Stack<>();
        init(new Map(mapName).getMapData());

		/* 菜单 */
        JMenu pick = new JMenu("选关");
        List<String> map = Map.getAllMapName();
        for (String name : map) {
            JMenuItem item = new JMenuItem(name);
            item.addActionListener(this);
            pick.add(item);
        }

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(pick);
        setJMenuBar(menuBar);
        /*----*/


		/* 按钮 */
        JButton back = new JButton("回退");
        back.addActionListener(this);
		/*----*/

		/* 画布 */
        canvas = new MyCanvas();
        addKeyListener(this);
        canvas.addKeyListener(this);
		/* ---- */

        setLayout(new BorderLayout());
        add(back, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void init(List<String> md) {
        terminals = new Vector<>();
        List<String> mapData = md;
        imgSrc = PicSrc.getInstance();

        column = mapData.get(0).length();
        row = mapData.size();

        blocks = new Block[column * row];
        picSize = new Dimension(imgSrc.getImgByIndex(0).getIconWidth(), imgSrc.getImgByIndex(0).getIconHeight());

        //通过map数据初始化所有的块
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                ImageIcon imageIcon = imgSrc.getImgByIndex(mapData.get(i).charAt(j) - '0');
                int type = Integer.parseInt(imageIcon.getDescription());
                int pos = i * column + j;
                blocks[pos] = new Block(new Point(j * picSize.width, i * picSize.height), picSize, imageIcon);
                if (BlockType.isMen(type)) men = blocks[pos];
                if (BlockType.TERMINAL == type) terminals.add(blocks[pos]);
            }
        }
        setSize(column * picSize.width, row * picSize.height);
    }

    //键盘监测
    @Override
    public void keyPressed(KeyEvent e) {
        if (blockMove(men.getLocation(), e.getKeyCode())) {
            backStack.push(Map.creatMapData(blocks, row, column));
        }
        canvas.repaint();
        if (isWin())
            JOptionPane.showMessageDialog(null, "You win!", "", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //按钮检测
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "回退") {
            if (backStack.size() == 0) return;
            this.init(backStack.pop());
        } else {
            backStack = new Stack<>();
            this.init(new Map(e.getActionCommand()).getMapData());
        }
        canvas.repaint();
    }

    private class MyCanvas extends Canvas {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            //Integer i = 0;
            for (Block block : blocks) {
                g2d.draw(block);
                g2d.drawImage(block.getIcon().getImage(), block.getLocation().x, block.getLocation().y, this);
                //g2d.drawString(i.toString(),  block.getLocation().x,  block.getLocation().y+picSize.height);
                //i++;
            }
        }

    }

    //块移动检测
    private boolean blockMove(Point point, int keyCode) {
        int x = point.x,
                y = point.y;
        int currPos = (y / picSize.height) * column + (x / picSize.width);
        int men_type = BlockType.MEN_DOWN;
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                x += picSize.width;
                men_type = BlockType.MEN_RIGHT;
                break;
            case KeyEvent.VK_DOWN:
                y += picSize.height;
                men_type = BlockType.MEN_DOWN;
                break;
            case KeyEvent.VK_LEFT:
                x -= picSize.width;
                men_type = BlockType.MEN_LEFT;
                break;
            case KeyEvent.VK_UP:
                y -= picSize.height;
                men_type = BlockType.MEN_UP;
                break;
            default:
                break;
        }
        int nextPos = (y / picSize.height) * column + (x / picSize.width);


        System.out.println("currPos:" + currPos + "  " + "newtPos:" + nextPos);

        int curr_box_type = blocks[currPos].getIconType(),
                next_box_type = blocks[nextPos].getIconType();

        if (BlockType.BORDER == next_box_type) return false;
        if (BlockType.isBox(curr_box_type) && BlockType.isBox(next_box_type)) return false;

        if (BlockType.isBox(curr_box_type) && BlockType.isRoad(next_box_type)) {
            blocks[nextPos].setIcon(imgSrc.getImgByIndex(next_box_type == BlockType.TERMINAL ? BlockType.BOX_DONE : BlockType.BOX));
            blocks[currPos].setIcon(imgSrc.getImgByIndex(blocks[currPos].getType()));
            return true;
        }

        if (BlockType.isBox(next_box_type)) {
            if (!blockMove(new Point(x, y), keyCode)) return false;
        }

        next_box_type = blocks[nextPos].getIconType();
        if (BlockType.isRoad(next_box_type)) {
            System.out.println("check road");
            blocks[nextPos].setIcon(imgSrc.getImgByIndex(men_type));
            blocks[currPos].setIcon(imgSrc.getImgByIndex(blocks[currPos].getType()));
            men = blocks[nextPos];
            return true;
        }
        return false;
    }

    //检测是否完成
    private boolean isWin() {
        for (Block block : terminals) {
            if (block.getIconType() != BlockType.BOX_DONE) return false;
        }
        return true;
    }


}
