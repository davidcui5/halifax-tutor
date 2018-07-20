CREATE TABLE ActivationTable
(
    ID int PRIMARY KEY AUTO_INCREMENT,
    ActivationCode varchar(50),
    Date date
);
CREATE TABLE Tutor
(
    ID int PRIMARY KEY AUTO_INCREMENT,
    FirstName varchar(25),
    LastName varchar(25),
    Email varchar(50),
    Password varchar(25),
    PhoneNumber varchar(25),
    Bio text,
    AccountActivation int DEFAULT 1 COMMENT 'The value could be 0 is not active or 1 is active',
    PlanID int,
    ExpiryDate date,
    CreditCardHoldName varchar(25),
    CreditCardNumber varchar(16),
    CreditCardExpiryDate varchar(7),
    ScurityCode varchar(3)
);

CREATE TABLE Student
(
    ID int PRIMARY KEY AUTO_INCREMENT,
    FirstName varchar(25),
    LastName varchar(25),
    Email varchar(25),
    Password varchar(25),
    AccountActivation int DEFAULT 1 COMMENT 'The value is even 0 for not activated account or 1 for activated account',
    School varchar(50)
);

CREATE TABLE Course
(
    ID int PRIMARY KEY AUTO_INCREMENT,
    Name varchar(25),
    School varchar(25)
);

CREATE TABLE WeeklySchedule
(
    ScheduleID int PRIMARY KEY AUTO_INCREMENT,
    TutorID int,
    Su1 int DEFAULT 0,
    Su2 int DEFAULT 0,
    Su3 int DEFAULT 0,
    Mo1 int DEFAULT 0,
    Mo2 int DEFAULT 0,
    Mo3 int DEFAULT 0,
    Tu1 int DEFAULT 0,
    Tu2 int DEFAULT 0,
    Tu3 int DEFAULT 0,
    We1 int DEFAULT 0,
    We2 int DEFAULT 0,
    We3 int DEFAULT 0,
    Th1 int DEFAULT 0,
    Th2 int DEFAULT 0,
    Th3 int DEFAULT 0,
    Fr1 int DEFAULT 0,
    Fr2 int DEFAULT 0,
    Fr3 int DEFAULT 0,
    Sa1 int DEFAULT 0,
    Sa2 int DEFAULT 0,
    Sa3 int DEFAULT 0
);

CREATE TABLE Admin
(
    Email varchar(50) PRIMARY KEY,
    Password varchar(25)
);

CREATE TABLE SubscriptionPlan
(
    ID int PRIMARY KEY,
    Name varchar(25),
    Price decimal(10,2),
    Description text,
    Months int
);

CREATE TABLE TutorCourses
(
    TutorID int,
    CourseID int PRIMARY KEY,
    Price float
);

CREATE TABLE Review
(
    ID int PRIMARY KEY,
    StudentID int,
    Text text,
    Date date,
    Rate int
);

ALTER TABLE TutorCourses
ADD CONSTRAINT TutorCourses_Course_ID_fk
FOREIGN KEY (CourseID) REFERENCES Course (ID);
ALTER TABLE TutorCourses
ADD CONSTRAINT TutorCourses_Tutor_ID_fk
FOREIGN KEY (TutorID) REFERENCES Tutor (ID);


ALTER TABLE Review
ADD CONSTRAINT Review_Student_ID_fk
FOREIGN KEY (StudentID) REFERENCES Student (ID);

ALTER TABLE Tutor
ADD CONSTRAINT Review_SubscriptionPlan_ID_fk
FOREIGN KEY (PlanID) REFERENCES Tutor (ID);

DROP FUNCTION IF EXISTS IsPhoneNew;
CREATE function IsPhoneNew(_phone varchar(15))
  returns boolean
  begin
    DECLARE counter INT;
    DECLARE isNew BOOL;
    set isNew = FALSE;

    SELECT count(Student.PhoneNumber)
    from Student
    where Student.PhoneNumber like _phone
    into counter;

    IF counter > 0
    then
      set isNew = false;
      return isNew;
    else
      SELECT count(Tutor.PhoneNumber)
      from Tutor
      where Tutor.PhoneNumber like _phone
      into counter;
      IF counter > 0
      THEN
        set isNew = false;
      else
        set isNew = true;
      end if;
    end if;
    return isNew;
  end;



DROP FUNCTION IF EXISTS IsEmailNew;
CREATE function IsEmailNew(_email varchar(50))
  returns boolean
  begin
    DECLARE counter INT;
    DECLARE isNew BOOL;
    SELECT count(*)
    from Student
    where Student.Email like _email
    into counter;

    IF counter > 0
    then
      set isNew = false;
    else
      SELECT count(*)
      from Tutor
      where Tutor.Email like _email
      into counter;
      IF counter > 0
      THEN
        set isNew = false;
      else
        set isNew = true;
      end if;
    end if;
    return isNew;
  end;

