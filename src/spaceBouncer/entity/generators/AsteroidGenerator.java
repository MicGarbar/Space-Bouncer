package spaceBouncer.entity.generators;

import spaceBouncer.entity.entities.Asteroid;
import spaceBouncer.utility.maths.Vector;

import java.util.Random;

public class AsteroidGenerator extends Generator {

    private String[] textures;

    public AsteroidGenerator(int amount, String[] textures) {
        super(amount, textures);
        this.textures = textures;

        Random random = new Random();

        for(int i = 0; i < amount; i++){
            Asteroid asteroid = new Asteroid();
            asteroid.setTriggerAttitude(random.nextInt(350) + 255);
            asteroid.setPosition(new Vector(Math.random() > 0.5 ? -20.0f : 20.0f, random.nextFloat()*10));
            asteroid.setAmplitude(8);
            asteroid.setDeltaX(asteroid.getPosition().x < 0 ?
                    random.nextFloat()*0.1f :
                    random.nextFloat()*0.1f - 0.1f);
            asteroid.setTexture(Math.random() > 0.5 ? textures[0] : textures[1]);
            asteroid.setRotationZ(0);
            entities.add(asteroid);
        }
    }

    public void addExtraAsteroids(int extraAsteroidsAmount){
        Random random = new Random();

        for(int i = 0; i < extraAsteroidsAmount; i++){
            Asteroid asteroid = new Asteroid();
            asteroid.setTriggerAttitude(random.nextInt(350) + 1440);
            asteroid.setPosition(new Vector(Math.random() > 0.5 ? -20.0f : 20.0f, random.nextFloat()*10));
            asteroid.setDeltaX(asteroid.getPosition().x < 0 ?
                    random.nextFloat()*0.1f :
                    random.nextFloat()*0.1f - 0.1f);
            asteroid.setTexture(Math.random() > 0.5 ? textures[0] : textures[1]);
            asteroid.setRotationZ(0);
            entities.add(asteroid);
        }
    }

    public static final class AsteroidGeneratorBuilder {

        private int asteroidAmount;
        private String[] asteroidTextures;

        public AsteroidGeneratorBuilder withAmount(int asteroidAmount){
            this.asteroidAmount = asteroidAmount;
            return this;
        }

        public AsteroidGeneratorBuilder withTextures(String[] asteroidTextures){
            this.asteroidTextures = asteroidTextures;
            return this;
        }

        public AsteroidGenerator build(){
            return new AsteroidGenerator(asteroidAmount, asteroidTextures);
        }
    }

}
