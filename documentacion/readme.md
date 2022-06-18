## **MELI-PRO-LOCATARIO-CONSULTAS**

### **Documentaci�n APIS**

https://www.postman.com/dark-satellite-408918/workspace/mercado-libre/request/8617671-50046468-8619-437e-9e0b-f4aec2da850a

#### Descripci�n de la necesidad

Mercado Libre est� implementando un nuevo beneficio para los usuarios que m�s usan la plataforma con un cup�n de cierto monto gratis que les permitir� comprar tantos �tems marcados como favoritos sea posible, siempre que no excedan el monto del cup�n. Para esto se est� analizando construir una API que dado una lista de item_id y el monto total pueda devolver la lista de �tems que maximice el total gastado.

Premisas: 
- S�lo se puede comprar una unidad por item_id.
- No hay preferencia en la cantidad total de �tems.
- El precio puede contener hasta 2 decimales.

### **Atributos de calidad de la soluci�n**

En la siguiente tabla se relacionan los atributos de calidad asociados a la soluci�n:

| **Seguridad**                                                |                                         |
| ------------------------------------------------------------ | --------------------------------------- |
| **Caracter�stica**                                           | **Observaci�n**                         |
| Identificaci�n y Autenticaci�n                               | N/A                                     |
| Autorizaci�n                                                 | N/A                                     |
| Confidencialidad                                             | N/A                                     |
| Integridad                                                   |                                         |
| Auditabilidad                                                |                                         |
| **Desempe�o**                                                |                                         |
| **Caracter�stica**                                           | **Observaci�n**                         |
| Transacciones por segundo                                    | N/A                                     |
| Tiempo de Respuesta M�ximo en Segundos                       | N/A                                     |
| Tiempo de Respuesta Promedio en Segundos                     | N/A                                     |
| Frecuencia                                                   | A demanda                               |
| Registros entregados en orden                                | No                                      |
| **Escalamiento**                                             |                                         |
| **Caracter�stica**                                           | **Observaci�n**                         |
| Cantidad Estimada de Transacciones por D�a/Mes/A�o           | N/A                                     |
| Porcentaje de Crecimiento Estimado de Transacciones por D�a/Mes/A�o (%) | N/A                          |
| **Disponibilidad**                                           |                                         |
| **Caracter�stica**                                           | **Observaci�n**                         |
| Horario de Disponibilidad de la Soluci�n                     | 7-24                                    |
| Contingencia                                                 | Re-env�o de informaci�n desde el origen |
| **Manejo de errores**                                        |                                         |
| **Caracter�stica**                                           | **Observaci�n**                         |
| Trazabilidad                                                 | N/A                                      |
| Errores                                                      | CloudWatch                                    |
| Alertamiento                                                 | N/A                                       |
| Monitoreo                                                    | N/A                                     |
| Reintentos                                                   | N/A                                     |

### **Diagrama de componentes de la Interfaz**

En el siguiente diagrama se muestra el dise�o de la integraci�n y la relaci�n con los diferentes componentes:

![Dise�o de Arquitectura](Diagrama-Arquitectura-Meli.png)

| **Nombre Componente**           | **Descripci�n del componente**                | **Responsabilidad** | **Tipo**      | **Herramienta**  |
|---------------------------------|-----------------------------------------------|---------------------|---------------|------------------|
| meli-coupon                     | Servicios de redimir coup�n                   | Exponer API         | Microservicio | Java             |
| meli-favorites                  | Servicios de consultas y creaci�n             | Exponer API         | Microservicio | Java             |
| meli-gateway                    | Servicios de autorizaci�                      | Exponer API         | Microservicio | Java             |
| Favorites                       | Base de datos de favoritos                    | Almacenar datos     | Base de Datos | SQL Server       |


#### Prerrequisitos

- [x] Verificar la creaci�n y los recursos requeridos para los namespaces.

  | Namespace         | Ambiente   | CPU  | Memoria |
  | ----------------- | ---------- | ---- | ------- |
  | prod              | Producci�n | 240m | 512Mi   |  

