# 프로젝트명
비트코인 자동매매 프로그램

# 프로젝트 설명
내가 설정한 기준으로 비트코인을 자동으로 매수 및 매도를 해주는 프로그램이다.
1. 매매할 코인을 선택한다.
2. 매수할 수량을 선택한다.
3. 매수와 매도 조건을 선택한다.
4. "자동매매 시작" 버튼을 클릭한다.
   
![image](https://github.com/ljs14741/Autotrading/assets/39641715/347595fd-6a71-4342-8317-1d1049b7a5e2)

추가 기능
1. 백테스팅 기능
  - 백테스팅: 과거 데이터를 기반으로 트레이딩 전략이 어떤 성과를 냈는지 테스트하여 실행 가능성을 살펴보는 것
  - 코인과 백테스팅 기간 설정 후 "백테스팅 시작" 버튼을 클릭하면 아래 사진처럼 기간별 수익률을 확인할 수 있다.          // 아래 사진 수정좀 찬규
    
![image](https://github.com/ljs14741/Autotrading/assets/39641715/cee600f7-2925-4e81-a044-e7a20cdbcaef)

2. 변동성돌파 전략 백테스팅 기능
  - 변동성 돌파: 일일 단위로 일정 수준 이상의 범위를 뛰어넘는 강한 상승세를 돌파 신호로 상승하는 추세를 따라가며 일 단위로 빠르게 수익을 실현하는 단기매매 전략
  - 코인을 선택 후 "변동성돌파 백테스팅" 버튼을 클릭하면 아래 사진처럼 기간별 수익률을 확인할 수 있다.
    
![image](https://github.com/ljs14741/Autotrading/assets/39641715/81e14415-11ca-4422-b74e-a9b6d9d1f616)

3. 진수의 백테스팅 기능
  - 나만의 투자방법으로 백테스팅 기능 추가
  - 일봉기준으로 매수시점을 잡았고, 이후 수익률을 확인할 수 있다.
  - 아래 사진은 리플코인을 기준으로 매수시점으로부터 약 3개월만에 31프로의 수익률을 확인할 수 있다.
![image](https://github.com/ljs14741/Autotrading/assets/39641715/832065ca-63e7-4ee8-9232-8fc92f745e8a)

※ 매수, 매도 기능은 지금 당장 실제로 투자할 것이 아니기 때문에 주석 처리하여 동작하지 않습니다.

※ AWS도 프리티어를 이용하고 있어서 인스턴스는 종료해놓은 상태입니다.


# 기술
- 업비트 API 연동
- JSP (jQuery, ajax) - https://blog.naver.com/ljs14741/223253596825
- JPA_QueryDsl - https://blog.naver.com/ljs14741/223314470889
- JPA_NativeQuery - https://blog.naver.com/ljs14741/223275377156
- SwaggerUi - https://blog.naver.com/ljs14741/223294620254
- JPA - https://blog.naver.com/ljs14741/223272796359
- AWS - https://blog.naver.com/ljs14741/223277550867

# 개선할 부분 
- 자바 백엔드 개발자로 성장하기 위해서 스프링부트와 위의 기술들을 접목하여 프로그램을 만들었습니다.
- (진행중)처음에 설계한 대로 어느정도 완성하였지만, 실제로 수익을 많이 낼 수 있는 프로그램을 만들기 위해서는 투자에 대한 공부를 하여 좀 더 좋은 수익을 낼 수 있는 기능을 추가할 것입니다.
- (완료)JPA를 처음 사용하는 거라 CRUD를 적용하는 것이 어려웠습니다. findByAll 등의 내장된 함수와 NativeQuery를 사용하였지만 queryDSL을 추가로 공부할 예정입니다.
- (완료)실무에서 백엔드와 프론트엔드간의 원활한 협업을 위해 사용하는 Swagger UI 기능을 추가하여 공부할 예정입니다.
- AWS와 비슷하며 요즘 핫하여 많이 들어본 Docker도 사용해보며 공부할 예정입니다.
- 기간과 결과에 중점적으로 프로젝트를 완수하다 보니 코드에 미비한 점이 많아, 리팩토링을 통하여 코드의 간결화를 진행할 예정입니다.

# 멤버
- 팀장: 이진수 https://github.com/ljs14741
- 팀원: 송찬규 https://github.com/SongChan-Gyu
- 참고자료: https://www.notion.so/c2841aba6c8a4a31b67622e184ed24b7

# 뱃지
![js](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![js](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![js](https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white)
![js](https://img.shields.io/badge/CSS-239120?&style=for-the-badge&logo=css3&logoColor=white)
![js](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white)
![js](https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![js](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
