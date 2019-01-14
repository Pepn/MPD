package com.pepn.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayList;
import java.util.List;


public class Square extends Entity
{
    private Tower tower;
    private boolean towerPlaced;
    int costToGetHere;
    List<Square> squaresToGetHere = new ArrayList<Square>();

    public int getCostToGetHere() {
        return costToGetHere;
    }

    public void setCostToGetHere(int costToGetHere) {
        this.costToGetHere = costToGetHere;
    }

    public List<Square> getSquaresToGetHere() {
        return squaresToGetHere;
    }

    public void setSquaresToGetHere(List<Square> squaresToGetHere) {
        this.squaresToGetHere = squaresToGetHere;
    }

    @Override
    public void Render(SpriteBatch sb)
    {
        sb.draw(texture, position.x, position.y);
    }

    @Override
    public void Dispose() {
        super.Dispose();
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
        towerPlaced = true;
    }

    public boolean isTowerPlaced() {
        return towerPlaced;
    }

    public void setTowerPlaced(boolean towerPlaced) {
        this.towerPlaced = towerPlaced;
    }

    public Square(int x, int y, String textureName)
    {
        super(x, y, textureName);
        tower = null;
        towerPlaced = false;
        costToGetHere = 0;
    }
    @Override
    public String toString()
    {
        return "" + this.getPosition().x / texture.getWidth() + ", " + this.getPosition().y / texture.getHeight();
    }

    @Override
    public void Update(float dt)
    {

    }
}
