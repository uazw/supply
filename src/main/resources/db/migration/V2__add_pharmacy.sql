CREATE TABLE pharmacy(
  id serial PRIMARY KEY
);

CREATE TABLE contracted_drug(
  id serial PRIMARY KEY,
  drug_id BIGINT NOT NULL,
  pharmacy_id BIGINT NOT NULL,
  allocated BIGINT NOT NULL,
  remaining BIGINT NOT NULL
);
CREATE INDEX contracted_drug_pharmacy_id on contracted_drug(pharmacy_id);
