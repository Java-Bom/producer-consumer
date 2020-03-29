# producer-consumer


## 요구사항


1. 사용자가 결제를 요청할 수 있다.
   - 결제의 종류는 카드결제와 현금결제 두가지다.
   - 카드결제 이벤트는 카드사 이름과 결제금액만 받아서 결제한다.
   - 현금결제는 결제금액, 이름을 받아 결제한다.

2. 받은 결제 요청을 비동기로 처리한다.
    - 카드결제와 현금결제는 각각 스레드 1개씩으로 처리한다.
    - 이벤트를 소모하기 시작할때 로그로 남겨놓는다.
    - 결제 요청이 100개 이상 쌓여있는 상태에서 들어오는 요청은 실패처리한다.

3. 결제가 성공하면 디비에 저장한다.
    - 카드결제이력과 현금결제이력을 따로 관리한다.

