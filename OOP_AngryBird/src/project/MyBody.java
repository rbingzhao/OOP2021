/**
 * MyBody.java
 * 刚体包装类
 */

package project;

import javax.swing.ImageIcon;
import java.awt.*;

import org.jbox2d.dynamics.Body;

enum DorS{Dynamic, Static};

public abstract class MyBody{

    public Body body;   // 刚体对象
    public Image img_highHp, img_lowHp; // 高、低生命值图片
    public int hp, hp_divide;   // 生命值、生命值高/低阈值

    public DorS dors;   // 运动/静止
    public BodyKind bk;

    // 创建运动刚体
    public MyBody(Body body, BodyInfo info){
        this.body = body;
        this.hp = info.hp;
        this.hp_divide = info.hp_divide;
        this.img_highHp = info.img_highHp;
        this.img_lowHp = info.img_lowHp;
        this.dors = DorS.Dynamic;
        this.bk = info.bk;
    }

    // 创建静止刚体
    public MyBody(Body body, String img_dir){
        this.body = body;
        this.img_highHp = new ImageIcon(img_dir).getImage();    // 静止刚体的生命值不会减少，故只用高生命值图片
        this.dors = DorS.Static;
        this.bk = BodyKind.staticBody;
    }

    public abstract void drawSelf(Graphics canvas); // 绘制图形
}