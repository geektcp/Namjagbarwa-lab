package com.geektcp.alpha.game.tetris.block;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

@Slf4j
public class Tetris extends JPanel {

    private static final long serialVersionUID = -807909536278284335L;
    private static final int BLOCK_SIZE = 10;
    private static final int BLOCK_WIDTH = 16;
    private static final int BLOCK_HEIGHT = 26;
    private static final int TIME_DELAY = 1000;

    private static final String[] AuthorInfo = {
            "制作人：", "HelloClyde"
    };

    // 存放已经固定的方块
    private boolean[][] blockMap = new boolean[BLOCK_HEIGHT][BLOCK_WIDTH];

    // 分数
    private int score = 0;

    //是否暂停
    private boolean isPause = false;

    // 7种形状
    static boolean[][][] shape = BlockV4.Shape;

    // 下落方块的位置,左上角坐标
    private Point nowBlockPos;

    // 当前方块矩阵
    private boolean[][] nowBlockMap;
    // 下一个方块矩阵
    private boolean[][] nextBlockMap;
    /**
     * 范围[0,28) 7种，每种有4种旋转状态，共4*7=28 %4获取旋转状态 /4获取形状
     */
    private int nextBlockState;
    private int nowBlockState;

    //计时器
    private Timer timer;

    public Tetris() {
        this.initial();
        timer = new Timer(Tetris.TIME_DELAY, this.timerListener);
        timer.start();
        this.addKeyListener(this.keyListener);
    }

    public void setMode(String mode) {
        if (mode.equals("v6")) {
            Tetris.shape = BlockV6.Shape;
        } else {
            Tetris.shape = BlockV4.Shape;
        }
        this.initial();
        this.repaint();
    }

    /**
     * 新的方块落下时的初始化
     */
    private void getNextBlock() {
        // 将已经生成好的下一次方块赋给当前方块
        this.nowBlockState = this.nextBlockState;
        this.nowBlockMap = this.nextBlockMap;
        // 再次生成下一次方块
        this.nextBlockState = this.CreateNewBlockState();
        this.nextBlockMap = this.getBlockMap(nextBlockState);
        // 计算方块位置
        this.nowBlockPos = this.calNewBlockInitPos();
    }

