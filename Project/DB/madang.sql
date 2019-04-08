--book 테이블에서 출판사별 평균가격

select PUBLISHER, avg(PRICE) from BOOK group by PUBLISHER;

/* select custid, count(*)
from orders
where saleprice >= 8000  */


/*SELECT custid, Count(*) AS 도서수량
FROM Orders
WHERE saleprice >= 8000
GROUP BY custid
Having count(*) >=2; */


--select sum(price) as 합계 from BOOK where PUBLISHER = '대한미디어';
--SELECT * FROM Book ORDER BY price DESC, publisher ASC;
--select * from book order by price, publisher desc;