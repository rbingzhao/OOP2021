/**
 * GameController.java
 * 鼠标动作监听器
 */

package project;

import java.awt.EventQueue;
import java.awt.event.*;

import static project.WorldConfig.*;
import static project.Box2dUtil.*;
import static project.GlobalVaribles.*;

public class GameController implements MouseListener, MouseMotionListener{

	public MyFrame myFrame;		// 主界面

    public GameController(MyFrame myFrame){
        this.myFrame = myFrame;
    }

	// 鼠标拖动监听器
	@Override
	public void mouseDragged(MouseEvent e){

		// 无等待鸟、当前有鸟或游戏已经结束时不接受拖动
		if(myFrame.waitingBirdList.isEmpty())
			return;
		if(myFrame.bird != null)
			return;
		if(gameOver || paused)
			return;

		// 鼠标坐标
        int myhandX = e.getX();
		int myhandY = e.getY() - 30;
		
		// 拖动距离和x、y方向相对弹弓的偏移量
        float myHandL = getDistance(myhandX, myhandY, SLING_X + 30, SLING_Y + 20);
        int deltaX = myhandX - SLING_X - 30;
		int deltaY = myhandY - SLING_Y - 20;
		
		// 在弹弓周围SLING_L的范围内首次拖动或之前正在拖动时，响应拖动
		// 鸟被限制在弹弓周围SLING_L的范围内
		if(myHandL < SLING_A || isDragged){
            if(myHandL > SLING_L){
                handX = (int)(SLING_X + 30 + (float)deltaX * SLING_L / myHandL);
                handY = (int)(SLING_Y + 20 + (float)deltaY * SLING_L / myHandL);
                handL = SLING_L;
            }
            else{
            	handX = myhandX;
            	handY = myhandY;
            	handL = myHandL;
			}
			if(!isDragged){
				isDragged = true;
				new AudioPlayer("Audio/sfx/slingshot_streched.mp3").start();
			}
			// 更新鸟的位置
            myFrame.waitingBirdList.get(0).x = handX - 30;
            myFrame.waitingBirdList.get(0).y = handY - 20;
        }
	}

	// 鼠标按键松开监听器
	@Override
	public void mouseReleased(MouseEvent e){

		// 无等待鸟、当前有鸟或游戏已经结束时不接受拖动
		if(myFrame.waitingBirdList.isEmpty())
			return;
		if(myFrame.bird != null)
			return;
		if(gameOver || paused)
			return;

		// 计算鸟的初速度
		int deltaX = SLING_X + (SLING_W / 2) - handX;
        int deltaY = SLING_Y - handY;
        float vx = (float)0.33 * deltaX;
		float vy = (float)0.33 * deltaY;
		
		float myHandL = getDistance(handX, handY, SLING_X + 30, SLING_Y + 20);

		// 当前正在拖动时，若鸟拖出的距离大于HAND_BOTTOM_LIMIT，则创建鸟
		if(isDragged){
			if(myHandL > HAND_BOTTOM_LIMIT){
				myFrame.waitingBirdList.remove(0);	// 删除等待鸟列表的第一个元素
				myFrame.bird = createBird(handX - 30, handY, myFrame.world, vx, vy, 20);
				new AudioPlayer("Audio/sfx/bird_shot-a" + randomInt(3) + ".mp3").start();
				new AudioPlayer("Audio/sfx/bird_0" + randomInt(6) + "_flying.mp3").start();
			}
			else{
				myFrame.waitingBirdList.get(0).x = SLING_X + 5;
                myFrame.waitingBirdList.get(0).y = SLING_Y;
			}
			isDragged = false;
		}
	}

	// 按键点击监听器
	@Override
	public void mouseClicked(MouseEvent e){

		// 游戏已经结束时，按键无效
		if(gameOver)
			return;

		// 鼠标坐标
		int handX = e.getX();
		int handY = e.getY() - 20;
		
		// 暂停/继续按钮
		if((int)getDistance(handX, handY, PR_CENTER_X, PR_CENTER_Y) <= PR_RADIUS){
			if(paused)
				paused = false;
			else{
				paused = true;
				myFrame.wd.repaint();	// 刷新按钮样式
			}
		}
		// 重置按钮
		else if((int)getDistance(handX, handY, RESET_CENTER_X, RESET_CENTER_Y) <= RESET_RADIUS){
			// 停止当前界面，创建新界面
			myFrame.stop();
			MyFrame newFrame = new MainFrame(levelNow);
			myFrame.dispose();
			newFrame.start();
		}
		// 菜单按钮
		else if((int)getDistance(handX, handY, EXIT_CENTER_X, EXIT_CENTER_Y) <= EXIT_RADIUS){
			// 停止当前界面，创建选关界面
			myFrame.stop();
			new LevelOption();
			myFrame.dispose();
		}
		// 清除当前鸟按钮
		else if((int)getDistance(handX, handY, KILLBIRD_CENTER_X, KILLBIRD_CENTER_Y) <= KILLBIRD_RADIUS && !paused){
			// 只在有鸟时操作
			if(myFrame.bird != null){
				new AudioPlayer("Audio/sfx/bird_destroyed.mp3").start();
				myFrame.world.destroyBody(myFrame.bird.body);	// 从世界中移除物体
            	myFrame.bird = null;
            	birdNum--;
            	// 如果有下一个鸟，则将下一个鸟放到弹弓上
            	if(!myFrame.waitingBirdList.isEmpty()){
            	    myFrame.waitingBirdList.get(0).x = SLING_X + 5;
            	    myFrame.waitingBirdList.get(0).y = SLING_Y;
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e){}

	@Override
	public void mousePressed(MouseEvent e){}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}

	// getDistance
	// 计算两点之间的距离
    public float getDistance(int x1, int y1, int x2, int y2){
        return (float)Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}