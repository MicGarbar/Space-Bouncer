package spaceBouncer.entity.generators;

import spaceBouncer.entity.entities.Cloud;
import spaceBouncer.utility.maths.Vector;

import java.util.Random;

public class CloudGenerator extends Generator {

    public CloudGenerator(int cloudsAmount, String[] cloudsTextures){
        super(cloudsAmount, cloudsTextures);

        Random random = new Random();

        for(int i = 0; i < cloudsAmount; i++) {
            Cloud cloud = new Cloud();
            cloud.setTriggerAttitude(random.nextInt(18000) + 2000);
            cloud.setPosition(new Vector(Math.random() > 0.5 ? -20.0f : 20.0f, random.nextFloat()*10));
            cloud.setDeltaX(cloud.getPosition().x < 0 ?
                    random.nextFloat()*0.1f :
                    random.nextFloat()*0.1f - 0.1f);
            cloud.setDeltaY(random.nextFloat()*0.1f);
            cloud.setTexture((Math.random() > 0.5) ?
                    (Math.random() > 0.5 ? cloudsTextures[0] : cloudsTextures[1]) :
                    (Math.random() > 0.5 ? cloudsTextures[2] : cloudsTextures[3]));
            entities.add(cloud);
        }
    }

    public static final class CloudGeneratorBuilder {

        private int cloudsAmount;
        private String[] cloudsTextures;

        public CloudGeneratorBuilder withCloudsAmount(int cloudsAmount){
            this.cloudsAmount = cloudsAmount;
            return this;
        }

        public CloudGeneratorBuilder withTextures(String[] cloudsTextures){
            this.cloudsTextures = cloudsTextures;
            return this;
        }

        public CloudGenerator build(){
            return new CloudGenerator(cloudsAmount, cloudsTextures);
        }
    }

}
