package com.thedoctor.game.objects.item;

import com.thedoctor.game.objects.entity.Bullet;
import com.thedoctor.game.objects.entity.EntityLiving;
import org.newdawn.slick.SlickException;

public class ItemSimpleGun extends ItemRangeWeapon{

    public static final int ID = 4;

    public ItemSimpleGun() throws SlickException {
        super("core/simple_gun.png", 3, 300, -1);

    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void shoot(EntityLiving sender) throws SlickException {
        sender.getLevel().addEntity(new Bullet(sender.getX(), sender.getY(), sender.getLevel(), sender));
    }
}
