-- Table: public.api_positions

-- DROP TABLE public.api_positions;

CREATE TABLE public.api_positions
(
  id integer,
  protocol character varying(128),
  deviceid integer,
  servertime timestamp without time zone,
  devicetime timestamp without time zone,
  fixtime timestamp without time zone,
  valid boolean,
  latitude double precision,
  longitude double precision,
  altitude double precision,
  speed double precision,
  course double precision,
  address character varying(512),
  attributes character varying(4000),
  accuracy double precision,
  network character varying(4000)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.api_positions
  OWNER TO postgres;
