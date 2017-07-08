drop table if exists BOOK, USER, ROLE, BOOK_SALES, BOOK_INVENTORY, BOOK_RATINGS;

drop procedure if exists USER_REGISTRATION;
drop procedure if exists USER_LOGIN;
drop procedure if exists USER_PASSWORD_RESET;
drop procedure if exists AUTHOR_CREATE_BOOK;
drop procedure if exists AUTHOR_UPLOAD_CONTENTS;
drop procedure if exists AUTHOR_VIEW_BOOK_CONTENTS;
drop procedure if exists AUTHOR_DELETE_BOOK;
drop procedure if exists AUTHOR_VIEW_BOOKS;
drop procedure if exists USER_VIEW_BOOKS;
drop function if exists USER_PLACE_ORDER;
drop procedure if exists USER_BOOK_QUANTITY;
drop procedure if exists USER_DELETE_ORDER;
drop procedure if exists SEARCH_ALL_BOOK;
drop procedure if exists SEARCH_BY_TITLE;
drop procedure if exists SEARCH_BY_AUTHOR;
drop procedure if exists SEARCH_BY_PRICE;
drop procedure if exists SEARCH_BY_ISBN;
drop procedure if exists ALTER_BOOK_QUANTITY;
drop procedure if exists AUTHOR_BOOKS_SOLD;
drop procedure if exists USER_RATING;



create table BOOK (
	ISBN13 varchar(13) not null unique,
	TITLE varchar(100) not null,
	AUTHOR varchar(50) not null,
	PUBLISH_DATE timestamp not null,
	CONTENT varchar(200) not null,
	PRICE float(5,2) not null,
	STATUS varchar(10),
	constraint STATUS_CQ check( STATUS in ( 'DRAFT' , 'PUBLISHED' ) ),
	constraint ISBN13_PK primary key ( ISBN13 )
);

create table USER (
	ID int(10) auto_increment,
	NAME varchar(50) not null,
	PASSWORD varchar(16) not null,
	MOBILE_NO varchar(13),
	EMAIL_ID varchar(30) not null unique,
	ACTIVE varchar(10) not null,
	ROLE varchar(20),
	constraint ID_PK primary key ( ID )
);

create table ROLE (
	ID int(10) auto_increment,
	NAME varchar(10) not null,
	constraint NAME_CQ check( NAME in ('AUTHOR', 'USER', 'ADMIN')),
	constraint ID_PK primary key ( ID ),
	constraint ID_FK foreign key ( ID ) references USER (ID) on delete cascade on update cascade
);

create table BOOK_SALES (
	SALES_ID int(10) not null auto_increment,
	USER_ID int(10) not null,
	BOOK_ID varchar(13) not null,
	QTY varchar(2) not null,
	TOTAL_AMOUNT varchar(10) not null,
	STATUS varchar(10) not null,
	SALE_DATE timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
        constraint SALES_PK primary key ( SALES_ID ),
	constraint STATUS_CQ check( STATUS in ('BOOKED', 'CANCELLED', 'DELIVERED'))
);

create table BOOK_INVENTORY (
	BOOK_ID varchar(13) not null,
	QTY int(2) not null
);

create table BOOK_RATINGS (
	USER_ID int(10),
	BOOK_ID varchar(13),
	RATING varchar(1),
	constraint RATING_CQ check( RATING > 0 and RATING < 6)
);


delimiter $$
create procedure USER_REGISTRATION (fname varchar(50), fpassword varchar(16), fmobile varchar(13), fmail varchar(30), factive varchar(10), frole varchar(20))
        begin
                insert into USER(NAME, PASSWORD, MOBILE_NO, EMAIL_ID, ACTIVE, ROLE) values(fname, fpassword, fmobile, fmail, factive, frole);
                insert into ROLE(NAME) values(fname);
        end$$
delimiter ;


