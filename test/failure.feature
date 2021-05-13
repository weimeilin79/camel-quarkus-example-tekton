Feature: integration runs

  Background:
    Given load variables namespace.properties
    Given URL: http://camel-hello-quarkus.${namespace}.svc.cluster.local:8080
    
  Scenario: Given integration prescription is running
    Given print 'Then integration hello should print Hello Camel K from Tekton pipelines'

  
  Scenario: Get a result from API
    When send GET /prescription?profileid=234456
    Then verify HTTP response body: {"status": "NOT FOUND"}
    Then receive HTTP 404 OK