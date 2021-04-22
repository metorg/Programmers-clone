# Programmers-clone


![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fmsmn1729%2FProgrammers-clone&count_bg=%2306A1F1&title_bg=%23555555&icon=iconify.svg&icon_color=%23FFFFFF&title=hits&edge_flat=false)

- 클론 코딩할 사이트: [프로그래머스](http://programmers.co.kr/learn/challenges)

- 클론 코딩한 사이트: http://programmers99.shop/
  - AWS 비용 문제로 서버가 닫힐 수 있습니다.

- 시연 동영상: https://youtu.be/Ejoa31jhNrc

----

## 전체 문제 조회
![image](https://user-images.githubusercontent.com/59201008/115716349-0fd52600-a3b4-11eb-8e07-5ec02c08a5b7.png)
- 클론코딩한 화면이고 실제 프로그래머스에 있는 문제들을 전부 보여줍니다.

----

## 필터링한 문제 조회
![image](https://user-images.githubusercontent.com/59201008/115716530-385d2000-a3b4-11eb-860a-d4057a0d496a.png)
- 클론코딩한 화면이고 실제 프로그래머스에 있는 문제들 중에 좌측에 선택한 조건에 의해 필터링 된 문제들만 보여줍니다.

----

## 배운 점

- 프로그래머스 사이트 클론 코딩을 하며 front-end와 back-end의 협업 경험을 쌓았다.
- DB에 저장하기 위해 스크래핑을 할 때, 자바의 JSOUP 라이브러리로 진행하다 상세 문제 api접근이 막혀있어서 selenium을 활용했다.
- 스크래핑을 하면서 html을 보기 위해 개발자 도구를 자주 활용하게 되었고 셀레니움을 잘 활용할 수 있도록 학습했다.
- DB에 저장한 데이터를 api로 프론트에 넘겨주기 위해 스프링부트로 개발했다.
- 상세 문제 필터링하는 부분의 알고리즘을 구현하며 해시맵과 유사한 방법으로 필터링 기능을 구현하게 되었다.
- 문제 필터링과 페이징 기능을 합치는 과정에서 어려움이 있었는데, 전체 문제들 중 필터링한 문제들만 하나의 임시 DB 테이블에 저장한 후 그 테이블에서 페이징을 하는 방식으로 해결했다.
- 쿼리문을 사용하지 않고 스프링 내에서 DB 쿼리 처리를 해주는 Spring data JPA의 편의성을 깨닫게 되었다.
- DB 테이블 join과 many to one (다대일) 방식에 대해 알게 되었다.
