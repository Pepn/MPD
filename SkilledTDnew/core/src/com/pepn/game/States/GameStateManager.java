package com.pepn.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager
{
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void Push(State state)
    {
        states.push(state);
    }

    public void Pop()
    {
        states.pop();
    }

    public void Set(State state)
    {
        states.pop();
        states.push(state);
    }

    public void Update(float dt)
    {
        states.peek().Update(dt);
    }

    public void Render(SpriteBatch sb)
    {
        states.peek().Render(sb);
    }
}
