/**
 * MyCircleBody.java
 * 圆形可运动刚体的定义
 */

package project;

import java.awt.*;

import org.jbox2d.dynamics.Body;

import static project.WorldConfig.*;

public class MyCircleBody extends MyBody{

    public int r;   // 半径
    
    public MyCircleBody(Body body, BodyInfo info, int r){
        super(body, info);
        this.r = r;
    }
    
    // 绘图函数
    @Override
    public void drawSelf(Graphics canvas){

        // 计算刚体左上角坐标与中心坐标
        int xc = (int)(body.getPosition().x * RATE);
        int x = xc - r;
        int yc = (int)(body.getPosition().y * RATE);
        int y = yc - r;

        // 旋转画布
        float theta = body.getAngle();
    	Graphics2D canvas2d = (Graphics2D)canvas;
        canvas2d.rotate(theta, xc, yc);
        
        // 根据生命值选择不同的图片
        if(this.hp >= this.hp_divide)
            canvas2d.drawImage(img_highHp, x, y, r * 2, r * 2, null);
        else
            canvas2d.drawImage(img_lowHp, x, y, r * 2, r * 2, null);

        // 恢复画布原来的角度
        canvas2d.rotate(-theta, xc, yc);
    }
}