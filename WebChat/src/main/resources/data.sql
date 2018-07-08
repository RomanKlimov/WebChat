--
-- PostgreSQL database dump
--

-- Dumped from database version 10.2
-- Dumped by pg_dump version 10.2

-- Started on 2018-07-08 18:10:39
CREATE DATABASE "webchat";

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 70503)
-- Name: dialogs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE dialogs (
    id bigint NOT NULL
);


ALTER TABLE dialogs OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 70501)
-- Name: dialogs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE dialogs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dialogs_id_seq OWNER TO postgres;

--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 196
-- Name: dialogs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE dialogs_id_seq OWNED BY dialogs.id;


--
-- TOC entry 199 (class 1259 OID 70511)
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE messages (
    id bigint NOT NULL,
    creation_date character varying(255),
    value character varying(255) NOT NULL,
    dialog_id bigint,
    user_id bigint
);


ALTER TABLE messages OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 70509)
-- Name: messages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE messages_id_seq OWNER TO postgres;

--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 198
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE messages_id_seq OWNED BY messages.id;


--
-- TOC entry 200 (class 1259 OID 70520)
-- Name: user_dialog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_dialog (
    dialog_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE user_dialog OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 70525)
-- Name: user_registration_form; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_registration_form (
    id bigint NOT NULL,
    last_name character varying(255),
    login character varying(255),
    name character varying(255),
    password character varying(255)
);


ALTER TABLE user_registration_form OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 70523)
-- Name: user_registration_form_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_registration_form_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_registration_form_id_seq OWNER TO postgres;

--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 201
-- Name: user_registration_form_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_registration_form_id_seq OWNED BY user_registration_form.id;


--
-- TOC entry 204 (class 1259 OID 70536)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id bigint NOT NULL,
    hash_password character varying(255),
    last_name character varying(255),
    login character varying(255) NOT NULL,
    name character varying(255),
    role character varying(255)
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 70534)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 203
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 2695 (class 2604 OID 70506)
-- Name: dialogs id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dialogs ALTER COLUMN id SET DEFAULT nextval('dialogs_id_seq'::regclass);


--
-- TOC entry 2696 (class 2604 OID 70514)
-- Name: messages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY messages ALTER COLUMN id SET DEFAULT nextval('messages_id_seq'::regclass);


--
-- TOC entry 2697 (class 2604 OID 70528)
-- Name: user_registration_form id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_registration_form ALTER COLUMN id SET DEFAULT nextval('user_registration_form_id_seq'::regclass);


--
-- TOC entry 2698 (class 2604 OID 70539)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 2835 (class 0 OID 70503)
-- Dependencies: 197
-- Data for Name: dialogs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY dialogs (id) FROM stdin;
1
2
3
4
\.


--
-- TOC entry 2837 (class 0 OID 70511)
-- Dependencies: 199
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY messages (id, creation_date, value, dialog_id, user_id) FROM stdin;
1	2018.07.08	Hey!	1	1
2	2018.07.08	Hii	1	2
3	2018.07.08	How it's going??	1	1
4	2018.07.08	good!	1	2
5	2018.07.08	Hello Misha!	2	1
6	2018.07.08	heeeey	2	3
7	2018.07.08	Oleeeg	3	1
8	2018.07.08	Where r u??	3	1
9	2018.07.08	hmm	3	4
\.


--
-- TOC entry 2838 (class 0 OID 70520)
-- Dependencies: 200
-- Data for Name: user_dialog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_dialog (dialog_id, user_id) FROM stdin;
1	1
1	2
2	1
2	3
3	1
3	4
4	1
4	5
\.


--
-- TOC entry 2840 (class 0 OID 70525)
-- Dependencies: 202
-- Data for Name: user_registration_form; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_registration_form (id, last_name, login, name, password) FROM stdin;
\.


--
-- TOC entry 2842 (class 0 OID 70536)
-- Dependencies: 204
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, hash_password, last_name, login, name, role) FROM stdin;
1	$2a$10$C8/aE4bbuA.qqJ76nMEyi.islpN8mJ8kvyaWgwCal/Z0jOiRgspW2	klimov	roman	roman	USER
2	$2a$10$KTaaEkQNVcN6UQDGnnEwYe3tPEUSWjKkwZ9BDjfvTu3gKfyX5zj56	kuznecov	max	max	USER
3	$2a$10$EKhee2gFFk3XKo6MWwprqOzRCgAvHxMyVL8JSl4HCKU76fwSK7qi6	kiku	misha	misha	USER
4	$2a$10$eyuMRFgmECDHTbjDMgeFNeHrft/2HNvwfE1HPdCodXbdwmyPhLkUW	sviridov	oleg	oleg	USER
5	$2a$10$WpJEsp74fQtchSWDYELLLOpOte19XKBP5JLNvmTudwSreD5pcW5qS	bot	bot	bot	USER
\.


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 196
-- Name: dialogs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('dialogs_id_seq', 4, true);


--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 198
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('messages_id_seq', 9, true);


--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 201
-- Name: user_registration_form_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_registration_form_id_seq', 1, false);


--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 203
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 5, true);


--
-- TOC entry 2700 (class 2606 OID 70508)
-- Name: dialogs dialogs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dialogs
    ADD CONSTRAINT dialogs_pkey PRIMARY KEY (id);


--
-- TOC entry 2702 (class 2606 OID 70519)
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 70546)
-- Name: users uk_ow0gan20590jrb00upg3va2fn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_ow0gan20590jrb00upg3va2fn UNIQUE (login);


--
-- TOC entry 2704 (class 2606 OID 70533)
-- Name: user_registration_form user_registration_form_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_registration_form
    ADD CONSTRAINT user_registration_form_pkey PRIMARY KEY (id);


--
-- TOC entry 2708 (class 2606 OID 70544)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2709 (class 2606 OID 70547)
-- Name: messages fkjbkac2909pydsesgme6wc0mn5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY messages
    ADD CONSTRAINT fkjbkac2909pydsesgme6wc0mn5 FOREIGN KEY (dialog_id) REFERENCES dialogs(id);


--
-- TOC entry 2710 (class 2606 OID 70552)
-- Name: messages fkpsmh6clh3csorw43eaodlqvkn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY messages
    ADD CONSTRAINT fkpsmh6clh3csorw43eaodlqvkn FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2711 (class 2606 OID 70557)
-- Name: user_dialog fkq59x2svgtr4ws2r15a48mj5dd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_dialog
    ADD CONSTRAINT fkq59x2svgtr4ws2r15a48mj5dd FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2712 (class 2606 OID 70562)
-- Name: user_dialog fkr78ryo2lep55wh100okjiky8e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_dialog
    ADD CONSTRAINT fkr78ryo2lep55wh100okjiky8e FOREIGN KEY (dialog_id) REFERENCES dialogs(id);


-- Completed on 2018-07-08 18:10:39

--
-- PostgreSQL database dump complete
--

