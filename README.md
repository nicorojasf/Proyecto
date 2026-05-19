# Proyecto
proyecto de fullstack 1 
El Proyecto Clínica Bupa es una plataforma integral de Gobernanza, Trazabilidad y Abastecimiento Textil Hospitalario diseñada bajo una arquitectura de microservicios. Su objetivo principal es automatizar y auditar todo el ciclo de vida de las prendas de la clínica (sábanas quirúrgicas, uniformes del personal médico, batas de pacientes y fundas), controlando desde la adquisición con proveedores externos hasta su distribución en áreas críticas, su higienización en lavandería y su desecho final.

El sistema garantiza que la Clínica Bupa cuente con un suministro constante de ropa limpia en sus pisos clínicos, optimice los costos operativos evitando pérdidas de inventario y mantenga un estándar estricto de control interno mediante auditorías.

La Arquitectura del Proyecto: Tus 6 Microservicios
Para asegurar que la plataforma sea escalable, rápida y modular, el ecosistema de la Clínica Bupa se divide en 6 aplicaciones independientes:

Usuarios: Controla la seguridad y el acceso a la plataforma. Gestiona los perfiles, credenciales, roles y permisos específicos del personal de la clínica (adquisiciones, enfermería, lavandería, jefaturas y auditores).

Gestión Textil: Es el núcleo administrativo y de compras. Registra las fichas técnicas de las prendas y administra el catálogo de Proveedores y las Órdenes de Compra, centralizando la adquisición de nuevos textiles.

Inventario: Funciona como el almacén o bodega central de la clínica. Recibe los ingresos de mercancía aprobados por Gestión Textil y maneja el stock maestro global antes de ser distribuido.

Asignaciones: Coordina la logística interna de la clínica. Distribuye las prendas desde la bodega central hacia los stocks locales de las diferentes unidades médicas (como UCI, Maternidad, Urgencias o Pabellón) y gestiona las salidas de ropa sucia.

Lavandería: Se encarga del proceso de sanitización e higiene. Recibe la ropa usada enviada por Asignaciones, registra su estado en la base de datos como "PROCESANDO" y coordina el retorno seguro de la ropa limpia a su área correspondiente.

Descarte y Auditoría: Supervisa el fin de la vida útil de los textiles. Si una prenda se rompe, se desgasta o se contamina con químicos, este servicio procesa su baja definitiva (Descarte). Además, registra un historial detallado (log) de cada movimiento en el sistema para auditorías de control e inspecciones.

El Circuito de la Ropa en la Clínica Bupa (Flujo de Negocio)
Abastecimiento: El área de compras genera una Orden de Compra a un Proveedor en Gestión Textil. Al llegar el pedido a la clínica, el stock impacta masivamente en el Inventario central.

Distribución Interna: El servicio de Asignaciones toma sábanas del inventario central y las entrega a un piso clínico (por ejemplo, Maternidad), creando un stock local disponible para los pacientes.

Uso e Higiene: La ropa se utiliza y se ensucia. El personal de enfermería registra la salida en Asignaciones (descontando el stock local de Maternidad) y la envía al microservicio de Lavandería. Una vez lavada y esterilizada, la aplicación gestiona su devolución automática para volver a sumar stock limpio en Maternidad.

Baja y Control: Si durante el proceso se detecta una prenda dañada, el sistema la deriva a Descarte y Auditoría para sacarla del circuito legalmente, dejando un rastro digital inalterable de por qué se eliminó esa pieza.

Esta estructura convierte al Proyecto Clínica Bupa en una solución de software empresarial madura, robusta y con una lógica de negocio del más alto nivel logístico.
