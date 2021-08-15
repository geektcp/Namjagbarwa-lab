package com.geektcp.alpha.game.wuziqi.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author haiyang on 2019/9/20.
 */ // 实现鼠标事件接口
public class QiMouseEvent implements MouseListener {
    public void mouseClicked(MouseEvent e) {
        int x = round(e.getX()), y = round(e.getY());
        if (x >= 45 && x <= 675 && y >= 45 && y <= 675 && AIService.chessBoard[y / 45][x / 45] == 0 && AIService.black == false) {
            AIService.putChess(x, y);
            if (!AIService.finished) {
                AIService.black = true;
                AIService.myAI();
            }
            AIService.finished = false;
        }
}

    // 得到鼠标点击点附近的棋盘精准点
    private static int round(int x) {
        return (x % 45 < 22) ? x / 45 * 45 : x / 45 * 45 + 45;
    }

    public void mouseExited(MouseEvent e) {
        // do something
    }

    public void mouseEntered(MouseEvent e) {
        // do something
    }

    public void mouseReleased(MouseEvent e) {
        // do something
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // do something
    }
}
