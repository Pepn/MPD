package com.pepn.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Bullet extends Entity
{
    protected Entity targetEntity;
    protected double angle;
    public Bullet(int x, int y, String textureName, Entity targetEntity)
    {
        super(x, y, textureName);
        this.targetEntity = targetEntity;
        angle = calcAngle();
    }

    @Override
    public void Update(float dt)
    {

        angle = calcAngle();

        velocity = new Vector2((float)Math.cos(angle) * 80, (float)Math.sin(angle) * 80);
        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1/dt);
    }

    @Override
    public void Render(SpriteBatch sb)
    {
        sb.draw(texture, position.x, position.y);
    }

    @Override
    public void Dispose()
    {
        super.Dispose();
    }

    public double calcAngle()
    {
        double dX = targetEntity.getCenterPosition().x - this.getCenterPosition().x;
        double dY = targetEntity.getCenterPosition().y - this.getCenterPosition().y;
        return Math.atan2(dY, dX);
    }

    public boolean IsCollision()
    {
        if(calcDistance(targetEntity) < 2)
            return true;
        return false;
    }
}
