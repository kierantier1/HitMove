package gdx.hitmove;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GdxHitMove extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    Sprite2 sprVlad;
    Sprite sprRock;
    Texture txWall, txHero;
    Animation araniVlad[];
    int fW, fH, fSx, fSy; // height and width of SpriteSheet image - and the starting upper coordinates on the Sprite Sheet
    int nFrame, nPos;
    int nX, nY, nRX=400, nRY=200;
    int nSpeed;
    int nUpDown, nLeftRight;
    Rectangle rect;

    @Override
    public void create() {
        Gdx.input.setInputProcessor((this));
        nSpeed = 5;
        batch = new SpriteBatch();
        txWall = new Texture("rock.png");
        txHero = new Texture("algore.png");
        sprRock = new Sprite(txWall);
        sprRock.setPosition(nRX, nRY);
        fW = txHero.getWidth();
        fH = txHero.getHeight();
        sprVlad = new Sprite2(txHero, fSx, fSy, fW, fH, 300, 500);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprVlad.dLastX = sprVlad.dX;
            sprVlad.dX -= nSpeed;
            if (isHit(sprVlad, sprRock)) {
                sprVlad.dX += nSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprVlad.dLastX = sprVlad.dX;
            sprVlad.dX += nSpeed;

            if (isHit(sprVlad, sprRock)) {
                sprVlad.dX -= nSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprVlad.dLastY = sprVlad.dY;
            sprVlad.dY += nSpeed;

            if (isHit(sprVlad, sprRock)) {
                sprVlad.dY -= nSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprVlad.dLastY = sprVlad.dY;
            sprVlad.dY -= nSpeed;
                if (isHit(sprVlad, sprRock)) {
                    sprVlad.dY += nSpeed;
                }
        }
        System.out.println("y" + sprVlad.getY());
        System.out.println(sprVlad.getY());
        batch.begin();
        batch.draw(txHero, Math.round((float) sprVlad.dX), Math.round((float) sprVlad.dY));
        batch.draw(sprRock, sprRock.getX(), sprRock.getY());
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
        txWall.dispose();
        batch.dispose();
    }

    public static void setXY(Sprite spr, int nX, int nY) {
        spr.setX(nX);
        spr.setY(nY);
    }

    public static boolean isHit(Sprite2 spr1, Sprite spr2) {
        setXY(spr2, Math.round(spr2.getX()), Math.round(spr2.getY()));
        return spr1.retRect().overlaps(spr2.getBoundingRectangle()); //System.out.println("is hit");
    }
}
