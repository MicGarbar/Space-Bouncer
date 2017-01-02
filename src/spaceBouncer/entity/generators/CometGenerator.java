package spaceBouncer.entity.generators;

import spaceBouncer.entity.entities.Comet;
import spaceBouncer.utility.maths.Vector;

import java.util.Random;

public class CometGenerator extends Generator {

    public CometGenerator(int amount, String[] textures) {
        super(amount, textures);

        Random random = new Random();

        for(int i = 0; i < amount; i++){
            Comet comet = new Comet();
            comet.setTriggerAttitude(random.nextInt(4500) + 300);
            comet.setRotationY(Math.random() > 0.5 ? 0 : 180);
            comet.setPosition(new Vector(comet.getRotationY() == 0 ? -20.0f : 20.0f, random.nextFloat()*10));
            comet.setDeltaX(comet.getRotationY() == 0 ?
                    random.nextFloat()*0.1f :
                    random.nextFloat()*0.1f - 0.1f);
            comet.setDeltaY(random.nextFloat()*0.1f);
            comet.setTexture((Math.random() > 0.5) ?
                    (Math.random() > 0.5 ? textures[0] : textures[1]) : textures[2]);
            entities.add(comet);
        }
    }

    public static final class CometGeneratorBuilder {

        private int cometAmount;
        private String[] cometTextures;

        public CometGeneratorBuilder withAmount(int cometAmount){
            this.cometAmount = cometAmount;
            return this;
        }

        public CometGeneratorBuilder withTextures(String[] cometTextures){
            this.cometTextures = cometTextures;
            return this;
        }

        public CometGenerator build(){
            return new CometGenerator(cometAmount, cometTextures);
        }
    }

}
