DROP TABLE IF EXISTS tab_awards_information;

CREATE TABLE tab_awards_information (
   id INT AUTO_INCREMENT PRIMARY KEY,
   year VARCHAR(250) NOT NULL,
   nominee VARCHAR(250) NOT NULL,
   category VARCHAR(1000) NOT NULL,
   additional_info VARCHAR(1000),
   won VARCHAR(1000) NOT NULL
) as SELECT * FROM CSVREAD('classpath:academy_awards.csv')
