/**
 * GlobalVaribles.java
 * 记录全局变量
 */

package project;

public class GlobalVaribles{

    public static int levelNow = 0; // 当前是第几关
    public static int levelNum = 4; // 关卡总数

    public static int handX;    // 鼠标x坐标
    public static int handY;    // 鼠标y坐标
    public static float handL;  // 鼠标到弹弓的距离
    public static boolean isDragged = false;    // 是否正在拖动鼠标

    public static boolean gameOver = false; // 游戏是否结束
    public static boolean paused = false;   // 游戏是否暂停
    
    public static int birdNum = 5;  // 当前剩余鸟的个数
}