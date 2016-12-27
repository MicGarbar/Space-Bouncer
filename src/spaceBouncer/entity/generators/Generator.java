package spaceBouncer.entity.generators;

import spaceBouncer.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Generator {

    protected List<Entity> entities;

    public Generator(int amount, String[] textures){
        entities = new ArrayList<>();
    }

    public void update(){
        for(Entity entity : entities){
            entity.update();
        }
    }

    public void render(){
        for(Entity entity : entities){
            entity.render();
        }
    }

    public List<Entity> getEntities(){
        return this.entities;
    }

}
