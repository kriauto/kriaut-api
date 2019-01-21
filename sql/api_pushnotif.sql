-- Table: public.api_pushnotif

-- DROP TABLE public.api_pushnotif;

CREATE TABLE public.api_pushnotif
(
  id bigint NOT NULL,
  pushtoken character varying(250),
  idprofile bigint,
  CONSTRAINT api_pushnotif_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.api_pushnotif
  OWNER TO postgres;
