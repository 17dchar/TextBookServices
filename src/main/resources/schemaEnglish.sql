CREATE TABLE "bookInfo"
(
    "bookCode" VARCHAR2(8 CHAR) NOT NULL ENABLE,
    "bookYear" VARCHAR2(4 CHAR) NOT NULL ENABLE,
    "bookTitle" VARCHAR2(40 CHAR),
    "seqNr" NUMBER(5,0),
    "barCode" VARCHAR2(13 CHAR) NOT NULL ENABLE
);

INSERT INTO bookInfo (bookCode, bookYear, bookTitle, seqNr, barCode)
    VALUES ('"+bc+"', '"+by+"', '"+bt+"', '"+bs+"', '"+bb+"');