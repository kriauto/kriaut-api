-- Table: public.api_car

-- DROP TABLE public.api_car;

CREATE TABLE public.api_car
(
  id bigint NOT NULL,
  mark character varying(250),
  model character varying,
  immatriculation character varying(250),
  htmlcolor character varying(250),
  agencyid bigint,
  consumption real,
  deviceid integer,
  CONSTRAINT api_car_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.api_car
  OWNER TO postgres;
