package com.sewm.defaultteam;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

/**
 * Created by stefan on 23.04.2016.
 */
public class WorldController {
    World world_;
    Player player_;
    ArrayList<Enemy> enemies_;
    ArrayList<Target> targets_;
    Vector3 touchpoint_;

    public WorldController(World world){
        world_ = world;
        player_ = world_.getPlayer_();
        enemies_ = world_.getEnemies_();
        targets_ = world_.getTargets_();
        touchpoint_ = new Vector3();
    }

    public void update(Vector2 new_pos){
        player_.update(new_pos);
        updateEnemy();
        updateTarget();

    }
    public void updateEnemy(){
        for(Enemy enemy : enemies_){
            enemy.update(new Vector2(player_.surrounding_.x, player_.surrounding_.y));
        }
    }



    public void updateTarget() {
        for(Target target : targets_){
            target.update(new Vector2(player_.surrounding_.x,player_.surrounding_.y)); //needs to be changed
        }
    }
}
