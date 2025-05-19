CREATE TABLE prescription(
  id serial PRIMARY KEY,
  patient_id BIGINT NOT NULL,
  pharmacy_id  BIGINT NOT NULL,
  drugs JSONB NOT NULL
);
