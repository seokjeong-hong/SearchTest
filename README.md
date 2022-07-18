# searchTest
## Contents
- 카카오 검색 API, 네이버 검색 API를 통해 각각 최대 5개씩, 총 10개의 키워드 관련 장소를 검색 (특정 서비스 검색 결과가 5개 이하면 최대한 총 10개에 맞게 적용)
- 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드 목록을 제공

## Logic
- **업체 판단 기준**
  - html tag 제거 후 장소명 비교
     - 선택이유
      - naver 장소검색 open api에는 html 태그가 포함되어 넘어오는 경우가 있어 태그 제거 후 비교
      - 태그 제거 후에도 같은 장소이지만 각 api에서 장소명이 다르게 나타나는 경우가 있어 이후 문자열 유사도 비교 혹은 위치 비교로 개선해야할 필요가 있음
- **최대한 총 10개의 장소 검색**
  - kakao open api 조회시 10개 조회, naver open api 조회시 5개 조회(naver는 한 번 호출 시 최대 5개 조회가능)
  - kakao open api와 naver open api에서 중복된 장소 추출
  - 총 개수 10에서 중복된 장소를 제외하고 남은 개수를 구하고, 남은 개수가 홀수일 경우 kakao에 remain/2+1, 아닐 경우 kakao와 naver 모두 remain/2
  - kakao, naver에 할당 가능한 수보다 적은 결과가 있으면 그 수만큼 타 open api에 추가로 가져오기

## Development Environment
- Java 17
- SpringBoot 2.4.5
- Gradle
- H2
- JPA
- Open Feign (FeignClient를 사용해 interface로 간단히 호출 가능하여 선택)
- Resilience4j (Netflix Hystrix가 2018년 11월부터 개발이 중단되어 Resilience4j 선택)


## Test URI
- H2 실행 후 진행 (http://h2database.com/html/main.html 에서 다운로드 후 h2/bin 에서 ./h2.sh)
- 키워드 관련 장소검색 및 TOP10 키워드 조회 : Scratches/search-http-request.http 에서 테스트 가능)
