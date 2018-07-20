-- we don't know how to generate schema CSCI5308_12_TEST (class Schema) :(
create table ActivationTable
(
  ID            int auto_increment
    primary key,
  AcivationCode varchar(50) null,
  Date          date        null
);

create table Admin
(
  Email    varchar(50) not null,
  Password varchar(40) not null,
  constraint Email_UNIQUE
  unique (Email)
);

create table Course
(
  ID     int auto_increment
    primary key,
  Name   varchar(25) null,
  School varchar(25) null
);

create table Log
(
  LogID          int auto_increment
    primary key,
  LogDateAndTime varchar(255) null,
  LogLevel       varchar(5)   null,
  LogLogger      text         null,
  LogMsg         text         null,
  LogException   text         null
);

create table Student
(
  ID                int auto_increment
    primary key,
  FirstName         varchar(25)         not null,
  LastName          varchar(25)         not null,
  Email             varchar(25)         not null,
  Password          varchar(40)         not null,
  AccountActivation tinyint default '0' not null
  comment 'The value is even 0 for not activated account or 1 for activated account',
  School            varchar(50)         not null,
  PhoneNumber       varchar(15)         not null,
  Banned            tinyint default '0' not null
);

create table SubscriptionPlan
(
  ID          int auto_increment
    primary key,
  Name        varchar(25) null,
  Price       int         null,
  Description text        null,
  Dayes       int         null
);

create table Tutor
(
  ID                   int auto_increment
    primary key,
  FirstName            varchar(25)         not null,
  LastName             varchar(25)         not null,
  Email                varchar(50)         not null,
  Password             varchar(40)         not null,
  PhoneNumber          varchar(25)         not null,
  Education            text                null,
  AccountActivation    tinyint default '0' not null
  comment 'The value could be 0 is not active or 1 is active',
  PlanID               int                 null,
  ExpiryDate           date                null,
  CreditCardHoldName   varchar(25)         null,
  CreditCardNumber     varchar(16)         null,
  CreditCardExpiryDate varchar(7)          null,
  SecurityCode         varchar(3)          null,
  Banned               tinyint default '0' not null,
  constraint Tutor_SubscriptionPlan_ID_fk
  foreign key (PlanID) references SubscriptionPlan (ID)
);

create table Review
(
  ID        int  not null
    primary key,
  StudentID int  null,
  Text      text null,
  Date      date null,
  Rate      int  null,
  TutorID   int  null,
  constraint Review_Student_ID_fk
  foreign key (StudentID) references Student (ID),
  constraint Review_Tutor_ID_fk
  foreign key (TutorID) references Tutor (ID)
);

create index Review_Student_ID_fk
  on Review (StudentID);

create index Review_Tutor_ID_fk
  on Review (TutorID);

create index Tutor_SubscriptionPlan_ID_fk
  on Tutor (PlanID);

CREATE TABLE TutorCourse
(
    TutorId int,
    CourseId int,
    Price float,
    CONSTRAINT TutorCourse_TutorId_CourseId_pk PRIMARY KEY (TutorId, CourseId),
    CONSTRAINT TutorCourse_Tutor_ID_fk FOREIGN KEY (TutorId) REFERENCES Tutor (ID),
    CONSTRAINT TutorCourse_Course_ID_fk FOREIGN KEY (CourseId) REFERENCES Course (ID)
);

create table WeeklySchedule
(
  ScheduleID int auto_increment
    primary key,
  TutorID    int             null,
  Su1        int default '0' null,
  Su2        int default '0' null,
  Su3        int default '0' null,
  Mo1        int default '0' null,
  Mo2        int default '0' null,
  Mo3        int default '0' null,
  Tu1        int default '0' null,
  Tu2        int default '0' null,
  Tu3        int default '0' null,
  We1        int default '0' null,
  We2        int default '0' null,
  We3        int default '0' null,
  Th1        int default '0' null,
  Th2        int default '0' null,
  Th3        int default '0' null,
  Fr1        int default '0' null,
  Fr2        int default '0' null,
  Fr3        int default '0' null,
  Sa1        int default '0' null,
  Sa2        int default '0' null,
  Sa3        int default '0' null
);

