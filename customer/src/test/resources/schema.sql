CREATE TABLE PERSON (
                        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        NAME VARCHAR(255) NOT NULL,
                        GENDER VARCHAR(255) NOT NULL,
                        AGE INTEGER NOT NULL,
                        DOCUMENT_TYPE VARCHAR(255),
                        IDENTIFICATION_NUMBER VARCHAR(255) NOT NULL UNIQUE,
                        ADDRESS VARCHAR(255) NOT NULL,
                        PHONE_NUMBER VARCHAR(255) NOT NULL
);

CREATE TABLE CUSTOMER (
                          ID BIGINT PRIMARY KEY,
                          PASSWORD VARCHAR(255) NOT NULL,
                          STATE BOOLEAN NOT NULL,
                          FOREIGN KEY (ID) REFERENCES PERSON(ID)
);