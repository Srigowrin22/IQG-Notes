-- Script to create the Products and Categories tables for the JavaEE course
-- These two tables replace the Products and Categories Views
-- $Id: createFODJavaEESchemaObjects.sql 

--set echo off

SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 132
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 999

SEt VERIFY OFF


SPOOL FOD_SpecialTablesInstall.log

SET ECHO ON

DROP VIEW PRODUCT_CATEGORIES;
DROP VIEW PRODUCTS;


CREATE TABLE PRODUCT_CATEGORIES_TEMP
AS SELECT
--    CATEGORY_TRANSLATIONS.LANGUAGE LANGUAGE_ID, 
    CATEGORY_TRANSLATIONS.CATEGORY_NAME CATEGORY_NAME, 
    CATEGORY_TRANSLATIONS.CATEGORY_DESCRIPTION CATEGORY_DESCRIPTION, 
    CATEGORY_ID CATEGORY_ID, 
    PRODUCT_CATEGORIES_BASE.PARENT_CATEGORY_ID PARENT_CATEGORY_ID, 
    PRODUCT_CATEGORIES_BASE.CATEGORY_LOCKED_FLAG CATEGORY_LOCKED_FLAG
FROM CATEGORY_TRANSLATIONS INNER JOIN PRODUCT_CATEGORIES_BASE USING (CATEGORY_ID)
WHERE
    CATEGORY_TRANSLATIONS.LANGUAGE = 'US' 
;





CREATE TABLE PRODUCTS_TEMP AS SELECT 
    PRODUCT_TRANSLATIONS.DESCRIPTION DESCRIPTION, 
    PRODUCT_TRANSLATIONS.ADDITIONAL_INFO ADDITIONAL_INFO, 
--    PRODUCT_TRANSLATIONS.LANGUAGE LANGUAGE_ID,
    PRODUCTS_BASE.COST_PRICE COST_PRICE, 
    PRODUCTS_BASE.SUPPLIER_ID SUPPLIER_ID, 
    PRODUCTS_BASE.PRODUCT_NAME PRODUCT_NAME, 
    PRODUCTS_BASE.LIST_PRICE LIST_PRICE, 
    PRODUCT_ID PRODUCT_ID, 
    PRODUCTS_BASE.CATEGORY_ID CATEGORY_ID, 
    PRODUCTS_BASE.MIN_PRICE MIN_PRICE, 
    PRODUCTS_BASE.WARRANTY_PERIOD_MONTHS WARRANTY_PERIOD_MONTHS, 
    PRODUCTS_BASE.SHIPPING_CLASS_CODE SHIPPING_CLASS_CODE, 
    PRODUCTS_BASE.EXTERNAL_URL EXTERNAL_URL, 
    PRODUCTS_BASE.ATTRIBUTE_CATEGORY ATTRIBUTE_CATEGORY
--    PRODUCTS_BASE.ATTRIBUTE15 ATTRIBUTE15, 
--    PRODUCTS_BASE.ATTRIBUTE1 ATTRIBUTE1, 
--    PRODUCTS_BASE.ATTRIBUTE2 ATTRIBUTE2, 
--    PRODUCTS_BASE.ATTRIBUTE3 ATTRIBUTE3, 
--    PRODUCTS_BASE.ATTRIBUTE4 ATTRIBUTE4, 
--    PRODUCTS_BASE.ATTRIBUTE5 ATTRIBUTE5, 
--    PRODUCTS_BASE.ATTRIBUTE6 ATTRIBUTE6, 
--    PRODUCTS_BASE.ATTRIBUTE7 ATTRIBUTE7, 
--    PRODUCTS_BASE.ATTRIBUTE8 ATTRIBUTE8, 
--    PRODUCTS_BASE.ATTRIBUTE9 ATTRIBUTE9,
--    PRODUCTS_BASE.ATTRIBUTE10 ATTRIBUTE10, 
--    PRODUCTS_BASE.ATTRIBUTE11 ATTRIBUTE11, 
--    PRODUCTS_BASE.ATTRIBUTE12 ATTRIBUTE12, 
--    PRODUCTS_BASE.ATTRIBUTE13 ATTRIBUTE13, 
--    PRODUCTS_BASE.ATTRIBUTE14 ATTRIBUTE14 
FROM 
    PRODUCT_TRANSLATIONS INNER JOIN PRODUCTS_BASE USING (PRODUCT_ID)
WHERE
    PRODUCT_TRANSLATIONS.LANGUAGE = 'US' 
;


select   object_type,
         object_name 
from     user_objects 
group by object_type, object_name
order by object_type desc, object_name;


COMMENT ON TABLE PRODUCT_CATEGORIES_TEMP IS 'This table combines the Category_base table and the Category_Translations table. The table includes only the US language translations.'
;

COMMENT ON TABLE PRODUCTS_TEMP IS 'This table combines the Products_Base and Product_Translation tables. The table includes only the US language translations.'
;


--DROP TABLES


DROP TABLE PRODUCT_CATEGORIES_BASE CASCADE constraints;
DROP TABLE PRODUCT_TRANSLATIONS CASCADE constraints;
DROP TABLE PRODUCTS_BASE CASCADE constraints;
DROP TABLE CATEGORY_TRANSLATIONS CASCADE constraints;





-- create real tables from temp

CREATE TABLE PRODUCTS AS
	SELECT * FROM PRODUCTS_TEMP;



ALTER TABLE PRODUCTS
ADD CONSTRAINT PRODUCTS_PK PRIMARY KEY
(
PRODUCT_ID
)
 ENABLE
;


