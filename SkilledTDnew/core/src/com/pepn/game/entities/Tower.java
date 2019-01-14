package com.pepn.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Tower extends Entity
{
    protected int attackRange;
    protected int attackRate;
    protected float attackTimer;
    protected int attackDamage;
    protected Entity targetEntity;
    protected boolean isShooting;
    protected Vector3 gridPosition;

    List<Bullet> bullets = new ArrayList<Bullet>();

    public Tower(int x, int y, int gridX, int gridY, String textureName)
    {
        super(x, y, textureName);
        targetEntity = null;
        attackRange = 100;
        attackRate = 1;
        attackTimer = 1;
        attackDamage = 1;
        isShooting = false;
        gridPosition = new Vector3();
        gridPosition.x = gridX;
        gridPosition.y = gridY;
    }

    @Override
    public void Update(float dt)
    {
        attackTimer += dt;
        if(isShooting && attackTimer > attackRate)
        {
            bullets.add(new Bullet((int)getCenterPosition().x, (int)getCenterPosition().y, "bullet.png", targetEntity));
            attackTimer = 0;
        }

            Iterator itr = bullets.iterator();
            Bullet b;
            while(itr.hasNext())
            {
                b = (Bullet)itr.next();
                b.Update(dt);

                if(b.IsCollision())
                {
                    b.targetEntity.hp--;
                    itr.remove();
                }
            }

        if(targetEntity != null && targetEntity.getIsDestroyed())
        {
            targetEntity = null;
            isShooting = false;
        }
    }
    public void stopShoot()
    {
        if(isShooting)
        {
            if(calcDistance(targetEntity) > attackRange)
            {
                this.targetEntity = null;
                isShooting = false;
            }
        }
    }
    public void Shoot(Entity targetEntity)
    {
        if(!isShooting)
        {
            if(calcDistance(targetEntity)< attackRange)
            {
                this.targetEntity = targetEntity;
                isShooting = true;
            }
        }

    }

    @Override
    public void Render(SpriteBatch sb)
    {
        sb.draw(texture, position.x, position.y);
    }

    public void RenderBullets(SpriteBatch sb)
    {
        for(Bullet b : bullets)
        {
            b.Render(sb);
        }
    }

    @Override
    public void Dispose()
    {
        super.Dispose();
        for(Bullet b : bullets)
            b.Dispose();
    }

    public Vector3 getGridPosition()
    {
        return gridPosition;
    }
}
