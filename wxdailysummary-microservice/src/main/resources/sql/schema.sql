DROP TABLE user IF EXISTS;

CREATE TABLE user (
 id BIGINT IDENTITY NOT NULL PRIMARY KEY,
 firstName VARCHAR(40),
 lastName VARCHAR(40),
 phonenum VARCHAR(15),
 email VARCHAR(40),
 birthDate DATE
)