create database ssce;
use ssce;

-- ----------------------------
-- TABLE DEFINITIONS
-- ----------------------------

CREATE TABLE Courses (
  Cid INT AUTO_INCREMENT PRIMARY KEY,
  Cname VARCHAR(100) UNIQUE NOT NULL,
  Ccompany VARCHAR(100),
  Ccertification VARCHAR(100),
  Ctype VARCHAR(50),
  Cduration INT NOT NULL,
  Cfee INT NOT NULL
);

CREATE TABLE StudentDetails (
  Sid INT AUTO_INCREMENT PRIMARY KEY,
  Sname VARCHAR(100) UNIQUE NOT NULL,
  Sphone VARCHAR(15) UNIQUE NOT NULL,
  Saddress TEXT,
  StotalDuration INT DEFAULT 0,
  SdurationLeft INT DEFAULT 0,
  StotalFee INT DEFAULT 0,
  SbalanceFee INT DEFAULT 0
);

CREATE TABLE SCourses (
  SCid INT AUTO_INCREMENT PRIMARY KEY,
  Sid INT,
  Sname VARCHAR(100),
  Cid INT,
  Cname VARCHAR(100),
  Cduration INT,
  Cfee INT,
  SCnum INT,
  SCdiscount INT DEFAULT 0,
  SCdiscFee INT,
  SCpayType VARCHAR(20) NOT NULL,
  SCpayMode VARCHAR(20) NOT NULL,
  SCadmissionDate DATE,
  SCeraStatus VARCHAR(20),
  SCcertification VARCHAR(100),
  SCresult VARCHAR(100),
  FOREIGN KEY (Sid) REFERENCES StudentDetails(Sid),
  FOREIGN KEY (Cid) REFERENCES Courses(Cid)
);

CREATE TABLE EMI (
  Eid INT AUTO_INCREMENT PRIMARY KEY,
  Sid INT NOT NULL,
  Sname VARCHAR(100),
  Cid INT NOT NULL,
  Cname VARCHAR(100),
  SCnum INT NOT NULL,
  Emonth DATE,
  Einstallment INT,
  Epaid BOOLEAN DEFAULT FALSE,
  EpayDate DATE,
  EpayMode VARCHAR(20),
  FOREIGN KEY (Sid) REFERENCES StudentDetails(Sid),
  FOREIGN KEY (Cid) REFERENCES Courses(Cid)
);

-- ----------------------------
-- TRIGGERS
-- ----------------------------

-- Fill EMI Sname and Cname before insert
DELIMITER $$
CREATE TRIGGER trg_autofill_EMI
BEFORE INSERT ON EMI
FOR EACH ROW
BEGIN
  DECLARE v_sname VARCHAR(100);
  DECLARE v_cname VARCHAR(100);

  SELECT Sname INTO v_sname FROM StudentDetails WHERE Sid = NEW.Sid;
  SELECT Cname INTO v_cname FROM Courses WHERE Cid = NEW.Cid;

  SET NEW.Sname = v_sname;
  SET NEW.Cname = v_cname;
END $$
DELIMITER ;

-- Update StudentDetails after EMI is paid
DELIMITER $$
CREATE TRIGGER trg_update_after_emi
AFTER UPDATE ON EMI
FOR EACH ROW
BEGIN
  IF NEW.Epaid = TRUE AND OLD.Epaid = FALSE THEN
    UPDATE StudentDetails
    SET 
      SbalanceFee = SbalanceFee - NEW.Einstallment,
      SdurationLeft = SdurationLeft - 1
    WHERE Sid = NEW.Sid;
  END IF;
END $$
DELIMITER ;

-- Auto-fill SCourses fields before insert
DELIMITER $$
CREATE TRIGGER trg_autofill_SCourses
BEFORE INSERT ON SCourses
FOR EACH ROW
BEGIN
  DECLARE v_sname VARCHAR(100);
  DECLARE v_cname VARCHAR(100);
  DECLARE v_cduration INT;
  DECLARE v_cfee INT;

  SELECT Sname INTO v_sname FROM StudentDetails WHERE Sid = NEW.Sid;
  SELECT Cname, Cduration, Cfee INTO v_cname, v_cduration, v_cfee FROM Courses WHERE Cid = NEW.Cid;

  SET NEW.Sname = v_sname;
  SET NEW.Cname = v_cname;
  SET NEW.Cduration = v_cduration;
  SET NEW.Cfee = v_cfee;
  SET NEW.SCdiscFee = v_cfee - NEW.SCdiscount;
