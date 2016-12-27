package spaceBouncer.entity.generators;

import spaceBouncer.entity.Cloud;
import spaceBouncer.utility.maths.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CloudGenerator {

    private List<Cloud> cloudList;

    public CloudGenerator(int cloudsAmount, String[] cloudsTextures){
        Random random = new Random();
        cloudList = new ArrayList<>();

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
            cloudList.add(cloud);
        }
    }

    public void update(){
        for(Cloud cloud : cloudList){
            cloud.update();
        }
    }

    public void render(){
        for(Cloud cloud : cloudList){
            cloud.render();
        }
    }

    public List<Cloud> getCloudList(){
        return this.cloudList;
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
