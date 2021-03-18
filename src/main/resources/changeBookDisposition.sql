CREATE TABLE "bookDisposition" (
    "bookCode" VARCHAR2(8 CHAR) NOT NULL ENABLE,
    "bookYear" VARCHAR2(4 CHAR) NOT NULL ENABLE,
    "barCode" VARCHAR2(13 CHAR) NOT NULL ENABLE,
    "bookTitle" VARCHAR2(40 CHAR),
    "seqNr" NUMBER(5,0),
    "bookDis" VARCHAR2(1 CHAR),
);

SELECT bookTitle, seqNr, bookDis
    FROM bookDisposition
    WHERE bookCode = 'bc' OR bookYear = 'by' OR barCode = 'br';