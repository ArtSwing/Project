-- �̸�: london.sql

-- SQLPlus ������ 
-- SQL>@london.sql
-- SQL Developer F5 ��ũ���� ����


DROP USER london CASCADE;

CREATE USER london IDENTIFIED BY london DEFAULT TABLESPACE users TEMPORARY TABLESPACE temp PROFILE DEFAULT;

GRANT CONNECT, RESOURCE TO london;
GRANT CREATE VIEW, CREATE SYNONYM TO london;
ALTER USER london ACCOUNT UNLOCK;

conn london/london;

-- �ָ޴� ���̺�
CREATE TABLE Food (
  Foodid      NUMBER(3) PRIMARY KEY,
  Foodname    VARCHAR2(40),
  price       NUMBER(10), 
  Tabid       Number(3) REFERENCES TabList(Tabid)
);

-- �Ǹ�� ���̺�
Create Table TabList (
    Tabid Number(3) Primary key,
    Tabname VARCHAR2(40)
    );

-- ���� ���̺�
CREATE TABLE  employee (
  empid      NUMBER(2) PRIMARY KEY,  
  empname      VARCHAR2(40),
  sex          VARCHAR2(10),
  rank        VARCHAR2(40),
  phone       VARCHAR2(20),
  salary     VARCHAR2(50)
);

-- �ϸ��� ��� ���̺�
CREATE TABLE Dailyincome (
    dailyid NUMBER(2) PRIMARY KEY,
    dailyfoodname VARCHAR2(40) REFERENCES Food(Foodname),
    dailyprice NUMBER(8) REFERENCES Food(price),
    dailytime VARCHAR2(30)
    );
    
-- ������ ��� ���̺�
CREATE TABLE Monthincome (
    monthid NUMBER(2) PRIMARY KEY,
    monthfoodname VARCHAR2(40) REFERENCES Food(Foodname),
    monthprice NUMBER(8) REFERENCES Food(price),
    monthtime VARCHAR2(30)
    );
    
-- ���̺� ����
INSERT INTO Food VALUES(1, '���ڽĻ�', 3000);
INSERT INTO Food VALUES(2, '��Ļ�', 3500);
INSERT INTO Food VALUES(3, '��ȣ�ڰ�����', 4000);
INSERT INTO Food VALUES(4, '���Ͻ��Ļ�', 4500);
INSERT INTO Food VALUES(5, '����佺Ʈ', 3000);
INSERT INTO Food VALUES(6, '��Ļ�',4000);
INSERT INTO Food VALUES(7, '�׷��ιٰ�Ʈ', 4000);
INSERT INTO Food VALUES(8, 'ũ��������', 3500);
INSERT INTO Food VALUES(9, '����Ÿ', 2500);
INSERT INTO Food VALUES(10, '�����극��', 3000);
INSERT INTO Food VALUES(11, 'ȣ��ȣ�λ�', 3500);
INSERT INTO Food VALUES(12, 'ȣ�ΰ�������', 2500);
INSERT INTO Food VALUES(13, '���������޸�', 3000);
INSERT INTO Food VALUES(14, '����ȣ�δ޸�', 3000);
INSERT INTO Food VALUES(15, '���������ڽ�ƽ', 1500);
INSERT INTO Food VALUES(16, '��������Ŷ', 2000);
INSERT INTO Food VALUES(17, '�����н��򸮵���', 2500);
INSERT INTO Food VALUES(18, '�������׽�Ÿ', 2500);
INSERT INTO Food VALUES(19, '���Խ��佺Ʈ', 2000);
INSERT INTO Food VALUES(20, '�ҽ����Ƕ��', 2000);
INSERT INTO Food VALUES(21, '�߽�ĭ�׸�', 2500);
INSERT INTO Food VALUES(22, '�ܿ��׸��', 3000);
INSERT INTO Food VALUES(23, 'Ʈ����Ʈ', 2000);
INSERT INTO Food VALUES(24, '�ҽ���������', 2000);


INSERT INTO employee VALUES (1, 'ȫ����', '��', '�Ŵ���','010-1234-5678','2000000');
INSERT INTO employee VALUES (2, '�谩��', '��', '����','010-2345-2134','1500000');  
INSERT INTO employee VALUES (3, '�ּ���', '��','����', '010-2341-1243','1800000');
INSERT INTO employee VALUES (4, '�߻��', '��','�Ŵ���', '010-8321-1111','12000000');
INSERT INTO employee VALUES (5, '�ڿ���', '��','����','010-1241-1245','1300000');

INSERT INTO TabList VALUES (1,'�Ļ�');
INSERT INTO TabList VALUES (2,'����');
INSERT INTO TabList VALUES (3,'�츮��');
INSERT INTO TabList VALUES (4,'��������');


select * from Food;