DROP FUNCTION IF EXISTS ActivateStudent;
create function ActivateStudent(_id int)
  returns tinyint(1)
  begin
    DECLARE _result TINYINT;
    set _result = 0;

    UPDATE Student
    SET Student.AccountActivation = 1
    where Student.ID = _id;

    set _result = 1;
    return _result;
  end;

DROP FUNCTION IF EXISTS ActivateTutor;
create function ActivateTutor(_id int)
  returns tinyint(1)
  begin
    DECLARE _result tinyint;
    SET _result = 0;
    UPDATE Tutor
    SET Tutor.AccountActivation = 1
    where Tutor.ID = _id;

    set _result = 1;

    return _result;
  end;

DROP PROCEDURE IF EXISTS CheckActivationCode;
CREATE PROCEDURE CheckActivationCode(IN _code varchar(50))
  begin
    SELECT *
    FROM ActivationTable
    WHERE ActivationTable.AcivationCode LIKE _code;
  end;

DROP PROCEDURE IF EXISTS AuthorizeStudent;
CREATE PROCEDURE AuthorizeStudent(IN _email varchar(50), IN _password varchar(50))
  begin
    SELECT *
    from Student
    WHERE Student.Email LIKE _email AND Student.Password LIKE _password;
  end;

DROP PROCEDURE IF EXISTS AuthorizeTutor;
CREATE PROCEDURE AuthorizeTutor(IN _email varchar(50), IN _password varchar(50))
  begin
    SELECT *
    from Tutor
    WHERE Tutor.Email LIKE _email AND Tutor.Password LIKE _password;
  end;

DROP PROCEDURE IF EXISTS GETTutorEmail;
CREATE PROCEDURE GETTutorEmail(IN _email varchar(50))
  begin
    SELECT *
    from Tutor
    WHERE Tutor.Email LIKE _email;
  end;

DROP PROCEDURE IF EXISTS GETStudentEmail;
CREATE PROCEDURE GETStudentEmail(IN _email varchar(50))
  begin
    SELECT *
    from Student
    WHERE Student.Email LIKE _email;
  end;

DROP FUNCTION IF EXISTS DeleteStudent;
CREATE FUNCTION DeleteStudent(id int)
  returns tinyint(1)
  begin
    DECLARE _result INT;
    set _result = 0;

    DELETE from Student
    where Student.ID like id;

    SET _result = 1;
    return _result;
  end;

DROP FUNCTION IF EXISTS DeleteTutor;
CREATE FUNCTION DeleteTutor(id int)
  returns tinyint(1)
  begin
    DECLARE _result INT;
    set _result = 0;

    DELETE from Tutor
    where Tutor.ID like id;

    SET _result = 1;
    return _result;
  end;

DROP PROCEDURE IF EXISTS GetStudentId;
create procedure GetStudentId(IN _Email varchar(50))
  begin
    select *
    from Student
    where Student.Email like _Email;
  end;

DROP PROCEDURE IF EXISTS GetTutorID;
create procedure GetTutorID(IN _Email varchar(50))
  begin
    select *
    from Tutor
    where Tutor.Email like _Email;
  end;

DROP FUNCTION IF EXISTS IsCreditCardNew;
CREATE FUNCTION IsCreditCardNew(_cnumber varchar(50))
  returns INT
  begin
    DECLARE counter INT;
    SET counter = 0;

    SELECT count(email)
    from Tutor
    where Tutor.CreditCardNumber like _cnumber
    into counter;

    return counter;
  end;

DROP FUNCTION IF EXISTS IsEmailNew;
CREATE FUNCTION IsEmailNew(_email varchar(50))
  RETURNS INT
  begin
    DECLARE counterS INT;
    DECLARE counterT INT;

    SET counterS = 0;
    SET counterT = 0;

    SELECT count(*)
    from Student
    where Student.Email like _email
    into counterS;

    SELECT count(*)
    from Tutor
    where Tutor.Email like _email
    into counterT;

    SET counterS = counterS + counterT;

    return counterS;
  end;

DROP FUNCTION IF EXISTS IsPhoneNew;
CREATE FUNCTION IsPhoneNew(_phone varchar(15))
  RETURNS INT
  begin
    DECLARE counterS INT;
    DECLARE counterT INT;

    SELECT count(*)
    from Student
    where Student.PhoneNumber like _phone
    into counterS;


    SELECT count(Tutor.PhoneNumber)
    from Tutor
    where Tutor.PhoneNumber like _phone
    into counterT;

    SET counterS = counterS + counterT;
    return counterS;
  end;

