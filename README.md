# 배민찬 서비스 - 장바구니

---
## 진행 방법
* 자신의 계정으로 fork한다.
* fork한 프로젝트를 자신의 컴퓨터로 clone한 후 디렉토리로 이동한다.
  * 자신의 github id가 javajigi라면 다음과 같이 자신의 브랜치만 clone할 수 있다.
  * ex) git clone -b javajigi --single-branch https://github.com/wwh-techcamp-2018/baeminchan_cart
* 자신의 계정 브랜치에서 요구사항을 구현하고 add, commit, push한다.
* github 서비스에서 pull request를 보낸다.
  ex) javajigi/baeminchan_cart javajigi 브랜치 => wwh-techcamp-2018/baeminchan_cart javajigi 브랜치

---
## 요구사항
#### 장바구니 기능 요구사항
* 배민찬의 장바구니 기능을 구현한다. 
  * 반찬 목록 페이지는 필수로 구현해야 하며, 반찬 상세 페이지는 선택이다.
* 수량은 1개에서 무한대로 입력가능. 
* 수량을 늘릴때마다 가격이 실시간으로 바로 변경돼서 보여져야 함. 
* 담기를 누르면 ajax요청을 보낸 후 응답을 받는다.
  * 담기결과 응답을 받은 후에는 우측 장바구니 UI에 갯수가 표시되고 바로 하단에 담기완료라는 layer가 애니메이션과 함께 나타났다가 사라짐.
  * 위 기능은 배민찬 실제 서비스의 장바구니 담기 기능을 참고한다.
* 로그인하지 않은 사용자도 장바구니에 상품을 담을 수 있어야 한다.
* 로그아웃 상태에서 로그인을 하더라도 장바구니 상품 목록이 유지되어야 한다.
* 관리자는 각 사용자가 장바구니에 상품 등록 이력을 추적해 추후 상품 발굴, 사용자 패턴을 분석하는데 활용하고 싶다.
  * 상품을 등록한 후 구매까지 이어졌는지의 여부, 상품이 추가되었다가 바로 취소되는 경우 등등 향후 데이터 분석을 위해 필요한 데이터를 쌓는다.

---
#### 반찬 가격 결정 정책
* 각 반찬마다 할인율이 있다.
* 같은 반찬을 10개이상 주문하면 기존 할인율에서 5% 추가 할인한다.
* 할인율이 20% 이상인 반찬은 10개 이상 주문하더라도 5% 추가 할인하지 않는다.
* 구매 금액이 4만원 미만인 경우 2500원의 배송비가 추가된다.
* 구매 금액이 4만원 이상 구매시 배송비가 무료이다.

---
#### 프로그래밍 요구사항
* 다른 개발자가 읽기 좋은 코드를 구현하기 위해 노력한다.
* 비지니스 로직에 대한 단위 테스트를 구현한다.(필수)
* 인수테스트(Acceptance Test)를 구현한다.(선택)
