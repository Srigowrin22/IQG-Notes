1) Unzip this file to a directory of your choice; it will create a Scripts subdirectory
2) Modify the CONNECT statement in build.sql to use your DB SID
3) Start a command prompt and navigate to the Scripts subdirectory. 
3) Log in to SQL*Plus on your database as the system user; for example, system/manager@XE
4) Run build.sql
5) Log in to SQL*Plus as fod01/fusion@<yourSID>; for example, fod/fusion@XE

NOTE: This script creates only a single user named fod01. If you want to create more users, just modify build.sql to change the line: DEFINE DEMO_SCHEMA = FOD01 
to, for example: DEFINE DEMO_SCHEMA = FOD02
and then run the script again for each user you want to create.


This version of the DB Scripts was created on 7-30-2008 11:40 am
By: Gary Williams

It includes the addition of the OBJECT_VERSION_ID column to the PRODUCTS and CATEGORIES tables.



FOR THE UI Training for Apps only (just leaving this in the readme in case we need to do something similar later -- otherwise just ignore -- but if this is ever needed again, need to uncomment the line in build.sql that creates the UI_Training_User_Setup package)

6) Determine the string that represents your system user (the userid for logging in to your computer), 
   and pass it as an argument in the following command: 
     execute UI_Training_User_Setup.Setup_User(<system user>);
   for example:
     execute UI_Training_User_Setup.Setup_User('PGAMER');
7) Issue a commit statement:
     commit;
8) Exit SQL*Plus:
     exit