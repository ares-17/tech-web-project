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
    maxErrors BIGINT NOT NULL,
    isOpen BOOLEAN,
    FOREIGN KEY (createdBy) REFERENCES Customer(uid)
);

-- Create the table for Question
CREATE TABLE Question (
    uid UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isMultipleChoices BOOLEAN NOT NULL,
    uidQuiz UUID NOT NULL,
    FOREIGN KEY (uidQuiz) REFERENCES Quiz(uid)
);

-- Create the table for MultipleChoiceQuestion inheriting from Question
CREATE TABLE MultipleChoiceQuestion (
    uid UUID PRIMARY KEY REFERENCES Question(uid),
    choices TEXT[] NOT NULL CHECK (array_length(choices, 1) = 4),
    correctChoice BIGINT NOT NULL
);

-- Create the table for OpenChoiceQuestion inheriting from Question
CREATE TABLE OpenChoiceQuestion (
    uid UUID PRIMARY KEY REFERENCES Question(uid),
    answer  VARCHAR(255)
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

-- Create the table for Answer
CREATE TABLE Answer (
    uid UUID PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    uidQuestion UUID NOT NULL,
    FOREIGN KEY (uidQuestion) REFERENCES Question(uid)
);
