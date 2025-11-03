prompt Adding status and discount columns...

alter table order_items add (
 discount_unit_price number(8,2)); 

update order_items set discount_unit_price = 
  (select list_price from product_information where 
  product_information.product_id = order_items.product_id);
 
alter table customers add ( 
 status varchar2(10)); 
 
update customers set status = 'Silver';

update customers set status = 'Gold' 
 where mod(customer_id, 4) = 0;

update customers set status = 'Platinum' 
 where mod(customer_id, 5) = 0;

commit;


prompt Adding orders trigger


create or replace trigger insert_ord_id
  BEFORE INSERT ON orders
  FOR EACH ROW
DECLARE
    new_id number;
  BEGIN
    SELECT orders_seq.NextVal 
	 INTO new_id from Dual;
    :new.order_id := new_id;
  END;
/

CREATE OR REPLACE TRIGGER insert_ord_line
  BEFORE INSERT ON order_items
  FOR EACH ROW 
  DECLARE 
    new_line number; 
  BEGIN 
    SELECT (NVL(MAX(line_item_id),0)+1) INTO new_line 
      FROM order_items
      WHERE order_id = :new.order_id; 
    :new.line_item_id := new_line; 
  END; 
/
