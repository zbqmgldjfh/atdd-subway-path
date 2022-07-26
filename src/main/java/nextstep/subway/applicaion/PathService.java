package nextstep.subway.applicaion;

import nextstep.subway.applicaion.dto.PathResponse;
import nextstep.subway.domain.Graph;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathService {

    private final LineService lineService;
    private final StationService stationService;

    public PathService(LineService lineService, StationService stationService) {
        this.lineService = lineService;
        this.stationService = stationService;
    }

    public PathResponse findShortestPath(Long source, Long target) {
        Station startStation = stationService.findById(source);
        Station arrivalStation = stationService.findById(target);
        List<Line> lines = lineService.getAllLines();

        Graph graph = new Graph(lines);
        return PathResponse.of(graph.getShortestPathResult(startStation, arrivalStation));
    }
}
