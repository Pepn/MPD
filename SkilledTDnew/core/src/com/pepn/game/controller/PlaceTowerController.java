package com.pepn.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pepn.game.entities.Grid;
import com.pepn.game.entities.Square;
import com.pepn.game.entities.Tower;

import java.util.ArrayList;
import java.util.List;


public class PlaceTowerController {
    private Grid grid;
    private int column;
    private int row;
    private Square[] horizontalSelectionBox;
    private Square[] verticalSelectionBox;
    private float timer;
    private int horBounce;
    private int verBounce;
    private float maxTimeToNextSelection;
    boolean verticalSelected;
    boolean horizontalSelected;
    boolean mouseSelection;
    private Square selectedSquare;
    //List<Tower> towers = new ArrayList<Tower>();

    public PlaceTowerController(Grid grid)
    {
        column = 0;
        row = 0;
        this.grid = grid;
        timer = 0;
        horBounce = 1;
        verBounce = 1;
        verticalSelected = false;
        horizontalSelected = false;
        maxTimeToNextSelection = 0.2f;
        horizontalSelectionBox = new Square[grid.getGridWidth()];
        verticalSelectionBox = new Square[grid.getGridHeight()];
        selectedSquare = null;
        mouseSelection = false;
    }

    public Square getSelectedSquare() {
        return selectedSquare;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setSelectedSquare(Square selectedSquare) {
        this.selectedSquare = selectedSquare;
    }

    public void PlaceTowerMouseSelection(List<Tower> towers, int x, int y)
    {
        Tower t = new Tower(x/grid.getSquareWidth()*grid.getSquareWidth(), y/grid.getSquareHeight()*grid.getSquareWidth(), x/grid.getSquareWidth(), y/grid.getSquareHeight(), "tower.png");
        towers.add(t);
        //CombineTowers(t, ShapeCross);
    }

    public boolean ReadyToPlaceTower()
    {
        if(isHorizontalSelected() && isVerticalSelected())
            return true;
        return false;
    }

    public void PlaceTowerXY(List<Tower> towers)
    {
       // Tower t = new Tower(x/grid.getSquareWidth()*grid.getSquareWidth(), y/grid.getSquareHeight()*grid.getSquareWidth(), x/grid.getSquareWidth(), y/grid.getSquareHeight(), "tower.png");
       // towers.add(t);
    }

    public boolean PlaceTower(List<Tower> towers)
    {
        if(ReadyToPlaceTower()) {
            Tower t = new Tower(column * grid.getSquareWidth(), row * grid.getSquareHeight(), column, row, "tower.png");
            if (grid.getSquare(column, row).getTower() == null) {
                towers.add(t);
                //grid.FreeNeighBourSquares(grid.getSquare(column, row));
                grid.getSquare(column, row).setTower(t);
                if (grid.getSquare((int) t.getGridPosition().x, (int) t.getGridPosition().y).isTowerPlaced()) {
                    Gdx.app.debug("test", "there is tower" + t.getGridPosition().x + "  " + t.getGridPosition().y);
                }
                //CombineTowers(t, ShapeCross);
                return true;
            }

            ResetSelection();
        }
        return false;
    }

    private void CombineTowers(Tower t, int[][] shape)
    {
        boolean found = true;
        for(int x = (int)t.getGridPosition().x-2; x < (int)t.getGridPosition().x +1; x++)
        {
            for(int y = (int)t.getGridPosition().y; y < (int)t.getGridPosition().y + 3; y++)
            {
                found = true;

                if(y < 0) y = 0;
                if (x < 0) x = 0;
                //grid.getSquare(x,y).setTexture(new Texture("redsquare.png"));
                for(int x2 = 0; x2 <= 2; x2++)
                {
                    for(int y2 = 0; y2 >= -2; y2--)
                    {
                        grid.getSquare(x2+x,y2+y).setTexture(new Texture("redsquare.png"));
                        if(shape[x2][Math.abs(y2)] == 1 && !grid.getSquare(x+x2, y2+y).isTowerPlaced())
                        {
                            Gdx.app.debug("towers", "shape x2 y2 " + x2 + " " + y2 + " gridgetSquare: "
                                    + (x + x2) + " " + (y + y2));
                            found = false;
                        }
                    }
                }

                if(found == true) // towers formed in shape of shape
                {
                    Gdx.app.debug("towers", "alright found shape");
                }
                //else Gdx.app.debug("towers", "nope nuffin found");
            }
        }


    }



    public int[][] ShapeCross = {
            {0,0,0},
            {0,1,1},
            {0,0,0}
    };
    public boolean isMouseSelection() { return isMouseSelection(); }
    public void setMouseSelection(boolean m) { this.mouseSelection = m; }

    public boolean isVerticalSelected() {
        return verticalSelected;
    }

    public boolean isHorizontalSelected() {
        return horizontalSelected;
    }

    public void setHorizontalSelected(boolean horizontalSelected) {
        this.horizontalSelected = horizontalSelected;
    }

    public void setVerticalSelected(boolean verticalSelected) {
        this.verticalSelected = verticalSelected;
    }

//    public List<Tower> getTowers() {
//        return towers;
//    }

    public void Update(float dt)
    {
        //Gdx.app.debug("Pathing", "BRAAP" + String.valueOf(timer));
        if(mouseSelection)
            return;
        timer += dt;
        if(!horizontalSelected)
        {
            if(timer > 0.2) {
                timer = 0;
                row += horBounce;
                if (row == grid.getGridHeight()) {
                    row = grid.getGridHeight() - 1;
                    horBounce = -1;
                } else if (row == 0) {
                    row = 0;
                    horBounce = 1;

                }

                for (int i = 0; i < grid.getGridWidth(); ++i) {
                    if(horizontalSelectionBox[i] != null)
                        horizontalSelectionBox[i].setTexture(new Texture("square.png"));
                    horizontalSelectionBox[i] = grid.getSquares()[i][row];
                    horizontalSelectionBox[i].setTexture(new Texture("hoverSquare.png"));
                }

            }
        }
        if(horizontalSelected && !verticalSelected)
        {
            if(timer > maxTimeToNextSelection) {
                timer = 0;
                column += verBounce;
                if (column == grid.getGridWidth()) {
                    column = grid.getGridWidth() - 1;
                    verBounce = -1;
                } else if (column == 0) {
                    column = 0;
                    verBounce = 1;
                }

                for (int i = 0; i < grid.getGridHeight(); ++i) {
                    if (verticalSelectionBox[i] != null)
                        verticalSelectionBox[i].setTexture(new Texture("square.png"));
                    if(i != row)
                    {
                        verticalSelectionBox[i] = grid.getSquares()[column][i];
                        verticalSelectionBox[i].setTexture(new Texture("hoverSquare.png"));
                    }
                }

            }
        }
    }

    public void Render(SpriteBatch sb)
    {

    }

    public void Dispose()
    {

    }

    public void ResetSelection()
    {
        for(int x = 0; x < grid.getGridWidth(); ++x)
        {
            grid.getSquares()[x][row].setTexture(new Texture("square.png"));
            horizontalSelectionBox[x] = null;
        }
        for(int y = 0; y < grid.getGridHeight(); ++y)
        {
            grid.getSquares()[column][y].setTexture(new Texture("square.png"));
            verticalSelectionBox[y] = null;
        }

        column = 0;
        row = 0;
        horBounce = 1;
        verBounce = 1;
        verticalSelected = false;
        horizontalSelected = false;
        timer = 0;
        selectedSquare = null;
    }
}