DROP FUNCTION IF EXISTS RegStudent;
CREATE FUNCTION RegStudent(_FirstName varchar(50), _LastName varchar(50), _Email varchar(50),
                           _Password  varchar(50), _School varchar(50), _PhoneNumber varchar(50))
  RETURNS INT
  begin
    DECLARE result INT;

    SET result = 0;

    INSERT INTO Student (`FirstName`, `LastName`, `Email`, `Password`, `AccountActivation`,
                         `School`, `PhoneNumber`, `Banned`)
    VALUES (_FirstName, _LastName, _Email, _Password, 0, _School, _PhoneNumber, 0);

    SET result = 1;
    return result;
  end;

DROP FUNCTION IF EXISTS RegTutor;
CREATE FUNCTION RegTutor(_FirstName varchar(25), _LastName varchar(25), _Email varchar(50),
                         _Password  varchar(40), _PhoneNumber varchar(25))
  RETURNS INT
  begin
    DECLARE result INT;
    SET result = 0;

    INSERT INTO `Tutor` (`FirstName`, `LastName`, `Email`, `Password`, `PhoneNumber`
      , `Education`, `AccountActivation`, `PlanID`, `ExpiryDate`, `CreditCardHoldName`, `CreditCardNumber`
      , `CreditCardExpiryDate`, `SecurityCode`, `Banned`)
    VALUES (_FirstName, _LastName, _Email, _Password, _PhoneNumber, NULL, 0, NULL, NULL, NULL
      , NULL, NULL, NULL, 0);

    SET result = 1;
    return result;
  end;

DROP PROCEDURE IF EXISTS GetAdmin;
CREATE PROCEDURE GetAdmin(_Email varchar(50))
  BEGIN
    SELECT *
    FROM Admin
    WHERE Admin.Email LIKE _Email;
  END;

DROP FUNCTION IF EXISTS RegCourse;
CREATE FUNCTION RegCourse(_NameCourse varchar(25), _School varchar(25))
  RETURNS INT
  BEGIN
    DECLARE _result INT;
    SET _result = 0;
    INSERT INTO Course (`Name`, `School`) VALUES (_NameCourse, _School);
    SET _result = 1;
    RETURN _result;
  END;

DROP PROCEDURE IF EXISTS GetCourse;
CREATE PROCEDURE GetCourse(_NameCourse varchar(25))
  BEGIN
    SELECT *
    FROM Course
    WHERE Course.School LIKE _NameCourse;
  END;

DROP FUNCTION IF EXISTS SetTutorCourse;
CREATE FUNCTION SetTutorCourse(_NameCourse varchar(25), _TutorId INT, _Price float)
  RETURNS TINYINT
  BEGIN
    DECLARE _result TINYINT;
    SET _result = 0;
    INSERT INTO TutorCourses (`TutorID`, `CourseID`, `Price`)
    VALUES (_NameCourse, _TutorId, _Price);
    SET _result = 1;
    return _result;
  END;


DROP FUNCTION IF EXISTS UpdatePasswordStudent;
CREATE FUNCTION UpdatePasswordStudent(_email varchar(25), _Password varchar(50))
  RETURNS TINYINT
  BEGIN
    DECLARE _result TINYINT;
    SET _result = 0;

    UPDATE Student
    SET Student.Password = _Password
    where Student.Email = _email;

    SET _result = 1;
    return _result;
  END;

DROP FUNCTION IF EXISTS UpdatePasswordTutor;
CREATE FUNCTION UpdatePasswordTutor(_email varchar(25), _Password varchar(50))
  RETURNS TINYINT
  BEGIN
    DECLARE _result TINYINT;
    SET _result = 0;

    UPDATE Tutor
    SET Tutor.Password = _Password
    where Tutor.Email = _email;

    SET _result = 1;
    return _result;
  END;

DROP PROCEDURE IF EXISTS GetCourses;
CREATE PROCEDURE GetCourses()
  BEGIN
    SELECT *
    FROM Course;
  END;

CREATE FUNCTION SaveActivationCode(`_code` varchar(50))
  returns INT
  begin
    DECLARE _result INT;
    set _result = 0;

    INSERT into ActivationTable (`AcivationCode`, `Date`) values (_code, NOW());

    SET _result = 1;
    return _result;
  end;