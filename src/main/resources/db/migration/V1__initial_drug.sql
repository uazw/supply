CREATE TABLE drug(
  id serial PRIMARY KEY,
  name VARCHAR NOT NULL,
  manufacturer VARCHAR NOT NULL
);
CREATE INDEX drug_name_manufacturer_idx ON drug(name, manufacturer);

CREATE TABLE drug_stock(
  id serial PRIMARY KEY,
  drug_id BIGINT NOT NULL,
  batch_number VARCHAR NOT NULL,
  expired_date TIMESTAMP WITH TIME ZONE NOT NULL,
  stock BIGINT NOT NULL,
  remaining BIGINT NOT NULL
);
CREATE INDEX drug_stock_drug_id on drug_stock(drug_id);