package com.pepn.game.controller;

import com.pepn.game.entities.Grid;
import com.pepn.game.entities.Mob;
import com.pepn.game.entities.Square;

import java.util.ArrayList;
import java.util.List;


public class MobSpawner
{
    private List<Mob> mobs;
    private float timerNextMob;
    private float timeBetweenMobs;
    private Grid grid;
    private Square spawnSquare;
    private Square destinationSquare;
    private int mobHP;

    public MobSpawner(List<Mob> mobs, Grid grid, Square spawnSquare, Square destinationSquare)
    {
        this.grid = grid;
        this.mobs = mobs;
        this.spawnSquare = spawnSquare;
        this.destinationSquare = destinationSquare;
        timerNextMob = 0;
        timeBetweenMobs = 1.0f;
        mobHP = 1;
    }

    public void Update(float dt)
    {
        timerNextMob += dt;
        if(timerNextMob > timeBetweenMobs)
        {
            timerNextMob = 0;
            mobs.add(new Mob(spawnSquare, "bird.png", destinationSquare, grid, mobHP));
            mobHP++;
        }
    }
}
