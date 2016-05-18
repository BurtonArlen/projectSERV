--
-- PostgreSQL database dump
--

<<<<<<< HEAD
-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2
=======
-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
<<<<<<< HEAD

-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:

=======
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
<<<<<<< HEAD

-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:

=======
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
<<<<<<< HEAD
-- Name: skills; Type: TABLE; Schema: public; Owner: team_serv
=======
-- Name: skills; Type: TABLE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE TABLE skills (
    id integer NOT NULL,
    skill character varying
);


<<<<<<< HEAD
ALTER TABLE skills OWNER TO team_serv;

--
-- Name: skills_id_seq; Type: SEQUENCE; Schema: public; Owner: team_serv
=======
ALTER TABLE skills OWNER TO "Guest";

--
-- Name: skills_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE SEQUENCE skills_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


<<<<<<< HEAD
ALTER TABLE skills_id_seq OWNER TO team_serv;

--
-- Name: skills_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: team_serv
=======
ALTER TABLE skills_id_seq OWNER TO "Guest";

--
-- Name: skills_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER SEQUENCE skills_id_seq OWNED BY skills.id;


--
<<<<<<< HEAD
-- Name: students; Type: TABLE; Schema: public; Owner: team_serv
=======
-- Name: students; Type: TABLE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE TABLE students (
    id integer NOT NULL,
<<<<<<< HEAD
    student_name character varying,
    bio character varying,
    email character varying,
    password character varying,
    created_at timestamp without time zone
);


ALTER TABLE students OWNER TO team_serv;

--
-- Name: students_exps; Type: TABLE; Schema: public; Owner: team_serv
=======
    student_first_name character varying,
    student_last_name character varying,
    bio text,
    email character varying,
    password character varying
);


ALTER TABLE students OWNER TO "Guest";

--
-- Name: students_exps; Type: TABLE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE TABLE students_exps (
    id integer NOT NULL,
    student_id integer,
    exp_id integer
);


<<<<<<< HEAD
ALTER TABLE students_exps OWNER TO team_serv;

--
-- Name: students_exps_id_seq; Type: SEQUENCE; Schema: public; Owner: team_serv
=======
ALTER TABLE students_exps OWNER TO "Guest";

--
-- Name: students_exps_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE SEQUENCE students_exps_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


<<<<<<< HEAD
ALTER TABLE students_exps_id_seq OWNER TO team_serv;

--
-- Name: students_exps_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: team_serv
=======
ALTER TABLE students_exps_id_seq OWNER TO "Guest";

--
-- Name: students_exps_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER SEQUENCE students_exps_id_seq OWNED BY students_exps.id;


--
<<<<<<< HEAD
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: team_serv
=======
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE SEQUENCE students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


<<<<<<< HEAD
ALTER TABLE students_id_seq OWNER TO team_serv;

--
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: team_serv
=======
ALTER TABLE students_id_seq OWNER TO "Guest";

--
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER SEQUENCE students_id_seq OWNED BY students.id;


--
<<<<<<< HEAD
-- Name: students_skills; Type: TABLE; Schema: public; Owner: team_serv
=======
-- Name: students_skills; Type: TABLE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE TABLE students_skills (
    id integer NOT NULL,
    student_id integer,
    skill_id integer
);


<<<<<<< HEAD
ALTER TABLE students_skills OWNER TO team_serv;

--
-- Name: students_skills_id_seq; Type: SEQUENCE; Schema: public; Owner: team_serv
=======
ALTER TABLE students_skills OWNER TO "Guest";

--
-- Name: students_skills_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE SEQUENCE students_skills_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


<<<<<<< HEAD
ALTER TABLE students_skills_id_seq OWNER TO team_serv;

--
-- Name: students_skills_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: team_serv
=======
ALTER TABLE students_skills_id_seq OWNER TO "Guest";

--
-- Name: students_skills_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER SEQUENCE students_skills_id_seq OWNED BY students_skills.id;


--
<<<<<<< HEAD
-- Name: work_exp; Type: TABLE; Schema: public; Owner: team_serv
=======
-- Name: work_exp; Type: TABLE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE TABLE work_exp (
    id integer NOT NULL,
    exp character varying
);


<<<<<<< HEAD
ALTER TABLE work_exp OWNER TO team_serv;

--
-- Name: work_exp_id_seq; Type: SEQUENCE; Schema: public; Owner: team_serv
=======
ALTER TABLE work_exp OWNER TO "Guest";

--
-- Name: work_exp_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

CREATE SEQUENCE work_exp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


<<<<<<< HEAD
ALTER TABLE work_exp_id_seq OWNER TO team_serv;

--
-- Name: work_exp_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: team_serv
=======
ALTER TABLE work_exp_id_seq OWNER TO "Guest";

--
-- Name: work_exp_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER SEQUENCE work_exp_id_seq OWNED BY work_exp.id;


