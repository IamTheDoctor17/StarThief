package com.thedoctor.game.planet;

import com.thedoctor.Util;
import com.thedoctor.game.objects.entity.Entity;
import com.thedoctor.game.objects.entity.EntityLiving;
import com.thedoctor.game.objects.entity.Player;
import com.thedoctor.game.objects.graphics.DamageGraphic;
import com.thedoctor.game.objects.item.ItemRangeWeapon;
import com.thedoctor.game.physics.SimpleVector;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DamageManager {

    PlayableMap map;

    public DamageManager(PlayableMap map) {
        this.map = map;
    }

    public void update() throws SlickException {
        List<Entity> entities = new ArrayList<>(map.entities);
        Iterator<Entity> entityIterator = entities.iterator();
        for (; entityIterator.hasNext(); ) {
            Entity e = entityIterator.next();
            if ((e instanceof EntityLiving && ((EntityLiving) e).isHitting()) || e.doesDamage()) {
                if ( e instanceof EntityLiving){
                    ((EntityLiving) e).setHitting(false);
                    if (((EntityLiving) e).getHeldItem() instanceof ItemRangeWeapon) {
                        if (((EntityLiving) e).getHeldItem() != null)
                            ((EntityLiving) e).getHeldItem().shoot(((EntityLiving) e));
                    } else {
                        for (Iterator<Entity> entityIterator1 = entities.iterator(); entityIterator1.hasNext(); ) {
                            Entity f = entityIterator1.next();
                            if (f instanceof EntityLiving && e != f && e.getClass() != f.getClass()) {
                                float dist = Util.getDistance(e, f);
                                if ((((EntityLiving) e).getRange() > dist && !((EntityLiving) f).isInvincible())) {
                                    ((EntityLiving) f).setDamage(e.getDamage());
                                    float knockback = Util.getRadianAngle(e.getX(), f.getX(), e.getY(), f.getY());
                                    f.addVector(new SimpleVector(knockback, e, (((EntityLiving) e).getHeldItem() == null ? 3 : 1 / ((EntityLiving) e).getHeldItem().getKnockback())));
                                    f.addGraphic(new DamageGraphic(f, "-" + String.valueOf(e.getDamage())));
                                    if (f instanceof Player) ((Player) f).setInvincible(true);
                                }
                            }
                        }
                    }
                }
                else {
                    for (Iterator<Entity> entityIterator1 = entities.iterator(); entityIterator1.hasNext(); ) {
                        Entity f = entityIterator1.next();
                        if (f instanceof EntityLiving && e != f && e.getClass() != f.getClass() && e.getSender() != f) {
                            if (f.objectsCollided.contains(e)) {
                                ((EntityLiving) f).setDamage(e.getDamage());
                                f.addGraphic(new DamageGraphic(f, "-" + String.valueOf(e.getDamage())));
                                map.entities.remove(e);
                            }
                        }
                    }
                }
            }
        }
    }
}
