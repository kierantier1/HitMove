package gdx.hitmove;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SprWall extends Sprite{
    double dX, dY;
    Texture txWall;
    float fSx, fSy, fW, fH;
    Rectangle rect;
    SprWall(Texture txHero_, float fW_, float fH_, double dX_, double dY_) {
        fW = fW_;
        fH = fH_;
        dX = dX_;
        dY = dY_;
    }
    public Rectangle retRect() { 
        rect = new Rectangle(Math.round((float)dX), Math.round((float)dY), fW, fH);
        return rect;
    }
}
