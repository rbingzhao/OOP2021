/**
 * MyContactListener.java
 * 碰撞监听器，减少双方生命值
 */

package project;

import org.jbox2d.callbacks.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

import static project.WorldConfig.*;

public class MyContactListener implements ContactListener{

    // 在碰撞的一瞬间减少生命值
    @Override
    public void beginContact(Contact contact){

        // 获得对应的刚体
        Body b1 = contact.getFixtureA().getBody();
        Body b2 = contact.getFixtureB().getBody();

        // 获得对应的包装类
        MyBody mb1 = (MyBody)b1.getUserData();
        MyBody mb2 = (MyBody)b2.getUserData();
        
        // 获得双方的速度
        Vec2 v1 = b1.getLinearVelocity();
        Vec2 v2 = b2.getLinearVelocity();

        // 一方减少的生命值 = 另一方的质量 * 相对速度的平方 * HP_BY_SPEED
        float temp = (float)Math.pow(v1.sub(v2).length(), 2);
        int dec_hp_1 = (int)(temp * b1.getMass() * HP_BY_SPEED);
        int dec_hp_2 = (int)(temp * b2.getMass() * HP_BY_SPEED);

        // 只减少运动刚体的生命值
        if(mb1.dors == DorS.Dynamic)
            mb1.hp -= dec_hp_2;
        if(mb2.dors == DorS.Dynamic)
            mb2.hp -= dec_hp_1;
    }

    @Override
    public void endContact(Contact arg0){}

    @Override
    public void postSolve(Contact arg0, ContactImpulse arg1){}

    @Override
    public void preSolve(Contact arg0, Manifold arg1){}
}