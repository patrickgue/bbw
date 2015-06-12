DROP DATABASE IF EXISTS cms;

CREATE DATABASE cms;

USE cms;

CREATE TABLE cms_user(
       user_id INTEGER NOT NULL AUTO_INCREMENT,
       user_name VARCHAR(64) NOT NULL,
       user_email VARCHAR(64) NOT NULL,
       user_password VARCHAR(64) NOT NULL,
       user_type ENUM('normal', 'content', 'technical') NOT NULL,
       user_bio VARCHAR(1024),
       user_age INTEGER ,
       user_gender ENUM('female', 'male', 'other') NOT NULL,

       PRIMARY KEY(user_id)
);

CREATE TABLE cms_post(
       post_id INTEGER NOT NULL AUTO_INCREMENT,
       post_title VARCHAR(64) NOT NULL,
       post_content VARCHAR(4096) NOT NULL,
       post_likes INTEGER NOT NULL,
       post_user_id INTEGER NOT NULL,
       post_date DATE NOT NULL,

       PRIMARY KEY(post_id)
);

CREATE TABLE cms_comment(
       comment_id INTEGER NOT NULL AUTO_INCREMENT,
       comment_user_id INTEGER NOT NULL,
       comment_content VARCHAR(2048) NOT NULL,
       comment_post_id INTEGER NOT NULL,
       comment_date DATE NOT NULL,

       PRIMARY KEY(comment_id)
);

CREATE TABLE cms_pinwall(
       pin_id INTEGER NOT NULL AUTO_INCREMENT,
       pin_post_id INTEGER NOT NULL,
       pin_user_id INTEGER NOT NULL,

       PRIMARY KEY(post_id)
);
