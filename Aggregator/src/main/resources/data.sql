DROP TABLE IF EXISTS product;

CREATE TABLE product (
  UUID VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  provider VARCHAR(250) NOT NULL,
  available VARCHAR(250) NOT NULL,
  measurementUnits BOOLEAN NOT NULL,
  createdAt DATE DEFAULT CURRENT_DATE() NOT NULL,
  modifiedAt DATE DEFAULT CURRENT_DATE() NOT NULL
);
