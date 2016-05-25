package com.sewm.defaultteam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by stefan on 22.04.2016.
 */
public class World {
    static public Player player_;
    public EnemyAttribute enemy_easy_ = new EnemyAttribute(1, 3.f, 4, 1);
    public EnemyAttribute enemy_medium_ = new EnemyAttribute(8, 3.f, 13, 2);
    public EnemyAttribute enemy_hard_ = new EnemyAttribute(13, 3.f, 8, 3);
    ArrayList<GameEntity> entities_;
    ArrayList<Enemy> inactive_enemies_;
    int target_count_;
    float time_;


    int width_;
    int height_;

    public ArrayList<GameEntity> getEntities_() {
        return entities_;
    }



    public Player getPlayer_() {
        return player_;
    }

    public World(){


        player_ = new Player();
        entities_ = new ArrayList<GameEntity>();
        inactive_enemies_ = new ArrayList<Enemy>();
        time_ = 0.f;


        width_ = Gdx.graphics.getWidth();
        height_ = Gdx.graphics.getHeight();
    }

    public int getHeight_() {
        return height_;
    }

    public int getWidth_() {
        return width_;
    }

    public void parseLevelFile(String filename) throws IOException
    {
            Parser parser = new Parser(filename);

            parser.parseTextures();

            player_ = parser.parsePlayer();

            Target target;
            while((target = parser.parseTarget()) != null) {
                entities_.add(target);
            }

            Enemy enemy;
            while((enemy = parser.parseEnemy(this)) != null) {
                entities_.add(enemy);
            }

            //TODO: implement Actionpoints
            /*
            Actionpoint actionpoint;
            while((actionpoint = parser.parseActionpoint())) {
                entities_.add(actionpoint);
            }
            */
    }