delimiter $$
create procedure USER_LOGIN (fname varchar(50), fpassword varchar(16), fmail varchar(30))
        begin
                select exists(select * from USER where(NAME=fname and PASSWORD=fpassword and EMAIL_ID=fmail));
        end$$
delimiter ;


delimiter $$
create procedure USER_PASSWORD_RESET (fname varchar(50),fmail varchar(30), fpassword varchar(16), fnewpassword varchar(16))
        begin
                update USER set PASSWORD=fnewpassword where(NAME=fname and EMAIL_ID=fmail and PASSWORD=fpassword);
        end$$
delimiter ;


delimiter $$
create procedure AUTHOR_CREATE_BOOK (fisbn13 varchar(13), ftitle varchar(100), fauthor varchar(50), fpublishdate date, fcontent varchar(200), fprice float(5,2), fstatus varchar(10))
        begin
                insert into BOOK(ISBN13, TITLE ,AUTHOR , PUBLISH_DATE, CONTENT, PRICE, STATUS) values(fisbn13 , ftitle, fauthor, fpublishdate, fcontent, fprice, fstatus);
        end$$
delimiter ;


delimiter $$
create procedure AUTHOR_UPLOAD_CONTENTS(fauthor varchar(50), fisbn13 varchar(13), fnewcontent varchar(200))
        begin
                update BOOK set CONTENT=fnewcontent where(AUTHOR=fauthor and ISBN13=fisbn13);
        end$$
delimiter ;


delimiter $$
create procedure AUTHOR_VIEW_BOOK_CONTENTS(fauthor varchar(50))
        begin
                select CONTENT, TITLE from BOOK where(AUTHOR=fauthor);
        end$$
delimiter ;


delimiter $$
create procedure AUTHOR_DELETE_BOOK(fauthor varchar(50), fisbn13 varchar(13))
        begin
                delete from BOOK where(AUTHOR=fauthor and ISBN13=fisbn13);
        end$$
delimiter ;


delimiter $$
create procedure AUTHOR_VIEW_BOOKS(fauthor varchar(50))
        begin
                select TITLE from BOOK where(AUTHOR=fauthor);
        end$$
delimiter ;


delimiter $$
create procedure USER_VIEW_BOOKS()
        begin
                select TITLE from BOOK;
        end$$
delimiter ;


delimiter $$
create function USER_PLACE_ORDER (fmail varchar(10), fbookid varchar(13), fqty int(2), ftotal varchar(10), fstatus varchar(10))
        returns decimal
        begin
                declare fuserid int(10);
                if ((select QTY from BOOK_INVENTORY where BOOK_ID=fbookid) < fqty) then
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Out of stock';
                end if;
                
                set fuserid = (select ID from USER where(EMAIL_ID=fmail)); 
                insert into BOOK_SALES(USER_ID, BOOK_ID, QTY, TOTAL_AMOUNT, STATUS) values(fuserid, fbookid, fqty, ftotal, fstatus);
                return 1;
        end$$
delimiter ;


delimiter $$
create procedure USER_BOOK_QUANTITY(fbookid varchar(13))
        begin
                select QTY from BOOK_INVENTORY where BOOK_ID=fbookid;
        end$$
delimiter ;


delimiter $$
create procedure USER_DELETE_ORDER (fmail varchar(10), fbookid varchar(13))
        begin
                declare fuserid int(10);
                declare fsalesid int(10);
                set fuserid = (select ID from USER where(EMAIL_ID=fmail));
                set fsalesid = (select SALES_ID from BOOK_SALES where USER_ID=fuserid and BOOK_ID=fbookid);
                update BOOK_SALES set STATUS='CANCELLED' where(SALES_ID=fsalesid and USER_ID=fuserid and BOOK_ID=fbookid);
        end$$
delimiter ;


delimiter $$
create procedure SEARCH_ALL_BOOK()
        begin
                select TITLE from BOOK;
        end$$
delimiter ;


delimiter $$
create procedure SEARCH_BY_TITLE(ftitle varchar(100))
        begin
                select TITLE from BOOK where(TITLE regexp ftitle);
        end$$
