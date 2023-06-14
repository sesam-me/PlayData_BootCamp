use test;
drop table user;

-- # 사용자
create table user(
	user_seq int primary key auto_increment,
    user_id varchar(255) unique key not null,
    user_email varchar(255) not null,
    user_pwd varchar(255) not null
);

insert into user(user_id , user_email, user_pwd) values("admin","apple@naver.com","1234");
insert into user(user_id , user_email, user_pwd) values("a","apple@naver.com","1234");
insert into user(user_id , user_email, user_pwd) values("b","apple@naver.com","1234");
insert into user(user_id , user_email, user_pwd) values("c","apple@naver.com","1234");
insert into user(user_seq,user_id , user_email, user_pwd) values(2,"cd","apple@naver.com","1234");

select * from user where user_id = "admin" and user_pwd = "1234";

select * from user;


-- drop table movie;
-- # 영화
create table movie(
	movie_seq int primary key auto_increment,
    title varchar(255) unique key not null,
    release_date date not null,
    duration int not null,
    description varchar(255) not null,
    rating int not null,
    genre  varchar(255) not null,
    director varchar(255) not null
);

insert into movie(title, release_date, duration, description, rating, genre, director) values("범죄도시3", "2023-05-31", 105, "노잼", 15, "범죄", "이상용");
insert into movie(title, release_date, duration, description, rating, genre, director) values("트랜스포머", "2023-06-06", 127, "몰라", 12, "액션", "스티븐 카플 주니어");
insert into movie(title, release_date, duration, description, rating, genre, director) values("가디언즈오브갤럭시", "2023-05-03", 150, "굿", 12, "액션", "제임스 건" );
insert into movie(title, release_date, duration, description, rating, genre, director) values("분노의질주", "2023-05-17", 141, "달리거나,죽거나", 15, "액션", "루이스 리터리어");

select * from movie;

-- drop table actor;
-- # 배우
create table actor(
	actor_seq int primary key auto_increment,
    name varchar(255) not null,
    birth_date date not null,
    nation varchar(255) not null,
    gender varchar(255) not null
);

insert into actor(name, birth_date, nation, gender) values("마동석","1999-01-01", "한국", "남");
insert into actor(name, birth_date, nation, gender) values("김철수","1988-01-01", "한국", "남");
insert into actor(name, birth_date, nation, gender) values("이유리","1977-01-01", "한국", "여");
insert into actor(name, birth_date, nation, gender) values("최맹구","1966-01-01", "한국", "남");
select * from actor;


drop table review;
-- # 리뷰
create table review(
	review_seq int primary key not null auto_increment,
    rating int not null,
    date date not null,
    user_seq int not null,
    contents varchar(255) not null,
	movie_seq int not null
);
select * from review;
-- review 왜리캐 수정(user)
alter table review add column FK_user_seq int;
alter table review add constraint FK_review_user foreign key(user_seq) references user(user_seq);

-- review 외래키 수정(movie)
alter table review add column FK_movie_seq int;
alter table review add constraint FK_review_movie foreign key(movie_seq) references movie(movie_seq);

insert into review(rating, date, contents, user_seq, movie_seq) values(5, "2023-01-01", "꿀잼", 1,1);
insert into review(rating, date, contents, user_seq, movie_seq) values(1, "2023-02-01", "노잼", 2,1);
insert into review(rating, date, contents, user_seq, movie_seq) values(5, "2023-02-01", "꿀잼", 3,2);
insert into review(rating, date, contents, user_seq, movie_seq) values(1, "2023-02-01", "노잼", 4,2);



-- 1. 특정 영화에 해당되는 리뷰를 전부 조회
select * from review as r
join movie as m
on m.movie_seq = r.movie_seq
where m.title = "범죄도시3";





-- drop table movie_actor;
create table movie_actor(
	actor_seq int not null,
    movie_seq int not null,
    foreign key(actor_seq) references actor(actor_seq),
    foreign key(movie_seq) references movie(movie_seq)
);

select * from movie_actor;

insert into movie_actor(movie_seq, actor_seq) values((select movie_seq from movie where title = "범죄도시3"), 1);

insert into movie_actor(movie_seq, actor_seq) values((select movie_seq from movie where title = "범죄도시3"), 2);

insert into movie_actor(movie_seq, actor_seq) values((select movie_seq from movie where title = "범죄도시3"), 4);

select * from actor;




-- 2. 특정 배우가 출연한 영화를 모두 출력
select m.title from movie_actor as ma
join actor as a on a.actor_seq = ma.actor_seq
join movie as m on m.movie_seq = ma.movie_seq
where a.name = "마동석";

-- 3. 특정 영화에 출연한 배우를 모두 출력
select a.name from movie_actor as ma
join actor as a on a.actor_seq = ma.actor_seq
join movie as m on m.movie_seq = ma.movie_seq
where m.title = "범죄도시3";

select * from user;
-- 4. 회원 삭제 id기준
delete from user where user_id="a";
