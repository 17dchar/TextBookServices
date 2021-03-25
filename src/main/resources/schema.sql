CREATE TABLE Sys."NWTXDT"
   (    "NWTXDT_BOOK_CODE" VARCHAR2(8 CHAR) NOT NULL ENABLE,
    "NWTXDT_EDITION_YEAR" VARCHAR2(4 CHAR) NOT NULL ENABLE,
    "NWTXDT_SEQ_NR" NUMBER(5,0),
    "NWTXDT_BARCODE" VARCHAR2(13 CHAR) NOT NULL ENABLE,
    "NWTXDT_PIDM" VARCHAR2(8 CHAR),
    "NWTXDT_TERM" VARCHAR2(6 CHAR),
    "NWTXDT_DATE_CHECKED_OUT" DATE,
    "NWTXDT_DISPOSITION" VARCHAR2(1 CHAR),
    "NWTXDT_BOOK_SALE_PRICE" NUMBER(12,2),
    "NWTXDT_PREV_PIDM" NUMBER(8,0),
    "NWTXDT_PREV_TERM" VARCHAR2(6 CHAR),
    "NWTXDT_PREV_DATE_CHECKED_IN" DATE,
    "NWTXDT_ACTIVITY_DATE" DATE,
    "NWTXDT_BILLABLE_FLAG" VARCHAR2(1 CHAR)
   );

   -- notes: nwtxdt = northwest textbook database table
   -- pidm = 919
   -- seq_num = what copy number of the book. like 15th copy of a literature book.
   -- isbn = manufacturer book code

   CREATE TABLE "SPRIDEN"
   (	"SPRIDEN_PIDM" NUMBER(8,0) NOT NULL ENABLE,
	"SPRIDEN_ID" VARCHAR2(9 CHAR) NOT NULL ENABLE,
	"SPRIDEN_LAST_NAME" VARCHAR2(60 CHAR) NOT NULL ENABLE,
	"SPRIDEN_FIRST_NAME" VARCHAR2(60 CHAR),
	"SPRIDEN_MI" VARCHAR2(60 CHAR));

    -- spriden = student info?,
    -- spriden_pidm = nwtxdt_pidm
    -- mi = middle name

 CREATE TABLE "SCBCRSE"
   (	"SCBCRSE_SUBJ_CODE" VARCHAR2(4 CHAR) NOT NULL ENABLE,
	"SCBCRSE_CRSE_NUMB" VARCHAR2(5 CHAR) NOT NULL ENABLE,
	"SCBCRSE_EFF_TERM" VARCHAR2(6 CHAR),
	"SCBCRSE_TITLE" VARCHAR2(30 CHAR));

    -- scbcrse = course
	-- notes: subj_code "CSIS", crse_numb "44140", eff_term current term if active or last term
	-- notes: how do we get "44"? Another table? Just hardcode or user would know.

  CREATE TABLE "STVTERM"
   (	"STVTERM_CODE" VARCHAR2(6 CHAR) NOT NULL ENABLE,
	"STVTERM_DESC" VARCHAR2(30 CHAR) NOT NULL ENABLE);


	-- notes: term table  (ie: current 202120 fall- 10, summer - 30)  desc is the name 20 = Spring

 CREATE TABLE "SFRSTCR"
   (	"SFRSTCR_TERM_CODE" VARCHAR2(6 CHAR) NOT NULL ENABLE,
	"SFRSTCR_PIDM" NUMBER(8,0) NOT NULL ENABLE,
	"SFRSTCR_CRN" VARCHAR2(5 CHAR) NOT NULL ENABLE,
	"SFRSTCR_RSTS_CODE" VARCHAR2(2 CHAR),
	"SFRSTCR_LEVL_OVER" VARCHAR2(1 CHAR));

	-- notes: term_code is the STVTERM_CODE, CRN course number not 44140 unique to course and section, rsts_code online or reg or prof...,
	-- level Y? or N?

 CREATE TABLE  "SSBSECT"
   (	"SSBSECT_TERM_CODE" VARCHAR2(6 CHAR) NOT NULL ENABLE,
	"SSBSECT_CRN" VARCHAR2(5 CHAR) NOT NULL ENABLE,
	"SSBSECT_PTRM_CODE" VARCHAR2(3 CHAR),
	"SSBSECT_SUBJ_CODE" VARCHAR2(4 CHAR) NOT NULL ENABLE,
	"SSBSECT_CRSE_NUMB" VARCHAR2(5 CHAR) NOT NULL ENABLE,
	"SSBSECT_SEQ_NUMB" VARCHAR2(3 CHAR) NOT NULL ENABLE);

	-- notes: for sub sections in a course
	-- ptrm reg or online or prof or waiver, subj CSIS, crse 44140, seq is section 01 or 02

CREATE TABLE "SSRMEET"
   (	"SSRMEET_TERM_CODE" VARCHAR2(6 CHAR) NOT NULL ENABLE,
	"SSRMEET_CRN" VARCHAR2(5 CHAR) NOT NULL ENABLE,
	"SSRMEET_BEGIN_TIME" VARCHAR2(4 CHAR),
	"SSRMEET_END_TIME" VARCHAR2(4 CHAR),
	"SSRMEET_MON_DAY" VARCHAR2(1 CHAR),
	"SSRMEET_TUE_DAY" VARCHAR2(1 CHAR),
	"SSRMEET_WED_DAY" VARCHAR2(1 CHAR),
	"SSRMEET_THU_DAY" VARCHAR2(1 CHAR),
	"SSRMEET_FRI_DAY" VARCHAR2(1 CHAR));

	-- notes: meet times for a class crn
	-- begin time 1030, 0930 MILITARY time
	-- ss = subsection

