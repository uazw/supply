CREATE TABLE audit(
  id serial PRIMARY KEY,
  patient_id BIGINT NOT NULL,
  prescription_id BIGINT NOT NULL,
  pharmacy_id  BIGINT NOT NULL,
  requested JSONB NOT NULL,
  dispensed JSONB NOT NULL,
  reasons VARCHAR NOT NULL,
  success boolean NOT NULL
);

CREATE INDEX audit_patient_id on audit(patient_id);
CREATE INDEX audit_pharmacy_id on audit(pharmacy_id);
CREATE INDEX audit_success on audit(success);
