package spaceBouncer.entity.generators;

import spaceBouncer.entity.entities.Probe;
import spaceBouncer.utility.maths.Vector;

import java.util.Random;

public class ProbeGenerator extends Generator {

    public ProbeGenerator(int amount, String[] textures) {
        super(amount, textures);

        Random random = new Random();

        for(int i = 0; i < amount; i++){
            Probe probe = new Probe();
            probe.setTriggerAttitude(random.nextInt(500) + 130);
            probe.setPosition(new Vector(Math.random() > 0.5 ? -20.0f : 20.0f, random.nextFloat()*10));
            probe.setAmplitude(random.nextInt(10) + 1);
            probe.setDeltaX(probe.getPosition().x < 0 ?
                    random.nextFloat()*0.1f :
                    random.nextFloat()*0.1f - 0.1f);
            probe.setTexture((Math.random() > 0.5) ?
                    (Math.random() > 0.5 ? textures[0] : textures[1]) :
                    (Math.random() > 0.5 ? textures[2] : textures[3]));
            probe.setRotationZ(0);
            entities.add(probe);
        }
    }

    public static final class ProbeGeneratorBuilder {

        private int probesAmount;
        private String[] probesTextures;

        public ProbeGeneratorBuilder withAmount(int probesAmount){
            this.probesAmount = probesAmount;
            return this;
        }

        public ProbeGeneratorBuilder withTextures(String[] probesTextures){
            this.probesTextures = probesTextures;
            return this;
        }

        public ProbeGenerator build(){
            return new ProbeGenerator(probesAmount, probesTextures);
        }
    }
}
