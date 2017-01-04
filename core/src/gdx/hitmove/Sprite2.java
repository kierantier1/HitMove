/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.hitmove;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Sprite2 extends Sprite {
    double dX, dY;
    Texture txHero;
    float fSx, fSy, fW, fH;
    Rectangle rect;
    double dLastX, dLastY;

    //Constuctor
    //Sprite2(sFile, fX, fY)
    Sprite2(Texture txHero_, float fW_, float fH_, double dX_, double dY_) {
        fW = fW_;
        fH = fH_;
        dX = dX_;
        dY = dY_;
    }
    
    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float)dX), Math.round((float)dY), fW, fH);
        return rect;
    }
}
