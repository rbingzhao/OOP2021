/**
 * MyFrame.java
 * 主界面的抽象类
 */

package project;

import javax.swing.JFrame;
import java.util.List;
import java.awt.*;

import org.jbox2d.dynamics.World;

public abstract class MyFrame extends JFrame{

	public WindowDisplay wd;	// 绘图对象
	public World world;			// 物理世界
	public List<MyBody> bodyList;	// 刚体列表
	public List<MyBody> pigList;	// 猪列表
	public List<MyImage> imageList;	// 非刚体图片列表
	public List<MyImage> waitingBirdList;	// 等待鸟列表
	public MyBody bird;
	public Image bgnd;	// 背景图片

	public AudioPlayer bgmPlayer;
	public AudioPlayer beginPlayer;

	protected abstract void setFrame(int levelNum);
	protected abstract void init();
	
	public abstract void start();
	public abstract void stop();
	public abstract void addDynamicBody(DynamicBodyEntry[] lst);
	public abstract void addStaticBody(StaticBodyEntry[] lst);
	public abstract void addImage(ImageEntry[] lst);
	public abstract void addWaitingBird(ImageEntry[] lst);
}