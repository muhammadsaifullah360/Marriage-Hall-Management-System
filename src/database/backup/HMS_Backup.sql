;
DROP ALL OBJECTS;
CREATE USER IF NOT EXISTS "" SALT '' HASH '' ADMIN;           
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_6D069E33_2D7A_4CE7_B0C7_787272488096" START WITH 2 BELONGS_TO_TABLE;
CREATE CACHED TABLE "PUBLIC"."EMPLOYEE_EDUCATION_DETAIL"(
    "EMPLOYEE_ID" INT,
    "CERTIFICATE_TITLE" VARCHAR2(256) NOT NULL,
    "ORGANIZATION_NAME" VARCHAR2(255) NOT NULL,
    "VERIFICATION_NO" VARCHAR2(36) NOT NULL,
    "ISSUE_DATE" DATE NOT NULL,
    "DESCRIPTION" VARCHAR2(1000) NOT NULL,
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);            
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.EMPLOYEE_EDUCATION_DETAIL;               
INSERT INTO "PUBLIC"."EMPLOYEE_EDUCATION_DETAIL" VALUES
(1, 'CEHv10', 'EC-Council', 'EBCD-124', DATE '2020-12-27', 'This is 2020', DATE '2020-12-27');       
CREATE CACHED TABLE "PUBLIC"."EMPLOYEE_SKILL_DETAIL"(
    "EMPLOYEE_ID" INT,
    "SKILL_TITLE" VARCHAR2(255) NOT NULL,
    "FIELD_TITLE" VARCHAR2(255) NOT NULL,
    "DESCRIPTION" VARCHAR2(1000) NOT NULL,
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);           
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.EMPLOYEE_SKILL_DETAIL;   
INSERT INTO "PUBLIC"."EMPLOYEE_SKILL_DETAIL" VALUES
(1, 'Network Security Expert', 'Network Security', 'Expert in Penetration Testing', DATE '2020-12-27');  
CREATE CACHED TABLE "PUBLIC"."EMPLOYEE_EXPERIENCE_DETAIL"(
    "EMPLOYEE_ID" INT,
    "JOB_TITLE" VARCHAR2(256) NOT NULL,
    "ORGANIZATION_NAME" VARCHAR2(255) NOT NULL,
    "FIELD_TITLE" VARCHAR2(255) NOT NULL,
    "DURATION" FLOAT NOT NULL,
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);   
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.EMPLOYEE_EXPERIENCE_DETAIL;              
INSERT INTO "PUBLIC"."EMPLOYEE_EXPERIENCE_DETAIL" VALUES
(1, 'Software Developer', 'Comsats', 'Software Engineering', 5.0, DATE '2020-12-27');               
CREATE CACHED TABLE "PUBLIC"."TEAM"(
    "ID" INT NOT NULL,
    "TASK_TYPE" VARCHAR2(256)
);               
ALTER TABLE "PUBLIC"."TEAM" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");         
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TEAM;    
CREATE CACHED TABLE "PUBLIC"."EMPLOYEE_DUTY_DETAIL"(
    "EMPLOYEE_ID" INT NOT NULL,
    "TEAM_ID" INT,
    "JOB_TITLE" VARCHAR2(256) NOT NULL,
    "SHIFT" VARCHAR2(36) NOT NULL,
    "WORKING_HOURS" INT NOT NULL,
    "SALARY" INT NOT NULL,
    "JOIN_DATE" DATE DEFAULT CURRENT_TIMESTAMP,
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);    
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.EMPLOYEE_DUTY_DETAIL;    
INSERT INTO "PUBLIC"."EMPLOYEE_DUTY_DETAIL" VALUES
(1, NULL, 'Not Defined', 'Night', 15, 100000, DATE '2020-12-27', DATE '2020-12-27');      
CREATE CACHED TABLE "PUBLIC"."EMPLOYEE"(
    "ID" INT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_6D069E33_2D7A_4CE7_B0C7_787272488096" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_6D069E33_2D7A_4CE7_B0C7_787272488096",
    "NAME" VARCHAR2(36) NOT NULL,
    "PHONE_NO" VARCHAR2(12) NOT NULL,
    "EMAIL" VARCHAR2(256),
    "ADDRESS" VARCHAR2(256) NOT NULL,
    "DOB" DATE,
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);            
ALTER TABLE "PUBLIC"."EMPLOYEE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");     
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.EMPLOYEE;
INSERT INTO "PUBLIC"."EMPLOYEE" VALUES
(1, 'Saif Ali', '0306-5292097', 'chsaifali097@gmail.com', 'Chak 97/F', DATE '2020-01-16', DATE '2020-12-27');         
CREATE CACHED TABLE "PUBLIC"."CUSTOMER"(
    "ID" INT NOT NULL,
    "NAME" VARCHAR2(256) NOT NULL,
    "PHONE_NUMBER" VARCHAR2(12) NOT NULL,
    "EMAIL" VARCHAR2(256),
    "ADDRESS" VARCHAR2(256) NOT NULL,
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);        
ALTER TABLE "PUBLIC"."CUSTOMER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_52" PRIMARY KEY("ID");    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CUSTOMER;
CREATE CACHED TABLE "PUBLIC"."BOOKING"(
    "ID" INT NOT NULL,
    "CUSTOMER_ID" INT NOT NULL,
    "TEAM_ID" INT NOT NULL,
    "TYPE" VARCHAR2(256) NOT NULL,
    "BOOKING_DATE" DATE NOT NULL,
    "START_TIME" DATE DEFAULT CURRENT_TIMESTAMP,
    "DURATION" INT NOT NULL,
    "PERSON_COUNT" INT NOT NULL,
    "LOCATION" VARCHAR2(256),
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);     
ALTER TABLE "PUBLIC"."BOOKING" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2E" PRIMARY KEY("ID");     
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.BOOKING; 
CREATE CACHED TABLE "PUBLIC"."MENU"(
    "BOOKING_ID" INT NOT NULL,
    "MENU_SERVICE" ENUM('Self', 'Hall'),
    "DECORATION" ENUM('Simple', 'Fancy', 'Custom'),
    "FACILITY" ENUM('AC', 'Heater', 'Photographer'),
    "DESCRIPTION" VARCHAR2(1000)
);               
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MENU;    
CREATE CACHED TABLE "PUBLIC"."BILLING"(
    "BOOKING_ID" INT NOT NULL,
    "TOTAL_AMOUNT" INT NOT NULL,
    "ADVANCE_PAYMENT" INT NOT NULL,
    "PER_HEAD_CHARGES" INT NOT NULL,
    "INVOICE_NO" VARCHAR2(36) NOT NULL,
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);             
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.BILLING; 
CREATE CACHED TABLE "PUBLIC"."DISH"(
    "ID" INT NOT NULL,
    "NAME" VARCHAR2(256),
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);   
ALTER TABLE "PUBLIC"."DISH" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_20" PRIMARY KEY("ID");        
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.DISH;    
CREATE CACHED TABLE "PUBLIC"."BOOKING_DISH"(
    "BOOKING_ID" INT NOT NULL,
    "DISH_ID" INT NOT NULL
);  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.BOOKING_DISH;            
CREATE CACHED TABLE "PUBLIC"."SUPPLIER"(
    "ID" INT NOT NULL,
    "NAME" VARCHAR2(256) NOT NULL,
    "PHONE_NUMBER" VARCHAR2(12) NOT NULL,
    "EMAIL" VARCHAR2(256),
    "ADDRESS" VARCHAR2(256) NOT NULL,
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);        
ALTER TABLE "PUBLIC"."SUPPLIER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_C" PRIMARY KEY("ID");     
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.SUPPLIER;
CREATE CACHED TABLE "PUBLIC"."MATERIAL"(
    "ID" INT NOT NULL,
    "MATERIAL_NAME" VARCHAR2(256) NOT NULL,
    "QUANTITY_AVAILABLE" INT NOT NULL,
    "DESCRIPTION" VARCHAR2(1000),
    "DATE_TIME" DATE DEFAULT CURRENT_TIMESTAMP
);  
ALTER TABLE "PUBLIC"."MATERIAL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");     
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MATERIAL;
CREATE CACHED TABLE "PUBLIC"."MATERIAL_PURCHASE"(
    "SUPPLIER_ID" INT NOT NULL,
    "MATERIAL_ID" INT NOT NULL,
    "QUANTITY" INT NOT NULL,
    "UNIT_PRICE" INT NOT NULL,
    "INVOICE_NUMBER" VARCHAR2(36) NOT NULL,
    "DATE_TIME" DATE NOT NULL
);             
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MATERIAL_PURCHASE;       
CREATE FORCE VIEW "PUBLIC"."EMPLOYEE_VIEW"("ID", "NAME", "PHONE_NO", "EMAIL", "ADDRESS", "DOB", "CERTIFICATE_TITLE", "ORGANIZATION_NAME_EDU", "VERIFICATION_NO", "ISSUE_DATE", "DESCRIPTION_EDU", "SKILL_TITLE", "FIELD_TITLE_SKILL", "SKILL_DATE", "DESCRIPTION_SKILL", "JOB_TITLE_EXP", "ORGANIZATION_NAME_EXP", "FIELD_TITLE_EXP", "DURATION", "JOB_TITLE_DUTY", "TEAM_ID", "SHIFT", "WORKING_HOURS", "SALARY", "JOIN_DATE") AS
SELECT
    "ID",
    "NAME",
    "PHONE_NO",
    "EMAIL",
    "ADDRESS",
    "DOB",
    "CERTIFICATE_TITLE",
    "EDU"."ORGANIZATION_NAME" AS "ORGANIZATION_NAME_EDU",
    "VERIFICATION_NO",
    "ISSUE_DATE",
    "EDU"."DESCRIPTION" AS "DESCRIPTION_EDU",
    "SKILL_TITLE",
    "SKILL"."FIELD_TITLE" AS "FIELD_TITLE_SKILL",
    "SKILL"."DATE_TIME" AS "SKILL_DATE",
    "SKILL"."DESCRIPTION" AS "DESCRIPTION_SKILL",
    "EXP"."JOB_TITLE" AS "JOB_TITLE_EXP",
    "EXP"."ORGANIZATION_NAME" AS "ORGANIZATION_NAME_EXP",
    "EXP"."FIELD_TITLE" AS "FIELD_TITLE_EXP",
    "DURATION",
    "DUTY"."JOB_TITLE" AS "JOB_TITLE_DUTY",
    "TEAM_ID",
    "SHIFT",
    "WORKING_HOURS",
    "SALARY",
    "JOIN_DATE"