    /**
     * 判断正在下落的方块和墙、已经固定的方块是否有接触
     *
     * @return
     */
    private boolean isTouch(boolean[][] srcNextBlockMap, Point srcNextBlockPos) {
        for (int i = 0; i < srcNextBlockMap.length; i++) {
            for (int j = 0; j < srcNextBlockMap[i].length; j++) {
                if (srcNextBlockMap[i][j]) {
                    if (srcNextBlockPos.y + i >= Tetris.BLOCK_HEIGHT || srcNextBlockPos.x + j < 0 || srcNextBlockPos.x + j >= Tetris.BLOCK_WIDTH) {
                        return true;
                    } else {
                        if (srcNextBlockPos.y + i < 0) {
                            continue;
                        } else {
                            if (this.blockMap[srcNextBlockPos.y + i][srcNextBlockPos.x + j]) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 固定方块到地图
     */
    private boolean fixBlock() {
        for (int i = 0; i < this.nowBlockMap.length; i++) {
            for (int j = 0; j < this.nowBlockMap[i].length; j++) {
                if (this.nowBlockMap[i][j])
                    if (this.nowBlockPos.y + i < 0) {
                        return false;
                    } else {
                        this.blockMap[this.nowBlockPos.y + i][this.nowBlockPos.x + j] = this.nowBlockMap[i][j];
                    }
            }
        }
        return true;
    }

    /**
     * 计算新创建的方块的初始位置
     *
     * @return 返回坐标
     */
    private Point calNewBlockInitPos() {
        return new Point(Tetris.BLOCK_WIDTH / 2 - this.nowBlockMap[0].length / 2, -this.nowBlockMap.length);
    }

    /**
     * 初始化
     */
    public void initial() {
        //清空Map
        for (int i = 0; i < this.blockMap.length; i++) {
            for (int j = 0; j < this.blockMap[i].length; j++) {
                this.blockMap[i][j] = false;
            }
        }
        //清空分数
        this.score = 0;
        // 初始化第一次生成的方块和下一次生成的方块
        this.nowBlockState = this.CreateNewBlockState();
        this.nowBlockMap = this.getBlockMap(this.nowBlockState);
        this.nextBlockState = this.CreateNewBlockState();
        this.nextBlockMap = this.getBlockMap(this.nextBlockState);
        // 计算方块位置
        this.nowBlockPos = this.calNewBlockInitPos();
        this.repaint();
    }

    public void setPause(boolean value) {
        this.isPause = value;
        if (this.isPause) {
            this.timer.stop();
        } else {
            this.timer.restart();
        }
        this.repaint();
    }

    /**
     * 随机生成新方块状态
     */
    private int CreateNewBlockState() {
        int Sum = Tetris.shape.length * 4;
        return (int) (Math.random() * 1000) % Sum;
    }

    private boolean[][] getBlockMap(int BlockState) {
        int Shape = BlockState / 4;
        int Arc = BlockState % 4;
        log.info(BlockState + "," + Shape + "," + Arc);
        return this.RotateBlock(Tetris.shape[Shape], Arc);
    }

    /**
     * 原算法
     *
     * 旋转方块Map，使用极坐标变换,注意源矩阵不会被改变
     * 使用round解决double转换到int精度丢失导致结果不正确的问题
     *
     * @param blockMap
     *            需要旋转的矩阵
     * @param angel
     *            rad角度，应该为pi/2的倍数
     * @return 转换完成后的矩阵引用

    private boolean[][] RotateBlock(boolean[][] blockMap, double angel) {
    // 获取矩阵宽高
    int Heigth = blockMap.length;
    int Width = blockMap[0].length;
    // 新矩阵存储结果
    boolean[][] ResultBlockMap = new boolean[Heigth][Width];
    // 计算旋转中心
    float CenterX = (Width - 1) / 2f;
    float CenterY = (Heigth - 1) / 2f;
    // 逐点计算变换后的位置
    for (int i = 0; i < blockMap.length; i++) {
    for (int j = 0; j < blockMap[i].length; j++) {
    //计算相对于旋转中心的坐标
    float RelativeX = j - CenterX;
    float RelativeY = i - CenterY;
    float ResultX = (float) (Math.cos(angel) * RelativeX - Math.sin(angel) * RelativeY);
    float ResultY = (float) (Math.cos(angel) * RelativeY + Math.sin(angel) * RelativeX);
    // 调试信息
    //log.info("RelativeX:" + RelativeX + "RelativeY:" + RelativeY);
    //log.info("ResultX:" + ResultX + "ResultY:" + ResultY);

    //将结果坐标还原
    Point OrginPoint = new Point(Math.round(CenterX + ResultX), Math.round(CenterY + ResultY));
    ResultBlockMap[OrginPoint.y][OrginPoint.x] = blockMap[i][j];
    }
    }
    return ResultBlockMap;
    }
     **/

    /**
     * @param shape 7种图形之一
     * @param time  旋转次数
     * @return https://blog.csdn.net/janchin/article/details/6310654  翻转矩阵
     */

    private boolean[][] RotateBlock(boolean[][] shape, int time) {
        if (time == 0) {
            return shape;
        }
        int heigth = shape.length;
        int width = shape[0].length;
        boolean[][] resultMap = new boolean[heigth][width];
        int tmpH = heigth - 1, tmpW = 0;
        for (int i = 0; i < heigth && tmpW < width; i++) {
            for (int j = 0; j < width && tmpH > -1; j++) {
                resultMap[i][j] = shape[tmpH][tmpW];
                tmpH--;
            }
            tmpH = heigth - 1;
            tmpW++;
        }
        for (int i = 1; i < time; i++) {
            resultMap = RotateBlock(resultMap, 0);
        }
        return resultMap;
    }

    /**
     * 测试方法，测试旋转函数
     *
     * @param args
     */
    public static void main(String... args) {
        boolean[][] srcMap = Tetris.shape[3];
        Tetris.ShowMap(srcMap);
		/*
		for (int i = 0;i < 7;i ++){
			log.info(i);
			Tetris.ShowMap(Tetris.shape[i]);
		}
		*/

        Tetris tetris = new Tetris();
        boolean[][] result = tetris.RotateBlock(srcMap, 1);
        Tetris.ShowMap(result);

    }

    /**
     * 测试方法，显示矩阵
     *
     * @param SrcMap
     */
    static private void ShowMap(boolean[][] SrcMap) {
        log.info("-----");
        for (int i = 0; i < SrcMap.length; i++) {
            for (int j = 0; j < SrcMap[i].length; j++) {
                if (SrcMap[i][j])
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            log.info("");
        }
        log.info("-----");
    }

    /**
     * 绘制游戏界面
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 画墙
        for (int i = 0; i < Tetris.BLOCK_HEIGHT + 1; i++) {
            g.drawRect(0 * Tetris.BLOCK_SIZE, i * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE);
            g.drawRect((Tetris.BLOCK_WIDTH + 1) * Tetris.BLOCK_SIZE, i * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE,
                    Tetris.BLOCK_SIZE);
        }
        for (int i = 0; i < Tetris.BLOCK_WIDTH; i++) {
            g.drawRect((1 + i) * Tetris.BLOCK_SIZE, Tetris.BLOCK_HEIGHT * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE,
                    Tetris.BLOCK_SIZE);
        }
        // 画当前方块
        for (int i = 0; i < this.nowBlockMap.length; i++) {
            for (int j = 0; j < this.nowBlockMap[i].length; j++) {
                if (this.nowBlockMap[i][j])
                    g.fillRect((1 + this.nowBlockPos.x + j) * Tetris.BLOCK_SIZE, (this.nowBlockPos.y + i) * Tetris.BLOCK_SIZE,
                            Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE);
            }
        }
        // 画已经固定的方块
        for (int i = 0; i < Tetris.BLOCK_HEIGHT; i++) {
            for (int j = 0; j < Tetris.BLOCK_WIDTH; j++) {
                if (this.blockMap[i][j])
                    g.fillRect(Tetris.BLOCK_SIZE + j * Tetris.BLOCK_SIZE, i * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE,
                            Tetris.BLOCK_SIZE);
            }
        }
        //绘制下一个方块
        for (int i = 0; i < this.nextBlockMap.length; i++) {
            for (int j = 0; j < this.nextBlockMap[i].length; j++) {
                if (this.nextBlockMap[i][j])
                    g.fillRect(190 + j * Tetris.BLOCK_SIZE, 30 + i * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE);
            }
        }
        // 绘制其他信息
        g.drawString("游戏分数:" + this.score, 190, 10);
        for (int i = 0; i < Tetris.AuthorInfo.length; i++) {
            g.drawString(Tetris.AuthorInfo[i], 190, 100 + i * 20);
        }

        //绘制暂停
        if (this.isPause) {
            g.setColor(Color.white);
            g.fillRect(70, 100, 50, 20);
            g.setColor(Color.black);
            g.drawRect(70, 100, 50, 20);
            g.drawString("PAUSE", 75, 113);
        }
    }


    // 定时器监听
    private ActionListener timerListener = new ActionListener() {
        /**
         * @return
         */
        private int clearLines() {
            int lines = 0;
            for (int i = 0; i < blockMap.length; i++) {
                boolean isLine = true;
                for (int j = 0; j < blockMap[i].length; j++) {
                    if (!blockMap[i][j]) {
                        isLine = false;
                        break;
                    }
                }
                if (isLine) {
                    for (int k = i; k > 0; k--) {
                        blockMap[k] = blockMap[k - 1];
                    }
                    blockMap[0] = new boolean[Tetris.BLOCK_WIDTH];
                    lines++;
                }
            }
            return lines;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO 自动生成的方法存根
            if (Tetris.this.isTouch(Tetris.this.nowBlockMap, new Point(Tetris.this.nowBlockPos.x, Tetris.this.nowBlockPos.y + 1))) {
                if (Tetris.this.fixBlock()) {
                    Tetris.this.score += clearLines() * 10;
                    Tetris.this.getNextBlock();
                } else {
                    JOptionPane.showMessageDialog(Tetris.this.getParent(), "GAME OVER");
                    Tetris.this.initial();
                }
            } else {
                Tetris.this.nowBlockPos.y++;
            }
            Tetris.this.repaint();
        }
    };

    //按键监听
    java.awt.event.KeyListener keyListener = new java.awt.event.KeyListener() {
        @Override
        public void keyPressed(KeyEvent e) {
            // TODO 自动生成的方法存根
            if (!isPause) {
                Point DesPoint;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        DesPoint = new Point(Tetris.this.nowBlockPos.x, Tetris.this.nowBlockPos.y + 1);
                        if (!Tetris.this.isTouch(Tetris.this.nowBlockMap, DesPoint)) {
                            Tetris.this.nowBlockPos = DesPoint;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        boolean[][] TurnBlock = Tetris.this.RotateBlock(Tetris.this.nowBlockMap, 1);
                        if (!Tetris.this.isTouch(TurnBlock, Tetris.this.nowBlockPos)) {
                            Tetris.this.nowBlockMap = TurnBlock;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        DesPoint = new Point(Tetris.this.nowBlockPos.x + 1, Tetris.this.nowBlockPos.y);
                        if (!Tetris.this.isTouch(Tetris.this.nowBlockMap, DesPoint)) {
                            Tetris.this.nowBlockPos = DesPoint;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        DesPoint = new Point(Tetris.this.nowBlockPos.x - 1, Tetris.this.nowBlockPos.y);
                        if (!Tetris.this.isTouch(Tetris.this.nowBlockMap, DesPoint)) {
                            Tetris.this.nowBlockPos = DesPoint;
                        }
                        break;
                }
                //log.info(Tetris.this.nowBlockPos);
                repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO 自动生成的方法存根

        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO 自动生成的方法存根

        }

    };


    static class BlockV4 {
        private BlockV4() {
        }

        static final boolean[][][] Shape = {
                // I
                {
                        {false, false, false, false},
                        {true, true, true, true},
                        {false, false, false, false},
                        {false, false, false, false}
                },
                // J
                {
                        {true, false, false},
                        {true, true, true},
                        {false, false, false}
                },
                // L
                {
                        {false, false, true},
                        {true, true, true},
                        {false, false, false}
                },
                // O
                {
                        {true, true},
                        {true, true}
                },
                // S
                {
                        {false, true, true},
                        {true, true, false},
                        {false, false, false}
                },
                // T
                {
                        {false, true, false},
                        {true, true, true},
                        {false, false, false}
                },
                // Z
                {
                        {true, true, false},
                        {false, true, true},
                        {false, false, false}
                }};
    }

    static class BlockV6 {
        private BlockV6() {
        }

        static final boolean[][][] Shape = {
                /*
                 * oooooo
                 */
                {
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {true, true, true, true, true, true},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false}
                },
                /*
                 * ooo
                 * ooo
                 */
                {
                        {true, true, true},
                        {true, true, true},
                        {false, false, false}
                },
                /*
                 * ooo
                 *   o
                 *   o
                 *   o
                 */
                {
                        {true, true, true, false},
                        {false, false, true, false},
                        {false, false, true, false},
                        {false, false, true, false}
                },
                /*
                 * ooo
                 * o
                 * o
                 * o
                 */
                {
                        {false, true, true, true},
                        {false, true, false, false},
                        {false, true, false, false},
                        {false, true, false, false}
                },
                /*
                 *  o
                 *  o
                 * oo
                 * o
                 * o
                 */
                {
                        {false, false, true, false, false},
                        {false, false, true, false, false},
                        {false, true, true, false, false},
                        {false, true, false, false, false},
                        {false, true, false, false, false}
                },
                /*
                 * o
                 * o
                 * oo
                 *  o
                 *  o
                 */
                {
                        {false, true, false, false, false},
                        {false, true, false, false, false},
                        {false, true, true, false, false},
                        {false, false, true, false, false},
                        {false, false, true, false, false}
                },
                /*
                 * ooo
                 *  o
                 *  o
                 *  o
                 */
                {
                        {false, true, true, true},
                        {false, false, true, false},
                        {false, false, true, false},
                        {false, false, true, false}
                },
                /*
                 * ooooo
                 *   o
                 */
                {
                        {false, false, false, false, false},
                        {true, true, true, true, true},
                        {false, false, true, false, false},
                        {false, false, true, false, false},
                        {false, false, false, false, false}
                }
        };
    }


}
