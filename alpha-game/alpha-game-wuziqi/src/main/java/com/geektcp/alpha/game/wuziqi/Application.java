package com.geektcp.alpha.game.wuziqi;


import com.geektcp.alpha.game.wuziqi.panel.AIService;
import com.geektcp.alpha.game.wuziqi.panel.PanelService;
import com.geektcp.alpha.game.wuziqi.panel.QiMouseEvent;

/**
 * @author haiyang on 2019/9/20.
 */
public class Application {

    public static void main(String[] args) {
        PanelService panel = new PanelService(700, 700);
        QiMouseEvent qiMouseEvent = new QiMouseEvent();
        panel.addMouseListener(qiMouseEvent);
        AIService.init(panel);
        AIService.initChessBoard();
    }
}