delimiter ;


delimiter $$
create procedure SEARCH_BY_AUTHOR(fauthor varchar(50))
        begin
                select TITLE from BOOK where(AUTHOR regexp fauthor);
        end$$
delimiter ;


delimiter $$
create procedure SEARCH_BY_PRICE(fhighprice float(5, 2), flowprice float(5, 2))
        begin
                select TITLE from BOOK where(PRICE <= fhighprice and PRICE >= flowprice);
        end$$
delimiter ;


delimiter $$
create procedure SEARCH_BY_ISBN(fisbn varchar(13))
        begin
                select TITLE from BOOK where ISBN13=fisbn;
        end$$
delimiter ;


delimiter $$
create procedure ALTER_BOOK_QUANTITY(fbookid varchar(13), fqty int(2))
        begin
                update BOOK_INVENTORY set QTY=fqty where BOOK_ID=fbookid;
        end$$
delimiter ;


delimiter $$
create procedure AUTHOR_BOOKS_SOLD(fbookid varchar(13), ffilter varchar(10))
        begin
                if(ffilter='week') then
                        select count(SALES_ID) from BOOK_SALES where((TIMESTAMPDIFF(WEEK, SALE_DATE, CURRENT_TIMESTAMP) < 8) and STATUS='DELIVERED' and BOOK_ID=fbookid);
                elseif(ffilter='month') then
                        select count(SALES_ID) from BOOK_SALES where((TIMESTAMPDIFF(MONTH, SALE_DATE, CURRENT_TIMESTAMP) < 2) and STATUS='DELIVERED' and BOOK_ID=fbookid);
                elseif(ffilter='yearly') then
                        select count(SALES_ID) from BOOK_SALES where((TIMESTAMPDIFF(YEAR, SALE_DATE, CURRENT_TIMESTAMP) < 2) and STATUS='DELIVERED' and BOOK_ID=fbookid);
                else
                        select count(SALES_ID) from BOOK_SALES where STATUS='DELIVERED';
                end if;
        end$$
delimiter ;


delimiter $$
create procedure USER_RATING(fmail varchar(10), fbookid varchar(13), frating varchar(10))
        begin
                declare fuserid int(10);
                set fuserid = (select ID from USER where(EMAIL_ID=fmail));
                insert into BOOK_RATINGS(USER_ID, BOOK_ID, RATING) values(fuserid, fbookid, frating);
        end$$
delimiter ;


call USER_REGISTRATION('ben', 'pass', '1234567890', 'ben@ben.com', 'yes', 'USER');
call USER_LOGIN("ben","pass","ben@ben.com");
call USER_PASSWORD_RESET('ben','ban@ben.com', 'pass', 'newpass');
call AUTHOR_CREATE_BOOK(1234567890123, 'jack book', 'jack', '2010-10-10 10:10:10', 'contents', 10.2, 'PUBLISHED');
call AUTHOR_UPLOAD_CONTENTS('jack', 1234567890123, 'new content');
call AUTHOR_VIEW_BOOK_CONTENTS('jack');
call AUTHOR_DELETE_BOOK('jack', 1234567890123);
call AUTHOR_VIEW_BOOKS('jack');
call USER_VIEW_BOOKS();
select USER_PLACE_ORDER(1000, 1234567890, 2, 20, 'BOOKED') as USER_PLACE_ORDER;
call USER_BOOK_QUANTITY(1234567890);
call USER_DELETE_ORDER('ben@ben.com', 1234567890123);
call SEARCH_ALL_BOOK();
call SEARCH_BY_TITLE('jack book');
call SEARCH_BY_AUTHOR('jack');
call SEARCH_BY_PRICE(20,10);
call SEARCH_BY_ISBN(1234567890123);
call ALTER_BOOK_QUANTITY(1234567890123, 20);
call AUTHOR_BOOKS_SOLD(100, 'week');
call USER_RATING(100, 1000, 5);
