SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 132
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 999
SET ECHO OFF
SET CONCAT '.'
COLUMN object_name     FORMAT A25
COLUMN subobject_name  FORMAT A25
COLUMN owner           FORMAT A10

CONNECT system/&&password_system

SPOOL verify_accounts.log

SELECT  owner, object_type, status, count(*)
FROM dba_objects
WHERE owner like 'ORA%' 
AND object_name LIKE 'SYS%'
GROUP BY owner, object_type, status;

SELECT          owner, table_name, num_rows
 FROM           dba_tables
 WHERE owner like 'ORA%' 
 ORDER BY       2,1,3;

@verify_intermedia

SPOOL OFF
