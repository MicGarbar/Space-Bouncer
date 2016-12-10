package spaceBouncer.entity.generators;

import spaceBouncer.entity.Balloon;
import spaceBouncer.utility.maths.Vector;

import java.util.ArrayList;
import java.util.List;

public class BalloonGenerator {

    private int balloonAmount;
    private int[] balloonsAttitudeMilestones;
    private float[] balloonsDeltaMilestones;
    private Vector[] balloonsPositionMilestones;
    private String[] balloonsTextures;

    private List<Balloon> balloonList;

    public BalloonGenerator(int balloonAmount, int[] balloonsAttitudeMilestones,
                          float[] balloonsDeltaMilestones,
                          Vector[] balloonsPositionMilestones, String[] balloonsTextures) {
        this.balloonAmount = balloonAmount;
        this.balloonsAttitudeMilestones = balloonsAttitudeMilestones;
        this.balloonsDeltaMilestones = balloonsDeltaMilestones;
        this.balloonsPositionMilestones = balloonsPositionMilestones;
        this.balloonsTextures = balloonsTextures;

        balloonList = new ArrayList<>();
        for(int i = 0; i < balloonAmount; i++) {
            Balloon balloon = new Balloon();
            balloon.setPosition(balloonsPositionMilestones[i]);
            balloon.setDeltaX(balloonsDeltaMilestones[i]);
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
        private int[] balloonsAttitudeMilestones;
        private float[] balloonsDeltaMilestones;
        private Vector[] balloonsPositionMilestones;
        private String[] balloonsTextures;

        public static BalloonGeneratorBuilder generator(){
            return new BalloonGeneratorBuilder();
        }

        public BalloonGeneratorBuilder withBalloonsAmount(int planesAmount){
            this.balloonsAmount = planesAmount;
            return this;
        }

        public BalloonGeneratorBuilder withAttitudeMilestones(int[] attitudeMilestones){
            this.balloonsAttitudeMilestones = attitudeMilestones;
            return this;
        }

        public BalloonGeneratorBuilder withDeltaMilestones(float[] deltaMilestones){
            this.balloonsDeltaMilestones = deltaMilestones;
            return this;
        }

        public BalloonGeneratorBuilder withPositionMilestones(Vector[] positionMilestones){
            this.balloonsPositionMilestones = positionMilestones;
            return this;
        }

        public BalloonGeneratorBuilder withTextures(String[] planesTextures){
            this.balloonsTextures = planesTextures;
            return this;
        }

        public BalloonGenerator build(){
            BalloonGenerator balloonGenerator = new BalloonGenerator(balloonsAmount,
                    balloonsAttitudeMilestones,
                    balloonsDeltaMilestones, balloonsPositionMilestones, balloonsTextures);
            return balloonGenerator;
        }
    }

}
