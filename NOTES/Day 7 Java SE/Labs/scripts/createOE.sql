Rem
Rem    NAME
Rem	createOE.sql - clones Sample Schema objects
Rem
Rem    DESCRIPTION
Rem     Creates a common user oe with a username and password oe/oe. 
Rem     In classrooms, students each have a database on their individual machine. 
Rem     They connect to it using the string above.
REM	When it runs DBAs will be prompted for the SYSTEM password and Connect String
Rem
Rem
Rem	Created 15-Feb-2005 jgallus. Modified June 2009 kheap

SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 132
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 999
SET ECHO OFF


Prompt Connecting as SYSTEM to create oe
Prompt Ensure you enter values for the following parameters.
Connect system/&&your_SYSTEM_Password@&&your_Connect_String

spool cre_oe.log


DROP USER oe CASCADE;

CREATE USER oe IDENTIFIED BY oe
 DEFAULT TABLESPACE users
 TEMPORARY TABLESPACE temp
 QUOTA UNLIMITED ON users;

GRANT create session
    , create table
    , create procedure
    , create sequence
    , create trigger
    , create view
    , create synonym
    , alter session
    , create type
    , create materialized view
    , query rewrite
    , create dimension
    , create any directory
    , alter user
    , resumable
    , ALTER ANY TABLE  -- These
    , DROP ANY TABLE   -- five are
    , LOCK ANY TABLE   -- needed
    , CREATE ANY TABLE -- to use
    , SELECT ANY TABLE -- DBMS_REDEFINITION
TO oe;

GRANT select_catalog_role
    , execute_catalog_role
TO oe;


REM  connect to user account and invoke the scripts that create schema objects.
REM  the location of the demo scrip files in in the <ORACLE_DB_HOME>\Ora<90><10>\...

CONNECT oe/oe@&&your_Connect_String

@@hr_cre
@@hr_popul
@@hr_idx
@@hr_code
@@hr_comnt

@@oe_cre
@@oe_p_pi
@@oe_p_pd
@@oe_p_whs
@@oe_p_cus
@@oe_p_ord
@@oe_p_itm
@@oe_p_inv
@@oe_views
@@oe_idx

@@javaCurrMods.sql
@@popCatagories.sql

Prompt What OBJECTS were created?
column object_name format a30
column object_type format a30
select object_name, object_type from user_objects order by object_type;
Prompt Are there any INVALID OBJECTS?
select object_name from user_objects where status='INVALID';

spool off

