/**
 * AudioPlayer.java
 * 播放音效
 */

package project;

import java.io.*;

import javazoom.jl.player.*;
import javazoom.jl.decoder.*;

public class AudioPlayer extends Thread{

    Player player;

    // 构造方法
    // 载入音频，构建player
    AudioPlayer(String dir){
        try{
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File(dir)));
            player = new Player(input);
        }catch(FileNotFoundException fnfex){
            fnfex.printStackTrace();
        }catch(JavaLayerException jlex){
            jlex.printStackTrace();
        }
    }

    // run
    // 调用player.play()
    @Override
    public void run(){
        super.run();
        try{
            player.play();
        }catch(JavaLayerException jlex){
            jlex.printStackTrace();
        }
    }

    // close
    // 停止播放
    public void close(){
        player.close();
    }
}