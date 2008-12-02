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
    uid1 VARCHAR(20) references USERS(name), 
    uid2 VARCHAR(20) references USERS(name), 
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
    uid VARCHAR(20) NOT NULL, 
	url VARCHAR(255) NOT NULL, 
	foreign key (uid) references USERS(name), 
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


/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/


/* Users */

insert into USERS values ('agustin','databases', '1987-07-21', 'Mexico', 'Student');
insert into USERS values ('mattwonder','databases', '1987-08-12', 'Philadelphia', 'Student');
insert into USERS values ('andrewcc','databases', '1987-04-01', 'Philadelphia', 'Student');
insert into USERS values ('coder','coder', '1960-07-21', 'New York', 'Coder');
insert into USERS values ('rms','rms', '1967-07-21', 'Massachusetts', 'Hacker');
insert into USERS values ('esr','esr', '1979-07-21', 'Philadelphia', 'Hacker');
insert into USERS values ('bliskov','bliskov', '1943-07-21', 'Texas', 'Professor');
insert into USERS values ('johndoe','johndoe', '1930-07-21', 'Cambridge, MA', 'Secret Agent');
insert into USERS values ('ana','ana', '1988-07-21', 'New Jersey', 'Model');
insert into USERS values ('ramakrishnan','ramakrishnan', '1979-07-21', 'Wisconsin', 'Professor');



/* Categories */

insert into CATEGORIES values ('Misc');
insert into CATEGORIES values ('News');
insert into CATEGORIES values ('Politics');
insert into CATEGORIES values ('Sports');
insert into CATEGORIES values ('Technology');



/* Stories */
insert into STORIES values ('http://www.complex.com/blogs/2008/12/01/the-5-most-infamous-self-inflicted-athelete-injuries/',
                            1, 'agustin', 'The 5 Most Infamous Self-Inflicted Athelete Injuries', 
                            0, 'By now, youve probably heard all about New York Giants wide receiver Plaxico Burress’s wild Thanksgiving weekend. But is it the worst off-field, self-inflicted (and non-lethal, word to our already iffy sports injury karma) injury in sports history? Read on to see our top 5...',
                            '2008-12-1', 132, 'Sports');
insert into STORIES values ('http://www.treehugger.com/files/2008/12/worlds-fastest-electric-superbike-125mph.php',
                            2, 'mattwonder', 'World’s Fastest Electric Superbike: 125 MPH & No Carbon Emis', 
                            1, 'If you live in the United States, you may have missed the announcement made last Thursday, that the world ’s fastest all-electric superbike has been unveiled. Making its first appearance at the 2008 NEC Bike Show in the UK , the TTX01 isn’t commercially available yet.',
                            '2008-11-29', 5, 'Sports');
insert into STORIES values ('http://arstechnica.com/journals/apple.ars/2008/12/01/apple-offers-free-licensing-for-mini-displayport-spec',
                            3, 'andrewcc', 'Apple offers free licensing for Mini DisplayPort spec', 
                            0, 'When Apple recently introduced its revamped notebook line, it also introduced the world to the Mini DisplayPort. It turns out that the company is offering no-fee licenses to anyone interested in developing products that use the Mini DisplayPort specification.',
                            '2008-11-28', 54, 'Technology');
insert into STORIES values ('http://www.askmen.com/top_10/sports/top-5-sports-changes-we-want-to-see-obama-make_5.html',
                            4, 'esr', 'Top 5: Sports Changes We Want To See Obama Make', 
                            1, 'From the Olympics to the NCAA, Im fairly certain Obama has more pull in certain arenas, but its fun to hope.',
                            '2005-01-03', 0, 'Politics');
insert into STORIES values ('http://home.howstuffworks.com/dangerous-home-products.htm',
                            5, 'mattwonder', '10 Dangerous Everyday Things in Your Home', 
                            0, 'Either by accident or faulty manufacturing, household consumer products injure an estimated 33.1 million people in the United States every year. Where are these toxins coming from and what can we do about it? Read on to learn about 10 of the most common products that people are starting to think twice about bringing into their houses.',
                            '2002-08-11', 54, 'Misc');


/* Votes */

insert into VOTES values ('agustin', 1);
insert into VOTES values ('agustin', 2);
insert into VOTES values ('agustin', 5);
insert into VOTES values ('mattwonder', 2);
insert into VOTES values ('mattwonder', 4);
insert into VOTES values ('mattwonder', 5);
insert into VOTES values ('mattwonder', 3);
insert into VOTES values ('andrewcc', 3);
insert into VOTES values ('andrewcc', 4);
insert into VOTES values ('ana', 4);
insert into VOTES values ('esr', 4);
insert into VOTES values ('esr', 2);



/* Comments */
insert into COMMENTS values (1, 'This story sucks.', 1, '2008-12-1 1:15');
insert into COMMENTS values (2, 'I agree. This story sucks.', 1, '2008-12-1 2:00');
insert into COMMENTS values (3, 'I disagree. The story rocks!', 2, '2008-12-1 3:40');
insert into COMMENTS values (4, 'Cool stuff.', 1, '2008-12-1 17:50');
insert into COMMENTS values (5, 'Everyones should read this!.', 1, '2008-12-1');



/* Friends */

insert into FRIENDS values (1, 'agustin', 'mattwonder');
insert into FRIENDS values (1, 'agustin', 'andrewcc');
insert into FRIENDS values (1, 'mattwonder', 'andrewcc');
insert into FRIENDS values (1, 'esr', 'ana');
insert into FRIENDS values (1, 'ana', 'esr');
insert into FRIENDS values (1, 'johndoe', 'agustin');
insert into FRIENDS values (1, 'rms', 'esr');
insert into FRIENDS values (0, 'coder', 'andrewcc');
insert into FRIENDS values (0, 'coder', 'mattwonder');
insert into FRIENDS values (0, 'coder', 'agustin');


/* InvIndex */



/* Posts */

