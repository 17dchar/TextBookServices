SELECT * FROM NWTXIN WHERE NWTXIN_BOOK_CODE = "book code"; --shows all data of book from their book code.

INSERT INTO NWTXIN (NWTXIN_EDITION_YEAR, NWTXIN_TITLE, NWTXIN_AUTHOR, NWTXIN_PUBLISHER, NWTXIN_BOOK_STATUS,
NWTXIN_CURRENT_PRICE, NWTXIN_ISBN, NWTXIN_PURCHASE_DATE, NWTXIN_FIRST_USED_DATE, NWTXIN_DISCONTINUED_DATE, NWTXIN_ACTIVITY_DATE, NWTXIN_COURSE_NAME)
VALUES (NWTXIN_EDITION_YEAR, NWTXIN_TITLE, NWTXIN_AUTHOR, NWTXIN_PUBLISHER, NWTXIN_BOOK_STATUS,
       NWTXIN_CURRENT_PRICE, NWTXIN_ISBN, NWTXIN_PURCHASE_DATE, NWTXIN_FIRST_USED_DATE, NWTXIN_DISCONTINUED_DATE, NWTXIN_ACTIVITY_DATE, NWTXIN_COURSE_NAME)
       WHERE NWTXIN_BOOK_CODE = "book code"; --its weird my logic was that if the book code equals this add it to this table with all its info

INSERT INTO NWTXIN (*)
VALUES (*) WHERE NWTXIN_BOOK_CODE = "book code"; -- same thing as above?