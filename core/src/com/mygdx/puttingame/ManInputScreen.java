package com.mygdx.puttingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ManInputScreen implements Screen {
    private Main parent;
    private Stage stage;
    public ManInputScreen(Main x){

        parent = x;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("C:\\Users\\zaker\\Desktop\\PuttingGame\\core\\assets\\vhs-ui.json"));
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont();
        label1Style.font = myFont;
        label1Style.fontColor = Color.WHITE;


       // TextField.TextFieldStyle textField1Style = new TextField.TextFieldStyle();
        Label heightProfile = new Label("Height Profile", label1Style );
        Label frictionCoefficient = new Label("Friction Coefficient", label1Style );
        Label startPosition = new Label("Start Position", label1Style );
        Label goalPosition = new Label("Goal Position", label1Style );
        Label radius = new Label("Radius Of Target", label1Style );
        Label maxV = new Label("Maximum Velocity", label1Style);
        TextField heightField = new TextField("", skin);
        TextField frictionField = new TextField("", skin);
        TextField startField = new TextField("", skin);
        TextField goalField = new TextField("", skin);
        TextField radiusField = new TextField("", skin);
        TextField maxVField = new TextField("", skin);
        table.add(heightProfile);
        table.add(heightField);
        table.row();
        table.add(frictionCoefficient);
        table.add(frictionField);
        table.row();
        table.add(startPosition);
        table.add(startField);
        table.row();
        table.add(goalPosition);
        table.add(goalField);
        table.row();
        table.add(radius);
        table.add(radiusField);
        table.row();
        table.add(maxV);
        table.add(maxVField);
        table.row();

        TextButton readInput = new TextButton("Apply", skin);
        TextButton back = new TextButton("Return", skin);
        table.add(back);
        table.add(readInput);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Main.MENU);
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
