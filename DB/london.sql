-- 이름: london.sql

-- SQLPlus 실행방법 
-- SQL>@london.sql
-- SQL Developer F5 스크립터 실행


DROP USER london CASCADE;

CREATE USER london IDENTIFIED BY london DEFAULT TABLESPACE users TEMPORARY TABLESPACE temp PROFILE DEFAULT;

GRANT CONNECT, RESOURCE TO london;
GRANT CREATE VIEW, CREATE SYNONYM TO london;
ALTER USER london ACCOUNT UNLOCK;

conn london/london;

-- 주메뉴 테이블
CREATE TABLE Food (
  Foodid      NUMBER(3) PRIMARY KEY,
  Foodname    VARCHAR2(40),
  price       NUMBER(10), 
  Tabid       Number(3) REFERENCES TabList(Tabid)
);

-- 탭목록 테이블
Create Table TabList (
    Tabid Number(3) Primary key,
    Tabname VARCHAR2(40)
    );

-- 직원 테이블
CREATE TABLE  employee (
  empid      NUMBER(2) PRIMARY KEY,  
  empname      VARCHAR2(40),
  sex          VARCHAR2(10),
  rank        VARCHAR2(40),
  phone       VARCHAR2(20),
  salary     VARCHAR2(50)
);

-- 일매출 통계 테이블
CREATE TABLE Dailyincome (
    dailyid NUMBER(2) PRIMARY KEY,
    dailyfoodname VARCHAR2(40) REFERENCES Food(Foodname),
    dailyprice NUMBER(8) REFERENCES Food(price),
    dailytime VARCHAR2(30)
    );
    
-- 월매출 통계 테이블
CREATE TABLE Monthincome (
    monthid NUMBER(2) PRIMARY KEY,
    monthfoodname VARCHAR2(40) REFERENCES Food(Foodname),
    monthprice NUMBER(8) REFERENCES Food(price),
    monthtime VARCHAR2(30)
    );
    
-- 테이블 생성
INSERT INTO Food VALUES(1, '감자식빵', 3000);
INSERT INTO Food VALUES(2, '곡물식빵', 3500);
INSERT INTO Food VALUES(3, '단호박검은깨', 4000);
INSERT INTO Food VALUES(4, '데니쉬식빵', 4500);
INSERT INTO Food VALUES(5, '모닝토스트', 3000);
INSERT INTO Food VALUES(6, '밤식빵',4000);
INSERT INTO Food VALUES(7, '그레인바게트', 4000);
INSERT INTO Food VALUES(8, '크렌베리빵', 3500);
INSERT INTO Food VALUES(9, '자파타', 2500);
INSERT INTO Food VALUES(10, '찰떡브레드', 3000);
INSERT INTO Food VALUES(11, '호밀호두빵', 3500);
INSERT INTO Food VALUES(12, '호두건포도빵', 2500);
INSERT INTO Food VALUES(13, '베리베리달링', 3000);
INSERT INTO Food VALUES(14, '초코호두달링', 3000);
INSERT INTO Food VALUES(15, '달콤한초코스틱', 1500);
INSERT INTO Food VALUES(16, '솔직담백비스킷', 2000);
INSERT INTO Food VALUES(17, '찹쌀패스츄리도넛', 2500);
INSERT INTO Food VALUES(18, '초코츄잉스타', 2500);
INSERT INTO Food VALUES(19, '한입쏙토스트', 2000);
INSERT INTO Food VALUES(20, '소시지또띠아', 2000);
INSERT INTO Food VALUES(21, '멕시칸그릴', 2500);
INSERT INTO Food VALUES(22, '햄에그모닝', 3000);
INSERT INTO Food VALUES(23, '트위스트', 2000);
INSERT INTO Food VALUES(24, '소시지조리빵', 2000);


INSERT INTO employee VALUES (1, '홍은수', '남', '매니저','010-1234-5678','2000000');
INSERT INTO employee VALUES (2, '김갑순', '여', '직원','010-2345-2134','1500000');  
INSERT INTO employee VALUES (3, '최선수', '남','직원', '010-2341-1243','1800000');
INSERT INTO employee VALUES (4, '추사랑', '여','매니저', '010-8321-1111','12000000');
INSERT INTO employee VALUES (5, '박우주', '여','직원','010-1241-1245','1300000');

INSERT INTO TabList VALUES (1,'식빵');
INSERT INTO TabList VALUES (2,'도넛');
INSERT INTO TabList VALUES (3,'우리밀');
INSERT INTO TabList VALUES (4,'프랑스빵');


select * from Food;