/**
 * YouWinOrLose.java
 * 游戏结束界面
 */

package project;

import java.awt.*;
import javax.swing.*;

import static project.WorldConfig.*;
import static project.GlobalVaribles.*;
import static project.Box2dUtil.*;

public class YouWinOrLose extends JFrame{

	JButton btnExitGame;
	JButton btnGotoLevel;
	JButton btnNextOrAgain;
	JLabel emptyLabel;
	Container ct;

	AudioPlayer player;

	public YouWinOrLose(Boolean isWin, MyFrame myFrame){
		
		ct = this.getContentPane();
        this.setLayout(null);
		
        setTitle("游戏结束");
		setBounds(0, 0, W_FRAME / 2, H_FRAME / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		if(isWin)
			player = new AudioPlayer("Audio/sfx/level_clear_military_a" + randomInt(2) + ".mp3");
		else
			player = new AudioPlayer("Audio/sfx/level_failed_birds.mp3");
		player.start();

		// 游戏结果
		if(isWin)
			emptyLabel = new JLabel("恭喜你！你赢了");
		else
			emptyLabel = new JLabel("很遗憾，你输了");
		emptyLabel.setBounds(240, 50, 300, 100);
		ct.add(emptyLabel);
		
		// 关卡列表按钮及监听器
		btnGotoLevel = new JButton("关卡列表");
		btnGotoLevel.setBounds(250,250,80,30);
		btnGotoLevel.addActionListener(e -> {
			// 关闭游戏界面和当前界面，打开选关界面
			myFrame.stop();
			new LevelOption();
			myFrame.dispose();
			player.close();
			dispose();
		});
		ct.add(btnGotoLevel);
		
		// 退出游戏按钮及监听器
		btnExitGame = new JButton("退出");
		btnExitGame.setBounds(350, 250, 80, 30);
		btnExitGame.addActionListener(e -> {
			// 关闭游戏界面和当前界面，退出程序
			myFrame.stop();
			myFrame.dispose();
			player.close();
			dispose();
			System.exit(0);
		});
		ct.add(btnExitGame);
		
		// 最左边的按钮
		if(isWin && levelNow != levelNum - 1)
			btnNextOrAgain = new JButton("下一关");		// 赢，不是最后一关
		else if(isWin && levelNow == levelNum - 1)
			btnNextOrAgain = new JButton("主菜单");		// 赢，是最后一关
		else
			btnNextOrAgain = new JButton("重新开始");	// 输

		btnNextOrAgain.setBounds(150,250,80,30);
		btnNextOrAgain.addActionListener(e -> {
			// 下一关
			if(isWin && levelNow != levelNum - 1){
				levelNow++;
				myFrame.stop();
				MyFrame newFrame = new MainFrame(levelNow);
				myFrame.dispose();
				player.close();
				dispose();
				newFrame.start();
			}
			// 主菜单
			else if(isWin && levelNow == levelNum - 1){
				myFrame.stop();
				new Menu();
				myFrame.dispose();
				player.close();
				dispose();
			}
			// 重新开始
			else if(!isWin){
				myFrame.stop();
				MyFrame newFrame = new MainFrame(levelNow);
				myFrame.dispose();
				player.close();
				dispose();
				newFrame.start();
			}
		});
		ct.add(btnNextOrAgain);
		
		setVisible(true);
	}
}