/**
 * MyCirclrBody.java
 * 长方形可运动刚体的定义
 */

package project;

import java.awt.*;
import org.jbox2d.dynamics.Body;

import static project.WorldConfig.*;

public class MyRectangleBody extends MyBody{

    public int w, h; // 长、宽
    
    public MyRectangleBody(Body body, BodyInfo info, int w, int h){
        super(body, info);
        this.w = w;
        this.h = h;
    }
    
    // 绘图函数
    @Override
    public void drawSelf(final Graphics canvas){

        // 计算刚体左上角坐标与中心坐标
        int xc = (int)(body.getPosition().x * RATE);
        int x = (int)(xc - (double)w / 2);
        int yc = (int)(body.getPosition().y * RATE);
        int y = (int)(yc - (double)h / 2);

        // 旋转画布
        float theta = body.getAngle();
    	Graphics2D canvas2d = (Graphics2D)canvas;
        canvas2d.rotate(theta, xc, yc);
        
        // 根据生命值选择不同的图片
        if(this.hp >= this.hp_divide)
            canvas.drawImage(img_highHp, x, y, w, h, null);
        else
            canvas.drawImage(img_lowHp, x, y, w, h, null);
            
        // 恢复画布原来的角度
        canvas2d.rotate(-theta, xc, yc);
    }
}