END $$
DELIMITER ;

-- Update StudentDetails summary after SCourses insert
DELIMITER $$
CREATE TRIGGER trg_update_student_summary_after_insert
AFTER INSERT ON SCourses
FOR EACH ROW
BEGIN
  UPDATE StudentDetails
  SET 
    StotalDuration = StotalDuration + NEW.Cduration,
    SdurationLeft = SdurationLeft + NEW.Cduration,
    StotalFee = StotalFee + NEW.SCdiscFee,
    SbalanceFee = SbalanceFee + NEW.SCdiscFee
  WHERE Sid = NEW.Sid;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_insert_emi_after_scourse
AFTER INSERT ON SCourses
FOR EACH ROW
BEGIN
  DECLARE calInstal INT;
  DECLARE i INT DEFAULT 1;
  DECLARE admDate DATE;
  IF NEW.SCpayType = 'EMI' THEN
    SET calInstal = ROUND(NEW.SCdiscFee / 3) + 250;
    SET calInstal = CEIL(calInstal / 50.0) * 50;
    SET admDate = NEW.SCadmissionDate;
    WHILE i <= 3 DO
      INSERT INTO EMI (Sid, Cid, SCnum, Emonth, Einstallment, Epaid)
      VALUES (
        NEW.Sid,
        NEW.Cid,
        NEW.SCnum,
        DATE_ADD(admDate, INTERVAL (i - 1) MONTH),
        calInstal,
        FALSE
      );
      SET i = i + 1;
    END WHILE;
  END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_delete_emi_on_full_payment
AFTER UPDATE ON SCourses
FOR EACH ROW
BEGIN
  IF OLD.SCpayType = 'EMI' AND NEW.SCpayType = 'FULL' THEN
    DELETE FROM EMI
    WHERE Sid = NEW.Sid AND Cid = NEW.Cid AND SCnum = NEW.SCnum;
  END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_delete_emi_after_scourse_delete
AFTER DELETE ON SCourses
FOR EACH ROW
BEGIN
  DELETE FROM EMI
  WHERE Sid = OLD.Sid AND Cid = OLD.Cid AND SCnum = OLD.SCnum;
END $$
DELIMITER ;

-- ----------------------------
-- VIEW
-- ----------------------------

CREATE VIEW unpaidEMIs AS
SELECT * FROM EMI WHERE Epaid = FALSE;

-- ----------------------------
-- SAMPLE INSERTS
-- ----------------------------

INSERT INTO Courses (Cname, Ccompany, Ccertification, Ctype, Cduration, Cfee)
VALUES ('MSCIT', 'MKCL', 'GOV', 'COMP', 3, 6000),
('MSOFFICE','SSC','PRIV','COMP',3,5000);

INSERT INTO StudentDetails (Sname, Sphone, Saddress)
VALUES ('Jagjit', '8879072265', 'Kopri'),
('Prasad','7045000807','Shivai'),
('Abhi','9821149098','RamBaugh');

-- INSERT INTO SCourses (Sid, Cid, SCnum, SCdiscount, SCpayType, SCpayMode, SCadmissionDate)
-- VALUES (1, 1, 1, 200, 'FULL', 'Cash', '2025-05-03'),
-- (2, 1, 1, 200, 'EMI', 'Cash', '2025-05-03'),
-- (3, 2, 1, 250, 'FULL', 'Cash', '2025-05-03'),
-- (3, 1, 2, 250, 'FULL', 'Cash', '2025-05-03');

-- =========================
-- 6. DB Commands
-- =========================

desc Courses;
desc StudentDetails;
desc SCourses;
desc EMI;

select * from Courses;
select * from StudentDetails;
select * from SCourses;
select * from EMI;

-- truncate Courses;
-- truncate StudentDetails;
-- truncate SCourses;
-- truncate EMI;

-- drop table Courses;
-- drop table StudentDetails;
-- drop table SCourses;
-- drop table EMI;

-- drop database ssce;