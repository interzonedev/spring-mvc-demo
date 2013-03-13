DROP DATABASE IF EXISTS spring_mvc_demo;
CREATE DATABASE spring_mvc_demo;

DROP DATABASE IF EXISTS spring_mvc_demo_test;
CREATE DATABASE spring_mvc_demo_test;

GRANT ALL PRIVILEGES ON spring_mvc_demo.* TO 'smduser'@'localhost' IDENTIFIED BY 'smdpass' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON spring_mvc_demo_test.* TO 'smduser'@'localhost' IDENTIFIED BY 'smdpass' WITH GRANT OPTION;
