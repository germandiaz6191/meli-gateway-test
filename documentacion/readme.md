## **MELI-PRO-LOCATARIO-CONSULTAS**

### **Documentación APIS**

https://www.postman.com/dark-satellite-408918/workspace/mercado-libre/request/8617671-50046468-8619-437e-9e0b-f4aec2da850a

#### Descripción de la necesidad

Mercado Libre está implementando un nuevo beneficio para los usuarios que más usan la plataforma con un cupón de cierto monto gratis que les permitirá comprar tantos ítems marcados como favoritos sea posible, siempre que no excedan el monto del cupón. Para esto se está analizando construir una API que dado una lista de item_id y el monto total pueda devolver la lista de ítems que maximice el total gastado.

Premisas: 
- Sólo se puede comprar una unidad por item_id.
- No hay preferencia en la cantidad total de ítems.
- El precio puede contener hasta 2 decimales.

### **Atributos de calidad de la solución**

En la siguiente tabla se relacionan los atributos de calidad asociados a la solución:

| **Seguridad**                                                |                                         |
| ------------------------------------------------------------ | --------------------------------------- |
| **Característica**                                           | **Observación**                         |
| Identificación y Autenticación                               | N/A                                     |
| Autorización                                                 | N/A                                     |
| Confidencialidad                                             | N/A                                     |
| Integridad                                                   |                                         |
| Auditabilidad                                                |                                         |
| **Desempeño**                                                |                                         |
| **Característica**                                           | **Observación**                         |
| Transacciones por segundo                                    | N/A                                     |
| Tiempo de Respuesta Máximo en Segundos                       | N/A                                     |
| Tiempo de Respuesta Promedio en Segundos                     | N/A                                     |
| Frecuencia                                                   | A demanda                               |
| Registros entregados en orden                                | No                                      |
| **Escalamiento**                                             |                                         |
| **Característica**                                           | **Observación**                         |
| Cantidad Estimada de Transacciones por Día/Mes/Año           | N/A                                     |
| Porcentaje de Crecimiento Estimado de Transacciones por Día/Mes/Año (%) | N/A                          |
| **Disponibilidad**                                           |                                         |
| **Característica**                                           | **Observación**                         |
| Horario de Disponibilidad de la Solución                     | 7-24                                    |
| Contingencia                                                 | Re-envío de información desde el origen |
| **Manejo de errores**                                        |                                         |
| **Característica**                                           | **Observación**                         |
| Trazabilidad                                                 | N/A                                      |
| Errores                                                      | CloudWatch                                    |
| Alertamiento                                                 | N/A                                       |
| Monitoreo                                                    | N/A                                     |
| Reintentos                                                   | N/A                                     |

### **Diagrama de componentes de la Interfaz**

En el siguiente diagrama se muestra el diseño de la integración y la relación con los diferentes componentes:

![Diseño de Arquitectura](Diagrama-Arquitectura-Meli.png)

| **Nombre Componente**           | **Descripción del componente**                | **Responsabilidad** | **Tipo**      | **Herramienta**  |
|---------------------------------|-----------------------------------------------|---------------------|---------------|------------------|
| meli-coupon                     | Servicios de redimir coupón                   | Exponer API         | Microservicio | Java             |
| meli-favorites                  | Servicios de consultas y creación             | Exponer API         | Microservicio | Java             |
| meli-gateway                    | Servicios de autorizació                      | Exponer API         | Microservicio | Java             |
| Favorites                       | Base de datos de favoritos                    | Almacenar datos     | Base de Datos | SQL Server       |


#### Prerrequisitos

- [x] Verificar la creación y los recursos requeridos para los namespaces.

  | Namespace         | Ambiente   | CPU  | Memoria |
  | ----------------- | ---------- | ---- | ------- |
  | prod              | Producción | 240m | 512Mi   |  

