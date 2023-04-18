drop table "WAREHOUSE"."GOODS" cascade constraints;
drop table "WAREHOUSE"."INVOICE_GOOD" cascade constraints;
drop table "WAREHOUSE"."INVOICES" cascade constraints;
drop table "WAREHOUSE"."PARTNERS" cascade constraints;
drop table "WAREHOUSE"."REGISTERS" cascade constraints;
drop table "WAREHOUSE"."ROLES" cascade constraints;
drop table "WAREHOUSE"."TRANSACTIONS" cascade constraints;
drop table "WAREHOUSE"."USERS" cascade constraints;

drop sequence "WAREHOUSE"."GOOD_SEQ";
drop sequence "WAREHOUSE"."INVOICE_GOOD_SEQ";
drop sequence "WAREHOUSE"."INVOICE_SEQ";
drop sequence "WAREHOUSE"."PARTNER_SEQ";
drop sequence "WAREHOUSE"."USER_SEQ";