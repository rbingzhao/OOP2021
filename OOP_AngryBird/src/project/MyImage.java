/**
 * MyImage.java
 * 非刚体图片类
 */

package project;

import javax.swing.ImageIcon;
import java.awt.*;

public class MyImage{
    
    Image image;

    int x, y;   // 左上角坐标
    int w, h;   // 长、宽

    int speed;  // 运动速度

    MyImage(ImageEntry entry){
        this.x = entry.x;
        this.y = entry.y;
        this.w = entry.w;
        this.h = entry.h;
        this.speed = entry.move_speed;
        this.image = new ImageIcon(entry.img_dir).getImage();
    }

    public void drawSelf(Graphics canvas){
        canvas.drawImage(image, x, y, w, h, null);
    }
}