CREATE OR REPLACE PACKAGE PERSON_API AS

procedure insert_person(p_person_id number,
p_principal_name varchar2, p_title varchar2,
p_first_name  varchar2, p_last_name  varchar2,
p_person_type_code varchar2, p_email varchar2,
p_marital_status_code varchar2, p_gender varchar2,
p_contactable_flag varchar2,
p_contact_by_affilliates_flag varchar2,
p_created_by varchar2, p_creation_date date,
p_last_updated_by varchar2, p_last_update_date date,
p_object_version_id number);
procedure update_person( p_person_id number,
p_principal_name varchar2, p_title varchar2,
p_first_name  varchar2, p_last_name  varchar2,
p_person_type_code varchar2, p_email varchar2,
p_marital_status_code varchar2, p_gender varchar2,
p_contactable_flag varchar2,
p_contact_by_affilliates_flag varchar2,
p_last_updated_by varchar2, p_last_update_date date);
procedure delete_person(p_person_id number);

END PERSON_API;
/

CREATE OR REPLACE PACKAGE BODY PERSON_API AS

procedure insert_person(p_person_id number,
p_principal_name varchar2, p_title varchar2,
p_first_name  varchar2, p_last_name  varchar2,
p_person_type_code varchar2, p_email varchar2,
p_marital_status_code varchar2, p_gender varchar2,
p_contactable_flag varchar2,
p_contact_by_affilliates_flag varchar2,
p_created_by varchar2, p_creation_date date,
p_last_updated_by varchar2, p_last_update_date date,
p_object_version_id number) AS
  BEGIN
    Insert into persons_v(PERSON_ID, PRINCIPAL_NAME, TITLE,
    FIRST_NAME, LAST_NAME, PERSON_TYPE_CODE, EMAIL, MARITAL_STATUS_CODE,
    GENDER, CONTACTABLE_FLAG, CONTACT_BY_AFFILLIATES_FLAG, CREATED_BY,
    CREATION_DATE, LAST_UPDATED_BY, LAST_UPDATE_DATE, OBJECT_VERSION_ID)
    values(p_person_id, p_principal_name, p_title, p_first_name, p_last_name,
    p_person_type_code, p_email, p_marital_status_code, p_gender,
    p_contactable_flag, p_contact_by_affilliates_flag, p_created_by,
    p_creation_date, p_last_updated_by, p_last_update_date, p_object_version_id);
  END insert_person;

procedure update_person(p_person_id number,
p_principal_name varchar2, p_title varchar2,
p_first_name  varchar2, p_last_name  varchar2,
p_person_type_code varchar2, p_email varchar2,
p_marital_status_code varchar2, p_gender varchar2,
p_contactable_flag varchar2,
p_contact_by_affilliates_flag varchar2,
p_last_updated_by varchar2, p_last_update_date date) AS
  BEGIN
    Update persons_v set PRINCIPAL_NAME=p_principal_name,
    TITLE=p_title, FIRST_NAME=p_first_name, LAST_NAME=p_last_name,
    PERSON_TYPE_CODE=p_person_type_code, EMAIL= p_email,
    MARITAL_STATUS_CODE= p_marital_status_code, GENDER=p_gender,
    CONTACTABLE_FLAG=p_contactable_flag,
    CONTACT_BY_AFFILLIATES_FLAG=p_contact_by_affilliates_flag,
    LAST_UPDATED_BY=p_last_updated_by, LAST_UPDATE_DATE=p_last_update_date
    where PERSON_ID=p_person_id;
  END update_person;

  procedure delete_person(p_person_id number) AS
  BEGIN
  delete from persons_v where PERSON_ID = p_person_id;
  END delete_person;

END PERSON_API;
/

Alter Package PERSON_API COMPILE;
Alter Package PERSON_API COMPILE Body;
show error