## Running API and UI Tests

Tests are grouped using JUnit 5 `@Tag` annotations (`api` and `ui`).  
You can run tests for each group or both groups using Maven:

- **Run only API tests:**
  ```bash
  mvn test -Dtest.groups=api

- **Run only UI tests:**
  ```bash
  mvn test -Dtest.groups=ui

- **Run both API and UI tests:**
  ```bash
  mvn test -Dtest.groups=api,ui