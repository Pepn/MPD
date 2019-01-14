package com.pepn.game.achievementsystem;


public interface IAchievementSystem
{
    //fill achievement
    void FillAchievements(String name, String text, int amount, Boolean hidden, Boolean unlocked);
    void CheckAchievements();
    void IncrementAchievement(String name, int amount);
    //check achievement
    //increment achievement
}


/*
in game UpdateAP
in loop AchievementSystem.CheckAchievements
*/
