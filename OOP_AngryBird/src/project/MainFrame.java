/**
 * MainFrame.java
 * 游戏主界面
 */

package project;

import java.util.*;

import org.jbox2d.dynamics.World;

import static project.Box2dUtil.*;
import static project.WorldConfig.*;
import static project.BodyConfig.*;
import static project.BodyInfoList.*;
import static project.GlobalVaribles.*;

public class MainFrame extends MyFrame{

    // 构造函数，开始第levelNum关
    public MainFrame(int levelNum){

        init(); // 初始化
        birdNum = 5;
        
        // 添加各个物体
    	addDynamicBody(BodyConfig.dynamicBodyList[levelNum]);
        addStaticBody(BodyConfig.staticBodyList[levelNum]);
        addImage(BodyConfig.imageList[levelNum]);
        addWaitingBird(BodyConfig.waitingBirdList[levelNum]);

        bgnd = bgndList[levelNum];  // 设置背景

        // 设置鼠标监听器
        addMouseListener(new GameController(this));
        addMouseMotionListener(new GameController(this));

        setFrame(levelNum); // 设置界面
    }

    // 开始游戏
    @Override
    public void start(){
        bgmPlayer.start();
        beginPlayer.start();
        wd.start();
    }

    // 结束游戏
    @Override
    public void stop(){
        bgmPlayer.close();
        beginPlayer.close();
        wd.stop();
    }

    // addDynamicBody
    // 根据运动刚体配置数组构造运动刚体，并添加到世界中
    @Override
    public void addDynamicBody(DynamicBodyEntry[] allDynamicBody){
        for(DynamicBodyEntry be : allDynamicBody){
            // 圆形刚体，需要讨论是否为猪
            if(be.bk == BodyKind.circStone || be.bk == BodyKind.circWood 
                    || be.bk == BodyKind.circIce || be.bk == BodyKind.pig){
                BodyInfo currInfo = getInfoFromKind(be.bk);
                MyCircleBody circleBody = createCircleBody(be.x, be.y, world, currInfo, be.vx, be.vy, be.r);
                if(be.bk == BodyKind.pig)
                    pigList.add(circleBody);
                else
                    bodyList.add(circleBody);
            }
            // 长方形刚体
            else if(be.bk == BodyKind.rectStone || be.bk == BodyKind.rectWood || be.bk == BodyKind.rectIce
            		|| be.bk == BodyKind.squareWood){
                BodyInfo currInfo = getInfoFromKind(be.bk);
                MyRectangleBody rectangleBody = createRectangleBody(be.x, be.y, world, currInfo, be.vx, be.vy, be.w, be.h);
                bodyList.add(rectangleBody);
            }
        }
    }

    // addStaticBody
    // 根据静止刚体配置数组构造静止刚体，并添加到世界中
    @Override
    public void addStaticBody(StaticBodyEntry[] allStaticBody){
        for(StaticBodyEntry be : allStaticBody){
            MyStaticBody staticBody = createStaticBody(be.x, be.y, world, be.w, be.h, be.img_dir);
            bodyList.add(staticBody);
        }
    }

    // addImage
    // 根据非刚体图片配置数组构造图片，并添加到世界中
    @Override
    public void addImage(ImageEntry[] allImage){
        for(ImageEntry ie : allImage){
            MyImage imageBody = new MyImage(ie);
            imageList.add(imageBody);
        }
    }

    // addWaitingBird
    // 根据等待鸟配置数组构造图片，并添加到世界中
    @Override
    public void addWaitingBird(ImageEntry[] allWaitingBird){
        for(ImageEntry ie : allWaitingBird){
            MyImage imageBody = new MyImage(ie);
            waitingBirdList.add(imageBody);
        }
    }

    // 初始化函数
    @Override
    protected void init(){

        // 初始化物体列表
        bodyList = new ArrayList<MyBody>();
        pigList = new ArrayList<MyBody>();
        imageList = new ArrayList<MyImage>();
        waitingBirdList = new ArrayList<MyImage>();
        bird = null;

        bgmPlayer = new AudioPlayer("Audio/music/ambience_jungle.mp3");
        beginPlayer = new AudioPlayer("Audio/sfx/level_start_military_a" + randomInt(2) + ".mp3");

        // 构造物理世界
        world = new World(GRAVITY);
        world.setContactListener(new MyContactListener());  // 设置碰撞监听器

        wd = new WindowDisplay(this);   // 构造绘图对象
    }

    // 设置界面
    @Override
    protected void setFrame(int levelNum){
        setTitle("Level " + (levelNum + 1));
        setBounds(0, 0, W_FRAME, H_FRAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(this.wd);
        setVisible(true);
    }
}