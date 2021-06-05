/**
 * Box2dUtil.java
 * 提供构造刚体的方法
 */

package project;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import static project.WorldConfig.*;
import static project.BodyInfoList.*;

public class Box2dUtil{

    // 构造圆形刚体
    public static MyCircleBody createCircleBody(int x, int y, World world, BodyInfo info, float vx, float vy, int r){

        // 构造刚体定义
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((x + r) / RATE, (y + r) / RATE);
        bodyDef.setType(BodyType.DYNAMIC);
        
        // 用刚体定义在世界中构造刚体
        Body bodyTemp = world.createBody(bodyDef);
        bodyTemp.setLinearVelocity(new Vec2(vx, vy));

        // 为刚体设置形状和各个参数
        CircleShape circle = new CircleShape();
        circle.setRadius(r / RATE);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.setShape(circle);
        fixtureDef.setDensity(info.density);
        fixtureDef.setFriction(info.friction);
        fixtureDef.setRestitution(info.restitution);
        bodyTemp.createFixture(fixtureDef);

        // 构造MyCircleBody包装类对象，并在Body中添加所属的MyCircleBody对象的信息
        MyCircleBody myCircleBody = new MyCircleBody(bodyTemp, info, r);
        bodyTemp.setUserData(myCircleBody);

        return myCircleBody;
    }

    // 构造长方形刚体
    public static MyRectangleBody createRectangleBody(int x, int y, World world, BodyInfo info, float vx, float vy, int w, int h){

        // 构造刚体定义
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((x + w / 2) / RATE, (y + h / 2) / RATE);
        bodyDef.setType(BodyType.DYNAMIC);

        // 用刚体定义在世界中构造刚体
        Body bodyTemp = world.createBody(bodyDef);
        bodyTemp.setLinearVelocity(new Vec2(vx, vy));

        // 为刚体设置形状和各个参数
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox((w / 2) / RATE, (h / 2) / RATE);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.setShape(polygon);
        fixtureDef.setDensity(info.density);
        fixtureDef.setFriction(info.friction);
        fixtureDef.setRestitution(info.restitution);
        bodyTemp.createFixture(fixtureDef);

        // 构造MyRectangleBody包装类对象，并在Body中添加所属的MyCircleBody对象的信息
        MyRectangleBody myRectangleBody = new MyRectangleBody(bodyTemp, info, w, h);
        bodyTemp.setUserData(myRectangleBody);

        return myRectangleBody;
    }

    // 构造静止刚体（只能为长方形）
    public static MyStaticBody createStaticBody(int x, int y, World world, int w, int h, String img_dir){

        // 构造刚体定义
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((x + w / 2) / RATE, (y + h / 2) / RATE);
        bodyDef.setType(BodyType.STATIC);

        // 用刚体定义在世界中构造刚体
        Body bodyTemp = world.createBody(bodyDef);
        bodyTemp.setLinearVelocity(new Vec2(0, 0));

        // 为刚体设置形状和各个参数
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox((w / 2) / RATE, (h / 2) / RATE);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.setShape(polygon);
        fixtureDef.setDensity(staticBodyInfo.density);
        fixtureDef.setFriction(staticBodyInfo.friction);
        fixtureDef.setRestitution(staticBodyInfo.restitution);
        bodyTemp.createFixture(fixtureDef);

        // 构造MyStaticBody包装类对象，并在Body中添加所属的MyCircleBody对象的信息
        MyStaticBody myStaticBody = new MyStaticBody(bodyTemp, w, h, img_dir);
        bodyTemp.setUserData(myStaticBody);

        return myStaticBody;
    }

    // 创建鸟类刚体
    //! 只允许通过此方法创建鸟，不允许通过Config列表
    public static MyCircleBody createBird(int x, int y, World world, float vx, float vy, int r){

        return createCircleBody(x, y, world, birdInfo, vx, vy, r);
    }

    // 随机播放音效时使用
    public static int randomInt(int upper_limit){

        return 1 + (int)Math.floor(Math.random() * upper_limit);
    }
}