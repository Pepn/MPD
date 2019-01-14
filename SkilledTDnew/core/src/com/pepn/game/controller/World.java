package com.pepn.game.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pepn.game.SkilledTD;
import com.pepn.game.entities.Grid;
import com.pepn.game.entities.Mob;
import com.pepn.game.entities.Tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sun.rmi.runtime.Log;

public class World {
    private Grid grid;
    private PlaceTowerController PTC;
    private List<Mob> mobs = new ArrayList<Mob>();
    private MobSpawner mobSpawner;
    private List<Tower> towers = new ArrayList<Tower>();
    private Camera cam;
    private int score;
    private int platformBuild;
    public World(Camera cam) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        grid = new Grid(15, 23);
        PTC = new PlaceTowerController(grid);
        this.cam = cam;
        //temp
        mobSpawner = new MobSpawner(mobs, grid, grid.getSquare(7,22), grid.getSquare(7, 0));
        TempPrePlacedTowers();
        score = 0;
        platformBuild = SkilledTD.ih.GetPlatformNumber();

    }

    private Vector3 getMousePosInGameWorld() {
        return cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }

    public void TempPrePlacedTowers()
    {
        for(int y = 1; y < 2; ++y)
        for(int x = 5; x < 10; ++x)
        {
            Tower t = new Tower(x*grid.getSquareWidth(), y*grid.getSquareHeight(),x,y, "tower.png");
            if(grid.getSquare(x, y).getTower() == null)
            {
                towers.add(t);
                grid.getSquare(x, y).setTower(t);
            }
        }
    }

    public void HandleInput() {

        if(platformBuild == 1) //desktop could be used to differentiate between input methods unneccessary now
        {
            if (SkilledTD.ih.CheckInput(0)) //we just using the difference in platform build decided by the interface
            {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
                cam.unproject(mousePos); // mousePos is now in world coordinates
                PTC.PlaceTowerMouseSelection(towers, (int)mousePos.x, (int)mousePos.y);

            }
        }
        else if(platformBuild == 2) //android
        {
            if (SkilledTD.ih.CheckInput(0)) {
                if (PTC.isHorizontalSelected() && !PTC.isVerticalSelected()) {
                    PTC.setVerticalSelected(true);
                }

                if (!PTC.isHorizontalSelected()) {
                    PTC.setHorizontalSelected(true);
                }
            }
        }


            //mobs.add(new Mob((int)getMousePosInGameWorld().x, (int)getMousePosInGameWorld().y, "bird.png", grid.getSquares()[10][10]));
            //mobs.add(new Mob(grid.getSquares()[0][0], "bird.png", grid.getSquares()[10][10], grid));
        //}
    }

    public void Update(float dt) {
        PTC.Update(dt);
        if (PTC.PlaceTower(towers)) {
            for(Mob m : mobs)
                m.getPathing().SearchPath(m.getTempDest(), m.getDestinationSquare());
        }
        mobSpawner.Update(dt);
        for(Tower t : towers) {
            t.Update(dt);
        }

        for (Tower t : towers) {
            t.stopShoot();
            for (Mob m : mobs) {
                t.Shoot(m);
            }
        }

        Iterator itr1 = towers.iterator();
        Tower t;
        while (itr1.hasNext()) {
            t = (Tower) itr1.next();
            t.Update(dt);

            if (t.getIsDestroyed()) {
                //Gdx.app.debug("Pathing", "destroying iterator");
                itr1.remove();
                break;
            }
        }

        Iterator itr = mobs.iterator();
        Mob b;
        while (itr.hasNext())
        {
            b = (Mob) itr.next();
            b.Update(dt);
            if (b.IsAtGoalSquare())
            {
                if (!towers.isEmpty())
                    towers.remove(towers.size() - 1);
            }
            if (b.getIsDestroyed())
            {
                itr.remove();
                score++;
                //Gdx.app.debug("score", ("" + getScore()));
                break;
            }
        }
    }

    public boolean GoToScoreState()
    {
        if(towers.isEmpty())
            return true;

        return false;
    }

    public void Render(SpriteBatch sb) {

        sb.begin();
        //sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        grid.Render(sb);

        for(Tower t : towers)
        {
            t.Render(sb);
        }
        for(Tower t : towers)
            t.RenderBullets(sb);

        for(Mob m : mobs)
            m.Render(sb);
        sb.end();
    }

    public void Dispose()
    {
        grid.Dispose();
        for(Tower t : towers)
        {
            t.Dispose();
        }
        for(Mob m : mobs)
            m.Dispose();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
