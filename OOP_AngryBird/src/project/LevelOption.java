/**
 * LevelOption.java
 * 关卡选择菜单
 */

package project;

import javax.swing.*;

import static project.WorldConfig.*;
import static project.GlobalVaribles.*;

public class LevelOption extends JFrame{

	JLayeredPane layeredPane;  
    // 创建一个Panel和一个Label用于存放图片，作为背景。  
    JPanel jp;  
    JLabel jl;  
    ImageIcon image;

    // 创建按钮 
    JButton[] levelbut = new JButton[levelNum];

    AudioPlayer bgmPlayer;
    
    public LevelOption(){  

        layeredPane = new JLayeredPane();  
        image = new ImageIcon("Image/background1.jpg");

        bgmPlayer = new AudioPlayer("Audio/music/ab_theme_rio_boss_lite5.mp3");

        // 创建背景
        jp = new JPanel();
        jp.setBounds(0, 0, W_FRAME, H_FRAME);  
        jl = new JLabel(image);  
        jp.add(jl);  

        // 创建按钮
        for(int i = 0; i < levelbut.length; i++){
			levelbut[i] = new JButton("Level" + (i + 1));
			levelbut[i].setBounds(W_FRAME / 2 - 80, 200 + 100 * i, 160, 30);
			levelbut[i].setVisible(true);
		}
        
		for(int i = 0; i < levelbut.length; i++){
			levelbut[i].addActionListener(e -> {
                String name = ((JButton)e.getSource()).getText();
				char[] c = name.toCharArray();
                levelNow = c[5] - '1';
                bgmPlayer.close();
				MyFrame myFrame = new MainFrame(levelNow);
                dispose();
                myFrame.start();
            });
			layeredPane.add(levelbut[i], JLayeredPane.MODAL_LAYER);
		}
		
        // 将jp放到最底层。                      
        layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);  
        
        this.setTitle("Welcome to AngryBird");
        this.setLayeredPane(layeredPane);
        this.setSize(W_FRAME, H_FRAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0, 0);
        this.setVisible(true);

        bgmPlayer.start();
    }
}