DROP FUNCTION IF EXISTS IsCreditCardNew;
CREATE function IsCreditCardNew(_cnumber varchar(50))
  returns boolean
  begin
    DECLARE counter INT;
    DECLARE isNew BOOL;
    SELECT count(email)
    from Tutor
    where Tutor.CreditCardNumber like _cnumber
    into counter;

    IF counter > 0
    then
      set isNew = false;
    else
      set isNew = true;
    end if;
    return isNew;
  end;


DROP FUNCTION IF EXISTS AuthorizeStudent;
CREATE FUNCTION AuthorizeStudent(_email varchar(50), _password varchar(50))
  returns boolean
  begin
    DECLARE counter INT;
    DECLARE isExist BOOL;

    SELECT count(*)
    from Student
    WHERE Student.Email LIKE _email AND Student.Password LIKE _password
    into counter;

    IF counter > 0
    THEN
      set isExist = true;
    else
      set isExist = false;
    end if;
    return isExist;
  end;

DROP FUNCTION IF EXISTS AuthorizeTutor;
CREATE FUNCTION AuthorizeTutor(_email varchar(50), _password varchar(50))
  returns boolean
  begin
    DECLARE counter INT;
    DECLARE isExist BOOL;

    SELECT count(*)
    from Tutor
    WHERE Tutor.Email LIKE _email AND Tutor.Password LIKE _password
    into counter;

    IF counter > 0
    THEN
      set isExist = true;
    else
      set isExist = false;
    end if;
    return isExist;
  end;

DROP FUNCTION IF EXISTS RegStudent;
CREATE FUNCTION RegStudent(_FirstName   varchar(50),
                           _LastName    varchar(50),
                           _Email       varchar(50),
                           _Password    varchar(50),
                           _School      varchar(50),
                           _PhoneNumber varchar(50))
  returns INT
  begin
    DECLARE checker BOOLEAN;
    DECLARE result INT;
    DECLARE lastID INT;
    DECLARE newID INT;

    SET result = 4;

    #   Check the email
    select IsEmailNew(_Email)
    into checker;

    IF checker like 0
    THEN
      return 2;
    end if;

    select IsPhoneNew(_PhoneNumber)
    into checker;

    IF checker like 0
    THEN
      return 3;
    end if;

    select count(id)
    from Student
    into lastID;

    INSERT INTO `Student` (`FirstName`, `LastName`, `Email`, `Password`, `AccountActivation`, `School`, `PhoneNumber`,`Banned`)
    VALUES (_FirstName, _LastName, _Email, _Password, 0, _School, _PhoneNumber,0);

    select count(id)
    from Student
    INTO newID;

    IF ((lastID + 1) like newID)
    THEN
      set result = 1;
    end if;

    return result;
  end;

DROP FUNCTION IF EXISTS RegTutor;
CREATE FUNCTION RegTutor(_FirstName   varchar(25),
                         _LastName    varchar(25),
                         _Email       varchar(50),
                         _Password    varchar(25),
                         _PhoneNumber varchar(25))
  returns INT
  begin
    DECLARE checker BOOLEAN;
    DECLARE result INT;
    DECLARE lastID INT;
    DECLARE newID INT;

    SET result = 4;

    select IsEmailNew(_Email)
    into checker;

    IF checker like 0
    THEN
      return 2;
    end if;

    select IsPhoneNew(_PhoneNumber)
    into checker;

    IF checker like 0
    THEN
      return 3;
    end if;

    select count(*)
    from Tutor
    into lastID;

    INSERT INTO `CSCI5308_12_DEVINT`.`Tutor` (`FirstName`, `LastName`, `Email`, `Password`, `PhoneNumber`
      , `Bio`, `AccountActivation`, `PlanID`, `ExpiryDate`, `CreditCardHoldName`, `CreditCardNumber`
      , `CreditCardExpiryDate`, `ScurityCode`, `Banned`)
    VALUES (_FirstName, _LastName, _Email, _Password, _PhoneNumber, NULL, 0, NULL, NULL, NULL
      , NULL, NULL, NULL,0);

    select count(*)
    from Tutor
    INTO newID;

    IF ((lastID + 1) like newID)
    THEN
      set result = 1;
    end if;

    return result;
  end;

DROP PROCEDURE IF EXISTS GetStudentId;
CREATE PROCEDURE GetStudentId(_Email varchar(50))
  begin
    select *
    from Student
    where Student.Email like _Email;
  end;

DROP PROCEDURE IF EXISTS GetTutorID;
CREATE PROCEDURE GetTutorID(_Email varchar(50))
  begin
    select *
    from Tutor
    where Tutor.Email like _Email;
  end;

