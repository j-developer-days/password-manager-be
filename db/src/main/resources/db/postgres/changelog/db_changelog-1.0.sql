--liquibase formatted sql

--changeset jdev_root:1 runInTransaction:false runAlways:true
--comment: drop database, tablespace and role
DROP DATABASE IF EXISTS password_manager_db;
DROP TABLESPACE IF EXISTS password_manager_ts;
DROP ROLE IF EXISTS pm_db_admin;

--changeset jdev_root:2 runInTransaction:false runAlways:true
--comment: create database, tablespace and role
CREATE ROLE pm_db_admin WITH SUPERUSER CONNECTION limit 50 LOGIN PASSWORD '753951$^0Ghf' VALID UNTIL '2024-06-01' ;
CREATE TABLESPACE password_manager_ts OWNER pm_db_admin LOCATION '/home/java_dev/postgesql_table_spaces/password_manager_ts';
CREATE DATABASE password_manager_db WITH OWNER pm_db_admin TABLESPACE password_manager_ts;