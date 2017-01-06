package spaceBouncer.entity.generators;

import spaceBouncer.entity.entities.Planet;
import spaceBouncer.entity.features.PlanetFeatures;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Vector;

import java.util.Random;

public class PlanetGenerator extends Generator implements PlanetFeatures {

    public PlanetGenerator(){
        Random random = new Random();

        for(int i = 0; i < planetsAmount; i++){
            Planet planet = new Planet();
            planet.setPosition(new Vector(random.nextFloat()*16 - 16.0f, 16.0f));
            planet.setVao(new VertexArrayObject(planetsVertices[i], indices, textureCoordinates));
            planet.setShader();
            planet.setProjection();
            planet.setSize(planetsSize[i]);
            planet.setTexture(planetsTextureSources[i]);
            planet.setTriggerAttitude(planetsTriggerAttitude[i]);
            planet.setDeltaY(planetVelocities[i]);
            entities.add(planet);
        }
    }

}
