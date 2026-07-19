# SOAP service sample

Standalone SOAP-сервис на Apache CXF (Jetty) по WSDL НБ РБ (`ExRates`).  
Реализован один метод: **`ExRatesDaily`**.

## Сборка и запуск

```bash
mvn -pl soap-service-sample -am package

mvn -pl soap-service-sample exec:java -Dexec.mainClass=by.andd3dfx.soap.SoapServerMain
```

После старта:
- endpoint: http://localhost:8080/services/exrates
- WSDL: http://localhost:8080/services/exrates?wsdl

## Вызов ExRatesDaily

SOAP 1.1, document/literal. Заголовок `SOAPAction` обязателен.

**Запрос:**

```http
POST http://localhost:8080/services/exrates
Content-Type: text/xml; charset=utf-8
SOAPAction: "https://www.nbrb.by/ExRatesDaily"
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:tns="https://www.nbrb.by/">
  <soap:Body>
    <tns:ExRatesDaily>
      <tns:onDate>2026-07-19T00:00:00</tns:onDate>
    </tns:ExRatesDaily>
  </soap:Body>
</soap:Envelope>
```

**curl (bash):**

```bash
curl -s -w "\nHTTP %{http_code}\n" -X POST http://localhost:8080/services/exrates \
  -H 'Content-Type: text/xml; charset=utf-8' \
  -H 'SOAPAction: "https://www.nbrb.by/ExRatesDaily"' \
  --data-binary @- <<'EOF'
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:tns="https://www.nbrb.by/">
  <soap:Body>
    <tns:ExRatesDaily>
      <tns:onDate>2026-07-19T00:00:00</tns:onDate>
    </tns:ExRatesDaily>
  </soap:Body>
</soap:Envelope>
EOF
```

В ответе — mock-курсы `USD` / `EUR` внутри `ExRatesDailyResult`.
