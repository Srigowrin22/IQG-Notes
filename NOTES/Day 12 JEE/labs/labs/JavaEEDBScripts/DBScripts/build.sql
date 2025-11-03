REM Main build Script for the Fusion Order Demo Schema, Objects and demo data 
REM To be run from SQL*Plus conected as a DBA
REM Note you can also run this SQL install from ANT

REM Schema Owner FOD is parameterized to allow for simpler workshop / 
REM classroom setup where only one shared server is available
REM $Id: build.sql 888 2007-03-01 16:27:31Z drmills $

SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 132
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 999
SET ECHO OFF
SEt VERIFY OFF


SPOOL FOD_Install.log

REM Define the changable data
DEFINE DEMO_SCHEMA = FOD
DEFINE DEMO_SCHEMA_PASSWORD = FUSION
DEFINE OVERRIDE_EMAIL_ADDRESS = gary.williams@oracle.com
DEFINE SMTP_SERVER = stmail.oracle.com

REM Create the schema owner - this can fail if the 
REM Script has already been run, but that's OK as the first 
REM Step of the createSchemaObjects is to drop the existing objects just in case

@@createFODSchema &&DEMO_SCHEMA &&DEMO_SCHEMA_PASSWORD

REM  connect to user account and invoke the scripts that create schema objects.

CONNECT &&DEMO_SCHEMA/&&DEMO_SCHEMA_PASSWORD@XE

@@createFODSchemaObjects

REM Add the triggers needed 
@@createSequenceTriggers

REM Add the sample data to the tables

@@populateFODSchemaTables &&OVERRIDE_EMAIL_ADDRESS &&SMTP_SERVER

-- HERE IS GARY's STUFF

@@createFODJavaEESchemaObjects


REM And Commit all that

commit;

REM Report Back 
Prompt What OBJECTS were created?
column object_name format a30
column object_type format a30

select   object_type,
         object_name 
from     user_objects 
group by object_type, object_name
order by object_type desc, object_name
/

Prompt ------------------------------------------------------
Prompt 
Prompt Are there any INVALID OBJECTS?

select 	 object_type,
         object_name 
from     user_objects 
where    status='INVALID'
group by object_type, object_name
order by object_type desc, object_name
/


REM Add package for setting up user for UI Training
REM @@UI_Training_User_Setup

Prompt Disconnecting....
disconnect

spool off
