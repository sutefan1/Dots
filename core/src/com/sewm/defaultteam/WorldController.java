package com.sewm.defaultteam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by stefan on 23.04.2016.
 */
public class WorldController {
    World world_;
    Player player_;
    ArrayList<GameEntity> entities_;
    Vector3 touchpoint_;

    public WorldController(World world){
        world_ = world;
        player_ = world_.getPlayer_();
        entities_ = world_.getEntities_();
        touchpoint_ = new Vector3();
    }

    public void update(Vector2 new_pos){
        if (GameScreen.is_touched)
        {
            player_.update(new_pos);
        }
        world_.time_ += Gdx.graphics.getDeltaTime();
        for (Iterator<Enemy> enemy = world_.inactive_enemies_.iterator(); enemy.hasNext();){
            Enemy current = enemy.next();
            if((float)(current.spawn_time_) <= world_.time_)
            {
                entities_.add(current);
                enemy.remove();
            }
        }
        updateEntities();
        cleanUp();

    }

    public void updateEntities() {
        for (GameEntity entity : entities_)
        {
            entity.update(new Vector2(player_.surrounding_.x, player_.surrounding_.y));
        }
    }

    private void cleanUp() {
        for (Iterator<GameEntity> entity = entities_.iterator(); entity.hasNext();)
        {
            if (!entity.next().isAlive_())
            {
                System.out.println("Something is dead..");
                entity.remove();
            }

        }

    }


    public void updateScore(int delta)
    {
        Player.score_ += delta;
        GameScreen.worldRenderer_.updateScore(Player.score_);
    }
}
