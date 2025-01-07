-- Create the database
CREATE DATABASE legalhelpwithjava;

-- Use the database
USE legalhelpwithjava;

-- Create the consultant table
CREATE TABLE consultant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    price DOUBLE NOT NULL,
    address VARCHAR(200),
    profile_path VARCHAR(200),
    bio VARCHAR(200)
);

INSERT INTO consultant (id, name, price) VALUES (1, 'System', 0);

-- Create the user table
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('user', 'admin') NOT NULL,
    handphone VARCHAR(20),
    balance DOUBLE DEFAULT 0,
    profile_path VARCHAR(255)
);

INSERT INTO user (name, username, password, role) VALUES ('Admin', 'admin', 'admin', 'admin');

-- Create the post table
CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    userId INT,
    userName VARCHAR(250),
    FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE
);

-- Create the transaction table
CREATE TABLE transaction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    senderId INT,
    receiverId INT NULL,
    sum DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (senderId) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (receiverId) REFERENCES consultant(id) ON DELETE SET NULL
);

CREATE TABLE article (
    id INT AUTO_INCREMENT PRIMARY KEY,
    picture_path VARCHAR(200) NOT NULL,
    headline VARCHAR(255) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);