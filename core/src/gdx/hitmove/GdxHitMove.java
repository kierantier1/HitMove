package gdx.hitmove;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxHitMove extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    Sprite2 sprHero;
    Sprite arsprRock[] = new Sprite[12];
    Sprite arsprWall[] = new Sprite[4];
    Texture txRock, txHero, txOWall, txBG;
    int nWH, nHH;
    int nFrame, nPos;
    int nRX, nRY;
    int nSpeed;

    @Override
    public void create() {
        Gdx.input.setInputProcessor((this));
        nSpeed = 5;
        batch = new SpriteBatch();
        txBG = new Texture("Room.jpg");
        txOWall = new Texture("rock.png");
        txRock = new Texture("rock.png");
        txHero = new Texture("Char.png");
        for (int i = 0; i < 4; i++) {
            arsprWall[i] = new Sprite(txOWall);
        }
        //Contains for the wall
        arsprWall[0].setSize(30, 600);
        arsprWall[1].setSize(30, 600);
        arsprWall[1].setPosition(1170,0);//the position wouldnt register in hit test without this
        arsprWall[2].setSize(1200, 40);
        arsprWall[3].setSize(1200, 40);
        arsprWall[3].setPosition(0,560);//the position wouldnt register in hit test without this
        //Seting up the rocks size and image
        for (int i = 0; i < arsprRock.length; i++) {
            arsprRock[i] = new Sprite(txRock);
            arsprRock[i].setSize(100, 100);
        }
        //Setting the positions of the rocks, this makes a row at the bottom
        /*for(int i=0;i<arsprRock.length;i++){
        arsprRock[i].setPosition(nRX,nRY);
        nRX+=100;
        }*/
        arsprRock[1].setPosition(30, 40);

        nWH = txHero.getWidth();
        nHH = txHero.getHeight();
        sprHero = new Sprite2(txHero, nWH, nHH, 300, 500);
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprHero.dLastX = sprHero.dX;
            sprHero.dX -= nSpeed;
            for (int i = 0; i < arsprWall.length; i++) {
                if (isHit(sprHero, arsprWall[i])) {
                    sprHero.dX += nSpeed;
                }
            }
            for (int i = 0; i < arsprRock.length; i++) {
                if (isHit(sprHero, arsprRock[i])) {
                    sprHero.dX += nSpeed;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprHero.dLastX = sprHero.dX;
            sprHero.dX += nSpeed;
            for (int i = 0; i < arsprWall.length; i++) {
                if (isHit(sprHero, arsprWall[i])) {
                    sprHero.dX -= nSpeed;
                }
            }
            for (int i = 0; i < arsprRock.length; i++) {
                if (isHit(sprHero, arsprRock[i])) {
                    sprHero.dX -= nSpeed;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprHero.dLastY = sprHero.dY;
            sprHero.dY += nSpeed;
            for (int i = 0; i < arsprWall.length; i++) {
                if (isHit(sprHero, arsprWall[i])) {
                    sprHero.dY -= nSpeed;
                }
            }
            for (int i = 0; i < arsprRock.length; i++) {
                if (isHit(sprHero, arsprRock[i])) {
                    sprHero.dY -= nSpeed;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprHero.dLastY = sprHero.dY;
            sprHero.dY -= nSpeed;
            for (int i = 0; i < arsprWall.length; i++) {
                if (isHit(sprHero, arsprWall[i])) {
                    sprHero.dY += nSpeed;
                }
            }
            for (int i = 0; i < arsprRock.length; i++) {
                if (isHit(sprHero, arsprRock[i])) {
                    sprHero.dY += nSpeed;
                }
            }
        }
        batch.begin();
        batch.draw(txBG, 0, 0, 1200, 600);
        batch.draw(txHero, Math.round((float) sprHero.dX), Math.round((float) sprHero.dY));
        //batch.draw(arsprWall[0], 0, 0, 30, 600); These arent needed to register hit detection
        //batch.draw(arsprWall[1], 1170, 0, 30, 600);
        //batch.draw(arsprWall[2], 0, 0, 1200, 40);
        //batch.draw(arsprWall[3], 0, 560, 1200, 40);
        /*for (int i = 0; i < arsprRock.length; i++) { Creates a row of rocks at the bottom
        batch.draw(arsprRock[i], arsprRock[i].getX(), arsprRock[i].getY(),70,70); 
        }*/
        batch.draw(arsprRock[1], arsprRock[1].getX(), arsprRock[1].getY(), 100, 100);
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode
    ) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode
    ) {
        return false;
    }

    @Override
    public boolean keyTyped(char character
    ) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button
    ) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button
    ) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer
    ) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY
    ) {
        return false;
    }

    @Override
    public boolean scrolled(int amount
    ) {
        return false;
    }

    @Override
    public void dispose() {
        txHero.dispose();
        txRock.dispose();
        batch.dispose();
    }

    public static boolean isHit(Sprite2 spr1, Sprite spr2) {
        return spr1.retRect().overlaps(spr2.getBoundingRectangle()); //System.out.println("is hit");
    }
}