--
<<<<<<< HEAD
-- Name: id; Type: DEFAULT; Schema: public; Owner: team_serv
=======
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY skills ALTER COLUMN id SET DEFAULT nextval('skills_id_seq'::regclass);


--
<<<<<<< HEAD
-- Name: id; Type: DEFAULT; Schema: public; Owner: team_serv
=======
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY students ALTER COLUMN id SET DEFAULT nextval('students_id_seq'::regclass);


--
<<<<<<< HEAD
-- Name: id; Type: DEFAULT; Schema: public; Owner: team_serv
=======
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY students_exps ALTER COLUMN id SET DEFAULT nextval('students_exps_id_seq'::regclass);


--
<<<<<<< HEAD
-- Name: id; Type: DEFAULT; Schema: public; Owner: team_serv
=======
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY students_skills ALTER COLUMN id SET DEFAULT nextval('students_skills_id_seq'::regclass);


--
<<<<<<< HEAD
-- Name: id; Type: DEFAULT; Schema: public; Owner: team_serv
=======
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY work_exp ALTER COLUMN id SET DEFAULT nextval('work_exp_id_seq'::regclass);


--
<<<<<<< HEAD
-- Data for Name: skills; Type: TABLE DATA; Schema: public; Owner: team_serv
=======
-- Data for Name: skills; Type: TABLE DATA; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

COPY skills (id, skill) FROM stdin;
\.


--
<<<<<<< HEAD
-- Name: skills_id_seq; Type: SEQUENCE SET; Schema: public; Owner: team_serv
=======
-- Name: skills_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

SELECT pg_catalog.setval('skills_id_seq', 1, false);


--
<<<<<<< HEAD
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: team_serv
--

COPY students (id, student_name, bio, email, password, created_at) FROM stdin;
=======
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY students (id, student_first_name, student_last_name, bio, email, password) FROM stdin;
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
\.


--
<<<<<<< HEAD
-- Data for Name: students_exps; Type: TABLE DATA; Schema: public; Owner: team_serv
=======
-- Data for Name: students_exps; Type: TABLE DATA; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

COPY students_exps (id, student_id, exp_id) FROM stdin;
\.


--
<<<<<<< HEAD
-- Name: students_exps_id_seq; Type: SEQUENCE SET; Schema: public; Owner: team_serv
=======
-- Name: students_exps_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

SELECT pg_catalog.setval('students_exps_id_seq', 1, false);


--
<<<<<<< HEAD
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: team_serv
=======
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

SELECT pg_catalog.setval('students_id_seq', 1, false);


--
<<<<<<< HEAD
-- Data for Name: students_skills; Type: TABLE DATA; Schema: public; Owner: team_serv
=======
-- Data for Name: students_skills; Type: TABLE DATA; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

COPY students_skills (id, student_id, skill_id) FROM stdin;
\.


--
<<<<<<< HEAD
-- Name: students_skills_id_seq; Type: SEQUENCE SET; Schema: public; Owner: team_serv
=======
-- Name: students_skills_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

SELECT pg_catalog.setval('students_skills_id_seq', 1, false);


--
<<<<<<< HEAD
-- Data for Name: work_exp; Type: TABLE DATA; Schema: public; Owner: team_serv
=======
-- Data for Name: work_exp; Type: TABLE DATA; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

COPY work_exp (id, exp) FROM stdin;
\.


--
<<<<<<< HEAD
-- Name: work_exp_id_seq; Type: SEQUENCE SET; Schema: public; Owner: team_serv
=======
-- Name: work_exp_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

SELECT pg_catalog.setval('work_exp_id_seq', 1, false);


--
<<<<<<< HEAD
-- Name: skills_pkey; Type: CONSTRAINT; Schema: public; Owner: team_serv
=======
-- Name: skills_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY skills
    ADD CONSTRAINT skills_pkey PRIMARY KEY (id);


--
<<<<<<< HEAD
-- Name: students_exps_pkey; Type: CONSTRAINT; Schema: public; Owner: team_serv
=======
-- Name: students_exps_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY students_exps
    ADD CONSTRAINT students_exps_pkey PRIMARY KEY (id);


--
<<<<<<< HEAD
-- Name: students_pkey; Type: CONSTRAINT; Schema: public; Owner: team_serv
=======
-- Name: students_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
<<<<<<< HEAD
-- Name: students_skills_pkey; Type: CONSTRAINT; Schema: public; Owner: team_serv
=======
-- Name: students_skills_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY students_skills
    ADD CONSTRAINT students_skills_pkey PRIMARY KEY (id);


--
<<<<<<< HEAD
-- Name: work_exp_pkey; Type: CONSTRAINT; Schema: public; Owner: team_serv
=======
-- Name: work_exp_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
--

ALTER TABLE ONLY work_exp
    ADD CONSTRAINT work_exp_pkey PRIMARY KEY (id);


--
<<<<<<< HEAD
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
=======
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
<<<<<<< HEAD
=======

>>>>>>> 24b99e54e3e4a334b71a463c7f85ca307dc78141
