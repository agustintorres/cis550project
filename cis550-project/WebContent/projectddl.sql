create table USERS 
( 
   name VARCHAR(20) PRIMARY KEY,
password VARCHAR(20) NOT NULL, 
    dob DATE NULL, 
    location VARCHAR(50) NULL, 
    profession VARCHAR(50) NULL 
); 
  
create table FRIENDS 
( 
	pending INTEGER NOT NULL,
    uid1 INTEGER references USERS(uid), 
    uid2 INTEGER references USERS(uid), 
    primary key (uid1, uid2) 
     
);

CREATE TABLE STORIES 
( 
    url VARCHAR(255) NOT NULL, 
	storyid INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
    title VARCHAR(255) NOT NULL, 
    private INTEGER NOT NULL, 
    description VARCHAR(2000) NULL, 
    storytime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  
    votes INTEGER NOT NULL, 
	category VARCHAR(50) NOT NULL,
	foreign key (name) references USERS(name),
	foreign key (Category) references CATEGORIES(category)
);

CREATE TABLE CATEGORIES
(
	Category VARCHAR(50) PRIMARY KEY
);
 


CREATE TABLE POSTS
(
	cid INTEGER NOT NULL,
	uid INTEGER NOT NULL,
	url VARCHAR(255) NOT NULL,
PRIMARY KEY (cid, uid, url),
FOREIGN KEY (cid) REFERENCES COMMENTS,
FOREIGN KEY (uid) REFERENCES USERS,
FOREIGN KEY (url) REFERENCES STORIES

);
  
CREATE TABLE COMMENTS 
( 
    cid INTEGER AUTO_INCREMENT PRIMARY KEY, 
    text VARCHAR(2000) NOT NULL, 
	parent INTEGER NOT NULL,
    commenttime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (parent) REFERENCES COMMENTS(cid) 
);

create table PARENTOF 
( 
    cidself INTEGER PRIMARY KEY, 
    cidparent INTEGER NOT NULL,
    foreign key (cidself) references COMMENTS(cid),
    foreign key (cidparent) references COMMENTS(cid) 
); 

create table VOTES
(
    uid INTEGER NOT NULL, 
url VARCHAR(255) NOT NULL, 
foreign key (uid) references USERS(uid), 
foreign key (url) references STORIES(url), 
primary key(uid, url)
);

create table INVINDEX
(
   word VARCHAR(255) NOT NULL,
   docid INTEGER NOT NULL,
   foreign key (docid) references STORIES(storyid),
   PRIMARY KEY (word, docid)
);
