# 스프링 시큐리티를 이용한 게시판
## 개발기간 
- 24.02.20 ~ 24.02.28

## 개발 환경
- java17
- **IDE** : intelliJ
- **Framework** : Springboot(3.x)
- **Database** : Mysql(8.1.0)
- **ORM** : JPA


## 주요기능
#### 로그인
- 로그인 시 스프링 시큐리티로 검증으로 로그인

#### 회원가입( 회원, 수의사 )
- 회원가입시 스프링 시큐리티 회원가입
- ID 중복 체크(완료)

#### 글
- 글 작성
- 글 삭제
- 글 수정
- 전체 글 리스트 확인

#### 회원 마이페이지
- 회원 정보 변경
- 회원 탈퇴
- 회원이 작성한 글 확인

#### 예외처리
- 회원 예외처리

## 배포
### vm 생성 - vagrant init
<img width="437" alt="create_vm_vagrant" src="https://github.com/kimhyeonjun-96/NoticeBoard/assets/69852555/281f88a8-e703-4eb7-82c5-1e94f5398586">


### vm 생성 - Vagrantfile 작성
<img width="1265" alt="create_vm_vagrant_02" src="https://github.com/kimhyeonjun-96/NoticeBoard/assets/69852555/ae947827-8585-4003-93ed-9929f08e7965">


### vm 생성 - vagrant 실행
<img width="313" alt="create_vm_vagrant_03" src="https://github.com/kimhyeonjun-96/NoticeBoard/assets/69852555/8324f844-0969-4133-80e2-026114848da0">


### vm 확인
<img width="332" alt="check_vm" src="https://github.com/kimhyeonjun-96/NoticeBoard/assets/69852555/d4208287-caa3-4146-a0a2-f6a6c5549456">


### vm 접속
<img width="470" alt="접속" src="https://github.com/kimhyeonjun-96/NoticeBoard/assets/69852555/8d12bd2e-005b-4a8e-927f-f6c8ff5ad828">

### DB와 Java 설치 확인
<img width="1252" alt="check_db_and_java17" src="https://github.com/kimhyeonjun-96/NoticeBoard/assets/69852555/e9dc5cc6-7c5d-488e-b700-2cde49270160">

### 게시판 프로그램 실행
<img width="573" alt="프로그램_빌드_실행" src="https://github.com/kimhyeonjun-96/NoticeBoard/assets/69852555/2cfe7c1c-f944-477c-b0e6-3ce7de02b7fb">

### 결과
<img width="752" alt="성공" src="https://github.com/kimhyeonjun-96/NoticeBoard/assets/69852555/95314ee5-5132-4072-a52e-6729f86264dd">