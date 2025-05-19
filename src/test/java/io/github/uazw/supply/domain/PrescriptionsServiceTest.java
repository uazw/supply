package io.github.uazw.supply.domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.uazw.supply.domain.model.PatientId;
import io.github.uazw.supply.domain.model.Pharmacy;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

class PrescriptionServiceTest {

  @Test
  void shouldDoNothingIfPharmacyNotExists() {
    var pharmacyPort = mock(PharmacyPort.class);
    var prescriptionPort = mock(PrescriptionPort.class);
    var prescriptionService =
        new PrescriptionService(prescriptionPort, pharmacyPort);
    var patientId = PatientId.from(1L);
    var pharmacyId = PharmacyId.from(1L);
    when(pharmacyPort.findBy(pharmacyId)).thenReturn(Option.none());

    prescriptionService.create(pharmacyId, patientId, List.of());

    verify(prescriptionPort, never()).create(patientId, pharmacyId, List.of());
  }

  @Test
  void shouldDoNothingIfPharmacyUnableToOffer() {
    var pharmacyPort = mock(PharmacyPort.class);
    var prescriptionPort = mock(PrescriptionPort.class);
    var prescriptionService =
        new PrescriptionService(prescriptionPort, pharmacyPort);
    var patientId = PatientId.from(1L);
    var pharmacyId = PharmacyId.from(1L);
    var pharmacy = mock(Pharmacy.class);
    when(pharmacyPort.findBy(pharmacyId)).thenReturn(Option.some(pharmacy));
    when(pharmacy.canOffer(List.of())).thenReturn(false);

    prescriptionService.create(pharmacyId, patientId, List.of());

    verify(prescriptionPort, never()).create(patientId, pharmacyId, List.of());
  }

  @Test
  void shouldOnlyCreatePrescriptionIfRemaining() {
    var pharmacyPort = mock(PharmacyPort.class);
    var prescriptionPort = mock(PrescriptionPort.class);
    var prescriptionService =
        new PrescriptionService(prescriptionPort, pharmacyPort);
    var patientId = PatientId.from(1L);
    var pharmacyId = PharmacyId.from(1L);
    var pharmacy = mock(Pharmacy.class);
    when(pharmacyPort.findBy(pharmacyId)).thenReturn(Option.some(pharmacy));
    when(pharmacy.canOffer(List.of())).thenReturn(true);

    prescriptionService.create(pharmacyId, patientId, List.of());

    verify(prescriptionPort).create(patientId, pharmacyId, List.of());
  }
}