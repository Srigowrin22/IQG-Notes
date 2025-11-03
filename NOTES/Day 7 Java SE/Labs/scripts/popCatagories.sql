rem
Rem $Header: cat_pop.sql 05-mar-2001.15:50:52 jgallus Exp $  
rem
rem Copyright (c) 2005 Oracle Corporation.  All rights reserved.
rem
rem Owner  : ahunold
rem
rem NAME
rem   cat_pop.sql - OC (Online Catalog) of OE Common Schema
rem
rem DESCRIPTON
rem   Populates CATORGIES tables
rem

prompt Adding Categories table

CREATE table Categories  
    ( category_name           VARCHAR2(50) 
    , category_description    VARCHAR2(1000) 
    , category_id             NUMBER(2) 
 ) 
/ 


INSERT INTO categories
  VALUES ('hardware1', 'monitors', 11) ;


INSERT INTO categories
  VALUES ('hardware2', 'printers', 12) ;


INSERT INTO categories
  VALUES ('hardware3', 'harddisks', 13) ;


INSERT INTO categories
  VALUES ('hardware4', 'memory components/upgrades', 14) ;


INSERT INTO categories
  VALUES ('software4', 'operating systems', 24) ;


INSERT INTO categories
  VALUES ('hardware5'
         , 'processors, sound and video cards, network cards, motherboards', 15) ;


INSERT INTO categories
  VALUES ('hardware6', 'keyboards, mouses, mouse pads', 16) ;


INSERT INTO categories
  VALUES ('hardware7'
         , 'other peripherals (CD-ROM, DVD, tape cartridge drives, ...)', 17) ;


INSERT INTO categories
  VALUES ('hardware8'
         , 'miscellaneous hardware (cables, screws, power supplies ...)', 19) ;

INSERT INTO categories
  VALUES ('software1', 'spreadsheet software', 21) ;


INSERT INTO categories
  VALUES ('software2', 'word processing software', 22) ;


INSERT INTO categories
  VALUES ('software3', 'database software', 23) ;


INSERT INTO categories
  VALUES ('software5', 'software development tools (including languages)', 25) ;


INSERT INTO categories
  VALUES ('software6', 'miscellaneous software', 29) ;


INSERT INTO categories
  VALUES ('office1', 'capitalizable assets (desks, chairs, phones ...)', 31) ;


INSERT INTO categories
  VALUES ('office2'
         , 'office supplies for daily use (pencils, erasers, staples, ...)', 32) ;


INSERT INTO categories
  VALUES ('office3', 'manuals, other books', 33) ;


INSERT INTO categories
  VALUES ('office4', 'miscellaneous office supplies', 39) ;


INSERT INTO categories
  VALUES ('hardware', 'computer hardware and peripherals', 10) ;


INSERT INTO categories
  VALUES ('software', 'computer software', 20) ;


INSERT INTO categories
  VALUES ('office equipment', 'office furniture and supplies', 30) ;


INSERT INTO categories
  VALUES ('online catalog'
         , 'catalog of computer hardware, software, and office equipment'
         , 90) ;

commit;


alter table categories
 add constraint categories_pk1 primary key (category_id);


commit;

 
alter table product_information
add constraint product_info_category_fk foreign key (category_id) references categories (category_id);

commit;



