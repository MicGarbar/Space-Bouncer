package spaceBouncer.entity.generators;

import spaceBouncer.entity.Plane;
import spaceBouncer.utility.maths.Vector;

import java.util.ArrayList;
import java.util.List;

public class PlaneGenerator {

    private int planesAmount;
    private int[] planesAttitudeMilestones;
    private int[] planesRotateMilestones;
    private float[] planesDeltaMilestones;
    private Vector[] planesPositionMilestones;
    private String[] planesTextures;

    private List<Plane> planeList;

    public PlaneGenerator(int planesAmount, int[] planesAttitudeMilestones,
                          int[] planesRotateMilestones, float[] planesDeltaMilestones,
                          Vector[] planesPositionMilestones, String[] planesTextures) {
        this.planesAmount = planesAmount;
        this.planesAttitudeMilestones = planesAttitudeMilestones;
        this.planesRotateMilestones = planesRotateMilestones;
        this.planesDeltaMilestones = planesDeltaMilestones;
        this.planesPositionMilestones = planesPositionMilestones;
        this.planesTextures = planesTextures;

        planeList = new ArrayList<>();
        for(int i = 0; i < planesAmount; i++) {
            Plane plane = new Plane();
            plane.setPosition(planesPositionMilestones[i]);
            plane.setRotationY(planesRotateMilestones[i]);
            plane.setDeltaX(planesDeltaMilestones[i]);
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
            PlaneGenerator planeGenerator = new PlaneGenerator(planesAmount,
                    planesAttitudeMilestones, planesRotateMilestones,
                    planesDeltaMilestones, planesPositionMilestones, planesTextures);
            return planeGenerator;
        }
    }

}