DROP function IF EXISTS SaveActivationCode;
CREATE function SaveActivationCode(_code varchar(50))
  RETURNS INT

  begin
    DECLARE _result INT;
    DECLARE _time DATE;
    DECLARE lastID INT;
    DECLARE newID INT;

    set _result = 4;

    select NOW()
    into _time;

    Select count(*)
    from ActivationTable
    Where ActivationTable.AcivationCode like _code
    into lastID;

    if lastID > 0
    then
      update ActivationTable
      set ActivationTable.Date = _time
      where AcivationCode = _code;
      set _result=1;
    else
      select max(ID)
      from ActivationTable
      into lastID;

      INSERT into ActivationTable (`AcivationCode`, `Date`) values (_code, _time);

      select max(ID)
      from ActivationTable
      INTO newID;

      IF ((lastID + 1) like newID)
      THEN
        set _result = 1;
      end if;
    end if;
    return _result;
  end;

DROP function IF EXISTS ActivateTutor;
CREATE function ActivateTutor(_id INT, _code varchar(50))
  RETURNS INT

  begin
    DECLARE _result INT;
    DECLARE _counter INT;
    DECLARE _diff INT;

    set _result = 4;

    SELECT count(*)
    from ActivationTable
    where ActivationTable.AcivationCode like _code
    into _counter;

    if _counter > 0
    then

      select datediff(date(now()), (SELECT ActivationTable.Date
                                    from ActivationTable
                                    where AcivationCode like _code))
      into _diff;

      if _diff > 3
      then
        set _result = 2;
      else
        UPDATE Tutor
        SET Tutor.AccountActivation = 1
        where Tutor.ID = _id;
        set _result = 1;
      end if;
    end if;
    return _result;
  end;
  RETURNS INT

  begin
    DECLARE _result INT;
    DECLARE _counter INT;

    set _result = 4;

    SELECT count(*)
    from ActivationTable
    where ActivationTable.AcivationCode like _code
    into _counter;

    if _counter > 0
    then
      UPDATE Tutor
      SET Tutor.AccountActivation = 1
      where Tutor.ID = _id;

      SET _result = 1;
    else
      set _result = 3;
    end if;
    return _result;
  end;
DROP FUNCTION IF EXISTS GetStudentId;
CREATE function GetStudentId(_email varchar(50))
  returns INT
  begin
    DECLARE id INT;

    SELECT Student.ID
    from Student
    where Student.Email like _email
    into id;

    return id;
  end;

DROP FUNCTION IF EXISTS GetStudentId;
CREATE function GetStudentId(_email varchar(50))
  returns INT
  begin
    DECLARE id INT;

    SELECT Student.ID
    from Student
    where Student.Email like _email
    into id;

    return id;
  end;

DROP FUNCTION IF EXISTS GetTutorId;
CREATE function GetTutorId(_email varchar(50))

  returns INT
  begin
    DECLARE id INT;

    SELECT Tutor.ID
    from Tutor
    where Tutor.Email like _email
    into id;

    return id;
  end;

DROP FUNCTION IF EXISTS DeleteStudent;
CREATE function DeleteStudent(id int)
  returns boolean
  begin
    DECLARE countB INT;
    DECLARE countA INT;

    select count(*)
    from Student
    into countB;

    DELETE from Student
    where Student.ID like id;

    select count(*)
    from Student
    into countA;

    if countB > countA
    then
      return true;
    else
      return false;
    end if;
  end;

DROP FUNCTION IF EXISTS DeleteTutor;
CREATE function DeleteTutor(id int)
  returns boolean
  begin
    DECLARE countB INT;
    DECLARE countA INT;

    select count(*)
    from Tutor
    into countB;

    DELETE from Tutor
    where Tutor.ID like id;

    select count(*)
    from Tutor
    into countA;

    if countB > countA
    then
      return true;
    else
      return false;
    end if;
  end;
DROP function IF EXISTS ActivateStudent;
CREATE function ActivateStudent(_id INT, _code varchar(50))
  RETURNS INT

  begin
    DECLARE _result INT;
    DECLARE _counter INT;
    DECLARE _diff INT;

    set _result = 4;

    SELECT count(*)
    from ActivationTable
    where ActivationTable.AcivationCode like _code
    into _counter;

    if _counter > 0
    then
      select datediff(date(now()), (SELECT ActivationTable.Date
                                    from ActivationTable
                                    where AcivationCode like _code))
      into _diff;

      if _diff > 3
      then
        set _result = 2;
      else
        UPDATE Student
        SET Student.AccountActivation = 1
        where Student.ID = _id;
        set _result = 1;
      end if;
    end if;
    return _result;
  end;
