/**
 * DrawThread.java
 * 绘图线程
 */

package project;

import static project.WorldConfig.*;
import static project.GlobalVaribles.*;

public class DrawThread extends Thread{

    WindowDisplay wd;       // 负责绘图的界面

    boolean stop;    // 是否停止绘制

    public DrawThread(WindowDisplay wd){
        this.wd = wd;
        this.stop = false;
    }

    @Override
    synchronized public void run(){
        // 每隔现实世界中的20ms，模拟物理世界中经过的时间并重新绘制界面
        // 在stop为true时结束绘制
        while(!stop){
            // 在paused为true时暂停绘制
            if(!paused){
                wd.myFrame.world.step(TIME_STEP, VELOCITY_ITERA, POSITION_ITERA);
                wd.repaint();
            }
            try{
                Thread.sleep(20);
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
}