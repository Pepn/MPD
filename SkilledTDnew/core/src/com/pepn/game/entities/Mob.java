package com.pepn.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pepn.game.controller.Pathing;
import com.pepn.game.controller.PlaceTowerController;


public class Mob extends Entity
{
    Square destinationSquare;
    Square tempDest;
    Square currentSquare;
    Pathing pathing;

    public Pathing getPathing() {return pathing;}
    public Square getCurrentSquare() {return currentSquare;}
    public Square getTempDest() {return tempDest;}
    public Square getDestinationSquare() {return destinationSquare;}

    public Mob(int x, int y, String textureName, Square destinationSquare, Grid grid)
    {
        super(x, y, textureName);
        this.destinationSquare = destinationSquare;

    }

    public Mob(Square currentSquare, String textureName, Square destinationSquare, Grid grid, int hp)
    {
        super((int)currentSquare.getCenterPosition().x, (int)currentSquare.getCenterPosition().y, textureName);
        this.destinationSquare = destinationSquare;
        tempDest = currentSquare;
        this.currentSquare = currentSquare;
        pathing = new Pathing(grid);
        pathing.SearchPath(currentSquare, destinationSquare);
        this.hp = hp;
        if(!pathing.getPossiblePath())
        {
            pathing.SearchPathIgnoreTowers(currentSquare, destinationSquare);
        }
    }

    @Override
    public void Render(SpriteBatch sb)
    {
        sb.draw(texture, position.x, position.y);
    }

    public boolean IsAtGoalSquare()
    {
        if(calcDistance(destinationSquare) < 2 )
        {
            isDestroyed = true;
            return true;
        }

        return false;
    }

    @Override
    public void Update(float dt)
    {

        if(hp <= 0)
        {
            isDestroyed = true;
        }

        if(this.calcDistance(tempDest) < 2)
        {
            //if there is a towerplaced in the currentpath calc new path
            if(!pathing.returnList.isEmpty())
            {
                Square t = pathing.returnList.getFirst();
                if(t.isTowerPlaced())
                {
                    pathing.SearchPath(tempDest, destinationSquare);
                    if(!pathing.getPossiblePath())
                    {
                        //Gdx.app.debug("Pathing", "destroying tower");
                        t.setTowerPlaced(false);
                        t.getTower().setIsDestroyed(true);
                        pathing.SearchPath(tempDest, destinationSquare);
                    }

                }
            }

            if(!pathing.returnList.isEmpty())
                tempDest = pathing.returnList.remove();
        }


        //Move(tempDest);

        //velocity.x = (float)(30 * Math.cos(calcRotation(tempDest)));
       // velocity.y = (float)(30 * Math.sin(calcRotation(tempDest)));
        //velocity.scl(dt);
       // position.x += velocity.x;
        //position.y += velocity.y;

        velocity = vectorTo(tempDest).nor();
        velocity.scl(60);
        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1/dt);
    }

    public void Move(Square destinationSquare){
        velocity.x = destinationSquare.getCenterPosition().x - getCenterPosition().x;
        velocity.y = destinationSquare.getCenterPosition().y - getCenterPosition().y;
    }
}
