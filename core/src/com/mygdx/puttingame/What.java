package com.mygdx.puttingame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.puttingame.Function2d.evaluate;

public class What extends ApplicationAdapter {

    private List<Entity> entities;
    private List<Entity> golfBall;
    private Matrix4 translationMatrix;
    private Camera cam;
    private Mesh mesh;
    public Mesh sphereMesh;
    private ShaderProgram shader;
    private ShaderProgram shader2;
    private Vector3 lightDirection;
    private FirstPersonCameraController controller;



    @Override
    public void create() {
        super.create();
        translationMatrix = new Matrix4();


        MeshBuilder builder = new MeshBuilder();
        VertexAttributes attributes = new VertexAttributes(new VertexAttribute(VertexAttributes.Usage.Position, 3,"a_Position"));
        builder.begin(attributes, GL20.GL_TRIANGLES);
        builder.box(0.1f,0.1f,0.1f);
        mesh = builder.end();

        builder = new MeshBuilder();
        builder.begin(attributes,GL20.GL_TRIANGLES);
        builder.setColor(Color.WHITE);
        builder.sphere(0.3f, 0.3f, 0.3f, 20, 20);

        sphereMesh = builder.end();

        golfBall = new ArrayList<Entity>();

        Entity ent = new Entity(sphereMesh);
        ent.setPosition(0,(float) (evaluate(new Vector2d(0,0))+0.15),0);
        golfBall.add(ent);



        entities = new ArrayList<Entity>();

        int xMaxSize = 40;
        int yMaxSize = 40;

            for (double i=-(0.5*yMaxSize);i<=(0.5*yMaxSize);i+= 0.1){
                for (double j=-(0.5*xMaxSize);j<=(0.5*xMaxSize);j+= 0.1) {


                    Entity ent1 = new Entity(mesh);
                    ent1.setPosition((float)-(i), (float) evaluate(new Vector2d(j,i)), (float)-(j));
                    entities.add(ent1);
                }
            }


            shader = new ShaderProgram(Gdx.files.internal("C:\\Users\\zaker\\Desktop\\PuttingGame\\core\\src\\com\\mygdx\\puttingame\\shader\\vertexshader.glsl").readString(), Gdx.files.internal("C:\\Users\\zaker\\Desktop\\PuttingGame\\core\\src\\com\\mygdx\\puttingame\\shader\\fragmentshader.glsl").readString());
            shader2 = new ShaderProgram(Gdx.files.internal("C:\\Users\\zaker\\Desktop\\PuttingGame\\core\\src\\com\\mygdx\\puttingame\\shader\\vertexshader.glsl").readString(), Gdx.files.internal("C:\\Users\\zaker\\Desktop\\PuttingGame\\core\\src\\com\\mygdx\\puttingame\\shader\\fragmentshader2.glsl").readString());



        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        cam.position.set(10f,10f,10f);
        cam.lookAt(10f,10f,10f);
        cam.near = 1f;
        cam.far = 3000f;
        cam.update();

        lightDirection = new Vector3(3,6f,9f);

        controller = new FirstPersonCameraController(cam);
        Gdx.input.setInputProcessor(controller);


    }
    public void render(){
        super.render();

        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        controller.update(Gdx.graphics.getDeltaTime());
        cam.update(true);


        Gdx.gl20.glViewport(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT| GL20.GL_DEPTH_BUFFER_BIT);

        Gdx.gl20.glClearColor(135f/255f,206f/255f, 235f/255f, 1f);


        shader2.begin();

        shader2.setUniformMatrix("viewProj", cam.combined);
        shader2.setUniformf("lightDirection", lightDirection);



        for (Entity b : golfBall){

            translationMatrix.setToTranslation(b.getPosition());
            shader2.setUniformMatrix("model", translationMatrix);
            b.getMesh().render(shader2, GL20.GL_TRIANGLES);

        }
        shader2.end();

        shader.begin();
        shader.setUniformMatrix("viewProj", cam.combined);
        //shader.setUniformf("lightDirection", lightDirection);

        for (Entity e : entities){

            translationMatrix.setToTranslation(e.getPosition());
            shader.setUniformMatrix("model", translationMatrix);
            e.getMesh().render(shader, GL20.GL_TRIANGLES);

        }

        shader.end();

    }


}
