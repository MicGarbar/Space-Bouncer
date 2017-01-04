package spaceBouncer.entity.generators;

import spaceBouncer.entity.entities.Plane;
import spaceBouncer.utility.maths.Vector;

import java.util.List;
import java.util.Random;

public class PlaneGenerator extends Generator {

    private List<Plane> planeList;

    public PlaneGenerator(int planesAmount, String[] planesTextures) {
        super(planesAmount, planesTextures);

        Random random = new Random();

        for(int i = 0; i < planesAmount; i++) {
            Plane plane = new Plane();
            plane.setTriggerAttitude(random.nextInt(2000) + 9000);
            plane.setRotationY(Math.random() > 0.5 ? 0 : 180);
            plane.setPosition(new Vector(plane.getRotationY() == 0 ? -20.0f : 20.0f, random.nextFloat()*10));
            plane.setAmplitude(random.nextInt(5));
            plane.setDeltaX(plane.getRotationY() == 0 ?
                    random.nextFloat()*0.1f :
                    random.nextFloat()*0.1f - 0.1f);
            plane.setTexture((Math.random() > 0.5) ? planesTextures[0] : planesTextures[1]);
            entities.add(plane);
        }
    }

    public static final class PlaneGeneratorBuilder {

        private int planesAmount;
        private String[] planesTextures;

        public PlaneGeneratorBuilder withPlanesAmount(int planesAmount){
            this.planesAmount = planesAmount;
            return this;
        }

        public PlaneGeneratorBuilder withTextures(String[] planesTextures){
            this.planesTextures = planesTextures;
            return this;
        }

        public PlaneGenerator build(){
            return new PlaneGenerator(planesAmount, planesTextures);
        }
    }

}
