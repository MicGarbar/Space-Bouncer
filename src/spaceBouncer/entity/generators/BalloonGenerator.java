package spaceBouncer.entity.generators;

import spaceBouncer.entity.Balloon;
import spaceBouncer.utility.maths.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BalloonGenerator {

    private Random random = new Random();

    private List<Balloon> balloonList;

    public BalloonGenerator(int balloonAmount, String[] balloonsTextures) {
        balloonList = new ArrayList<>();

        for(int i = 0; i < balloonAmount; i++) {
            Balloon balloon = new Balloon();
            balloon.setTriggerAttitude(random.nextInt(400) + 800);
            balloon.setPosition(new Vector(Math.random() > 0.5 ? -20.0f : 20.0f, random.nextFloat()*10));
            balloon.setDeltaX(balloon.getPosition().x < 0 ?
                    random.nextFloat()*0.1f :
                    random.nextFloat()*0.1f - 0.1f);
            balloon.setTexture((Math.random() > 0.5) ?
                    (Math.random() > 0.5 ? balloonsTextures[0] : balloonsTextures[1]) :
                    (Math.random() > 0.5 ? balloonsTextures[2] : balloonsTextures[3]));
            balloonList.add(balloon);
        }
    }

    public void update(){
        for(Balloon balloon : balloonList){
            balloon.update();
        }
    }

    public void render(){
        for(Balloon balloon : balloonList){
            balloon.render();
        }
    }

    public List<Balloon> getBalloonList(){
        return this.balloonList;
    }

    public static final class BalloonGeneratorBuilder {

        private int balloonsAmount;
        private String[] balloonsTextures;

        public BalloonGeneratorBuilder withBalloonsAmount(int planesAmount){
            this.balloonsAmount = planesAmount;
            return this;
        }

        public BalloonGeneratorBuilder withTextures(String[] planesTextures){
            this.balloonsTextures = planesTextures;
            return this;
        }

        public BalloonGenerator build(){
            return new BalloonGenerator(balloonsAmount, balloonsTextures);
        }
    }

}
