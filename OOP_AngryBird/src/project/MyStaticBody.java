/**
 * MyStaticBody.java
 * 静止刚体的定义
 */

package project;

import java.awt.*;

import org.jbox2d.dynamics.Body;

import static project.WorldConfig.*;

public class MyStaticBody extends MyBody{
    
    public int w, h; // 长、宽
    
    public MyStaticBody(Body body, int w, int h, String img_dir){
        super(body, img_dir);
        this.w = w;
        this.h = h;
        this.hp = 1;  // 防止误删
    }
    
    // 绘图函数
    @Override
    public void drawSelf(final Graphics canvas) {
        int x = (int)(body.getPosition().x * RATE - (double)w / 2);
        int y = (int)(body.getPosition().y * RATE - (double)h / 2);
        canvas.drawImage(img_highHp, x, y, w, h, null);
    }
}