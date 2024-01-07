CREATE TABLE Notepad (
  NotepadID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  UserID    integer(10) NOT NULL, 
  Title     varchar(64) NOT NULL, 
  FOREIGN KEY(UserID) REFERENCES "User"(UserID));
CREATE TABLE "User" (
  UserID           INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  FirstName        varchar(64) NOT NULL, 
  LastName         varchar(64) NOT NULL, 
  CreatedTimeStamp timestamp NOT NULL, 
  lastLogin        timestamp);
CREATE TABLE Category (
  CategoryID   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  UserID       integer(10) NOT NULL, 
  CategoryName varchar(64) NOT NULL UNIQUE, 
  Colour       varchar(10), 
  FOREIGN KEY(UserID) REFERENCES "User"(UserID));
CREATE TABLE Note (
  NoteID    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  NotepadID integer(10) NOT NULL, 
  NoteBody  varchar(255), 
  NoteType  varchar(255), 
  FOREIGN KEY(NotepadID) REFERENCES Notepad(NotepadID));
CREATE TABLE NoteCategory (
  NoteCategoryID     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  CategoryCategoryID integer(10) NOT NULL, 
  NoteNoteID         integer(10) NOT NULL, 
  FOREIGN KEY(CategoryCategoryID) REFERENCES Category(CategoryID), 
  FOREIGN KEY(NoteNoteID) REFERENCES Note(NoteID));
CREATE UNIQUE INDEX Notepad_NotepadID 
  ON Notepad (NotepadID);
CREATE UNIQUE INDEX User_UserID 
  ON "User" (UserID);
CREATE UNIQUE INDEX Category_CategoryID 
  ON Category (CategoryID);
CREATE UNIQUE INDEX Note_NoteID 
  ON Note (NoteID);
CREATE UNIQUE INDEX NoteCategory_NoteCategoryID 
  ON NoteCategory (NoteCategoryID);
