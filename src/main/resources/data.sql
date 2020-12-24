INSERT IGNORE INTO role(id, name) VALUES(1,'ROLE_USER'), (2,'ROLE_ADMIN');
INSERT IGNORE INTO my_users(id, username, password,fio,date_born) VALUES (1,'admin','$2a$10$7kQ1nv74qr7CiGAouEzxUOqoD9Pylh7nKY6WXDzAV6O1IF5R21tz.','admin','2020-01-01');
INSERT IGNORE INTO user_role(user_id, role_id) VALUE (1,2);