--book ���̺��� ���ǻ纰 ��հ���

select PUBLISHER, avg(PRICE) from BOOK group by PUBLISHER;

/* select custid, count(*)
from orders
where saleprice >= 8000  */


/*SELECT custid, Count(*) AS ��������
FROM Orders
WHERE saleprice >= 8000
GROUP BY custid
Having count(*) >=2; */


--select sum(price) as �հ� from BOOK where PUBLISHER = '���ѹ̵��';
--SELECT * FROM Book ORDER BY price DESC, publisher ASC;
--select * from book order by price, publisher desc;