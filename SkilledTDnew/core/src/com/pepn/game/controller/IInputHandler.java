package com.pepn.game.controller;

public interface IInputHandler {
    void check(); //test method
    boolean CheckInput(int key); //check what kind of input is handled
    int GetPlatformNumber(); //used in the World(game class) to check what kind of input method is used
}
