CREATE TABLE Users
(
    userid INTEGER,
    name VARCHAR2(20) NOT NULL,
    age INTEGER NULL,
    location VARCHAR2(50) NULL,
    profession VARCHAR2(50) NULL,
    PRIMARY KEY (userid)
);

CREATE TABLE Friends
(
    uid1 INTEGER,
    uid2 INTEGER, 
    FOREIGN KEY (uid1) REFERENCES Users(userid),
    FOREIGN KEY (uid2) REFERENCES Users(userid),
    PRIMARY KEY (uid1, uid2)
);

CREATE TABLE Stories
(
    url VARCHAR2(255),
    title VARCHAR2(255) NOT NULL,
    private INTEGER NOT NULL,
    description VARCHAR2(2000) NULL,
    storydate DATE NOT NULL,
    category VARCHAR2(50) NOT NULL,
    votes INTEGER NOT NULL,
    submitter INTEGER,
    PRIMARY KEY (url),
    FOREIGN KEY (submitter) REFERENCES USERS(userid)
);

CREATE TABLE COMMENTS
(
    cid INTEGER PRIMARY KEY,
    text VARCHAR2(2000) NOT NULL,
    commentdate DATE NOT NULL,
    parent INTEGER,
    commenter INTEGER,
    FOREIGN KEY (parent) REFERENCES COMMENTS(cid),
    FOREIGN KEY (commenter) REFERENCES USERS(userid)
);

CREATE TABLE ParentOf
(
    cidself PRIMARY KEY,
    cidparent NOT NULL,
    FOREIGN KEY (cidself) REFERENCES COMMENTS(cid),
    FOREIGN KEY (cidparent) REFERENCES COMMENTS(cid)
); 

CREATE TABLE Votes
(
    userid INTEGER,
    url VARCHAR(255),
    FOREIGN KEY (userid) references USERS (userid),
    FOREIGN KEY (url) references STORIES(url),
    PRIMARY KEY (userid, url)
);
