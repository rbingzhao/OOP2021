/**
 * WorldConfig.java
 * 设定世界的各个参数
 */

package project;

import org.jbox2d.common.Vec2;

public class WorldConfig{

    public static final int RATE = 8;   // 真实世界与物理世界的比例
    public static final float TIME_STEP = 4.0f / 60.0f; // 物理世界的时间流逝速度
    public static final int POSITION_ITERA = 10;
    public static final int VELOCITY_ITERA = 3;
    
    public static final float HP_BY_SPEED = 0.05f;   // 生命值减少量与动能的比例

    public static final int W_FRAME = 1200; // 界面宽度
    public static final int H_FRAME = 800;  // 界面高度

    public static final Vec2 GRAVITY = new Vec2(0.0f, 9.8f);    // 重力向量
    
    public static final int SLING_W = 60;   // 弹弓宽度
    public static final int SLING_H = 120;  // 弹弓高度
    public static final int SLING_X = 200;  // 弹弓x坐标
    public static final int SLING_Y = 450;  // 弹弓y坐标
    public static final float SLING_L = 80f;    // 弹弓的最长长度
    public static final float SLING_A = 25f;    // 开始拖动的范围
    public static final float HAND_BOTTOM_LIMIT = 30f;   // 弹弓释放时的最短长度

    public static final int H_GROUND = 225; // 地面高度
    public static final int Y_GROUND = 575; // 地面y坐标

    public static final int DESTROY_LIMIT = 30;
    public static final float DESTROY_SPD = 1f; // 连续DESTROY_LIMIT次速度小于DESTROY_SPD则删除鸟

    // 暂停/继续按钮的位置和半径
    public static final int PR_CENTER_X = 200;
    public static final int PR_CENTER_Y = 50;
    public static final int PR_RADIUS = 30;

    // 重置按钮的位置和半径
    public static final int RESET_CENTER_X = 125;
    public static final int RESET_CENTER_Y = 50;
    public static final int RESET_RADIUS = 30;

    // 退出按钮的位置和半径
    public static final int EXIT_CENTER_X = 50;
    public static final int EXIT_CENTER_Y = 50;
    public static final int EXIT_RADIUS = 30;

    // 删除当前鸟按钮的位置和半径
    public static final int KILLBIRD_CENTER_X = 50;
    public static final int KILLBIRD_CENTER_Y = 400;
    public static final int KILLBIRD_RADIUS = 26;
}