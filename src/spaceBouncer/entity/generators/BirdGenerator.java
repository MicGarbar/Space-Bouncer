package spaceBouncer.entity.generators;

import org.lwjgl.system.CallbackI;
import spaceBouncer.entity.Bird;
import spaceBouncer.utility.maths.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BirdGenerator {

    private List<Bird> birdList;

    public BirdGenerator(int birdAmount, String[] birdTextures){
        Random random = new Random();
        birdList = new ArrayList<>();

        for(int i = 0; i < birdAmount; i++) {
            Bird bird = new Bird();
            bird.setTriggerAttitude(random.nextInt(2000) + 10);
            bird.setRotationY(Math.random() > 0.5 ? 0 : 180);
            bird.setPosition(new Vector(bird.getRotationY() == 0 ? -20.0f : 20.0f, random.nextFloat()*10));
            bird.setDeltaX(bird.getRotationY() == 0 ?
                    random.nextFloat()*0.1f :
                    random.nextFloat()*0.1f - 0.1f);
            bird.setTexture((Math.random() > 0.5) ?
                    (Math.random() > 0.5 ? birdTextures[0] : birdTextures[1]) :
                    (Math.random() > 0.5 ? birdTextures[2] : birdTextures[3]));
            birdList.add(bird);
        }
    }

    public void update(){
        for(Bird bird : birdList){
            bird.update();
        }
    }

    public void render(){
        for(Bird bird : birdList){
            bird.render();
        }
    }

    public List<Bird> getBirdList(){
        return this.birdList;
    }

    public static final class BirdGeneratorBuilder {

        private int birdsAmount;
        private String[] birdsTextures;

        public BirdGeneratorBuilder withBirdsAmount(int birdsAmount){
            this.birdsAmount = birdsAmount;
            return this;
        }

        public BirdGeneratorBuilder withTextures(String[] birdsTextures){
            this.birdsTextures = birdsTextures;
            return this;
        }

        public BirdGenerator build(){
            return new BirdGenerator(birdsAmount, birdsTextures);
        }
    }

}
