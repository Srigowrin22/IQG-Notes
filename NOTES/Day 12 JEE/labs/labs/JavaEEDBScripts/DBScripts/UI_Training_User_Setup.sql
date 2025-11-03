SET VERIFY OFF
WHENEVER OSERROR EXIT FAILURE ROLLBACK;
WHENEVER SQLERROR EXIT FAILURE ROLLBACK;
CREATE OR REPLACE PACKAGE UI_Training_User_Setup AS

PROCEDURE Setup_User(Username IN VARCHAR2);

FUNCTION Create_User(Username IN VARCHAR2) RETURN NUMBER;

FUNCTION Create_Address(Username IN VARCHAR2) RETURN NUMBER;

FUNCTION Create_Payment_Option(CustomerId IN NUMBER, AddressId IN NUMBER) RETURN NUMBER;

FUNCTION Create_Order(CustomerId IN NUMBER, PaymentOptionId IN NUMBER, AddressId IN NUMBER) RETURN NUMBER;

PROCEDURE Delete_User(Username IN VARCHAR2);

END;
/

CREATE OR REPLACE PACKAGE BODY UI_Training_User_Setup AS

PROCEDURE Setup_User(Username IN VARCHAR2)
IS

l_person_id NUMBER;
l_billing_address_id NUMBER;
l_shipping_address_id NUMBER;
l_payment_option_id NUMBER;
l_order_id NUMBER;
l_user_name VARCHAR2(30) := upper(Username);

CURSOR CHECK_USER_EXISTS IS
SELECT PERSON_ID FROM PERSONS
WHERE PRINCIPAL_NAME = l_user_name;

CURSOR CHECK_ADDRESSES IS
SELECT ADDRESS_ID FROM ADDRESSES
WHERE CREATED_BY = l_user_name;

CURSOR CHECK_PAYMENT_OPTIONS IS
SELECT PAYMENT_OPTION_ID FROM PAYMENT_OPTIONS
WHERE CUSTOMER_ID = l_person_id;

CURSOR CHECK_ORDERS IS
SELECT ORDER_ID FROM ORDERS
WHERE CUSTOMER_ID = l_person_id
AND PAYMENT_OPTION_ID = l_payment_option_id;

BEGIN