CREATE TABLE "SGBSTDN"
   (	"SGBSTDN_PIDM" NUMBER(8,0) NOT NULL ENABLE,
	"SGBSTDN_TERM_CODE_EFF" VARCHAR2(6 CHAR) NOT NULL ENABLE,
	"SGBSTDN_STST_CODE" VARCHAR2(2 CHAR) NOT NULL ENABLE,
	"SGBSTDN_LEVL_CODE" VARCHAR2(2 CHAR));

	-- notes: student uhh stats?
	-- term code eff is 202120 last active/enrolled semester?, levl code undergrad, stst 'as' 'is'?

CREATE TABLE "SFRVERF"
   (	"SFRVERF_PIDM" NUMBER(8,0),
	"SFRVERF_TERM_CODE" VARCHAR2(6 CHAR),
	"SFRVERF_ACTIVITY_DATE" DATE,
	"SFRVERF_LEVL_CODE" VARCHAR2(4 CHAR),
	"SFRVERF_CANCEL_FLAG" VARCHAR2(1 CHAR));

	-- notes: student verification
	-- activity date date they verified, levl undergrad 'ug' or grad 'gr', cancel bool if they canceled their verification

   CREATE TABLE MyTable(
        pkey Number GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) primary key,
        personName varchar2(26 char) not null,
        age Integer not null check (age >= 18)

   );

   select * from sys.NWTXDT;

   select * from dba_tables where table_name = 'NWTXDT';

   insert into Sys.MyTable (personName, age) values('bob', 21);
   insert into Sys.MyTable (personName, age) values('nick', 22);
   select * from MyTable;

   CREATE TABLE "NWTXAR"
   (    "NWTXAR_PIDM" NUMBER(8,0),
    "NWTXAR_SEQ_NO" NUMBER(5,0),
    "NWTXAR_DETAIL_CODE" VARCHAR2(4 CHAR),
    "NWTXAR_AMOUNT" NUMBER(12,2),
    "NWTXAR_ACTIVITY_DATE" DATE,
    "NWTXAR_DOCUMENT_NO" VARCHAR2(8 CHAR),
    "NWTXAR_BILL_DATE" DATE,
    "NWTXAR_TERM" VARCHAR2(6 CHAR)
   );
   -- notes: nw textbook access resource?
   -- detail code is hard stuff, activity date is date of charge, doc num book code?, bill date not relevant, term of purchase


CREATE TABLE "NWTXBN"
   (    "NWTXBN_PIDM" NUMBER(8,0),
    "NWTXBN_BAG_NUMBER" NUMBER(5,0)
   );

   -- notes: bag number is number on bag when you pick up books

CREATE TABLE "NWTXCM"
   (    "NWTXCM_COURSE" VARCHAR2(9 CHAR) NOT NULL ENABLE,
    "NWTXCM_MESSAGE" VARCHAR2(15 CHAR),
    "NWTXCM_ACTIVITY_DATE" DATE
   );

   -- notes: course message
   -- course csis59901 or CSIS599** for all sections, message is custom message, date is when it was made (ddmmyy) 20-AUG-08

CREATE TABLE "NWTXIN"
   (    "NWTXIN_BOOK_CODE" VARCHAR2(8 CHAR) NOT NULL ENABLE,
    "NWTXIN_EDITION_YEAR" VARCHAR2(4 CHAR) NOT NULL ENABLE,
    "NWTXIN_TITLE" VARCHAR2(40 CHAR),
    "NWTXIN_AUTHOR" VARCHAR2(30 CHAR),
    "NWTXIN_PUBLISHER" VARCHAR2(30 CHAR),
    "NWTXIN_BOOK_STATUS" VARCHAR2(1 CHAR),
    "NWTXIN_CURRENT_PRICE" NUMBER(12,2),
    "NWTXIN_ISBN" VARCHAR2(20 CHAR),
    "NWTXIN_PURCHASE_DATE" DATE,
    "NWTXIN_FIRST_USED_DATE" DATE,
    "NWTXIN_DISCONTINUED_DATE" DATE,
    "NWTXIN_ACTIVITY_DATE" DATE,
    "NWTXIN_COURSE_NAME" VARCHAR2(40 CHAR),
    "NWTXIN_COURSE1" VARCHAR2(9 CHAR),
    "NWTXIN_COURSE2" VARCHAR2(9 CHAR),
    "NWTXIN_COURSE3" VARCHAR2(9 CHAR),
    "NWTXIN_COURSE4" VARCHAR2(9 CHAR),
    "NWTXIN_COURSE5" VARCHAR2(9 CHAR),
    "NWTXIN_COMMENT" VARCHAR2(100 CHAR)
   );

   -- notes: in means?
   -- status if discontiuned 'D' (if 'D' price is $2 always -- set current price), activity date is when first created, course name of course1!!
   -- comment is message tied to book, isbn manufacture barcode num NOT NW's barcode, NW's is strike barcode (we use this one)

CREATE TABLE "NWTXMS"
   (    "NWTXMS_PIDM" NUMBER(8,0),
    "NWTXMS_MESSAGE" VARCHAR2(255 CHAR)
   );

   -- notes: ms = message
   -- message is message on the indiv/patron

select * from dba_tables where Owner = 'SYS' and TABLE_NAME like 'NW%';

select * from dba_tables where Owner = 'SYS' and TABLE_NAME like 'SGB%';