package spaceBouncer.entity.generators;

import spaceBouncer.entity.Plane;
import spaceBouncer.utility.maths.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaneGenerator {

    private Random random = new Random();

    private List<Plane> planeList;

    public PlaneGenerator(int planesAmount, String[] planesTextures) {
        planeList = new ArrayList<>();

        for(int i = 0; i < planesAmount; i++) {
            Plane plane = new Plane();
            plane.setTriggerAttitude(random.nextInt(2000) + 9000);
            plane.setRotationY(Math.random() > 0.5 ? 0 : 180);
            plane.setPosition(new Vector(plane.getRotationY() == 0 ? -20.0f : 20.0f, random.nextFloat()*10));
            plane.setDeltaX(plane.getRotationY() == 0 ?
                    random.nextFloat()*0.1f :
                    random.nextFloat()*0.1f - 0.1f);
            plane.setTexture((Math.random() > 0.5) ? planesTextures[0] : planesTextures[1]);
            planeList.add(plane);
        }
    }

    public void update(){
        for(Plane plane : planeList){
            plane.update();
        }
    }

    public void render(){
        for(Plane plane : planeList){
            plane.render();
        }
    }

    public List<Plane> getPlaneList(){
        return this.planeList;
    }

    public static final class PlaneGeneratorBuilder {

        private int planesAmount;
        private int[] planesAttitudeMilestones;
        private int[] planesRotateMilestones;
        private float[] planesDeltaMilestones;
        private Vector[] planesPositionMilestones;
        private String[] planesTextures;

        public static PlaneGeneratorBuilder generator(){
            return new PlaneGeneratorBuilder();
        }

        public PlaneGeneratorBuilder withPlanesAmount(int planesAmount){
            this.planesAmount = planesAmount;
            return this;
        }

        public PlaneGeneratorBuilder withAttitudeMilestones(int[] attitudeMilestones){
            this.planesAttitudeMilestones = attitudeMilestones;
            return this;
        }

        public PlaneGeneratorBuilder withRotateMilestones(int[] rotateMilestones){
            this.planesRotateMilestones = rotateMilestones;
            return this;
        }

        public PlaneGeneratorBuilder withDeltaMilestones(float[] deltaMilestones){
            this.planesDeltaMilestones = deltaMilestones;
            return this;
        }

        public PlaneGeneratorBuilder withPositionMilestones(Vector[] positionMilestones){
            this.planesPositionMilestones = positionMilestones;
            return this;
        }

        public PlaneGeneratorBuilder withTextures(String[] planesTextures){
            this.planesTextures = planesTextures;
            return this;
        }

        public PlaneGenerator build(){
            PlaneGenerator planeGenerator = new PlaneGenerator(planesAmount, planesTextures);
            return planeGenerator;
        }
    }

}
