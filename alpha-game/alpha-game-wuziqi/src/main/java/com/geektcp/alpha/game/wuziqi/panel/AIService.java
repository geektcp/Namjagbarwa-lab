package com.geektcp.alpha.game.wuziqi.panel;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * AI算法和主函数入口
 */
@Data
public class AIService {

    public static boolean finished = false;
    public static boolean black = false;                   //标志棋子的颜色
    public static int[][] chessBoard = new int[17][17];    //棋盘棋子的摆放情况：0无子，1黑子，－1白子

    private AIService() {
    }
    //
    private static PanelService panel;
    private static Graphics graphics;
    private static HashSet<Point> judgePoint = new HashSet<>(); // ai可能会下棋的点
    //    private static int dr[] = new int[]{-1, 1, -1, 1, 0, 0, -1, 1}; // 方向向量
//    private static int dc[] = new int[]{1, -1, -1, 1, -1, 1, 0, 0}; //方向向量
    private static List<Integer> drList;
    private static List<Integer> dcList;
    private static final int MAXN = 1 << 28;
    private static final int MINN = -MAXN;
    private static int searchDeep = 4;    //搜索深度
    private static final int SIZE = 15;   // 棋盘大小


    public static void init(PanelService panel1) {
        panel = panel1;
        graphics = panel.getGraphics();
        drList = Lists.newArrayList(-1, 1, -1, 1, 0, 0, -1, 1);
        dcList = Lists.newArrayList(1, -1, -1, 1, -1, 1, 0, 0);
    }

    // 初始化函数，绘图
    public static void initChessBoard() {
        black = false;
        judgePoint.clear();
        panel.clear();
        panel.setBackground(Color.GRAY);

        graphics.setColor(Color.BLACK);
        for (int i = 45; i <= 675; i += 45) {
            graphics.drawLine(45, i, 675, i);
            graphics.drawLine(i, 45, i, 675);
        }
        // 棋盘上的五个定位基本点，图中的小圆圈
        graphics.setColor(Color.BLACK);
        graphics.fillOval(353, 353, 14, 14);
        graphics.fillOval(218, 218, 14, 14);
        graphics.fillOval(488, 218, 14, 14);
        graphics.fillOval(488, 488, 14, 14);
        graphics.fillOval(218, 488, 14, 14);
        // 初始化棋盘
        for (int i = 1; i <= 15; ++i)
            for (int j = 1; j <= 15; ++j)
                chessBoard[i][j] = 0;
        // ai先手
        graphics.fillOval(337, 337, 45, 45);
        chessBoard[8][8] = 1;
        for (int i = 0; i < 8; ++i)
            if (1 <= 8 + dcList.get(i) && 8 + dcList.get(i) <= SIZE && 1 <= 8 + drList.get(i) && 8 + drList.get(i) <= SIZE) {
                Point now = new Point(8 + dcList.get(i), 8 + drList.get(i));
                if (judgePoint.contains(now)) {
                    continue;
                }
                judgePoint.add(now);
            }
        black = false;
    }

    // 通过点击事件，得到棋子位置进行下棋
    public static void putChess(int x, int y) {
        if (black)
            graphics.setColor(Color.BLACK);
        else
            graphics.setColor(Color.WHITE);
        graphics.fillOval(x - 22, y - 22, 45, 45);
        chessBoard[y / 45][x / 45] = black ? 1 : -1;
        if (isEnd(x / 45, y / 45)) {
            String s = AIService.black ? "黑子胜" : "白子胜";
            JOptionPane.showMessageDialog(null, s);
            black = true;
            initChessBoard();
        } else {
            Point p = new Point(x / 45, y / 45);
            judgePoint.remove(p);
            for (int i = 0; i < 8; ++i) {
                Point now = new Point(p.x + dcList.get(i), p.y + drList.get(i));
                if (1 <= now.x && now.x <= SIZE && 1 <= now.y && now.y <= SIZE && chessBoard[now.y][now.x] == 0)
                    judgePoint.add(now);
            }
        }
    }

