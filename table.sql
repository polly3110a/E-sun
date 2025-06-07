CREATE DATABASE MySocialApp;
GO

USE MySocialApp;
GO

CREATE TABLE [User] (
    UserID INT IDENTITY(1,1) PRIMARY KEY,          
    UserName NVARCHAR(100) NOT NULL,               
    Email NVARCHAR(255) NOT NULL UNIQUE,           
    PasswordHash NVARCHAR(255) NOT NULL,           
    Salt NVARCHAR(255) NOT NULL,                   
    CoverImage NVARCHAR(500) NULL,                 
    Biography NVARCHAR(MAX) NULL                   
);


CREATE TABLE Post (
    PostID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    Content NVARCHAR(MAX) NOT NULL,
    Image NVARCHAR(500) NULL,
    CreatedAt DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Post_User FOREIGN KEY (UserID) REFERENCES [User](UserID)
);


CREATE TABLE Comment (
    CommentID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    PostID INT NOT NULL,
    Content NVARCHAR(MAX) NOT NULL,
    CreatedAt DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Comment_User FOREIGN KEY (UserID) REFERENCES [User](UserID),
    CONSTRAINT FK_Comment_Post FOREIGN KEY (PostID) REFERENCES Post(PostID)
);
