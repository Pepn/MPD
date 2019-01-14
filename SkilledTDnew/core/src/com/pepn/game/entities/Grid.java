package com.pepn.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Iterator;
import java.util.LinkedList;


public class Grid {
    private int gridWidth;
    private int gridHeight;

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight()
    {
        return gridHeight;
    }

    public int getSquareWidth() {
        return squareWidth;
    }

    public int getSquareHeight() {
        return squareHeight;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSquare(int column, int row)
    {
        return squares[column][row];
    }

    private int squareWidth;
    private int squareHeight;
    private Square[][] squares;

    public LinkedList<Square> FreeNeighBourSquares(Square square)
    //public void FreeNeighBourSquares(Square square)
    {
        LinkedList<Square> tempNB = new LinkedList<Square>();
        int posX = (int)square.getPosition().x/squareWidth;
        int posY = (int)square.getPosition().y/squareHeight;

        if(posX - 1 >= 0)
            tempNB.add(squares[posX-1][posY]);
        if(posX + 2 <= gridWidth)
            tempNB.add(squares[posX+1][posY]);
        if(posY - 1 >= 0)
            tempNB.add(squares[posX][posY-1]);
        if(posY + 2 <= gridHeight)
            tempNB.add(squares[posX][posY+1]);

        //remove squares which have towers
        //Iterator<Square> i = tempNB.iterator();
       // while (i.hasNext())
        //{
         //   Square s = i.next(); // must be called before you can call i.remove()
        //    if(s.isTowerPlaced())
        //        i.remove();
        //}
        //Gdx.app.debug("Pathing", String.valueOf(posX-1 + " " + posY));
       // Gdx.app.debug("Pathing", String.valueOf(posX+1 + " " + posY));
        //Gdx.app.debug("Pathing", String.valueOf(posX + " " + (posY - 1)));
        //Gdx.app.debug("Pathing", String.valueOf(posX + " " + (posY + 1)));
        //Gdx.app.debug("Pathing", String.valueOf(tempNB.size()));

        return tempNB;
    }

    public LinkedList<Square> RemoveTowers(LinkedList<Square> l) {
        Iterator<Square> i = l.iterator();
        while (i.hasNext()) {
            Square s = i.next(); // must be called before you can call i.remove()
            if (s.isTowerPlaced())
                i.remove();
        }

        return l;
    }

    public void Render(SpriteBatch sb)
    {
        for(int y = 0; y < gridHeight; ++y)
        {
            for(int x = 0; x < gridWidth; ++x)
            {
                squares[x][y].Render(sb);
            }
        }
    }

    public Grid(int gridWidth, int gridHeight)
    {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        squares = new Square[gridWidth][gridHeight];

        squareWidth = (new Texture("square.png")).getWidth();
        squareHeight = (new Texture("square.png")).getHeight();

        for(int y = 0; y < gridHeight; ++y)
        {
            for(int x = 0; x < gridWidth; ++x)
            {
                squares[x][y] = new Square(x*squareWidth, y*squareHeight, "square.png");
            }
        }


    }

    public void Dispose()
    {
        for(int y = 0; y < gridHeight; ++y)
        {
            for(int x = 0; x < gridWidth; ++x)
            {
                squares[x][y].Dispose();
            }
        }
    }
}