CREATE TABLE CATEGORIES AS
	SELECT * FROM product_CATEGORIES_TEMP;


ALTER TABLE CATEGORIES 
ADD CONSTRAINT CATEGORIES_PK PRIMARY KEY
(
CATEGORY_ID
)
 ENABLE
;


-- drop test

DROP TABLE PRODUCTS_TEMP CASCADE constraints;


DROP TABLE PRODUCT_CATEGORIES_TEMP CASCADE constraints;



-- Remove extra audit columns and add object_version_id to products and categories


--alter table orders drop column created_by;
--alter table orders drop column creation_date;
--alter table orders drop column last_updated_by;
--alter table orders drop column last_update_date;


--alter table order_items drop column created_by;
--alter table order_items drop column creation_date;
--alter table order_items drop column last_updated_by;
--alter table order_items drop column last_update_date;


--alter table persons drop column created_by;
--alter table persons drop column creation_date;
--alter table persons drop column last_updated_by;
--alter table persons drop column last_update_date;

alter table products add 
(created_by varchar(60),
 creation_date date,
 last_updated_by varchar(60),
 last_update_date date,
 OBJECT_VERSION_ID NUMBER(15));

alter table categories add 
(created_by varchar(60),
 creation_date date,
 last_updated_by varchar(60),
 last_update_date date,
 OBJECT_VERSION_ID NUMBER(15));



-- Fix data in order_items (remove rows with products that are not in our products table)




-- Cleanup data that points to products that are no longer in the products table



delete from order_items
where
product_id not in (select product_id from products)
;

delete from warehouse_stock_levels
where
product_id not in (select product_id from products)
;


ALTER TABLE ORDER_ITEMS
ADD CONSTRAINT ORDER_ITEMS_PRODUCTS_FK FOREIGN KEY
(
PRODUCT_ID
)
REFERENCES PRODUCTS
(
PRODUCT_ID
) ENABLE
;



ALTER TABLE PRODUCTS
ADD CONSTRAINT PRODUCTS_SUPPLIERS_FK FOREIGN KEY
(
SUPPLIER_ID
)
REFERENCES SUPPLIERS
(
SUPPLIER_ID
) ENABLE
;




ALTER TABLE PRODUCTS
ADD CONSTRAINT PRODUCTS_PRODUCT_CATEGORIES_FK FOREIGN KEY
(
CATEGORY_ID
)
REFERENCES CATEGORIES
(
CATEGORY_ID
) ENABLE
;




ALTER TABLE CATEGORIES
ADD CONSTRAINT PRODUCT_CATEGORIES_FK FOREIGN KEY
(
PARENT_CATEGORY_ID
)
REFERENCES CATEGORIES
(
CATEGORY_ID
) ENABLE
;




ALTER TABLE PRODUCT_IMAGES
ADD CONSTRAINT PRODUCT_IMAGES_PRODUCTS_FK FOREIGN KEY
(
PRODUCT_ID
)
REFERENCES PRODUCTS
(
PRODUCT_ID
) ENABLE
;



ALTER TABLE WAREHOUSE_STOCK_LEVELS
ADD CONSTRAINT WAREHOUSE_STOCK_LEVELS_FK1 FOREIGN KEY
(
PRODUCT_ID
)
REFERENCES PRODUCTS
(
PRODUCT_ID
) ENABLE
;




ALTER TABLE CUSTOMER_INTERESTS
ADD CONSTRAINT CUSTOMER_INTERESTS_FK1 FOREIGN KEY
(
CATEGORY_ID
)
REFERENCES CATEGORIES
(
CATEGORY_ID
) ENABLE
;




ALTER TABLE CATEGORIES
ADD CONSTRAINT PRODUCT_CATEGORIES_LOCKED_CHK CHECK
(CATEGORY_LOCKED_FLAG in ('Y','N'))
 ENABLE
;

-- This is the BOOKS table from Bijoy for the JavaEE Transactional practice.

DROP TABLE BOOKS;

CREATE TABLE BOOKS (
  BOOK_ID NUMBER(8,0) NOT NULL,
  NAME VARCHAR2(50) NOT NULL,
  GENRE	VARCHAR2(4000) NOT NULL
);

ALTER TABLE BOOKS
ADD CONSTRAINT BK_PK PRIMARY KEY (BOOK_ID) ENABLE;

Insert into BOOKS values 
(100,'The Time Machine','Science fiction novel/Allegory'); 

Insert into BOOKS values 
(101,'The Count of Monte Cristo','Historical, Adventure');

Insert into BOOKS values 
(102,'The Lord of the Rings','High fantasy, Adventure novel, Heroic romance, Action adventure');

Insert into BOOKS values 
(103,'Pride and Prejudice','Romantic comedy, Novel of manners');

Insert into BOOKS values 
(104,'The Adventures of Sherlock Holmes','Detective fiction short stories');

-- This is the LOGIN table from Bijoy for the JavaEE 

DROP TABLE LOGIN;

CREATE TABLE LOGIN(
  USERID NUMBER(15) NOT NULL,
  UNAME VARCHAR2(60) NOT NULL,
  PASSWORD VARCHAR2(12),
  FIRST_NAME VARCHAR2(30),
  LAST_NAME VARCHAR2(30)
);

insert into LOGIN values 
    (100
    , 'sking'
    , 'oracle'
    , 'Steven'
    , 'King'
    );


insert into LOGIN values 
    (110
    , 'jchen'
    , 'oracle'
    , 'John'
    , 'Chen'
    );


insert into LOGIN values 
    (106
    , 'vpatabal'
    , 'oracle'
    , 'Valli'
    , 'Pataballa'
    );


commit
;