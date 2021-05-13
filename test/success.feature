Feature: integration runs

  Background:
    Given load variables namespace.properties
    Given URL: http://camel-hello-quarkus.${namespace}.svc.cluster.local:8080

  Scenario: Given integration prescription is running
    Given print 'Then integration hello should print Hello Camel K from Tekton pipelines'

  
  Scenario: Checking GET Method
    When send GET /prescription?profileid=123456
    Then verify HTTP response body: {"profileid": 123456, "patient": "Me", "complaint": "Cold Symptoms", "diagnosis": "Acute Bronchitis", "prescription": [ { "medicine": "benzonatate", "dosage": "3 times per day" }, { "medicine": "ProAir HFA", "dosage": "every 4 to 6 hours" } ], "pharmancy": "CVS" }
    Then receive HTTP 200 OK