    public void loadTestLevel() {
        WorldRenderer.entities_textures.put("images/player_new.png", new Texture(Gdx.files.internal("images/player_new.png")));
        WorldRenderer.entities_textures.put("images/target.png", new Texture(Gdx.files.internal("images/target.png")));
        WorldRenderer.entities_textures.put("images/target_health_1.png", new Texture(Gdx.files.internal("images/target_health_1.png")));
        WorldRenderer.entities_textures.put("images/target_health_2.png", new Texture(Gdx.files.internal("images/target_health_2.png")));
        WorldRenderer.entities_textures.put("images/target_health_3.png", new Texture(Gdx.files.internal("images/target_health_3.png")));
        WorldRenderer.entities_textures.put("images/enemy_health_1.png", new Texture(Gdx.files.internal("images/enemy_health_1.png")));
        WorldRenderer.entities_textures.put("images/enemy_health_2.png", new Texture(Gdx.files.internal("images/enemy_health_2.png")));
        WorldRenderer.entities_textures.put("images/enemy_health_3.png", new Texture(Gdx.files.internal("images/enemy_health_3.png")));
        WorldRenderer.entities_textures.put("images/enemy_medium_health_1.png", new Texture(Gdx.files.internal("images/enemy_medium_health_1.png")));
        WorldRenderer.entities_textures.put("images/enemy_medium_health_2.png", new Texture(Gdx.files.internal("images/enemy_medium_health_2.png")));
        WorldRenderer.entities_textures.put("images/enemy_medium_health_3.png", new Texture(Gdx.files.internal("images/enemy_medium_health_3.png")));
        WorldRenderer.entities_textures.put("images/enemy_strong_health_1.png", new Texture(Gdx.files.internal("images/enemy_strong_health_1.png")));
        WorldRenderer.entities_textures.put("images/enemy_strong_health_2.png", new Texture(Gdx.files.internal("images/enemy_strong_health_2.png")));
        WorldRenderer.entities_textures.put("images/enemy_strong_health_3.png", new Texture(Gdx.files.internal("images/enemy_strong_health_3.png")));
        WorldRenderer.entities_textures.put("images/enemy_static_1.png", new Texture(Gdx.files.internal("images/enemy_static_1.png")));
        WorldRenderer.entities_textures.put("images/enemy_static_2.png", new Texture(Gdx.files.internal("images/enemy_static_2.png")));
        WorldRenderer.entities_textures.put("images/enemy_static_3.png", new Texture(Gdx.files.internal("images/enemy_static_3.png")));
        WorldRenderer.entities_textures.put("images/enemy_static_medium_1.png", new Texture(Gdx.files.internal("images/enemy_static_medium_1.png")));
        WorldRenderer.entities_textures.put("images/enemy_static_medium_2.png", new Texture(Gdx.files.internal("images/enemy_static_medium_2.png")));
        WorldRenderer.entities_textures.put("images/enemy_static_medium_3.png", new Texture(Gdx.files.internal("images/enemy_static_medium_3.png")));
        WorldRenderer.entities_textures.put("images/enemy_static_strong_1.png", new Texture(Gdx.files.internal("images/enemy_static_strong_1.png")));
        WorldRenderer.entities_textures.put("images/enemy_static_strong_2.png", new Texture(Gdx.files.internal("images/enemy_static_strong_2.png")));
        WorldRenderer.entities_textures.put("images/enemy_static_strong_3.png", new Texture(Gdx.files.internal("images/enemy_static_strong_3.png")));
        WorldRenderer.entities_textures.put("images/action_point.png", new Texture(Gdx.files.internal("images/action_point.png")));
        WorldRenderer.entities_textures.put("images/action_point_active.png", new Texture(Gdx.files.internal("images/action_point_active.png")));

        ArrayList<String> enemy_easy_textures = new ArrayList<String>();
        enemy_easy_textures.add("images/enemy_health_3.png");
        enemy_easy_textures.add("images/enemy_health_2.png");
        enemy_easy_textures.add("images/enemy_health_1.png");

        ArrayList<String> enemy_medium_textures = new ArrayList<String>();
        enemy_medium_textures.add("images/enemy_medium_health_3.png");
        enemy_medium_textures.add("images/enemy_medium_health_2.png");
        enemy_medium_textures.add("images/enemy_medium_health_1.png");

        ArrayList<String> enemy_hard_textures = new ArrayList<String>();
        enemy_hard_textures.add("images/enemy_strong_health_3.png");
        enemy_hard_textures.add("images/enemy_strong_health_2.png");
        enemy_hard_textures.add("images/enemy_strong_health_1.png");

        ArrayList<String> enemy_static_hard_textures = new ArrayList<String>();
        enemy_static_hard_textures.add("images/enemy_static_strong_3.png");
        enemy_static_hard_textures.add("images/enemy_static_strong_2.png");
        enemy_static_hard_textures.add("images/enemy_static_strong_1.png");

        ArrayList<String> enemy_static_medium_textures = new ArrayList<String>();
        enemy_static_medium_textures.add("images/enemy_static_medium_3.png");
        enemy_static_medium_textures.add("images/enemy_static_medium_2.png");
        enemy_static_medium_textures.add("images/enemy_static_medium_1.png");

        ArrayList<String> enemy_static_easy_textures = new ArrayList<String>();
        enemy_static_easy_textures.add("images/enemy_static_3.png");
        enemy_static_easy_textures.add("images/enemy_static_2.png");
        enemy_static_easy_textures.add("images/enemy_static_1.png");

        ArrayList<String> target_textures = new ArrayList<String>();
        target_textures.add("images/target_health_1.png");
        target_textures.add("images/target_health_2.png");
        target_textures.add("images/target_health_3.png");

        player_ = new Player();
        target_count_++;
        entities_.add(new Target(500,100,10,3,target_textures));

        inactive_enemies_.add(new NormalEnemy(800,300,enemy_easy_,1,1,enemy_easy_textures, 1));
        inactive_enemies_.add(new NormalEnemy(800,300,enemy_medium_,2,2,enemy_medium_textures, 3));
        inactive_enemies_.add(new NormalEnemy(800,300,enemy_hard_,3,3,enemy_hard_textures, 5));
        inactive_enemies_.add(new StaticEnemy(enemy_hard_,3,3,enemy_static_hard_textures, new Vector2(100, 100), new Vector2(500, 600), 8));
        inactive_enemies_.add(new StaticEnemy(enemy_medium_,3,3,enemy_static_medium_textures, new Vector2(500, 100), new Vector2(500, 600), 8));
        // entities_.add(new StaticEnemy(enemy_easy_,3,3,enemy_easy_textures, new Vector2(300, 100), new Vector2(700, 600)));
        ChainAP f1 = new ChainAP(400,400,true,0);
        ChainAP f2 = new ChainAP(500,450,false,0);
        ChainAP f3 = new ChainAP(800,400,false,0);
        f1.setFirst_(f1);
        f1.setNext(f2);
        f2.setFirst_(f1);
        f2.setNext(f3);
        f3.setFirst_(f1);
        f3.setNext(f3);
        entities_.add(f1);
        entities_.add(f2);
        entities_.add(f3);

    }

    public void decreaseTargetCount() {
        if(target_count_ > 1)
        {
            target_count_--;
        }
        else
        {
            target_count_ = 0;
            byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);

            Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
            BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
            WinScreen winscreen = new WinScreen();
            winscreen.background_ = new Texture(pixmap);
            StartPoint.startPoint_.setScreen(winscreen);
        }
    }
}
