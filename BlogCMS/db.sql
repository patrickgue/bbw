DROP DATABASE IF EXISTS cms;

CREATE DATABASE cms;

USE cms;

CREATE TABLE cms_user(
       user_id INTEGER NOT NULL AUTO_INCREMENT,
       user_name VARCHAR(64) NOT NULL,
       user_email VARCHAR(64) NOT NULL,
       user_password VARCHAR(64) NOT NULL,
       user_type ENUM('NORMAL', 'CONTENT', 'TECHNICAL') NOT NULL,
       user_bio VARCHAR(1024),
       user_age INTEGER ,
       user_gender ENUM('FEMALE', 'MALE', 'OTHER') NOT NULL,

       PRIMARY KEY(user_id)
);

CREATE TABLE cms_post(
       post_id INTEGER NOT NULL AUTO_INCREMENT,
       post_title VARCHAR(64) NOT NULL,
       post_content VARCHAR(4096) NOT NULL,
       post_likes INTEGER NOT NULL,
       post_user_id INTEGER NOT NULL,

       PRIMARY KEY(post_id)
);

CREATE TABLE cms_comment(
       comment_id INTEGER NOT NULL AUTO_INCREMENT,
       comment_user_id INTEGER NOT NULL,
       comment_content VARCHAR(2048) NOT NULL,
       comment_post_id INTEGER NOT NULL,

       PRIMARY KEY(comment_id)
);
