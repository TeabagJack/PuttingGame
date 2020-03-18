package com.mygdx.puttingame;

import com.badlogic.gdx.Game;

public class Main extends Game {

    private StartScreen startScreen;
    private MenuScreen menuScreen;
    private ManInputScreen manInputScreen;
    private FileInputScreen fileInputScreen;
    private LoadingScreen loadingScreen;


    public final static int MENU = 0;
    public final static int START = 1;
    public final static int MANINPUT = 2;
    public final static int FILEINPUT = 3;

    public Main(){

    }

    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                menuScreen = new MenuScreen(this); // added (this)
                this.setScreen(menuScreen);
                break;
            case START:
                startScreen = new StartScreen(this); //added (this)
                this.setScreen(startScreen);
                break;
            case MANINPUT:
                manInputScreen = new ManInputScreen(this); //added (this)
                this.setScreen(manInputScreen);
                break;
            case FILEINPUT:
                fileInputScreen = new FileInputScreen(this); //added (this)
                this.setScreen(fileInputScreen);
                break;

        }
    }

    @Override
    public void create () {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }
}