FROM "PUBLIC"."EMPLOYEE" "E"
INNER JOIN "PUBLIC"."EMPLOYEE_EDUCATION_DETAIL" "EDU"
    ON 1=1
INNER JOIN "PUBLIC"."EMPLOYEE_SKILL_DETAIL" "SKILL"
    ON 1=1
INNER JOIN "PUBLIC"."EMPLOYEE_EXPERIENCE_DETAIL" "EXP"
    ON 1=1
INNER JOIN "PUBLIC"."EMPLOYEE_DUTY_DETAIL" "DUTY"
    ON 1=1
WHERE ("EXP"."EMPLOYEE_ID" = "DUTY"."EMPLOYEE_ID")
    AND (("SKILL"."EMPLOYEE_ID" = "EXP"."EMPLOYEE_ID")
    AND (("EDU"."EMPLOYEE_ID" = "SKILL"."EMPLOYEE_ID")
    AND ("E"."ID" = "EDU"."EMPLOYEE_ID"))); 
ALTER TABLE "PUBLIC"."EMPLOYEE_EDUCATION_DETAIL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_5" FOREIGN KEY("EMPLOYEE_ID") REFERENCES "PUBLIC"."EMPLOYEE"("ID") ON DELETE CASCADE NOCHECK;            
ALTER TABLE "PUBLIC"."MATERIAL_PURCHASE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6" FOREIGN KEY("SUPPLIER_ID") REFERENCES "PUBLIC"."SUPPLIER"("ID") NOCHECK;      
ALTER TABLE "PUBLIC"."EMPLOYEE_SKILL_DETAIL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_58" FOREIGN KEY("EMPLOYEE_ID") REFERENCES "PUBLIC"."EMPLOYEE"("ID") ON DELETE CASCADE NOCHECK;               
ALTER TABLE "PUBLIC"."MENU" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_24" FOREIGN KEY("BOOKING_ID") REFERENCES "PUBLIC"."BOOKING"("ID") NOCHECK;    
ALTER TABLE "PUBLIC"."EMPLOYEE_EXPERIENCE_DETAIL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" FOREIGN KEY("EMPLOYEE_ID") REFERENCES "PUBLIC"."EMPLOYEE"("ID") ON DELETE CASCADE NOCHECK;           
ALTER TABLE "PUBLIC"."BILLING" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_243" FOREIGN KEY("BOOKING_ID") REFERENCES "PUBLIC"."BOOKING"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."BOOKING" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2EA" FOREIGN KEY("CUSTOMER_ID") REFERENCES "PUBLIC"."CUSTOMER"("ID") NOCHECK;              
ALTER TABLE "PUBLIC"."BOOKING" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2EA6" FOREIGN KEY("TEAM_ID") REFERENCES "PUBLIC"."TEAM"("ID") NOCHECK;     
ALTER TABLE "PUBLIC"."BOOKING_DISH" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_FC" FOREIGN KEY("BOOKING_ID") REFERENCES "PUBLIC"."BOOKING"("ID") NOCHECK;            
ALTER TABLE "PUBLIC"."BOOKING_DISH" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_FCF" FOREIGN KEY("DISH_ID") REFERENCES "PUBLIC"."DISH"("ID") NOCHECK; 
ALTER TABLE "PUBLIC"."MATERIAL_PURCHASE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6D" FOREIGN KEY("MATERIAL_ID") REFERENCES "PUBLIC"."MATERIAL"("ID") NOCHECK;     
ALTER TABLE "PUBLIC"."EMPLOYEE_DUTY_DETAIL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F7" FOREIGN KEY("TEAM_ID") REFERENCES "PUBLIC"."TEAM"("ID") NOCHECK;          
ALTER TABLE "PUBLIC"."EMPLOYEE_DUTY_DETAIL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" FOREIGN KEY("EMPLOYEE_ID") REFERENCES "PUBLIC"."EMPLOYEE"("ID") ON DELETE CASCADE NOCHECK; 