    // ai博弈入口函数
    public static void myAI() {
        Node node = new Node();
        dfs(0, node, MINN, MAXN, null);
        Point now = node.getBestChild().getPoint();
        // judgePoint.remove(now);
        putChess(now.x * 45, now.y * 45);
        black = false;
    }

    // alpha beta dfs
    private static void dfs(int deep, Node root, int alpha, int beta, Point p) {
        if (deep == searchDeep) {
            root.setMark( getMark());
            // System.out.printf("%d\t%d\t%d\n",p.x,p.y,root.mark);
            return;
        }
        ArrayList<Point> judgeSet = new ArrayList<Point>();
        Iterator it = judgePoint.iterator();
        while (it.hasNext()) {
            Point now = new Point((Point) it.next());
            judgeSet.add(now);
        }
        it = judgeSet.iterator();
        while (it.hasNext()) {
            Point now = new Point((Point) it.next());
            Node node = new Node();
            node.setPoint(now);
            root.addChild(node);
            boolean flag = judgePoint.contains(now);
            chessBoard[now.y][now.x] = ((deep & 1) == 1) ? -1 : 1;
            if (isEnd(now.x, now.y)) {
                root.setBestChild(node);
                root.setMark( MAXN * chessBoard[now.y][now.x]);
                chessBoard[now.y][now.x] = 0;
                return;
            }

            boolean flags[] = new boolean[8]; //标记回溯时要不要删掉
            Arrays.fill(flags, true);
            for (int i = 0; i < 8; ++i) {
                Point next = new Point(now.x + dcList.get(i), now.y + drList.get(i));
                if (1 <= now.x + dcList.get(i) && now.x + dcList.get(i) <= SIZE && 1 <= now.y + drList.get(i) && now.y + drList.get(i) <= SIZE && chessBoard[next.y][next.x] == 0) {
                    if (!judgePoint.contains(next)) {
                        judgePoint.add(next);
                    } else flags[i] = false;
                }
            }

            if (flag)
                judgePoint.remove(now);
            dfs(deep + 1, root.getLastChild(), alpha, beta, now);
            chessBoard[now.y][now.x] = 0;
            if (flag)
                judgePoint.add(now);
            for (int i = 0; i < 8; ++i)
                if (flags[i])
                    judgePoint.remove(new Point(now.x + dcList.get(i), now.y + drList.get(i)));
            // alpha beta剪枝
            // min层
            if ((deep & 1) == 1) {
                if (root.getBestChild() == null || root.getLastChild().getMark() < root.getBestChild().getMark()) {
                    root.setBestChild(root.getLastChild());
                    root.setMark(root.getBestChild().getMark());
                    if (root.getMark() <= MINN){
                    root.setMark(root.getMark()+deep);
                }
                    beta = Math.min(root.getMark(), beta);
                }
                if (root.getMark() <= alpha)
                    return;
            }
            // max层
            else {
                if (root.getBestChild() == null || root.getLastChild().getMark() > root.getBestChild().getMark()) {
                    root.setBestChild(root.getLastChild());
                    root.setMark(root.getBestChild().getMark());
                    if (root.getMark() == MAXN)
                        root.setMark(root.getMark()-deep);
                    alpha = Math.max(root.getMark(), alpha);
                }
                if (root.getMark() >= beta)
                    return;
            }
        }
    }

