package com.pepn.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public abstract class Entity {

    protected Vector2 position;
    protected Vector2 velocity;
    protected float rotation;
    protected Texture texture;
    protected boolean isDestroyed;
    protected int hp;

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public boolean getIsDestroyed(){return isDestroyed;}
    public void setIsDestroyed(boolean b) {isDestroyed = b;}
    public int getHp(){return hp;}
    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getCenterPosition()
    {
        return new Vector2(position.x + (texture.getWidth()/2), position.y + (texture.getHeight()/2));
    }

    public Entity(int x, int y, String textureName)
    {
        position = new Vector2(x, y);
        velocity = new Vector2(0,0);
        texture  = new Texture(textureName);
        rotation = 0;
        isDestroyed = false;
    }

    public double calcDistance(Entity targetEntity)
    {
        float dX = Math.abs(this.getCenterPosition().x - targetEntity.getCenterPosition().x);
        float dY = Math.abs(this.getCenterPosition().y - targetEntity.getCenterPosition().y);
        return Math.sqrt((dX*dX) + (dY*dY));
    }

    public Vector2 vectorTo(Entity targetEntity)
    {
        float dX = this.getCenterPosition().x - targetEntity.getCenterPosition().x;
        float dY = this.getCenterPosition().y - targetEntity.getCenterPosition().y;
        return new Vector2(dX, dY).scl(-1);
    }

    public double calcRotation(Entity targetEntity)
    {
        float dX = this.getCenterPosition().x - targetEntity.getCenterPosition().x;
        float dY = this.getCenterPosition().y - targetEntity.getCenterPosition().y;
        return Math.atan2(dY, dX) * (180/Math.PI);
    }


    public abstract void Update(float dt);
    public abstract void Render(SpriteBatch sb);
    public void Dispose()
    {
        texture.dispose();
    }

}
