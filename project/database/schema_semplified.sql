 
 -- Create the table for Customer inheriting from Player
CREATE TABLE Customer (
    uid UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    isLogged BOOLEAN NOT NULL,
    isAnonymous BOOLEAN NOT NULL
);

-- Create the table for Quiz
CREATE TABLE Quiz (
    uid UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    createdAt TIMESTAMP,
    createdBy UUID NOT NULL,
    maxErrors INT NOT NULL,
    isOpen BOOLEAN,
    FOREIGN KEY (createdBy) REFERENCES Customer(uid)
);

-- Create the table for Question
CREATE TABLE Question (
    uid UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    uidQuiz UUID NOT NULL,
    FOREIGN KEY (uidQuiz) REFERENCES Quiz(uid)
);

-- Create the table for Answer
CREATE TABLE Answer (
    uid UUID PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    isCorrect BOOLEAN NOT NULL,
    uidQuestion UUID NOT NULL,
    FOREIGN KEY (uidQuestion) REFERENCES Question(uid)
);

-- Create the table for ScoreQuizPlayer
CREATE TABLE ScoreQuizCustomer (
    uidQuiz UUID NOT NULL,
    uidCustomer UUID NOT NULL,
    score BIGINT NOT NULL CHECK (score >= 0),
    PRIMARY KEY (uidQuiz, uidCustomer),
    FOREIGN KEY (uidQuiz) REFERENCES Quiz(uid),
    FOREIGN KEY (uidCustomer) REFERENCES Customer(uid)
);


