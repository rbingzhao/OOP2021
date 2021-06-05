/**
 * BodyInfoList.java
 * 配置世界中物体的参数
 */

package project;

import java.awt.*;
import javax.swing.ImageIcon;

// 刚体种类
enum BodyKind{
    rectStone, rectWood, rectIce, circStone, circWood, circIce, pig, bird, squareWood, staticBody
};

// BodyInfoList
// 刚体信息列表类
public class BodyInfoList{
    
    // 格式：密度，摩擦系数，能量保持率，高生命值图片路径，低生命值图片路径，生命值，生命值高/低阈值（换图片），刚体种类
    public static BodyInfo rectStoneInfo = new BodyInfo(2.0f, 1f, 0.01f, "Image/stoneRect1.png", "Image/stoneRect4.png", 300, 150, BodyKind.rectStone);
    public static BodyInfo rectWoodInfo = new BodyInfo(0.5f, 1f, 0.01f, "Image/woodRect1.png", "Image/woodRect4.png", 200, 100, BodyKind.rectWood);
    public static BodyInfo rectIceInfo = new BodyInfo(0.9f, 0.2f, 0.01f, "Image/iceRect1.png", "Image/iceRect4.png", 100, 50, BodyKind.rectIce);
    public static BodyInfo circStoneInfo = new BodyInfo(2.0f, 1f, 0.01f, "Image/stoneCircle1.png", "Image/stoneCircle4.png", 300, 150, BodyKind.circStone);
    public static BodyInfo circWoodInfo = new BodyInfo(0.5f, 1f, 0.01f, "Image/woodCircle1.png", "Image/woodCircle4.png", 200, 100, BodyKind.circWood);
    public static BodyInfo circIceInfo = new BodyInfo(0.9f, 0.2f, 0.01f, "Image/iceCircle1.png", "Image/iceCircle4.png", 100, 50, BodyKind.circIce);
    public static BodyInfo squareWoodInfo = new BodyInfo(0.5f, 1f, 0.01f, "Image/woodSquare.png", "Image/woodSquare.png", 200, 100, BodyKind.squareWood);
    public static BodyInfo pigInfo = new BodyInfo(1.0f, 0.3f, 0.3f, "Image/pig1.png", "Image/pig2.png", 200, 100, BodyKind.pig);
    public static BodyInfo birdInfo = new BodyInfo(1.0f, 0.3f, 0.3f, "Image/bird1.png", "Image/bird4.png", 10000, 9000, BodyKind.bird);
    public static BodyInfo staticBodyInfo = new BodyInfo(1.0f, 0.5f, 0.05f, "", "", 1, 1, BodyKind.staticBody);  // 仅用前三个参数

    // getInfoFromKind
    // 根据BodyKind返回对应的BodyInfo对象
    public static BodyInfo getInfoFromKind(BodyKind bk){
        switch(bk){
            case rectStone:
                return rectStoneInfo;
            case rectWood:
                return rectWoodInfo;
            case rectIce:
                return rectIceInfo;
            case circStone:
                return circStoneInfo;
            case circWood:
                return circWoodInfo;
            case circIce:
                return circIceInfo;
            case pig:
                return pigInfo;
            case bird:
                return birdInfo;
            case squareWood:
                return squareWoodInfo;
            default:
                return staticBodyInfo;
        }
    }
}

// BodyInfo
// 刚体信息类
class BodyInfo{

    float density;      // 密度
    float friction;     // 摩擦系数
    float restitution;  // 能量保持率

    int hp;             // 生命值
    int hp_divide;      // 生命值高/低阈值

    Image img_highHp;   // 高生命值图片
    Image img_lowHp;    // 低生命值图片

    BodyKind bk;

    public BodyInfo(float density, float friction, float restitution, String imgHigh_dir, String imgLow_dir, int hp, int hp_divide, BodyKind bk){
        this.density = density;
        this.friction = friction;
        this.restitution = restitution;
        this.img_highHp = new ImageIcon(imgHigh_dir).getImage();
        this.img_lowHp = new ImageIcon(imgLow_dir).getImage();
        this.hp = hp;
        this.hp_divide = hp_divide;
        this.bk = bk;
    }
}