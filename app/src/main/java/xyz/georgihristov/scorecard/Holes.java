package xyz.georgihristov.scorecard;

/**
 * Created by gohv on 05.12.16.
 */

public class Holes {

    private String hole;
    private int pointCount;

    public Holes(String hole, int pointCount) {
        this.hole = hole;
        this.pointCount = pointCount;
    }

    public String getHole() {
        return hole;
    }

    public void setHole(String hole) {
        this.hole = hole;
    }

    public int getPointCount() {
        return pointCount;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }
}
