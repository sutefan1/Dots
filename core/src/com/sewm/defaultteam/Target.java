package com.sewm.defaultteam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.graphics.Color;


/**
 * Created by Lisa on 27.04.2016.
 */
public class Target extends GameEntity {

    public Target()
    {
        body_ = new Circle(50.f,50.f,Gdx.graphics.getWidth() / 80.f);
        speed_base_ = 1;
        health_ = 3;
        color_ = new Color(Color.ORANGE);
        target_pos_ = new Vector2(0,0);
        velocity_ = new Vector2(0,0);
        inertia_ = 0;
        texture_ = WorldRenderer.target_texture_;
        points_ = 1;
        points_on_death_ = 3;
    }


    public Target(Shape2D body, int speed_base, int lives, Vector2 velocity, int inertia,
                  int points, int points_on_death)
    {
        body_ = body;
        speed_base_ = speed_base;
        health_ = lives;
        color_ = new Color(Color.ORANGE);
        target_pos_ = new Vector2(0,0);
        velocity_ = velocity;
        inertia_ = inertia;
        texture_ = WorldRenderer.target_texture_;
        points_ = points;
        points_on_death_ = points_on_death;
    }

    @Override
    protected void updateTarget(Vector2 target_pos) {
        Circle body = new Circle((Circle) body_);
        body.radius *= 4f;
        //System.out.println("Radius is: " + body.radius);
        if (body.contains(target_pos))
        {
            onContact();
        }
    }

    @Override
    protected void updatePosition() {

    }

    private void jump()
    {
        Vector2 new_pos = new Vector2(Utils.random_.nextInt(Gdx.graphics.getWidth()), Utils.random_.nextInt(Gdx.graphics.getHeight()));
        ((Circle)body_).setPosition(new_pos);
    }

    @Override
    protected void onContact() {
        float value = Gdx.graphics.getDeltaTime();
        System.out.println("Target contact");

        if (value < health_)
        {
            float health_old = health_;
            health_-= value;

            if (Math.ceil(health_old) > Math.ceil(health_))
            {
                Player.score_ += points_;
                jump();
            }

        }
        else
        {
            health_ = 0;
            alive_ = false;
            System.out.println("Target is dead");
            Player.score_ += points_on_death_;
        }

        /*if(value > health_left_){
            health_ -= 1.f;

            if(health_ <= 0.f){
                setAlive_(false);
                Player.score_ += points_on_death_;
            }
            else{
                health_left_ = 1.f;
                Vector2 new_pos = new Vector2(Utils.random_.nextInt(Gdx.graphics.getWidth()), Utils.random_.nextInt(Gdx.graphics.getHeight()));
                ((Circle)body_).setPosition(new_pos);
                Player.score_ += points_;
            }
        }
        else{
            health_left_ -= value;
        }*/
        System.out.println("Health = " + health_);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        Circle circle = (Circle) body_;
        spriteBatch.draw(texture_,circle.x,circle.y);
    }

    @Override
    public void drawDebug(ShapeRenderer debugRenderer) {
        Circle body = (Circle) body_;
        Circle circle = new Circle(body.x + texture_.getWidth() / 2, body.y +texture_.getHeight()/2, texture_.getWidth()/2 + 10.f);
        debugRenderer.setColor(new Color(Color.BROWN));
        debugRenderer.circle(circle.x, circle.y,circle.radius);
    }

    public void decreaseHealth(float value){
        if(health_ > value)
            health_ -= value;
        else
            setAlive_(false);
    }

    public Rectangle getRect(){
        Vector2 pos = Utils.getOriginOfRect(new Vector2(((Circle)body_).x, ((Circle)body_).y), ((Circle)body_));
        Rectangle rect = new Rectangle(pos.x, pos.y, ((Circle)body_).radius * 2.f, ((Circle)body_).radius * 2.f);
        return rect;
    }

}
