# GYM 웹페이지 프로젝트

## 목차
1. [프로젝트 개요](#프로젝트-개요)
2. [프로젝트 진행기간](#프로젝트-진행기간)
3. [기능 요약](#기능-요약)
4. [기술 설명](#개발-환경-및-라이브러리)
5. [사용 방법](#사용-방법)
6. [팀 멤버 및 역할](#팀-멤버-및-역할)
7. [프로젝트 구조](#프로젝트-구조)

## 프로젝트 개요
- 프로젝트의 주요 목적, 개발 동기, 해결하고자 하는 문제 또는 제공하고자 하는 가치에 대해 간략하게 설명합니다.
- 프로젝트의 핵심 아이디어와 목표를 명확하게 전달하는 것이 중요합니다.

## 프로젝트 진행기간
- 시작일: 2023년 12월 21일
- 종료일: 2024년 1월 19일
- 유지보수 및 지속적인 보완: 프로젝트 종료 후에도 지속적인 업데이트 및 보완 작업 예정

## 기능 요약
- GYM 관련 간단한 ERP 
- 회원 기능
  1) 회원가입, 로그인, 내정보 확인, 정보수정, PW수정, 계정탈퇴 등 세션을 이용한 로그인 기능
  2) 멤버십(회원권) 결제 기능 / 간략 구현
  3) 결제 후 프로그램(운동종목) 선택 및 예약 기능
  4) 예약 후 본인 출석여부 조회 기능
  5) 출석 정보가 있는 회원계정에 대해 출석한 프로그램 리뷰 작성 기능
  6) 로그인된 전 회원계정에 대해 문의내용 작성 기능
  7) 공지사항 조회 기능
- 직원 기능
  1) 최초 세팅된 최상위 관리자 계정 로그인 후 타 관리자계정 추가기능 / 추가 시 해당계정 소속지점 선택
  2) 로그인된 관리자 계정에대해 공지사항 작성기능
  3) 회원에 의해 작성된 문의사항 답변기능
  4) 회원에 의해 작성된 리뷰 답변기능
  5) 멤버십(회원권) 종류 추가/삭제/수정 기능
  6) 프로그램(운동종목) 추가/삭제/수정 기능
  7) 지점별 본사에 대해 운동장비 발주 기능
  8) 지점별 본인 소속지점 운동장비 재고확인 기능


## 기술 스택
- 백엔드 : Java, Spring Boot , JSP
- 프론트 : HTML5, CSS, JS, jQuery, Vue3, axios,element-plus
- DB : Maria db, Mybatis
- WAS : Tomcat
- 서버 : AWS (Lightsail)
- 형상관리 : Git, GitHub
 
## 사용 방법
- 권도헌 : [QA 방법](여기에 링크를 입력해주세요.)
- 주가희 : [QA 방법](https://peppermint-falcon-0f8.notion.site/QA-cd71c15e40a6481e9148e4c23cc9f887?pvs=4)
- 박겨레 : [QA 방법](https://www.notion.so/QA-490d756e34c24713b3cd68c75ca8229a?pvs=4)
- 김지산 : [QA 방법](https://peppermint-falcon-0f8.notion.site/QA-3ac3df88996f479da47d4dfe7712d757?pvs=4)
  
## 팀 멤버 및 역할
- 권도헌 : 
- 주가희 : 
- 박겨레 : 직원 계정 및 세션 관련 CRUD(직원 Image포함), 공지사항 입력 및 답변 CRUD, 지점정보 관련 CRUD, 
- 김지산 : 회원 계정 및 세션 관련 CRUD(회원 Image포함), 문의사항 입력 및 답변 CRUD, 프로그램 검색에 따른 리뷰 입력 및 답변 CRUD, 멤버십(회원권) CRUD, 결제, 본인 결제정보 확인기능 및 결제취소, 회원명 검색에 따른 전체 결제정보 출력

## 프로젝트 구조
- 프로젝트의 전체적인 구조 및 디렉토리 설명


