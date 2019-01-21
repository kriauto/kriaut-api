-- Table: public.api_agency

-- DROP TABLE public.api_agency;

CREATE TABLE public.api_agency
(
  id bigint NOT NULL,
  name character varying(250),
  city character varying(250),
  address character varying(250),
  phone character varying(250),
  fax character varying(250),
  idprofile bigint,
  CONSTRAINT api_agency_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.api_agency
  OWNER TO postgres;
