package com.sewm.defaultteam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.FileNotFoundException;

/**
 * Created by stefan on 22.04.2016.
 */
public class Player {
    Circle surrounding_;
    Color color;
    static int score_ = 0;
    Texture texture_;

    public float getHealth_() {
        return health_;
    }

    float health_;

    static boolean get_damaged_;

    public Player()
    {
        surrounding_ = new Circle(Gdx.graphics.getWidth()/2.f, Gdx.graphics.getHeight()/2.f, 50.f);
        color = new Color(Color.LIGHT_GRAY);
        health_ = 3.f;
        get_damaged_ = false;
        texture_ = new Texture(Gdx.files.internal("images/player_new.png"));
    }

    public Player(int x, int y, int health, String texture)
    {
        surrounding_ = new Circle(new Float(x), new Float(y), 50.f);
        color = new Color(Color.LIGHT_GRAY);
        health_ = new Float(health);
        get_damaged_ = false;
        try {
            texture_ = new Texture(Gdx.files.internal(texture));
        } catch ( Exception e) {
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));
        }
    }

    public void update(Vector2 new_pos){

        surrounding_.setPosition(Utils.getOriginOfRect(new_pos, surrounding_));

    }

    public void decreaseHealth(float value){
        health_ -= value;
    }

    public Texture getTexture()
    {
        return texture_;
    }

    public Rectangle getRect(){
        Rectangle rect = new Rectangle(surrounding_.x - surrounding_.radius,
                                       surrounding_.y - surrounding_.radius,
                                       surrounding_.radius * 2,
                                       surrounding_.radius * 2);
        return rect;
    }


}
