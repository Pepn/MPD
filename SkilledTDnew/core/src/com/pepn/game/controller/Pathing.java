package com.pepn.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pepn.game.entities.Grid;
import com.pepn.game.entities.Square;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//public class SquareComparator implements Comparator<Square>
//{
//    @Override
//    public int compare(Square s1, Square s2)
//    {
//        if(s1.getCostToGetHere() == s2.getCostToGetHere())
//            return 0;
//        if(s1.getCostToGetHere() < s2.getCostToGetHere())
//            return 1;
//        else return -1;
//    }
//}

public class Pathing
{
    public class SquareAndPreviousSquare
    {
        public Square square;
        public SquareAndPreviousSquare previousSquare;

        public SquareAndPreviousSquare(Square s1, SquareAndPreviousSquare s2)
        {
            square = s1;
            previousSquare = s2;
        }
        @Override
        public String toString()
        {
            if(previousSquare != null)
                return "" + square.toString() + " " + previousSquare.square.toString();
            else
                return "" + square.toString() + " NULL";
        }


    }

    Queue<SquareAndPreviousSquare> fringe;
    Grid grid;
    public LinkedList<Square> returnList;
    public Pathing(Grid grid)
    {
        this.grid = grid;
        returnList = new LinkedList<Square>();
        possiblePath = false;
    }

    boolean possiblePath;
    public boolean getPossiblePath() { return possiblePath;}
    public Grid getGrid() { return grid;}


    public void SearchPath(Square beginSquare, Square destinationSquare)
    {
        returnList.clear();
        fringe = new LinkedList<SquareAndPreviousSquare>();
        SquareAndPreviousSquare temp = new SquareAndPreviousSquare(beginSquare, null);
        fringe.add(temp);
        Set<Square> visited = new HashSet<Square>();

        while(!fringe.isEmpty())
        {
            SquareAndPreviousSquare node = fringe.remove();

            if(node.square == destinationSquare)
            {
                //Gdx.app.debug("Pathing", "end node found: " + destinationSquare.toString());
                //Gdx.app.debug("Pathing", node.square.toString());
                while(node.previousSquare != null)
                {
                    returnList.add(node.square);
                    //node.square.setTexture(new Texture("pathfindingsquare.png"));

                    node = node.previousSquare;
                }
                Collections.reverse(returnList);
                //Gdx.app.debug("Pathing", "Size: " + returnList.size());
                possiblePath = true;
                return;
            }

            if(!visited.contains(node.square))
            {
                visited.add(node.square);

                for(Square s : grid.RemoveTowers(grid.FreeNeighBourSquares(node.square)))
                {
                    //Gdx.app.debug("Pathing", s.toString());
                    fringe.add(new SquareAndPreviousSquare(s, node));
                    //Gdx.app.debug("Pathing", "adding nodes " + s.toString() + ", " + node.toString());
                }
            }
        }

        //Gdx.app.debug("Pathing", "NO PATH POSSIBLE");
        possiblePath = false;
    }

    //when checking for neighbouring squares it doesnt remove squares with towers only difference nice code LOL
    public void SearchPathIgnoreTowers(Square beginSquare, Square destinationSquare)
    {
        returnList.clear();
        fringe = new LinkedList<SquareAndPreviousSquare>();
        SquareAndPreviousSquare temp = new SquareAndPreviousSquare(beginSquare, null);
        fringe.add(temp);
        Set<Square> visited = new HashSet<Square>();

        while(!fringe.isEmpty())
        {
            SquareAndPreviousSquare node = fringe.remove();

            if(node.square == destinationSquare)
            {
                //Gdx.app.debug("Pathing", "end node found: " + destinationSquare.toString());
                //Gdx.app.debug("Pathing", node.square.toString());
                while(node.previousSquare != null)
                {
                    returnList.add(node.square);
                    node.square.setTexture(new Texture("pathfindingsquare.png"));

                    node = node.previousSquare;
                }
                Collections.reverse(returnList);
                //Gdx.app.debug("Pathing", "Size: " + returnList.size());
                possiblePath = true;
                return;
            }

            if(!visited.contains(node.square))
            {
                visited.add(node.square);

                for(Square s : (grid.FreeNeighBourSquares(node.square)))
                {
                    //Gdx.app.debug("Pathing", s.toString());
                    fringe.add(new SquareAndPreviousSquare(s, node));
                    //Gdx.app.debug("Pathing", "adding nodes " + s.toString() + ", " + node.toString());
                }
            }
        }

        Gdx.app.debug("Pathing", "NO PATH POSSIBLE");
        possiblePath = false;
    }
}

