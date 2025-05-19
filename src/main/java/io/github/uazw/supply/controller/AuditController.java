package io.github.uazw.supply.controller;

import static io.github.uazw.supply.adapter.spec.AuditSpec.NOOP;

import io.github.uazw.supply.adapter.AuditRepository;
import io.github.uazw.supply.adapter.entity.AuditEntity;
import io.github.uazw.supply.adapter.spec.AuditSpec;
import io.github.uazw.supply.controller.dto.AuditResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
public class AuditController {


  private final AuditRepository auditRepository;

  @Autowired
  public AuditController(AuditRepository auditRepository) {
    this.auditRepository = auditRepository;
  }

  @GetMapping
  public List<AuditResponse> listAll(@RequestParam("patientId") Optional<Long> patientId,
                                     @RequestParam("pharmacyId") Optional<Long> pharmacyId,
                                     @RequestParam("success") Optional<Boolean> success) {


    var spec = Specification.<AuditEntity>where(null)
        .and(patientId.map(AuditSpec::byPatientId).orElseGet(() -> NOOP)).
        and(pharmacyId.map(AuditSpec::byPharmacyId).orElseGet(() -> NOOP))
        .and(success.map(AuditSpec::bySuccess).orElseGet(() -> NOOP));

    return auditRepository.findAll(spec).stream().map(
        audit -> new AuditResponse(audit.getId(), audit.getPrescriptionId(),
            audit.getPatientId(), audit.getPharmacyId(), audit.getRequested(),
            audit.getDispensed(), audit.getReasons(), audit.isSuccess())).toList();

  }
}
