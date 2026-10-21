# Arquitectura de driver-service

## Componentes

```mermaid
flowchart LR
  client[Cliente HTTP] --> api[DriverController]
  api --> service[DriverService]
  service --> repo[DriverRepository]
  repo --> db[(PostgreSQL driver_db)]
  db --- drivers[drivers]
  db --- credentials[driver_credentials]
  db --- certifications[driver_certifications]
```

## Consulta de asignación

```mermaid
sequenceDiagram
  participant C as Cliente
  participant API as DriverController
  participant S as DriverService
  participant DB as PostgreSQL
  C->>API: GET /drivers/by-vehicle/{vehicleId}
  API->>S: getByVehicle(vehicleId)
  S->>DB: Buscar asignación actual
  DB-->>S: Conductor, credencial y certificaciones
  S-->>API: DriverResponse
  API-->>C: 200 OK
```

La asignación actual pertenece al conductor. La entidad de conductor mantiene únicos `assignment_id` y `vehicle_id`; la API exige que ambos aparezcan juntos.
