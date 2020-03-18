package com.mygdx.puttingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    private Main parent;
    public Stage stage;

    public MenuScreen(Main x){
        parent = x;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("C:\\Users\\zaker\\Desktop\\PuttingGame\\core\\assets\\vhs-ui.json"));

        TextButton start = new TextButton("Start", skin);
        TextButton manInput = new TextButton("Manual Input", skin);
        TextButton fileInput = new TextButton("File Reader", skin);
        TextButton exit = new TextButton("Exit", skin);
        table.row().pad(10, 0, 10, 0);
        table.add(start).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(manInput).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);;
        table.add(fileInput).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);;
        table.add(exit).fillX().uniformX();

        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Add something for it to do.
            }
        });

        manInput.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Main.MANINPUT);
            }
        });

        fileInput.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Main.FILEINPUT);
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
