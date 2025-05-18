plugins {
  java
  id("org.springframework.boot") version "3.4.5"
  id("io.spring.dependency-management") version "1.1.7"
  checkstyle
  jacoco
}

group = "io.github.uazw"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

checkstyle {
  toolVersion = "10.23.1"
}

jacoco {
  toolVersion = "0.8.12"
}
tasks.jacocoTestReport {
  reports {
    xml.required = false
    csv.required = false
  }
  dependsOn(tasks.test)
  finalizedBy(tasks.jacocoTestCoverageVerification)
  classDirectories.setFrom(
    sourceSets.main.get().output.asFileTree.matching {
      exclude("io/github/uazw/supply/SupplyApplication*")
      exclude("**/dto/**/*.class")
      exclude("**/model/**/*.class")
      exclude("**/entity/**/*.class")
    }
  )
}
tasks.jacocoTestCoverageVerification {
  violationRules {
    rule {
      classDirectories.setFrom(sourceSets.main.get().output.asFileTree.matching {
        exclude("io/github/uazw/supply/SupplyApplication*")
        exclude("**/dto/**/*.class")
        exclude("**/entity/**/*.class")
      })
      limit {
        minimum = "0.8".toBigDecimal()
      }

    }
  }
}

tasks.test {
  finalizedBy(tasks.jacocoTestReport)
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.zaxxer:HikariCP:6.3.0")
  implementation("io.vavr:vavr:0.10.5")
  implementation("org.flywaydb:flyway-core")
  implementation("org.flywaydb:flyway-database-postgresql")
  implementation("org.postgresql:postgresql")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.testcontainers:junit-jupiter:1.20.4")
  testImplementation("org.testcontainers:postgresql:1.20.4")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.withType<Test> {
  useJUnitPlatform()
}
