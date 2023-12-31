# 지하철 노선도 미션

[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

## Step4 PR 수정 TODO list

- [ ] Sections의 deleteSection 메서드 내부의 추상화 수준 동일하게 만들기
- [ ] 예외 처리에 대한 고민하기, 장단점 생각하기
- [x] Graph 결과 반환 객체 만들기
- [ ] 테스트 케이스 추가하기

## Step4 TODO List

- [x] 인수 조건 도출
- [x] 인수 테스트 작성
- [x] 경로 조회 기능 구현
    - [x] 출발역이 존재하지 않는경우 예외발생
    - [x] 도착역이 존재하지 않는경우 예외발생
    - [x] 출발역과 도착역이 연결되어있지 않은경우 예외발생
    - [x] 출발역과 도착역이 같은경우 예외발생
    - [x] 출발역과 도착역의 최단거리를 정상 조회한다

## Step4 - 인수 조건

```
BackGround:
    Given 2호선 교대역 - 강남역
    Given 3호선 교대역 - 남부터미널역 - 양재역
    Given 신분당선 강남역 - 양재역

Scenario: 출발역이 존재하지 않는경우 예외발생
    When 건대입구역에서 강남역까지 촤단거리를 조회하면
    Then 예외가 발생한다
    
Scenario: 도착역이 존재하지 않는경우 예외발생
    When 강남역에서 건대입구역까지 촤단거리를 조회하면
    Then 예외가 발생한다
    
Scenario: 출발역과 도착역의 최단거리를 정상 조회한다
    When 강남역에서 남부터미널역까지 촤단거리를 조회하면
    Then 역 목록과 거리를 응답한다
    
Scenario: 출발역과 도착역이 연결되어있지 않은경우 예외발생
    Given 기존 노선에 연결되어있지 않은 광화문역을 생성
    When 광화문역에서 강남역까지 촤단거리를 조회하면
    Then 예외가 발생한다
    
Scenario: 출발역과 도착역이 같은경우 예외발생
    When 강남역에서 강남역까지 촤단거리를 조회하면
    Then 예외가 발생한다
    
```

## Step3 PR 수정 TODO list

- [x] Sections의 deleteMiddleStation 내부 return 제거, early return으로 변경해보기
- [x] if문 안의 deleteFirstOrLastStation에 CQRS 적용해보기
- [x] SectionsTest에서 displayName 추가와 주석 제거
- [x] Sections에서 isSizeLessThanOne 의 매직넘버 제거
- [x] Line에서 deleteLastSection 메서드 이름 변경
- [x] Sections에서 메서드 레퍼런스 활용하기
- [x] 제귀함수 고민하기

## Step3 TODO List

- [x] 인수 조건 도출
- [x] 인수 테스트 작성
- [x] 기존 구간 제거 구현
    - [x] 상행 종점역을 삭제할 수 있다.
    - [x] 하행 종점역을 삭제할 수 있다.
    - [x] 중간역을 삭제할 경우, 중간역의 좌우 역이 연결되어 하나의 새로운 구간이 되며, 구간의 거리는 그 합과 같다.
    - [x] 구간이 하나인 경우, 해당 마지막 구간은 제거할 수 없다
    - [x] 등록되지 않은 역은 삭제할 수 없다.

### Step3 - 인수 조건

```
BackGround:
    Given 2개의 구간을 가진 노선을 생성하고
    
Scenario: 상행 종점역 삭제
    When 상행 종점역을 상행역으로 하는 구간에 삭제 요청시
    Then 구간 삭제가 성공하고
    Then 역 목록을 응답 받는다
    
Scenario: 하행 종점역 삭제
    When 하행 종점역을 하행역으로 하는 구간에 삭제 요청시
    Then 구간 삭제가 성공하고
    Then 역 목록을 응답 받는다
    
Scenario: 중간역 삭제
    When 두 구간의 중간역을 삭제 요청시
    Then 구간 삭제가 성공하고
    Then 역 목록을 응답 받는다
    
Scenario: 구간이 하나인 경우, 해당 마지막 구간은 제거할 수 없다
    When 상행종점역과 하행종점역을 제외한 모든 역을 삭제요청하
    When 상행종점역을 삭제 요청시
    Then 구간 삭제가 실패한
    
Scenario: 등록되지 않은 역은 삭제할 수 없다.
    When 등록되지 않은 역에 삭제 요청시
    Then 구간 삭제가 실패한다    

```

## Step2 PR 추가 수정 TODO List

- [x] SectionsTest 경계값 관련된 테스트를 추가하기
- [x] Custom Exception 세분화 하기
- [x] 인수조건 주석 다시 생각해보기
- [x] Sections의 add의 hasUpStation && hasDownStation 부분 메서드로 추출하기
- [x] 신규역을 추가할때 StackOverFlow 가 발생하는 재귀호출 부분 해결하기
- [x] 구간 거리테스트 재확인 하기

## Step2 PR 수정 TODO list

- [x] sections의 isSectionsUpStation, isSectionsDownStation 두 if문 합치기
- [x] sections의 마지막 예외를 던지던 부분의 위치 고민하기
- [x] add_section_front_at_line 테스트 코드 보강하기
- [x] Sections 내부의 isGreaterThanDistance 를 Section 내부로 옮기기
- [x] section 의 distance 가 나누어졌는지 다시 생각해보기
- [x] 예외를 정의하여 어떤 장점/단점이 있는 지 고민해보기
- [x] 인수 조건의 상세 주석이 필요한가?
- [x] Sections.add() 메서드의 List 를 외부에서 생성하여 addStationByOrder 의 인자로 넘기는 부분 수정하기
- [x] create_section_with_invalid_distance에 @DisplayName 추가하기
- [x] Sections.divideSectionByMiddle() 단위 테스트 추가하기

## Step2 TODO List

- [x] 인수 조건 도출
- [x] 인수 테스트 작성
- [x] 새로운 구간 추가하기
    - [x] 상행종점역 으로 구간 추가하기 (신규 구간의 하행역이 기존 구간의 상행 종점역과 동일할 경우)
    - [x] 하행종점역 으로 구간 추가하기 (신규 구간의 상행역이 기존 구간의 하행 종점역과 동일할 경우)
    - [x] 역 사이에 새로운 역을 등록할 경우 (기존 구간 사이에 추가)
        - [x] 기존 구간 A-C에 신규 구간 A-B를 추가하는 경우 A역을 기준으로 추가되며 결과로 A-B, B-C 구간이 생김
        - [x] 기존 역 사이 길이보다 크거나 같으면 등록을 할 수 없다
        - [x] 상행역과 하행역이 이미 노선에 모두 등록되어 있다면 추가할 수 없다
        - [x] 상행역과 하행역 둘 중 하나도 포함되어있지 않으면 추가할 수 없다
    - [x] 노선 조회시 응답되는 역 목록 수정 (구간이 저장되는 순서로 역 목록을 조회할 경우 순서가 다르게 조회될 수 있기 떄문에 이를 수정)

### Step2 - 인수 조건

```
BackGround:
    Given 1개의 구간을 가진 노선을 생성하고
    Given 신규로 추가할 역을 생성 한다

Scenario: 새로운 상행 종점역 추가
    When 기존 구간의 상행 종점역과 동일한 하행역을 가지고
    When 구간 생성 요청 하면
    Then 구간 생성이 성공하고
    Then 역 목록을 응답 받는다
    
Scenario: 새로운 하행 종점역 추가
    When 기존 구간의 하행 종점역과 동일한 상행역을 가지고 
    When 구간 생성 요청 하면
    Then 구간 생성이 성공하고
    Then 역 목록을 응답 받는다
    
Scenario: 역 사이에 새로운 역 추가
    When 기존 구간의 상행 종점역과 동일한 상행역을 가지고 
    When 구간 생성 요청 하면
    Then 구간 생성이 성공하고
    Then (신규역 - 하행역)의 길이는 기존 구간의 길이에서 (상행역 - 신규역)의 길이를 뺸 길이로 할당하고
    Then (상행역 - 신규역 - 하행역)의 순서로 배치되고
    Then 역 목록을 응답 받는다
    
Scenario: 역 사이에 새로운 역 추가할때, 기존 역 사이 길이보다 크거나 같으면 등록을 할 수 없다
    When 기존 구간의 상행 종점역과 동일한 상행역을 가지고
    When 새로운 하행역 기존의 구간 거리보다 같거나 클떄
    When 구간 생성을 요청하면 
    Then 구간 생성이 실패한다
    
Scenario: 역 사이에 새로운 역 추가할때, 상행역과 하행역이 이미 노선에 모두 등록되어 있다면 추가할 수 없다
    When 기존 구간의 상행 종점역과 동일한 상행역을 가지고
    When 기존 구간의 하행 종점역과 동일한 하행역을 가지고
    When 구간 생성을 요청하면 
    Then 구간 생성이 실패한다
    
Scenario: 역 사이에 새로운 역 추가할때, 상행역과 하행역 둘 중 하나도 포함되어있지 않으면 추가할 수 없다
    When 기존 구간의 상행 종점역과 다른 상행역을 가지고
    When 기존 구간의 하행 종점역과 다른 하행역을 가지고
    When 구간 생성을 요청하면 
    Then 구간 생성이 실패한다
```

## Step1 TODO List

- [x] 구간 단위 테스트 작성 (LineTest)
- [x] Stub을 사용한 구간 서비스 단위 테스트 작성 (LineServiceMockTest)
- [x] Mock 없이 구간 서비스 단위 테스트 (LineServiceTest)
- [x] 단위 테스트를 기반으로 비즈니스 로직을 리팩터링 하기

## Step1 PR 추가 수정 TODO List

- [x] Sections.dontHasDonwStation 의 이름 변경하기
- [x] Line의 getSectionsSize 에 대하여 고민하기, 단순 테스트를 위한 메서드인가?
- [x] Sections의 getStations, deleteLastSection 빈 행 적용기
- [x] Sections의 getStations()에서 isInValidSize 의 적용과 이름이 적당한지 생각해보기
- [x] Section과 Sections에 대한 단위테스트 작성

## Step1 PR 수정 TODO list

- [x] Line의 getter를 비지니스 메서드보다 아래에 두기
- [x] IndexOutOfBoundsException 던지는 부분에 대한 변경
- [x] new ArrayList 로 wrapping한 부분 수정
- [x] Line에서 상행역을 가져오는 메서드 만들기
- [x] Line에서 마지막 구간을 가져오는 메서드 만들기
- [x] Line.deleteLastSection 에서 부정조건 피하기
- [x] Line.getFirstSection 매직넘버 0피하기
- [x] LineServiceMockTest에서 구현 완료된 부분의 주석 제거
- [x] 불필요한 Line.removeLastSection 제거
- [x] Sections를 1급 컬렉션으로 변경
- [x] Line<Section> 외부 노출 막기
