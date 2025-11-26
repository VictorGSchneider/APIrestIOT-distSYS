# API REST IoT

API REST simples (Spring Boot) para receber dados de sensores simulados, criada para integrar os JARs fornecidos na disciplina de Sistemas Distribuídos.

### Arquivos fornecidos no repositório
- `simulator-sensores-iot.jar`: simulador que gera leituras de **Temperatura**, **Umidade**, **Luminosidade** e **Movimento** e envia POSTs para `http://localhost:8080/api/sensor/data`.
- `server-iot-rest-example.jar`: servidor de exemplo enviado pelo professor. Ele pode ser executado sozinho, mas este projeto provê uma implementação equivalente aberta para ajustes.

## Pré-requisitos
- Java 21 instalado (verifique com `java -version`).
- Maven disponível no PATH.

## Como executar
1. Instale dependências e gere o JAR da API:
   ```bash
   mvn clean package
   ```
2. Inicie a API construída (porta padrão `8080`):
   ```bash
   java -jar target/iot-rest-1.0.0.jar
   ```
   > Se quiser apenas comparar com a versão entregue pelo professor, execute `java -jar server-iot-rest-example.jar` (usa os mesmos endpoints).
3. Em outro terminal, rode o simulador para enviar leituras automaticamente:
   ```bash
   java -jar simulator-sensores-iot.jar
   ```
   O simulador enviará dados de temperatura, umidade, luminosidade e movimento a cada 3 segundos. A execução será interrompida se a API não estiver acessível.

## Endpoints principais
- `POST /api/sensor/data`: recebe um JSON com os campos `sensorId`, `type`, `value` e `timestamp` (ISO-8601). Responde com `"Data received"`.
- `GET /api/sensor/data`: lista todas as leituras recebidas desde o início da aplicação.
- `GET /api/sensor/summary`: resumo por tipo de sensor, com contagem e média dos valores registrados.

### Exemplo de envio manual
```bash
curl -X POST http://localhost:8080/api/sensor/data \
  -H "Content-Type: application/json" \
  -d '{"sensorId":"T010","type":"temperature","value":21.7,"timestamp":"2024-05-01T12:00:00Z"}'
```

### Exemplo de consulta
```bash
curl http://localhost:8080/api/sensor/data
curl http://localhost:8080/api/sensor/summary
```

## Observações
- Os dados são mantidos em memória durante a execução da API (não há persistência em disco).
- As validações básicas garantem que todos os campos sejam informados antes de aceitar uma leitura.
