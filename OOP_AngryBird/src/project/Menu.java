/**
 * Menu.java
 * 主菜单界面
 */

package project;

import javax.swing.*;

import static project.WorldConfig.*;

public class Menu extends JFrame{

	JLayeredPane layeredPane;  
    // 创建一个Panel和一个Label用于存放图片，作为背景。  
    JPanel jp;  
    JLabel jl;  
    ImageIcon image;  

    // 创建开始游戏和帮助按钮 
    JButton beginBut;
	JButton helpBut;
    JDialog help;
    JTextArea textArea;

    AudioPlayer bgmPlayer;

    public Menu(){

        layeredPane = new JLayeredPane();  
        image = new ImageIcon("Image/background1.jpg");

        bgmPlayer = new AudioPlayer("Audio/music/menu_bgm.mp3");

        // 创建背景
        jp = new JPanel();
        jp.setBounds(0, 0, W_FRAME, H_FRAME);  

        jl = new JLabel(image); 
        jp.add(jl);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.append("玩法: 发射小鸟，消灭所有小猪获得胜利！\n\n");
        
        help = new JDialog(this);
        help.getContentPane().add(textArea);
		help.setModal(true);
		help.setSize(480, 320);

        // 创建按钮  
        beginBut = new JButton("开始游戏");
		beginBut.setBounds(W_FRAME / 2 - 80, H_FRAME / 2, 160, 30);
		beginBut.addActionListener(e -> {
            bgmPlayer.close();
            new LevelOption();
			dispose();
        });
		
		helpBut = new JButton("帮助");
		helpBut.setBounds(W_FRAME / 2 - 80, 5 * H_FRAME / 8, 160, 30);
		helpBut.addActionListener(e -> {
			help.setVisible(true);
        });
		
        // 将jp放到最底层
        layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
        // 将jb放到高一层的地方
        layeredPane.add(beginBut, JLayeredPane.MODAL_LAYER);
        layeredPane.add(helpBut, JLayeredPane.MODAL_LAYER);

        this.setTitle("Welcome to AngryBirds");
        this.setLayeredPane(layeredPane);
        this.setSize(W_FRAME, H_FRAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0, 0);
        this.setVisible(true);

        bgmPlayer.start();
    }
}
