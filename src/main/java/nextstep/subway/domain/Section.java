package nextstep.subway.domain;

import nextstep.subway.exception.sections.SectionDistanceException;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Section {

    private static final String SECTION_MINIMUM_DISTANCE_EXCEPTION = "구간의 최소거리는 1이상 입니다";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "line_id")
    private Line line;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "up_station_id")
    private Station upStation;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "down_station_id")
    private Station downStation;

    private int distance;

    public Section() {
    }

    public Section(Long id, Line line, Station upStation, Station downStation, int distance) {
        this(line, upStation, downStation, distance);
        this.id = id;
    }

    public Section(Line line, Station upStation, Station downStation, int distance) {
        if (isInValidDistance(distance)) {
            throw new IllegalArgumentException(SECTION_MINIMUM_DISTANCE_EXCEPTION);
        }

        this.line = line;
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
    }

    public static Section combineOf(Section front, Section back) {
        return new Section(front.getLine(), front.getUpStation(), back.getDownStation(), back.getDistance() + front.getDistance());
    }

    public Section divideSectionByMiddle(Section section) {
        if (isLessThanDistance(section.getDistance())) {
            throw new SectionDistanceException();
        }

        if (hasSameUpStation(section.getUpStation())) {
            return new Section(this.line, section.getDownStation(), this.downStation, this.distance - section.getDistance());
        }
        return new Section(this.line, this.upStation, section.getUpStation(), this.distance - section.getDistance());
    }

    public boolean hasNotDownStation(Station station) {
        return !this.downStation.equals(station);
    }

    public boolean hasSameUpStation(Station station) {
        return this.upStation.equals(station);
    }

    public boolean hasSameDownStation(Station station) {
        return this.downStation.equals(station);
    }

    public boolean hasStationIn(Section section) {
        return containsStation(section.getUpStation()) || containsStation(section.getDownStation());
    }

    public Long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public int getDistance() {
        return distance;
    }

    private boolean isInValidDistance(int distance) {
        return distance <= 0;
    }

    private boolean containsStation(Station station) {
        return this.upStation.equals(station) || this.downStation.equals(station);
    }

    private boolean isLessThanDistance(int distance) {
        return this.distance <= distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(getId(), section.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
