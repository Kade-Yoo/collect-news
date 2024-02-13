create sequence news_seq start with 1 increment by 50
create sequence search_keyword_seq start with 1 increment by 50
create sequence target_site_seq start with 1 increment by 50

create table member
(
    member_id int unsigned comment '사용자 테이블 id',
    username varchar not null comment '사용자 email',
    password varchar not null comment '비밀번호',
    primary key (member_id)
)

create table member_role
(
    member_role_id int unsigned comment '사용자 Role 테이블 id',
    member_id int unsigned unique comment '사용자 테이블 id',
    role varchar(255) comment '사용자 Role',
    primary key (member_role_id)
)

create table news
(
    news_id int unsigned not null comment '뉴스 테이블 id',
    keyword int unsigned comment '키워드 테이블 id',
    site int unsigned comment '사이트 테이블 id',
    agency varchar(255) ,
    content varchar(255),
    detail_news_site varchar(255),
    title varchar(255),
    primary key (news_id)
)

create table search_keyword
(
    search_keword_id bigint not null,
    keyword varchar(255),
    primary key (search_keword_id)
)

create table target_site
(
    target_site_id bigint not null,
    site varchar(255),
    primary key (target_site_id)
)