    public static int getMark() {
        int res = 0;
        for (int i = 1; i <= SIZE; ++i) {
            for (int j = 1; j <= SIZE; ++j) {
                if (chessBoard[i][j] != 0) {
                    // 行
                    boolean flag1 = false, flag2 = false;
                    int x = j, y = i;
                    int cnt = 1;
                    int col = x, row = y;
                    while (--col > 0 && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
                    if (col > 0 && chessBoard[row][col] == 0) flag1 = true;
                    col = x;
                    row = y;
                    while (++col <= SIZE && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
                    if (col <= SIZE && chessBoard[row][col] == 0) flag2 = true;
                    if (flag1 && flag2)
                        res += chessBoard[i][j] * cnt * cnt;
                    else if (flag1 || flag2) res += chessBoard[i][j] * cnt * cnt / 4;
                    if (cnt >= 5) res = MAXN * chessBoard[i][j];
                    // 列
                    col = x;
                    row = y;
                    cnt = 1;
                    flag1 = false;
                    flag2 = false;
                    while (--row > 0 && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
                    if (row > 0 && chessBoard[row][col] == 0) flag1 = true;
                    col = x;
                    row = y;
                    while (++row <= SIZE && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
                    if (row <= SIZE && chessBoard[row][col] == 0) flag2 = true;
                    if (flag1 && flag2)
                        res += chessBoard[i][j] * cnt * cnt;
                    else if (flag1 || flag2)
                        res += chessBoard[i][j] * cnt * cnt / 4;
                    if (cnt >= 5) res = MAXN * chessBoard[i][j];
                    // 左对角线
                    col = x;
                    row = y;
                    cnt = 1;
                    flag1 = false;
                    flag2 = false;
                    while (--col > 0 && --row > 0 && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
                    if (col > 0 && row > 0 && chessBoard[row][col] == 0) flag1 = true;
                    col = x;
                    row = y;
                    while (++col <= SIZE && ++row <= SIZE && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
                    if (col <= SIZE && row <= SIZE && chessBoard[row][col] == 0) flag2 = true;
                    if (flag1 && flag2)
                        res += chessBoard[i][j] * cnt * cnt;
                    else if (flag1 || flag2) res += chessBoard[i][j] * cnt * cnt / 4;
                    if (cnt >= 5) res = MAXN * chessBoard[i][j];
                    // 右对角线
                    col = x;
                    row = y;
                    cnt = 1;
                    flag1 = false;
                    flag2 = false;
                    while (++row <= SIZE && --col > 0 && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
                    if (row <= SIZE && col > 0 && chessBoard[row][col] == 0) flag1 = true;
                    col = x;
                    row = y;
                    while (--row > 0 && ++col <= SIZE && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
                    if (row > 0 && col <= SIZE && chessBoard[i][j] == 0) flag2 = true;
                    if (flag1 && flag2)
                        res += chessBoard[i][j] * cnt * cnt;
                    else if (flag1 || flag2) res += chessBoard[i][j] * cnt * cnt / 4;
                    if (cnt >= 5) res = MAXN * chessBoard[i][j];

                }
            }
        }
        return res;
    }

    // for debug
    public static void debug() {
        for (int i = 1; i <= SIZE; ++i) {
            for (int j = 1; j <= SIZE; ++j) {
                System.out.printf("%d\t", chessBoard[i][j]);
            }
            System.out.println("");
        }
    }

    // 判断是否一方取胜
    public static boolean isEnd(int x, int y) {
        // 判断一行是否五子连珠
        int cnt = 1;
        int col = x, row = y;
        while (--col > 0 && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
        col = x;
        row = y;
        while (++col <= SIZE && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
        if (cnt >= 5) {
            finished = true;
            return true;
        }
        // 判断一列是否五子连珠
        col = x;
        row = y;
        cnt = 1;
        while (--row > 0 && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
        col = x;
        row = y;
        while (++row <= SIZE && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
        if (cnt >= 5) {
            finished = true;
            return true;
        }
        // 判断左对角线是否五子连珠
        col = x;
        row = y;
        cnt = 1;
        while (--col > 0 && --row > 0 && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
        col = x;
        row = y;
        while (++col <= SIZE && ++row <= SIZE && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
        if (cnt >= 5) {
            finished = true;
            return true;
        }
        // 判断右对角线是否五子连珠
        col = x;
        row = y;
        cnt = 1;
        while (++row <= SIZE && --col > 0 && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
        col = x;
        row = y;
        while (--row > 0 && ++col <= SIZE && chessBoard[row][col] == chessBoard[y][x]) ++cnt;
        if (cnt >= 5) {
            finished = true;
            return true;
        }
        return false;
    }
}


