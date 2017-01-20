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
    SprHero sprHero;
    Sprite arsprRock[] = new Sprite[4];
    SprWall arsprWall[] = new SprWall[4];
    Texture txRock, txHero, txWall, txBG;
    int nWH, nHH;
    int nFrame, nPos;

    @Override
    public void create() {
        Gdx.input.setInputProcessor((this));
        batch = new SpriteBatch();
        txBG = new Texture("Grass Tile Demo.png");
        txWall = new Texture("Wall.png");
        txRock = new Texture("rock.png");
        txHero = new Texture("Char.png");
        //Contains for the wall
        arsprWall[0] = new SprWall(txWall, 30, 600, 0, 0);
        arsprWall[1] = new SprWall(txWall, 30, 600, 1170, 0);
        arsprWall[2] = new SprWall(txWall, 1200, 40, 0, 0);
        arsprWall[3] = new SprWall(txWall, 1200, 40, 0, 560);
        /*arsprWall[0].setSize(30, 600);
        arsprWall[1].setSize(30, 600);
        arsprWall[1].setPosition(1170,0);
        arsprWall[2].setSize(1200, 40);
        arsprWall[3].setSize(1200, 40);
        arsprWall[3].setPosition(0,560);*/
        //Setting up the rocks size and image
        arsprRock[0] = new Sprite(txRock);
        arsprRock[0].setSize(100, 100);
        arsprRock[0].setPosition(200, 300);

        nWH = txHero.getWidth();
        nHH = txHero.getHeight();
        sprHero = new SprHero(txHero, nWH, nHH, 300, 500);
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprHero.dLastX = sprHero.dX;
            sprHero.dX -= 5;
            for (int i = 0; i < arsprWall.length; i++) {
                if (isHit(sprHero, arsprWall[i])) {
                    sprHero.dX += 5;
                }
            }
                if (isHit(sprHero, arsprRock[0])) {
                    sprHero.dX += 5;
                }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprHero.dLastX = sprHero.dX;
            sprHero.dX += 5;
            for (int i = 0; i < arsprWall.length; i++) {
                if (isHit(sprHero, arsprWall[i])) {
                    sprHero.dX -= 5;
                }
            }
                if (isHit(sprHero, arsprRock[0])) {
                    sprHero.dX -= 5;
                }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            sprHero.dLastY = sprHero.dY;
            sprHero.dY += 5;
            for (int i = 0; i < arsprWall.length; i++) {
                if (isHit(sprHero, arsprWall[i])) {
                    sprHero.dY -= 5;
                }
            }
                if (isHit(sprHero, arsprRock[0])) {
                    sprHero.dY -= 5;
                }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            sprHero.dLastY = sprHero.dY;
            sprHero.dY -= 5;
            for (int i = 0; i < arsprWall.length; i++) {
                System.out.println("KeyDown " + i);
                if (isHit(sprHero, arsprWall[i])) {
                    sprHero.dY += 5;
                    System.out.println("Hit " + i);
                }
            }
                if (isHit(sprHero, arsprRock[0])) {
                    sprHero.dY += 5;
                }
        }
        batch.begin();
        batch.draw(txBG, 0, 0, 1200, 600);
        batch.draw(txHero, Math.round((float) sprHero.dX), Math.round((float) sprHero.dY));
        for (int i = 0; i < arsprWall.length; i++) {
            batch.draw(txWall, arsprWall[i].getX(), arsprWall[i].getY(), 100, 100);
        }
        batch.draw(arsprRock[0], arsprRock[0].getX(), arsprRock[0].getY(), 100, 100);
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void dispose() {
        txHero.dispose();
        txRock.dispose();
        batch.dispose();
    }

    public static boolean isHit(SprHero spr1, Sprite spr2) {
        return spr1.retRect().overlaps(spr2.getBoundingRectangle()); //System.out.println("is hit");
    }
}
