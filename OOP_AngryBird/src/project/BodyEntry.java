/**
 * BodyEntry.java
 * 定义BodyEntry条目的信息
 */

package project;

// DynamicBodyEntry
// 运动刚体配置类
class DynamicBodyEntry{

    int x, y;       // 左上角坐标
    int w, h, r;    // 长、宽、半径（具体使用哪些量，取决于刚体种类）
    float vx, vy;   // 初速度向量
    BodyKind bk;    // 刚体种类

    // 构造方形刚体配置
    public DynamicBodyEntry(int x, int y, BodyKind bk, float vx, float vy, int w, int h){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.w = w;
        this.h = h;
        this.bk = bk;
    }

    // 构造圆形刚体配置
    public DynamicBodyEntry(int x, int y, BodyKind bk, float vx, float vy, int r){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.r = r;
        this.bk = bk;
    }
}

// StaticBodyEntry
// 静止刚体配置类
class StaticBodyEntry{

    int x, y;   // 左上角坐标
    int w, h;   // 长、宽

    String img_dir; // 图片路径

    StaticBodyEntry(int x, int y, int w, int h, String img_dir){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img_dir = img_dir;
    }
}

// ImageEntry
// 非刚体图片配置类
class ImageEntry{

    int x, y;   // 左上角坐标
    int w, h;   // 长、宽

    int move_speed; // 运动速度

    String img_dir; // 图片路径

    ImageEntry(int x, int y, int w, int h, String img_dir, int move_speed){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img_dir = img_dir;
        this.move_speed = move_speed;
    }
}