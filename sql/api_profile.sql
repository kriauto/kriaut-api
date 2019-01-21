-- Table: public.api_profile

-- DROP TABLE public.api_profile;

CREATE TABLE public.api_profile
(
  id bigint NOT NULL,
  login character varying(250),
  password character varying(250),
  authtoken character varying(250),
  name character varying(250),
  phone character varying(250),
  mail character varying(250),
  islastposition boolean,
  ishistory boolean,
  isspeedmax boolean,
  iscourse boolean,
  iscarburantp boolean,
  iscarburants boolean,
  isnotifcons boolean,
  isnotifconf boolean,
  iszoneone boolean,
  iszonetwo boolean,
  istempm boolean,
  istempf boolean,
  isdoor boolean,
  isdriver boolean,
  isparameters boolean,
  isstartstop boolean,
  ismyaccount boolean,
  CONSTRAINT api_profile_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.api_profile
  OWNER TO postgres;
