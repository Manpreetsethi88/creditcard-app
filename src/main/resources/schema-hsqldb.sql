CREATE TABLE CREDIT_CARD_DETAILS (
	CREDIT_CARD_NO bigint not null,
	FIRST_NAME varchar(50) not null,
	LAST_NAME varchar(50) not null,
	CREDIT_LIMIT bigint not null,
	primary key(CREDIT_CARD_NO)
);