/**
 * WindowDisplay.java
 * 绘图类
 */

package project;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static project.WorldConfig.*;
import static project.GlobalVaribles.*;
import static project.BodyConfig.*;
import static project.Box2dUtil.*;

public class WindowDisplay extends JPanel{

    MyFrame myFrame;    // 主界面
    DrawThread drawThread;  // 绘图线程，负责每隔一段时间执行repaint()

    int destroy_cnt = 0;

    public WindowDisplay(MyFrame myFrame){
        this.myFrame = myFrame;
        setBackground(null);
        setOpaque(false);
        destroy_cnt = 0;
        drawThread = new DrawThread(this);  // 新建绘图线程
    }

    // 开始游戏
    public void start(){
        // 设置关键全局变量并启动绘图线程
        gameOver = false;
        paused = false;
        drawThread.start();
    }

    // 停止游戏
    public void stop(){
        drawThread.stop = true; // 停止绘图线程
    }
    
    // drawBody
    // 绘制界面中的所有物体
    public synchronized void drawBody(Graphics g){

        g.drawImage(myFrame.bgnd, 0, 0, W_FRAME, H_FRAME - 150, null);  // 绘制背景

        // 绘制按钮
        if(!paused)
            g.drawImage(img_pause, PR_CENTER_X - PR_RADIUS, PR_CENTER_Y - PR_RADIUS, 2 * PR_RADIUS, 2 * PR_RADIUS, null);
        else
            g.drawImage(img_cont, PR_CENTER_X - PR_RADIUS, PR_CENTER_Y - PR_RADIUS, 2 * PR_RADIUS, 2 * PR_RADIUS, null);
        g.drawImage(img_reset, RESET_CENTER_X - RESET_RADIUS, RESET_CENTER_Y - RESET_RADIUS, 2 * RESET_RADIUS, 2 * RESET_RADIUS, null);
        g.drawImage(img_exit, EXIT_CENTER_X - EXIT_RADIUS, EXIT_CENTER_Y - EXIT_RADIUS, 2 * EXIT_RADIUS, 2 * EXIT_RADIUS, null);
        g.drawImage(img_killBird, KILLBIRD_CENTER_X - KILLBIRD_RADIUS, KILLBIRD_CENTER_Y - KILLBIRD_RADIUS, 2 * KILLBIRD_RADIUS, 2 * KILLBIRD_RADIUS, null);

        // 绘制所有非刚体图片
        for(MyImage image : myFrame.imageList){
            // 暂停时重绘，不更新坐标
            if(!paused){
                image.x += image.speed;
                if(image.x > W_FRAME)
                    image.x %= W_FRAME;
            }
            image.drawSelf(g);
        }

        // 绘制所有等待鸟
        for(MyImage image : myFrame.waitingBirdList){
            if(!paused){
                image.x += image.speed;
                if(image.x > W_FRAME)
                    image.x %= W_FRAME;
            }
            image.drawSelf(g);
        }

        // 绘制鸟
        if(myFrame.bird != null){
            // 游戏暂停时，仅绘制不删除
            if(paused)
                myFrame.bird.drawSelf(g);
            else{
                // 鸟的生命值为0时删除鸟
                if(myFrame.bird.hp <= 0){
                    new AudioPlayer("Audio/sfx/bird_destroyed.mp3").start();
                    myFrame.world.destroyBody(myFrame.bird.body); // 从世界中移除物体
                    myFrame.bird = null;
                    birdNum--;
                    // 如果有下一个鸟，则将下一个鸟放到弹弓上
                    if(!myFrame.waitingBirdList.isEmpty()){
                        myFrame.waitingBirdList.get(0).x = SLING_X + 5;
                        myFrame.waitingBirdList.get(0).y = SLING_Y;
                    }
                }
                // 鸟的速度连续多次过小时删除鸟
                else if(myFrame.bird.body.getLinearVelocity().length() < DESTROY_SPD){
                    destroy_cnt++;
                    if(destroy_cnt > DESTROY_LIMIT){
                        destroy_cnt = 0;
                        new AudioPlayer("Audio/sfx/bird_destroyed.mp3").start();
                        myFrame.world.destroyBody(myFrame.bird.body); // 从世界中移除物体
                        myFrame.bird = null;
                        birdNum--;
                        // 如果有下一个鸟，则将下一个鸟放到弹弓上
                        if(!myFrame.waitingBirdList.isEmpty()){
                            myFrame.waitingBirdList.get(0).x = SLING_X + 5;
                            myFrame.waitingBirdList.get(0).y = SLING_Y;
                        }
                    }
                    else
                        myFrame.bird.drawSelf(g);
                }
                else{
                    myFrame.bird.drawSelf(g);
                    destroy_cnt = 0;
                }
            }
        }

        Iterator<MyBody> iterator;

        // 绘制所有猪
        iterator = myFrame.pigList.iterator();
        while(iterator.hasNext()){
            MyBody myBody = iterator.next();
            // 生命值为0且游戏未暂停，则删除
            if(myBody.hp <= 0 && !paused){
                new AudioPlayer("Audio/sfx/fruit_breaking_a" + randomInt(3) + ".mp3").start();
                myFrame.world.destroyBody(myBody.body); // 从世界中移除物体
                iterator.remove();                      // 从列表中移除物体
            }
            else
                myBody.drawSelf(g);
        }

        // 绘制其他刚体
        iterator = myFrame.bodyList.iterator();
        while(iterator.hasNext()){
            MyBody myBody = iterator.next();
            // 可运动刚体生命值为0且游戏未暂停，则删除
            if(myBody.hp <= 0 && myBody.bk != BodyKind.staticBody && !paused){
                AudioPlayer player;
                if(myBody.bk == BodyKind.rectStone || myBody.bk == BodyKind.circStone)
                    player = new AudioPlayer("Audio/sfx/rock_damage_a" + randomInt(3) + ".mp3");
                else if(myBody.bk == BodyKind.rectWood || myBody.bk == BodyKind.circWood || myBody.bk == BodyKind.squareWood)
                    player = new AudioPlayer("Audio/sfx/wood_damage_a" + randomInt(3) + ".mp3");
                else
                    player = new AudioPlayer("Audio/sfx/ice_light_collision_a8.mp3");
                player.start();
                myFrame.world.destroyBody(myBody.body); // 从世界中移除物体
                iterator.remove();                      // 从列表中移除物体
            }
            else
                myBody.drawSelf(g);
        }
        
        // 绘制弹弓上的皮筋
        if(isDragged && !paused){
        	Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(4.0f));
			g2.setColor(Color.BLACK);
			g2.drawLine(handX - 5, handY + 3, SLING_X + 10, SLING_Y + 20);
			g2.drawLine(handX - 5, handY + 3, SLING_X + SLING_W - 10, SLING_Y + 20);
        }

        // 判定游戏是否结束
        if(!paused){
            if(myFrame.pigList.isEmpty() && !gameOver){
                gameOver = true;
        	    new YouWinOrLose(true, myFrame);
            }
            else if(birdNum == 0 && !gameOver){
                gameOver = true;
        	    new YouWinOrLose(false, myFrame);
            }
        }
    }

    // paintComponent
    // 调用drawBody进行绘图
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBody(g);
    }
}