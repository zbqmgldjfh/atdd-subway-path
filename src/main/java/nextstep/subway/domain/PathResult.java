package nextstep.subway.domain;

import java.util.List;

public class PathResult {

    private List<Station> path;
    private int distance;

    public PathResult(List<Station> path, int distance) {
        this.path = path;
        this.distance = distance;
    }

    public List<Station> getPath() {
        return path;
    }

    public int getDistance() {
        return distance;
    }
}
