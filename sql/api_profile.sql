--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.11
-- Dumped by pg_dump version 9.5.11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- Data for Name: api_profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO api_profile (id, login, password, authtoken, name, phone, mail, islastposition, ishistory, isspeedmax, iscourse, iscarburantp, iscarburants, isnotifcons, isnotifconf, iszoneone, iszonetwo, istempm, istempf, isdoor, isdriver, isparameters, isstartstop, ismyaccount, iscontactus, isactive) VALUES (1, 'demo', 'demo', 'usG8ghA3q9TPBlouKz8MUk4SxH70yCGMafqacby+RLo=', 'toto ', '0033617638348', 'contact@kriauto.ma', true, true, true, true, true, false, true, true, true, true, false, false, false, false, true, true, true, true, true);


--
-- PostgreSQL database dump complete
--

