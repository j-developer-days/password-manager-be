--liquibase formatted sql

--changeset jdev:1
--comment: create new table t_group_accounts
--rollback DROP TABLE IF EXISTS t_group_accounts CASCADE;
CREATE TABLE IF NOT EXISTS t_group_accounts(
	id SMALLSERIAL NOT NULL,
	group_name VARCHAR(100) NOT NULL,

	CONSTRAINT t_group_accounts__id__pk PRIMARY KEY(id)
) TABLESPACE password_manager_ts;

--changeset jdev:2
--comment: create new table t_accounts
--rollback DROP TABLE IF EXISTS t_accounts CASCADE;
CREATE TABLE IF NOT EXISTS t_accounts(
	id SERIAL NOT NULL,
	account_name VARCHAR(100) NOT NULL,
	account_url VARCHAR(1000) NOT NULL,
	account_password VARCHAR(4000) NOT NULL,
	creation_datetime TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	group_id SMALLINT NOT NULL,

	CONSTRAINT t_accounts__id__pk PRIMARY KEY(id),
	CONSTRAINT t_accounts__group_id__fk FOREIGN KEY(group_id) REFERENCES t_group_accounts(id)
) TABLESPACE password_manager_ts;