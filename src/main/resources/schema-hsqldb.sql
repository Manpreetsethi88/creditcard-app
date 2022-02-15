CREATE TABLE CREDIT_CARD_DETAILS (
	CREDIT_CARD_NO varchar(500) not null,
	FIRST_NAME varchar(50) not null,
	LAST_NAME varchar(50),
	CREDIT_LIMIT numeric(50,2) not null,
	primary key(CREDIT_CARD_NO)
);