dbms_output.put_line('Begin call to UI_Training_User_Setup.Setup_User(''' || l_user_name || ''')');

l_person_id := NULL;
OPEN CHECK_USER_EXISTS;
FETCH CHECK_USER_EXISTS INTO l_person_id;
CLOSE CHECK_USER_EXISTS;

dbms_output.put_line('If user exists, print here: (''' || l_person_id || ''')');

IF (l_person_id IS NULL) THEN
    dbms_output.put_line('User ' || l_user_name || ' does not exist as a valid customer - creating new entry for you');
	l_person_id := Create_User(l_user_name);
	dbms_output.put_line('New entry created for you in PERSONS table - your person_id is ' || l_person_id);
ELSE
	dbms_output.put_line('Entry already exists for you in PERSONS table - your person_id is ' || l_person_id);
END IF;

IF (l_person_id IS NOT NULL) THEN
	OPEN CHECK_ADDRESSES;
	FETCH CHECK_ADDRESSES INTO l_billing_address_id;
	FETCH CHECK_ADDRESSES INTO l_shipping_address_id;
	CLOSE CHECK_ADDRESSES;
END IF;

IF (l_billing_address_id IS NULL) THEN
	dbms_output.put_line('Billing Address does not exist for customer ' || l_user_name || ' - creating new entry for you');
	l_billing_address_id := Create_Address(l_user_name);
	dbms_output.put_line('New entry created for you in ADDRESSES table - your billing address_id is ' || l_billing_address_id);
ELSE
	dbms_output.put_line('Entry already exists for you in ADDRESSES table - your billing address_id is ' || l_billing_address_id);
END IF;

IF (l_shipping_address_id IS NULL) THEN
	dbms_output.put_line('Shipping Address does not exist for customer ' || l_user_name || ' - creating new entry for you');
	l_shipping_address_id := Create_Address(l_user_name);
	dbms_output.put_line('New entry created for you in ADDRESSES table - your shipping address_id is ' || l_shipping_address_id);
ELSE
	dbms_output.put_line('Entry already exists for you in ADDRESSES table - your shipping address_id is ' || l_shipping_address_id);	
END IF;


IF (l_person_id IS NOT NULL) THEN
	OPEN CHECK_PAYMENT_OPTIONS;
	FETCH CHECK_PAYMENT_OPTIONS INTO l_payment_option_id;
	CLOSE CHECK_PAYMENT_OPTIONS;
END IF;

IF (l_payment_option_id IS NULL) THEN
	dbms_output.put_line('Payment Option does not exist for customer ' || l_user_name || ' - creating new entry for you');
	l_payment_option_id := Create_Payment_Option(l_person_id,l_billing_address_id);
	dbms_output.put_line('New entries created for you in PAYMENT_OPTIONS table - one of your payment_option_id is ' || l_payment_option_id);
ELSE
	dbms_output.put_line('Entry already exists for you in PAYMENT_OPTIONS table - one of your payment_option_id is ' || l_payment_option_id);	
END IF;

IF (l_payment_option_id IS NOT NULL) THEN
	OPEN CHECK_ORDERS;
	FETCH CHECK_ORDERS INTO l_order_id;
	CLOSE CHECK_ORDERS;
END IF;

IF (l_order_id IS NULL) THEN
	dbms_output.put_line('Order does not exist for customer ' || l_user_name || ' - creating new entry for you');
	l_order_id := Create_Order(l_person_id,l_payment_option_id,l_shipping_address_id);
	dbms_output.put_line('New entry created for you in ORDERS table - your order_id is ' || l_order_id);
ELSE
	dbms_output.put_line('Entry already exists for you in ORDERS table - your order_id is ' || l_order_id);
END IF;

--dbms_output.put_line('End call to UI_Training_User_Setup.Setup_User(''' || l_user_name || ''')');

END Setup_User;

FUNCTION Create_User(Username IN VARCHAR2) RETURN NUMBER
IS

l_person_id NUMBER;

BEGIN

SELECT PERSON_SEQ.NEXTVAL into l_person_id FROM DUAL;

insert into persons values (
l_person_id, Username, null, substr(Username,1,1), substr(Username,2), 'CUST', null, 'N', 2, null, null, Username || '@oracle.com', Username || '@oracle.com', '650.506.0000', null, null, to_date('16-AUG-64'), 'MARR', 'M', null, null, null, 'Y', 'Y', 'SEED_DATA', sysdate, 'SEED_DATA', sysdate, 1
);

return l_person_id;

END Create_User;

FUNCTION Create_Address(Username IN VARCHAR2) RETURN NUMBER
IS

l_address_id NUMBER;

BEGIN

SELECT ADDRESS_SEQ.NEXTVAL INTO l_address_id FROM DUAL;
insert into addresses values(l_address_id,'500 Oracle Parkway',null,'Redwood City','94065','CA','US',null,null,Username,sysdate,'0',sysdate,0);

return l_address_id;

END Create_Address;

FUNCTION Create_Payment_Option(CustomerId IN NUMBER, AddressId IN NUMBER) RETURN NUMBER
IS

l_pmt_option_id NUMBER;
l_pmt_option_id2 NUMBER;

BEGIN

SELECT PAYMENT_OPTION_SEQ.NEXTVAL INTO l_pmt_option_id FROM DUAL;
insert into payment_options values(
l_pmt_option_id, CustomerId,  'CC', AddressId, '444400413001128', 'VISA', add_months(sysdate,12), '333', null, null, sysdate, add_months(sysdate,36), 0, sysdate, 0, sysdate,	1);

SELECT PAYMENT_OPTION_SEQ.NEXTVAL INTO l_pmt_option_id2 FROM DUAL;
insert into payment_options values(
l_pmt_option_id2, CustomerId,  'IN', AddressId, '95317932161', null, null, null, null, null, sysdate, null, 0, sysdate, 0, sysdate, 1);

return l_pmt_option_id;

END Create_Payment_Option;

FUNCTION Create_Order(CustomerId IN NUMBER, PaymentOptionId IN NUMBER, AddressId IN NUMBER) RETURN NUMBER
IS

l_order_id NUMBER;
l_line_item_id NUMBER;

BEGIN


SELECT ORDER_SEQ.NEXTVAL INTO l_order_id FROM DUAL;
insert into orders values(l_order_id, sysdate, null, 'CART', 53.97, CustomerId, null, AddressId, null, 1, PaymentOptionId, null, null, 'Y', 'N', null, 'N', null, 0, sysdate, 0, sysdate, 1);

dbms_output.put_line('In Create_Order - creating line items for order');

SELECT ORDER_ITEMS_SEQ.NEXTVAL INTO l_line_item_id FROM DUAL;
insert into order_items values(l_order_id, l_line_item_id, 47, 1, 16.99, CustomerId, sysdate, CustomerId, sysdate, 1);
SELECT ORDER_ITEMS_SEQ.NEXTVAL INTO l_line_item_id FROM DUAL;
insert into order_items values(l_order_id, l_line_item_id, 40, 1, 24.99, CustomerId, sysdate, CustomerId, sysdate, 1);
SELECT ORDER_ITEMS_SEQ.NEXTVAL INTO l_line_item_id FROM DUAL;
insert into order_items values(l_order_id, l_line_item_id, 3, 1, 299.99, CustomerId, sysdate, CustomerId, sysdate, 1);
SELECT ORDER_ITEMS_SEQ.NEXTVAL INTO l_line_item_id FROM DUAL;
insert into order_items values(l_order_id, l_line_item_id, 39, 1, 35.99, CustomerId, sysdate, CustomerId, sysdate, 1);

--dbms_output.put_line('In Create_Order - after creating line items for order');

return l_order_id;

END Create_Order;

PROCEDURE Delete_User(Username IN VARCHAR2)
IS

l_person_id NUMBER;
l_address_id NUMBER;
l_payment_option_id NUMBER;
l_order_id NUMBER;
l_user_name VARCHAR2(30) := upper(Username);

CURSOR CHECK_USER_EXISTS IS
SELECT PERSON_ID FROM PERSONS
WHERE PRINCIPAL_NAME = l_user_name;

BEGIN

dbms_output.put_line('Begin call to UI_Training_User_Setup.Delete_User(''' || l_user_name || ''')');

OPEN CHECK_USER_EXISTS;
FETCH CHECK_USER_EXISTS INTO l_person_id;
CLOSE CHECK_USER_EXISTS;


IF (l_person_id IS NULL) THEN
	dbms_output.put_line('User ' || l_user_name || ' does not exist as a valid customer');
ELSE
	dbms_output.put_line('Begin Deletion of User ' || l_user_name || ' and related records');
	--update PERSONS set principal_name = to_char(person_id) || '_DELETED', last_update_date = sysdate where principal_name = l_user_name;
	dbms_output.put_line('Deleting order items...');
	delete from order_items where order_id IN (select order_id from orders where customer_id = l_person_id);
	dbms_output.put_line('Deleting order...');
	delete from orders where customer_id = l_person_id;
	dbms_output.put_line('Deleting payment options...');
	delete from payment_options where CUSTOMER_ID = l_person_id;
	dbms_output.put_line('Deleting addresses...');
	delete from addresses where CREATED_BY = l_user_name;
	dbms_output.put_line('Deleting person...');
	delete from persons where person_id = l_person_id;
	dbms_output.put_line('End Deletion of User ' || l_user_name || ' and related records');
END IF;

dbms_output.put_line('End call to UI_Training_User_Setup.Delete_User(''' || l_user_name || ''')');

END Delete_User;

END;
/
show err;
commit;
exit;


