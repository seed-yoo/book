insert into book
value(null, 'title', 'pubs', '2024/01/29', 3);

select *
from book b
left join author a on b.author_id = a.author_id
order by book_id asc;

select *
from book;

delete from book
where book_id = 9;

update book
set title = '기안84',
	pubs = '웹툰작가',
	pub_date = '2024/05/01',
	author_id = 2
where book_id = 14;
