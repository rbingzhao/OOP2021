/**
 * BodyConfig.java
 * 配置各个元素的参数
 */

package project;

import java.awt.*;
import javax.swing.ImageIcon;

import static project.WorldConfig.*;

public class BodyConfig{

    // 按键图片
    public static Image img_pause = new ImageIcon("Image/pause.png").getImage();
    public static Image img_cont = new ImageIcon("Image/continue.png").getImage();
    public static Image img_reset = new ImageIcon("Image/resume.png").getImage();
    public static Image img_exit = new ImageIcon("Image/menu/menu.png").getImage();
    public static Image img_killBird = new ImageIcon("Image/cross.png").getImage();

    // 背景图片数组
    public static Image[] bgndList = new Image[]{
        new ImageIcon("Image/sky1.jpg").getImage(),
        new ImageIcon("Image/sky2.png").getImage(),
        new ImageIcon("Image/sky3.png").getImage(),
        new ImageIcon("Image/background.png").getImage()
    };

    // 等待发射的鸟的数组
    // 格式: x, y, 长, 宽, 图片路径, 运动速度
    public static ImageEntry[][] waitingBirdList = new ImageEntry[][]{
        {
            new ImageEntry(SLING_X + 5, SLING_Y, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(15, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(65, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(115, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(165, 530, 40, 40, "Image/bird1.png", 0)
        },{
            new ImageEntry(SLING_X + 5, SLING_Y, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(15, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(65, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(115, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(165, 530, 40, 40, "Image/bird1.png", 0)
        },{
            new ImageEntry(SLING_X + 5, SLING_Y, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(15, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(65, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(115, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(165, 530, 40, 40, "Image/bird1.png", 0)
        },{
            new ImageEntry(SLING_X + 5, SLING_Y, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(15, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(65, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(115, 530, 40, 40, "Image/bird1.png", 0),
            new ImageEntry(165, 530, 40, 40, "Image/bird1.png", 0)
        }
    };

    // 非刚体图片数组
    // 格式: x, y, 长, 宽, 图片路径, 运动速度
    //! 第一行必须为弹弓中心坐标
    public static ImageEntry[][] imageList = new ImageEntry[][]{
        {
            new ImageEntry(SLING_X, SLING_Y, SLING_W, SLING_H, "Image/slingstick.png", 0),
            new ImageEntry(100, 75, 150, 75, "Image/menu/cloud.png", 2),
            new ImageEntry(300, 150, 150, 75, "Image/menu/cloud.png", 1)
        },{
            new ImageEntry(SLING_X, SLING_Y, SLING_W, SLING_H, "Image/slingstick.png", 0),
            new ImageEntry(400, 75, 150, 75, "Image/menu/cloud.png", 2),
            new ImageEntry(200, 150, 150, 75, "Image/menu/cloud.png", 1)
        },{
            new ImageEntry(SLING_X, SLING_Y, SLING_W, SLING_H, "Image/slingstick.png", 0),
            new ImageEntry(300, 75, 150, 75, "Image/menu/cloud.png", 2),
            new ImageEntry(100, 150, 150, 75, "Image/menu/cloud.png", 1)
        },{
            new ImageEntry(SLING_X, SLING_Y, SLING_W, SLING_H, "Image/slingstick.png", 0),
            new ImageEntry(400, 75, 150, 75, "Image/menu/cloud.png", 2),
            new ImageEntry(200, 150, 150, 75, "Image/menu/cloud.png", 1)
        }
        
    };

    private static int r = 20;
    private static int h = 20;
    private static int phase[]= {300,400,500,600,700,800,900,1000,1100};

    // 运动刚体数组
    // x, y, 类型, x方向速度, y方向速度, [半径]或[长, 宽](取决于类型)
    //! 参数个数必须与类型匹配，圆形参数为半径，四边形参数为长、宽
    public static DynamicBodyEntry[][] dynamicBodyList = new DynamicBodyEntry[][]{
    	{
    		new DynamicBodyEntry(phase[3], Y_GROUND - r, BodyKind.squareWood, 0f, 0f, 50, 50),
    		new DynamicBodyEntry(phase[3], Y_GROUND - r - 50, BodyKind.rectIce, 0f, 0f, 50, 50),
    		new DynamicBodyEntry(phase[4] + 15, Y_GROUND - r, BodyKind.squareWood, 0f, 0f, 50, 50),
    		new DynamicBodyEntry(phase[4] + 20, Y_GROUND - 3 * h / 2, BodyKind.pig, 0f, 0f, 20),
    		new DynamicBodyEntry(phase[5] + 25, Y_GROUND - r, BodyKind.squareWood, 0f, 0f, 50, 50),
    		new DynamicBodyEntry(phase[5] + 25, Y_GROUND - r - 50, BodyKind.rectIce, 0f, 0f, 50, 50)
    	},{
    		new DynamicBodyEntry(phase[3] + 40, Y_GROUND - h / 2, BodyKind.rectWood, 0.0f, 0.0f, 200, 20),
    		new DynamicBodyEntry(phase[3] + 40, Y_GROUND - h / 2, BodyKind.rectWood, 0.0f, 0.0f, 200, 20),
            new DynamicBodyEntry(phase[3] + 40, Y_GROUND - 3 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 40, 20),
            new DynamicBodyEntry(phase[3] + 80, Y_GROUND - 3 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 40, 20),
            new DynamicBodyEntry(phase[3] + 160, Y_GROUND - 3 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 40, 20),
            new DynamicBodyEntry(phase[3] + 200, Y_GROUND - 3 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 40, 20),
            new DynamicBodyEntry(phase[3] + 40, Y_GROUND - 5 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 40, 20),
            new DynamicBodyEntry(phase[3] + 80, Y_GROUND - 5 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 40, 20),
            new DynamicBodyEntry(phase[3] + 160, Y_GROUND - 5 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 40, 20),
            new DynamicBodyEntry(phase[3] + 200, Y_GROUND - 5 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 40, 20),
            new DynamicBodyEntry(phase[3] + 120, Y_GROUND - 5 * h / 2, BodyKind.pig, 0f, 0f, 20),
            new DynamicBodyEntry(phase[3] + 40, Y_GROUND - 7 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 200, 20),
            new DynamicBodyEntry(phase[3] + 40, Y_GROUND - 9 * h / 2, BodyKind.rectWood, 0.0f, 0.0f, 200, 20),
            new DynamicBodyEntry(phase[3] + 120, Y_GROUND - 11 * h / 2, BodyKind.pig, 0f, 0f, 20),
            new DynamicBodyEntry(phase[3] + 80, Y_GROUND - 11 * h / 2, BodyKind.rectStone, 0.0f, 0.0f, 40, 40),
            new DynamicBodyEntry(phase[3] + 160, Y_GROUND - 11 * h / 2, BodyKind.rectStone, 0.0f, 0.0f, 40, 40),
            new DynamicBodyEntry(phase[3] + 80, Y_GROUND - 13 * h / 2, BodyKind.rectStone, 0.0f, 0.0f, 120, 20),
            new DynamicBodyEntry(phase[2], Y_GROUND - h, BodyKind.circIce, 0f, 0f, 20),
            new DynamicBodyEntry(phase[7]-50, Y_GROUND - h, BodyKind.circIce, 0f, 0f, 20)
            
    	},{
    		new DynamicBodyEntry(phase[2], Y_GROUND - h / 2, BodyKind.rectStone, 0.0f, 0.0f, 80, 20),
    		new DynamicBodyEntry(phase[2], Y_GROUND - h / 2 - h, BodyKind.rectStone, 0.0f, 0.0f, 80, 20),
    		new DynamicBodyEntry(phase[2], Y_GROUND - h / 2 - 2 * h, BodyKind.rectStone, 0.0f, 0.0f, 80, 20),
    		new DynamicBodyEntry(phase[2], Y_GROUND - h / 2 - 3 * h, BodyKind.rectStone, 0.0f, 0.0f, 80, 20),
    		new DynamicBodyEntry(phase[2], Y_GROUND - h / 2 - 4 * h, BodyKind.rectStone, 0.0f, 0.0f, 80, 20),
    		new DynamicBodyEntry(phase[3] + 60, Y_GROUND - r, BodyKind.circStone, 0f, 0f, 20),
            new DynamicBodyEntry(phase[3] + 90, Y_GROUND - r, BodyKind.circIce, 0f, 0f, 20),
            new DynamicBodyEntry(phase[4] + 20, Y_GROUND - r, BodyKind.circWood, 0f, 0f, 20),
            new DynamicBodyEntry(phase[4] + 50, Y_GROUND - r, BodyKind.circIce, 0f, 0f, 20),
            new DynamicBodyEntry(phase[4] + 80, Y_GROUND - r, BodyKind.circStone, 0f, 0f, 20),
    		new DynamicBodyEntry(phase[3] + 80, Y_GROUND - r - 30, BodyKind.circWood, 0f, 0f, 20),
    		new DynamicBodyEntry(phase[4] + 0, Y_GROUND - r - 30, BodyKind.pig, 0f, 0f, 20),
    		new DynamicBodyEntry(phase[4] + 30, Y_GROUND - r - 30, BodyKind.pig, 0f, 0f, 20),
    		new DynamicBodyEntry(phase[4] + 60, Y_GROUND - r - 30, BodyKind.circWood, 0f, 0f, 20),
            new DynamicBodyEntry(phase[3] + 90, Y_GROUND - r - 60, BodyKind.circWood, 0f, 0f, 20),
            new DynamicBodyEntry(phase[4] + 20, Y_GROUND - r - 60, BodyKind.pig, 0f, 0f, 20),
            new DynamicBodyEntry(phase[4] + 50, Y_GROUND - r - 60, BodyKind.circWood, 0f, 0f, 20),
    		new DynamicBodyEntry(phase[4] + 0, Y_GROUND - r - 90, BodyKind.circWood, 0f, 0f, 20),
    		new DynamicBodyEntry(phase[4] + 30, Y_GROUND - r - 90, BodyKind.circWood, 0f, 0f, 20),
            new DynamicBodyEntry(phase[4] + 20, Y_GROUND - r - 120, BodyKind.circIce, 0f, 0f, 20),
    		new DynamicBodyEntry(phase[3] + 60, Y_GROUND - 10, BodyKind.rectStone, 0.0f, 0.0f, 20, 20),
            new DynamicBodyEntry(phase[5] + 0, Y_GROUND - 10, BodyKind.rectStone, 0.0f, 0.0f, 20, 20),
            new DynamicBodyEntry(phase[6], Y_GROUND - h / 2, BodyKind.rectStone, 0.0f, 0.0f, 80, 20),
    		new DynamicBodyEntry(phase[6], Y_GROUND - h / 2 - h, BodyKind.rectStone, 0.0f, 0.0f, 80, 20),
    		new DynamicBodyEntry(phase[6], Y_GROUND - h / 2 - 2 * h, BodyKind.rectStone, 0.0f, 0.0f, 80, 20),
    		new DynamicBodyEntry(phase[6], Y_GROUND - h / 2 - 3 * h, BodyKind.rectStone, 0.0f, 0.0f, 80, 20),
    		new DynamicBodyEntry(phase[6], Y_GROUND - h / 2 - 4 * h, BodyKind.rectStone, 0.0f, 0.0f, 80, 20)
    	},{
    		new DynamicBodyEntry(phase[2] + 50, Y_GROUND - r, BodyKind.pig, 0f, 0f, 20),
    		new DynamicBodyEntry(phase[2], Y_GROUND - r, BodyKind.squareWood, 0f, 0f, 50, 50),
    		new DynamicBodyEntry(phase[2], Y_GROUND - r - 50, BodyKind.rectIce, 0f, 0f, 50, 50),
    		new DynamicBodyEntry(phase[2], Y_GROUND - r - 120, BodyKind.squareWood, 0f, 0f, 50,50),
    		new DynamicBodyEntry(phase[3], Y_GROUND - r, BodyKind.squareWood, 0f, 0f, 50, 50),
    		new DynamicBodyEntry(phase[3], Y_GROUND - r - 50, BodyKind.rectIce, 0f, 0f, 50, 50),
            new DynamicBodyEntry(phase[3], Y_GROUND - r - 120, BodyKind.squareWood, 0f, 0f, 50, 50),
            new DynamicBodyEntry(phase[2] + 50, Y_GROUND - r - 110, BodyKind.pig, 0.0f, 0.0f, 20),
            new DynamicBodyEntry(phase[2], Y_GROUND - r - 70, BodyKind.rectWood, 0.0f, 0.0f, 160, 20),
            new DynamicBodyEntry(phase[2], Y_GROUND - r - 170, BodyKind.rectStone, 0.0f, 0.0f, 160, 20),
            new DynamicBodyEntry(phase[2] + 50, Y_GROUND - r - 190, BodyKind.pig, 0f, 0f, 20),
            new DynamicBodyEntry(phase[2] + 100, Y_GROUND - r - 190, BodyKind.circStone, 0f, 0f, 20),
            new DynamicBodyEntry(phase[2], Y_GROUND - r - 190, BodyKind.circStone, 0f, 0f, 20),
            new DynamicBodyEntry(phase[5], Y_GROUND - 2 * r, BodyKind.pig, 0f, 0f, 20),
            new DynamicBodyEntry(phase[5] - 40, Y_GROUND - 50, BodyKind.rectWood, 0f, 0f, 20, 60),
            new DynamicBodyEntry(phase[5] + 60, Y_GROUND - 50, BodyKind.rectWood, 0f, 0f, 20, 60),
            new DynamicBodyEntry(phase[5] - 50, Y_GROUND - 70, BodyKind.rectWood, 0f, 0f, 140, 20),
            new DynamicBodyEntry(phase[5], Y_GROUND - 100 - 2 * r, BodyKind.pig, 0f, 0f, 20),
            new DynamicBodyEntry(phase[5] - 100, Y_GROUND - 40, BodyKind.circWood, 0f, 0f, 20),
            new DynamicBodyEntry(phase[5] + 100, Y_GROUND - 40, BodyKind.circWood, 0f, 0f, 20),
            new DynamicBodyEntry(phase[5] - 40, Y_GROUND - 120, BodyKind.circIce, 0f, 0f, 20),
            new DynamicBodyEntry(phase[5] + 40, Y_GROUND - 120, BodyKind.circIce, 0f, 0f, 20),
            new DynamicBodyEntry(phase[5] - 50, Y_GROUND - 130, BodyKind.rectIce, 0f, 0f, 140, 20)
    	}
    };

    // 静止刚体数组
    // 格式: x, y, 长, 宽, 图片路径
    public static StaticBodyEntry[][] staticBodyList = new StaticBodyEntry[][]{
        {
            new StaticBodyEntry(0, Y_GROUND, W_FRAME, H_GROUND, "Image/ground.png"),
            new StaticBodyEntry(W_FRAME - 1, 0, 1, H_FRAME, ""), // 右侧墙壁
            new StaticBodyEntry(0, 0, 1, H_FRAME, ""),         // 左侧墙壁
            new StaticBodyEntry(0, 0, W_FRAME, 1, "")          // 上侧墙壁
        },{
            new StaticBodyEntry(0, Y_GROUND, W_FRAME, H_GROUND, "Image/ground.png"),
            new StaticBodyEntry(W_FRAME - 1, 0, 1, H_FRAME, ""), // 右侧墙壁
            new StaticBodyEntry(0, 0, 1, H_FRAME, ""),         // 左侧墙壁
            new StaticBodyEntry(0, 0, W_FRAME, 1, "")          // 上侧墙壁
        },{
            new StaticBodyEntry(0, Y_GROUND, W_FRAME, H_GROUND, "Image/ground.png"),
            new StaticBodyEntry(W_FRAME - 1, 0, 1, H_FRAME, ""), // 右侧墙壁
            new StaticBodyEntry(0, 0, 1, H_FRAME, ""),         // 左侧墙壁
            new StaticBodyEntry(0, 0, W_FRAME, 1, "")          // 上侧墙壁
        },{
            new StaticBodyEntry(0, Y_GROUND, W_FRAME, H_GROUND, "Image/ground.png"),
            new StaticBodyEntry(W_FRAME - 1, 0, 1, H_FRAME, ""), // 右侧墙壁
            new StaticBodyEntry(0, 0, 1, H_FRAME, ""),         // 左侧墙壁
            new StaticBodyEntry(0, 0, W_FRAME, 1, "")          // 上侧墙壁
        }
    };
}