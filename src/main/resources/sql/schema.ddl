DROP TABLE IF EXISTS location;

CREATE TABLE location (
  id          VARCHAR (32) PRIMARY KEY,
  type        VARCHAR(10) NOT NULL,
  host        VARCHAR(10) NOT NULL,
  scheme      VARCHAR(50) NOT NULL,
  path        VARCHAR(50) NOT NULL,
  searchByNameParamName VARCHAR(50) NOT NULL,
  items_key      VARCHAR(50) NOT NULL,
  price_key      VARCHAR(50) NOT NULL,
  name_key      VARCHAR(50) NOT NULL,
  UNIQUE (type)
);

DROP TABLE IF EXISTS requestparam;

CREATE TABLE requestparam (
  id          VARCHAR (32) PRIMARY KEY,
  location_id VARCHAR (25) NOT NULL,
  name        VARCHAR(10) NOT NULL,
  value       VARCHAR(50) NOT NULL,
  CONSTRAINT fk_requestParam_Location FOREIGN KEY (id_location)
  REFERENCES location (id_location),
  CONSTRAINT uc_locationParamName UNIQUE (location_id, name)
);

