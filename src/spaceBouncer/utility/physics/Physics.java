package spaceBouncer.utility.physics;

import spaceBouncer.entity.entities.Entity;

import java.awt.geom.Rectangle2D;

public class Physics {

    private Physics() {}

    public static boolean collision(Entity firstEntity, Entity secondEntity){

        Rectangle2D firstEntityRect = new Rectangle2D.Float(firstEntity.getPosition().x, firstEntity.getPosition().y,
                firstEntity.getSize(), firstEntity.getSize());
        Rectangle2D secondEntityRect = new Rectangle2D.Float(secondEntity.getPosition().x, secondEntity.getPosition().y,
                secondEntity.getSize(), secondEntity.getSize());

        return firstEntityRect.intersects(secondEntityRect);
    }

    public static boolean fallen(Entity entity){

        Rectangle2D entityRect = new Rectangle2D.Float(entity.getPosition().x, entity.getPosition().y,
                entity.getSize(), entity.getSize());
        Rectangle2D screenRect = new Rectangle2D.Float(-16.0f, -9.0f, 32.0f, 18.0f);

        return !screenRect.contains(entityRect);
    }

}
