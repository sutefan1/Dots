package com.sewm.defaultteam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.regexp.internal.RE;


/**
 * Created by stefan on 22.04.2016.
 */
public class Enemy extends GameEntity {

    public Enemy (){
        body_ = new Rectangle(20.f, 50.f, 20.f ,20.f);
        speed_base_ = 1;
        lives_ = 3;
        color_ = new Color(Color.GREEN);
        target_pos_ = new Vector2(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f);
        velocity_ = new Vector2(0,0);
        inertia_ = 1;
    }

    public Enemy ( Vector2 pos, int speed, int lives, int inertia){
        body_ = new Rectangle(pos.x, pos.y, 20.f ,20.f);
        speed_base_ = speed;
        lives_ = lives;
        color_ = new Color(Color.GREEN);
        target_pos_ = new Vector2(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f);
        velocity_ = new Vector2(0,0);
        inertia_ = inertia;
    }

    public Enemy(Vector2 pos, int difficulty){
        switch (difficulty){
            case 1:
                body_ = new Rectangle(pos.x, pos.y, 20.f, 20.f);
                speed_base_ = 1;
                lives_ = 1;
                color_ = new Color(Color.RED);
                target_pos_ = new Vector2(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f);
                velocity_ = new Vector2(0,0);
                inertia_ = 3;
                break;
            case 2:
                body_ = new Rectangle(pos.x, pos.y, 20.f, 20.f);
                speed_base_ = 2;
                lives_ = 2;
                color_ = new Color(Color.RED);
                target_pos_ = new Vector2(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f);
                velocity_ = new Vector2(0,0);
                inertia_ = 2;
                break;
            case 3:
                body_ = new Rectangle(pos.x, pos.y, 20.f, 20.f);
                speed_base_ = 3;
                lives_ = 3;
                color_ = new Color(Color.RED);
                target_pos_ = new Vector2(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f);
                velocity_ = new Vector2(0,0);
                inertia_ = 1;
                break;
            default:
                body_ = new Rectangle(pos.x, pos.y, 20.f, 20.f);
                speed_base_ = 1;
                lives_ = 1;
                color_ = new Color(Color.RED);
                target_pos_ = new Vector2(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f);
                velocity_ = new Vector2(0,0);
                inertia_ = 3;
                break;



        }

    }
    @Override
    protected void updateTarget(Vector2 target_pos){
        target_pos_.x = target_pos.x;
        target_pos_.y = target_pos.y;
    }
    @Override
    public void updatePosition(){
       Rectangle rect_body = (Rectangle)body_;
        Vector2 direction = rect_body.getPosition(new Vector2());
        //get direction to target(player)
        direction.x = target_pos_.x - direction.x;
        direction.y = target_pos_.y - direction.y;
        //calculate unit vector to just get the direction with length 1
        direction = Utils.getUnitVector(direction);
        //set length from diretion to speed
        direction.x *= (speed_base_ * 10) * Gdx.graphics.getDeltaTime();
        direction.y *= (speed_base_ * 10) * Gdx.graphics.getDeltaTime();
        //modify old velocity
        velocity_.x += (direction.x * 2);
        velocity_.y += (direction.y * 2);
        if(velocity_.len() > (5.f * inertia_))
        {
            velocity_ = Utils.getUnitVector(velocity_);
            velocity_.x *= 4.5f * inertia_;
            velocity_.y *= 4.5f * inertia_;
        }
        //create new pos for enemy
        Vector2 new_pos = rect_body.getPosition(new Vector2());
        

        new_pos.x += velocity_.x;
        new_pos.y += velocity_.y;

        rect_body.setPosition(new_pos);
    }

    @Override
    protected void onContact() {

    }


}
