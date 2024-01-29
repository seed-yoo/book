insert into author
value(null, '유영수', '학원학생');

select author_id,
	   author_name,
       author_desc
from author;

delete from author
where author_id = 9;

update author
set author_name = '기안84',
	author_desc = '웹툰작가'
where author_id = 11;
