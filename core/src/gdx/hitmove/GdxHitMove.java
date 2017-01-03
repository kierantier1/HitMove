package gdx.hitmove;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxHitMove extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    Sprite2 sprVlad;
    Sprite arsprRock[] = new Sprite[12];
    Texture txWall, txHero;
    Animation araniVlad[];
    int fW, fH, fSx, fSy; // height and width of SpriteSheet image - and the starting upper coordinates on the Sprite Sheet
    int nFrame, nPos;
    int nRX,nRY;
    int nSpeed;

    @Override
    public void create() {
        Gdx.input.setInputProcessor((this));
        nSpeed = 5;
        batch = new SpriteBatch();
        txWall = new Texture("rock.png");
        txHero = new Texture("algore.png");
        for (int i = 0; i < arsprRock.length; i++) {
            arsprRock[i] = new Sprite(txWall);
            arsprRock[i].setSize(100, 100);
        }
        for(int i=0;i<arsprRock.length;i++){
        arsprRock[i].setPosition(nRX,nRY);
        nRX+=100;
        }
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
            for (int i = 0; i < 10; i++) {
                if (isHit(sprVlad, arsprRock[i])) {
                    sprVlad.dX += nSpeed;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprVlad.dLastX = sprVlad.dX;
            sprVlad.dX += nSpeed;
            for (int i = 0; i < arsprRock.length; i++) {
                if (isHit(sprVlad, arsprRock[i])) {
                    sprVlad.dX -= nSpeed;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprVlad.dLastY = sprVlad.dY;
            sprVlad.dY += nSpeed;
            for (int i = 0; i < arsprRock.length; i++) {
                if (isHit(sprVlad, arsprRock[i])) {
                    sprVlad.dY -= nSpeed;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprVlad.dLastY = sprVlad.dY;
            sprVlad.dY -= nSpeed;
            for (int i = 0; i < arsprRock.length; i++) {
                if (isHit(sprVlad, arsprRock[i])) {
                    sprVlad.dY += nSpeed;
                }
            }
        }
        batch.begin();
        batch.draw(txHero, Math.round((float) sprVlad.dX), Math.round((float) sprVlad.dY));
        for (int i = 0; i < arsprRock.length; i++) {
        batch.draw(arsprRock[i], arsprRock[i].getX(), arsprRock[i].getY(),100,100);
        }
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
        txWall.dispose();
        batch.dispose();
    }

    public static boolean isHit(Sprite2 spr1, Sprite spr2) {
        return spr1.retRect().overlaps(spr2.getBoundingRectangle()); //System.out.println("is hit");
    }
}
