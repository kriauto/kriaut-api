-- Table: public.positions

-- DROP TABLE public.positions;

CREATE TABLE public.positions
(
  id integer NOT NULL DEFAULT nextval('positions_id_seq'::regclass),
  protocol character varying(128),
  deviceid integer NOT NULL,
  servertime timestamp without time zone NOT NULL,
  devicetime timestamp without time zone NOT NULL,
  fixtime timestamp without time zone NOT NULL,
  valid boolean NOT NULL,
  latitude double precision NOT NULL,
  longitude double precision NOT NULL,
  altitude double precision NOT NULL,
  speed double precision NOT NULL,
  course double precision NOT NULL,
  address character varying(512),
  attributes character varying(4000),
  accuracy double precision NOT NULL DEFAULT 0,
  network character varying(4000),
  CONSTRAINT pk_positions PRIMARY KEY (id),
  CONSTRAINT fk_position_deviceid FOREIGN KEY (deviceid)
      REFERENCES public.devices (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.positions
  OWNER TO postgres;

-- Index: public.deviceid_idx

-- DROP INDEX public.deviceid_idx;

CREATE INDEX deviceid_idx
  ON public.positions
  USING btree
  (deviceid);

-- Index: public.position_deviceid_fixtime

-- DROP INDEX public.position_deviceid_fixtime;

CREATE INDEX position_deviceid_fixtime
  ON public.positions
  USING btree
  (deviceid, fixtime);

-- Index: public.servertime_idx

-- DROP INDEX public.servertime_idx;

CREATE INDEX servertime_idx
  ON public.positions
  USING btree
  (servertime);

-- Index: public.valid_idx

-- DROP INDEX public.valid_idx;

CREATE INDEX valid_idx
  ON public.positions
  USING btree
  (valid);

