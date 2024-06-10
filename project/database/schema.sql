
 -- Create the table for Customer inheriting from Player
CREATE TABLE Customer (
    uid UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create the table for Quiz
CREATE TABLE Quiz (
    uid UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    createdAt TIMESTAMP,
    createdBy UUID NOT NULL,
    maxErrors INT NOT NULL,
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


CREATE TABLE Score (
	uid UUID PRIMARY KEY,
	customer UUID NOT NULL,
	quiz UUID NOT NULL,
	score INT NOT NULL CHECK (score >= 0),
	completedAt TIMESTAMP NOT NULL,
	FOREIGN KEY (customer) REFERENCES Customer(uid),
	FOREIGN KEY (quiz) REFERENCES Quiz(uid)
);
