# PIXEL Game
********
## 게임 컨셉:
### High Concept:
플레이어는 보드판 위에서 캐릭터를 조작하여 다양한 스탯을 획득하고, 이를 활용해 몬스터를 물리쳐 스테이지를 클리어하는 게임입니다. 플레이어는 캐릭터를 날려 벽에 부딪히면서 몬스터를 무찔러 스테이지를 클리어합니다.

### 핵심 메커니즘:
- **플레이어 조작**: 사용자가 직접 컨트롤하는 하단 플레이어 캐릭터는 원하는 방향으로 날려서 벽에 부딪히면 튕겨져 나오는 메커니즘을 가지고 있습니다.
- **아이템**: 공격력, 방어력, 폭탄 아이템 존재합니다.
하단 플레이어와 부딪힌 아이템으로 공격력, 방어력을 일시적으로 얻을 수 있고, 폭탄과 부딪히면 하단 플레이어의 이동이 멈추고 한 라운드가 종료됩니다.

********
## 개발 범위
- **보스 스테이지**:   총 1개
- **일반 스테이지**:   총 2개
- **보너스 스테이지**: 총 1개

### 몬스터 및 전투
- **상단 플레이어**: 몬스터와의 전투 상황을 보여줍니다.
- **하단 플레이어**: 컨트롤 가능한 캐릭터, 원하는 방향으로 날릴 수 있습니다.
- **몬스터 종류**:
  - **그린 슬라임**: 빠른 속도로 이동하며 근접 공격을 합니다.
  - **레드 슬라임**: 많은 체력을 가지고 있으며, 근접 공격을 합니다.
  - **보스 슬라임**: 높은 공격력과 많은 체력을 가진 몬스터 입니다.
### 보너스 스테이지
- 체력이나 공격력을 영구적으로 증가시키거나 감소시킬 수 있습니다.
********
## 예상 게임 실행 흐름
- 플레이어는 각 스테이지를 클리어하며 랜덤으로 나오는 보너스 스테이지를 통해 추가 스탯을 얻거나 잃을 수 있습니다. 최종적으로 모든 스테이지를 클리어하여 보스 스테이지에 도달합니다.

********
## 개발 일정
| 1주차   | 2주차       | 3주차        | 4주차         | 5주차     | 6주차            | 7주차          | 8주차  | 9주차  |
|:-------:|:-----------:|:------------:|:-------------:|:---------:|:----------------:|:--------------:|:------:|:------:|
| ui<br>배치  | 아이템<br>랜덤 배치 | 캐릭터 이동<br>구현 | 캐릭터<br>충돌 처리 | 몬스터<br>구현 | 스테이지1,2<br>구현 | 보스 스테이지<br>구현 |디버깅|디버깅|


