# driver-service

Guarda perfiles, credenciales, certificaciones y la asignación actual de cada conductor. Escucha en el puerto `18082`.

## Preparar la base

Crea una base PostgreSQL llamada `driver_db`. La configuración predeterminada usa `localhost:5432`, el usuario `postgres` y la contraseña `root`. Puedes cambiarla con `DRIVER_DB_URL`, `DRIVER_DB_USER` y `DRIVER_DB_PASSWORD`. Hibernate crea y actualiza las tablas al iniciar.

## Iniciar el servicio

```sh
./mvnw spring-boot:run
```

En Windows, ejecuta `mvnw.cmd spring-boot:run`.

## Usar la API

`POST /drivers` crea el perfil. `GET /drivers/{id}` consulta por identificador y `GET /drivers/by-vehicle/{vehicleId}` consulta la asignación actual.

```sh
curl -i http://localhost:18082/drivers \
  -H 'Content-Type: application/json' \
  -d '{"id":"drv-552","fullName":"Alicia Solís","licenseNumber":"LIC-552","licenseCategory":"Commercial-A","licenseIssuedOn":"2024-10-01","licenseExpiresOn":"2027-10-01","safetyScore":98.2,"certifications":["Hazmat"],"currentAssignment":{"assignmentId":"asg-9982-x","vehicleId":"veh-001"}}'
curl http://localhost:18082/drivers/by-vehicle/veh-001
```

`currentAssignment` es opcional. Si se envía, incluye ambos campos. Responde `400` ante solicitudes inválidas, `404` si no hay resultado y `409` ante identificadores, licencias o asignaciones duplicados. Consulta los [diagramas de arquitectura](docs/arquitectura.md) y la API compuesta.
