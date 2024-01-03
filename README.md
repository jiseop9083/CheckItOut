# 째끼라우

- 팀원
  
  한양대학교 컴퓨터소프트웨어학부 신지섭(sinphi03@gmail.com)
  
  카이스트 전산학부 김자누(pistachio@kaist.ac.kr)


- **슬로건**: 내 손 안의 출석부
- **개발 도구**: <img src="https://img.shields.io/badge/Android-34A853?style=for-the-badge&logo=Android&logoColor=white">
- **사용 언어**: <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Python&logoColor=white">
- **target device**: Galaxy S7

```
minSdkVersion 26
targetSdkVersion 33
```

&nbsp;
&nbsp;
## 프로젝트 소개(개발 동기)

- **타깃 사용자 그룹**:
    - 대리 출석을 방지하고 싶어서 출석체크를 위해 사진을 활용하시는 교수님
    - 복잡한 과정 없이 본인의 휴대폰으로 출결을 관리하고 싶은 교수님.
- 교수자들이 휴대폰을 이용하여 간편하게 출석 관리를 할 수 있도록 도와주는 어플리케이션입니다. 교수자들이 효율적으로 학생들의 출결을 체크하는 데 필요한 각종 기능이 탑재되어 있습니다.
- 사용자들은 편리하게 원하는 과목과 날짜에 대한 출석 체크를 클릭 한 번으로 할 수 있습니다. 학생들의 사진과 얼굴을 대조할 수 있기 때문에 대리 출석 또한 방지할 수 있습니다.
- 해당 과목을 수강하는 학생들의 정보도 간편하게 확인 가능하며, 해당 과목의 월별 출석 현황을 한 눈에 파악할 수 있습니다.


&nbsp;
&nbsp;

## 구성 및 기능 소개

### 0. 메인 화면

- `ViewPager2`와 `TabLayout`을 사용하여 탭 바 아이콘을 클릭하거나 화면을 스와이프 했을 때, 탭이 자연스럽게 전환할 수 있도록 구현하였습니다.
- `navigation drawer`를 이용해 슬라이드와 왼쪽 상단의 아이콘으로 사용자 프로필과 과목명을 선택할 수 있습니다

### 1. Tab1 프로필

- 해당 과목을 수강하는 학생들의 정보(이름, 학번, 전화번호, 출석률)을 확인할 수 있는 탭입니다.
- 학생들의 정보를 json 파일에서 불러와서 `recyclerview`로 보이도록 만들었습니다.
- `Intent`를 이용해 문자 아이콘과 전화 아이콘을 클릭하면 각각 메신저 앱과 통화 앱으로 연결되도록 구현하였습니다.

### 2. Tab2 출석 체크

- 학생 사진과 이름을 보고 출석 체크를 할 수 있는 탭입니다.
- 사진을 클릭하면 출석, 결석 상태를 전환할 수 있습니다.
- `gridView`를 이용해 사진을 그리드 형태로 불러오도록 하였습니다.
- `ContentResolver` 클래스와 `MediaStore` 클래스를 이용하여 핸드폰 갤러리에서 사진 이름이 student로 시작하는 사진들을 불러왔습니다.
- `DatePickerDialog`를 이용해  출석 체크를 하고자 하는 날짜를 선택할 수 있도록 하였습니다. 오늘보다 앞서간 날짜는 선택할 수 없도록 비활성화하였습니다.

### 3. Tab3 월별 출석 현황

- 캘린더 형태로 출석 현황을 확인할 수 있는 탭입니다.
- 커스텀 캘린더를 제작하여 캘린더에 날짜별 출석 인원과 결석 인원을 표시하였습니다.
    - `ViewPager2`와 `LinearLayout`을 이용해 월별 페이지를 위아래로 스와이프하여 이동할 수 있도록 구현하였습니다.
    - 각 월별 페이지에서는 `GridLayout`을 이용하여 각 날짜의 정보를 화면에 보여주었습니다.
        - 수업이 있는 날짜에는 출석 인원과 결석 인원을 작게 표시해주고, 수업이 없는 날이거나 현재 월(month)이 아닐 경우 표시하지 않습니다.
- 각 날짜를 선택하면 해당하는 일자의 출석의 상세 페이지(`dialog`)를 띄워줍니다.
    - 두 개의 `RecyclerView`를 이용해 출석한 인원과 결석한 인원을 동시에 보여줍니다.
    - X 버튼을 누르거나 dialog 바깥을 클릭하면 창을 닫을 수 있습니다.

&nbsp;
&nbsp;

## 앱 사용 시나리오

사용자는 출석부를 매번 챙기고 수정할 필요 없이 앱을 이용하여 여러 수업을 한 번에 관리할 수 있습니다.

- 시나리오 1: 수업 시작
    1. 사용자는 수업을 하기 위해 강의실에 들어갑니다.
    2. 상단 네비게이션 탭을 선택하여 해당 과목 명을 클릭, 해당 과목의 출석부로 들어갑니다.
    3. 두 번째 “출석 체크” 탭에 들어가 학생들을 하나씩 호명하고 사진과 얼굴을 대조하여 출석, 혹은 결석 표시를 합니다.
    4. 오지 않은 학생들에게 첫 번째 “프로필” 탭을 이용하여 전화를 걸거나, 문자를 보낼 수 있습니다.
- 시나리오 2: 수업 이후
    1. 조금 지각한 학생이거나, 출결이 잘못 처리된 학생이 사용자에게 출석 상태 변경을 요청합니다.
    2. 오늘 지각한 학생은 두 번째 “출석 체크” 탭에서 상태를 변경할 수 있습니다.
    3. 이전 수업에 출석했으나, 결석으로 잘못 처리된 학생의 기록을 바꾸기 위해 두 번째 “출석 체크” 탭 상단의 날짜 선택을 눌러 이전 수업의 기록을 변경합니다.
- 시나리오 3: 성적 부여
    1. 학생들의 성적에 출석률을 반영하기 위해 학기가 끝난 이후 첫 번째 “프로필” 탭의 출석률을 확인합니다.
- 시나리오 4: 강의 개선
    1. 세 번째 “월별 출석 현황” 탭을 확인하여 학생들이 언제 출석과 결석을 많이 하고 이 정보를 강의 일정을 개선하기 위해 활용할 수 있습니다.

&nbsp;
&nbsp;

## 스크린샷
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/99ae8f35-08a0-4078-829a-3b068e535f95.png" width="200" height="200"/>
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/115ea13b-770e-4f2b-a30e-edd9d0190f72.png" width="180" height="320"/>
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/eb8d5a39-89a7-4422-96b1-977c99b30f70.png" width="180" height="320"/>
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/1d50d0ee-4427-46d4-9e7e-b5d686c7c5a8.png" width="180" height="320"/>
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/8dec21cc-32ed-4834-8605-40e431f9ec0f.png" width="180" height="320"/>
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/f58bf5cd-aecf-415e-928f-4539195dd031.png" width="180" height="320"/>
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/d324166a-6213-4290-bc2c-ba914abaa9d5.png" width="180" height="320"/>
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/aa8e8648-3b63-4794-86f2-5a23d0137cd4.png" width="180" height="320"/>
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/2b5e21ef-53e8-4f6d-81cc-98f8ab7c1341.png" width="180" height="320"/>
<img src="https://github.com/jiseop9083/madcampWeek1/assets/90448022/9331bc3b-6131-4f05-9405-20a965cd5e0a.png" width="180" height="320"/>





