create database if not exists elCriollo;
use elCriollo;

create table if not exists sesiones(
	usuario varchar(50) not null primary key,
    fechaInicio datetime,
    fechaFin datetime,
    contraseña varchar(50) not null
);

create table if not exists empleados(
	idEmpleados varchar(10) not null primary key,
    nombreEmpleado varchar(50),
    rol varchar(20),
    fechaContratacion date,
    salario decimal,
    telefono varchar(20),
    direccion varchar(50),
    email varchar(50),
    usuario varchar(50),
    foreign key (usuario) references sesiones(usuario)
);

create table if not exists mesas(
	idMesas varchar(10) not null primary key,
    numeroMesa int,
    capacidad int,
    estado varchar(20)
);

create table if not exists proveedores(
	idProveedores varchar(10) not null primary key,
    nombreEmpresa varchar(50),
    contacto varchar(30),
    telefono varchar(30),
    email varchar(50),
    direccion varchar(50),
    terminoPago varchar(30)
);

create table if not exists categoria(
	idCategoria varchar(10) not null primary key,
    nombreCategoria varchar(40)
);

create table if not exists productos(
	idProductos varchar(10) not null primary key,
    nombreProducto varchar(30),
    descripcionProducto text,
    precio decimal,
    categoria varchar(10),
    foreign key (categoria) references categoria(idCategoria)
);

create table if not exists pedidos(
	idPedidos varchar(10) not null primary key,
    empleadosId varchar(10),
    mesasId varchar(10),
    fechaPedido date,
    estadoPedido varchar(20),
    precioTotal decimal,
    foreign key (empleadosId) references empleados(idEmpleados),
    foreign key (mesasId) references mesas(idMesas)
);

create table if not exists categoriaPlatos(
	idCategoriaPlatos varchar(10) not null primary key,
    nombreCategoriaPlatos varchar(50)
);

create table if not exists platos(
	idPlatos varchar(10) not null primary key,
    nombrePlato varchar(50),
    precio decimal,
    categoriaPlatosId varchar(10),
    foreign key (categoriaPlatosId) references categoriaPlatos(idCategoriaPlatos)
);

create table if not exists detallesPedido(
	pedidosId varchar(10),
    platosId varchar(10),
    cantidad int,
    precioUnitario decimal,
    foreign key (pedidosId) references pedidos(idPedidos),
    foreign key (platosId) references platos(idPlatos)
);

create table if not exists reservas (
	idReservas int not null primary key,
    nombreCliente varchar(30),
    fecha date, 
    numeroPersonas int,
    telefono varchar(10),
    nota text
);

select * from reservas;

ALTER TABLE reservas
    MODIFY COLUMN idReservas INT AUTO_INCREMENT;


create table if not exists tipoReporte(
	idTipoReporte varchar(10) not null primary key,
	nombreTipoReporte varchar(50),
	descripcion text
);

create table if not exists reportes(
	idReportes varchar(10) not null primary key,
    tipoReporteId varchar(10),
    fechaReporte date,
    descripcion text,
    datosReporte text,
    empleadosId varchar(10),
    fechaActualizacion date,
    foreign key (tipoReporteId) references tipoReporte(idTipoReporte),
    foreign key (empleadosId) references empleados(idEmpleados)
);

create table if not exists inventario(
	idInventario varchar(10) not null primary key,
    productosId varchar(10),
    stock varchar(10),
    fechaRecepcion date,
    precioCompraUnitario decimal,
    proveedorId varchar(10),
    foreign key (productosId) references productos(idProductos),
    foreign key (proveedorId) references proveedores(idProveedores)
);

create table if not exists mesasAtendidas(
	idMesasAtendidas varchar(10) not null primary key,
    empleadosId varchar(10),
    fecha date,
    numeroMesasAtendidas int,
    foreign key (empleadosId) references empleados(idEmpleados)
);

create table if not exists domicilios(
	idDomicilios varchar(10) not null primary key,
    nombreCliente varchar(50),
    direccion varchar(50),
    cantidad int,
    platosId varchar(10),
    platos text,
    precioUnitario decimal,
    precioTotal decimal,
    fecha date,
    observacion text,
    foreign key (platosId) references platos(idPlatos)
);

create table if not exists carta(
	platosId varchar(10),
    nombrePlato varchar(50),
    descripcion text,
    precio decimal,
    foreign key (platosId) references platos(idPlatos)
);

create table if not exists historialVentas(
	idHistorialVentas varchar(10) not null primary key,
    fechaVenta datetime,
    totalVenta decimal,
    empleadosId varchar(10),
    mesasId varchar(10),
    productosId varchar(10),
    cantidadProductos int,
    precioUnitarioProducto decimal,
    precioTotal decimal,
    foreign key (empleadosId) references empleados(idEmpleados),
    foreign key (productosId) references productos(idProductos),
    foreign key (mesasId) references mesas(idMesas)
);

CREATE TABLE IF NOT EXISTS cajaPago (
    idCajaPago VARCHAR(10) NOT NULL PRIMARY KEY,
    tipoPago VARCHAR(50),
    pedidoId VARCHAR(10),
    fechaPago DATETIME,
    montoPago DECIMAL,
    metodoPago VARCHAR(20),
    observaciones TEXT,
    FOREIGN KEY (pedidoId)
        REFERENCES pedidos (idPedidos)
);

-- Registros para la tabla sesiones
insert into sesiones(usuario, fechaInicio, fechaFin, contraseña) VALUES
('cocina', '2024-04-30 08:00:00', '2024-04-30 10:00:00', 'cocina');
insert into sesiones(usuario, fechaInicio, fechaFin, contraseña) VALUES
('cajera', '2024-04-30 08:00:00', '2024-04-30 10:00:00', 'caja');

insert into sesiones(usuario, fechaInicio, fechaFin, contraseña) VALUES
('admin', '2024-04-30 08:00:00', '2024-04-30 10:00:00', 'admin');

insert into sesiones(usuario, fechaInicio, fechaFin, contraseña) VALUES
('juanito', '2024-04-30 08:00:00', '2024-04-30 10:00:00', 'juan01');
INSERT INTO sesiones (usuario, fechaInicio, fechaFin, contraseña) VALUES
('usuario1', '2024-04-30 08:00:00', '2024-04-30 10:00:00', 'hash_contraseña_1'),
('usuario2', '2024-04-30 09:00:00', '2024-04-30 11:00:00', 'hash_contraseña_2'),
('usuario3', '2024-04-30 10:00:00', '2024-04-30 12:00:00', 'hash_contraseña_3');

-- Registros para la tabla empleados

INSERT INTO empleados (idEmpleados, nombreEmpleado, rol, fechaContratacion, salario, telefono, direccion, email, usuario) VALUES
('emp007', 'Pepe Rodriguez', 'Chef', '2020-01-01', 1500.00, '123456789', 'Calle Principal 123', 'juan@example.com', 'cocina');
INSERT INTO empleados (idEmpleados, nombreEmpleado, rol, fechaContratacion, salario, telefono, direccion, email, usuario) VALUES
('emp006', 'Maria Perez', 'Cajero', '2020-01-01', 1500.00, '123456789', 'Calle Principal 123', 'juan@example.com', 'cajera');

INSERT INTO empleados (idEmpleados, nombreEmpleado, rol, fechaContratacion, salario, telefono, direccion, email, usuario) VALUES
('emp005', 'Jose Perez', 'Administrador', '2020-01-01', 1500.00, '123456789', 'Calle Principal 123', 'juan@example.com', 'admin');

INSERT INTO empleados (idEmpleados, nombreEmpleado, rol, fechaContratacion, salario, telefono, direccion, email, usuario) VALUES
('emp004', 'Juan Pérez', 'Camarero', '2020-01-01', 1500.00, '123456789', 'Calle Principal 123', 'juan@example.com', 'juanito');

INSERT INTO empleados (idEmpleados, nombreEmpleado, rol, fechaContratacion, salario, telefono, direccion, email, usuario) VALUES
('emp001', 'Nathalia Galvis', 'Camarero', '2020-01-01', 1500.00, '123456789', 'Calle Principal 123', 'juan@example.com', 'usuario1'),
('emp002', 'María García', 'Chef', '2021-03-15', 2000.00, '987654321', 'Avenida Central 456', 'maria@example.com', 'usuario2'),
('emp003', 'Pedro Rodríguez', 'Cajero', '2022-05-10', 1800.00, '456789123', 'Plaza Mayor 789', 'pedro@example.com', 'usuario3');


-- Registros para la tabla mesas
INSERT INTO mesas (idMesas, numeroMesa, capacidad, estado) VALUES
('mesa001', 1, 4, 'Disponible'),
('mesa002', 2, 6, 'Ocupada'),
('mesa003', 3, 2, 'Reservada');

-- Registros para la tabla proveedores
INSERT INTO proveedores (idProveedores, nombreEmpresa, contacto, telefono, email, direccion, terminoPago) VALUES
('prov001', 'Proveedor A', 'Contacto A', '111111111', 'proveedorA@example.com', 'Calle Proveedor A 123', '30 días'),
('prov002', 'Proveedor B', 'Contacto B', '222222222', 'proveedorB@example.com', 'Avenida Proveedor B 456', '60 días'),
('prov003', 'Proveedor C', 'Contacto C', '333333333', 'proveedorC@example.com', 'Plaza Proveedor C 789', '45 días');

-- Registros para la tabla categoria
INSERT INTO categoria (idCategoria, nombreCategoria) VALUES
('cat001', 'Entrantes'),
('cat002', 'Platos Principales'),
('cat003', 'Postres');

-- Registros para la tabla productos
INSERT INTO productos (idProductos, nombreProducto, descripcionProducto, precio, categoria) VALUES
('prod001', 'Ensalada César', 'Ensalada con pollo, lechuga, croutones y aderezo César', 10.50, 'cat001'),
('prod002', 'Lomo Saltado', 'Plato peruano con lomo de res, cebolla, tomate y papas fritas', 15.75, 'cat002'),
('prod003', 'Tiramisú', 'Postre italiano con bizcocho, café, crema mascarpone y cacao', 8.99, 'cat003');

-- Registros para la tabla pedidos
INSERT INTO pedidos (idPedidos, empleadosId, mesasId, fechaPedido, estadoPedido, precioTotal) VALUES
('pedido004', 'emp002', 'mesa003', '2024-04-30', 'Completado', 35.25);


INSERT INTO pedidos (idPedidos, empleadosId, mesasId, fechaPedido, estadoPedido, precioTotal) VALUES
('pedido001', 'emp001', 'mesa001', '2024-04-30', 'En proceso', 35.25),
('pedido002', 'emp002', 'mesa002', '2024-04-30', 'Completado', 42.30),
('pedido003', 'emp003', 'mesa003', '2024-04-30', 'En proceso', 28.49);

-- Registros para la tabla categoriaPlatos
INSERT INTO categoriaPlatos (idCategoriaPlatos, nombreCategoriaPlatos) VALUES
('catpla001', 'Entrantes'),
('catpla002', 'Principales'),
('catpla003', 'Postres');

-- Registros para la tabla platos
INSERT INTO platos (idPlatos, nombrePlato, precio, categoriaPlatosId) VALUES
('plato001', 'Ceviche', 18.50, 'catpla001'),
('plato002', 'Pollo a la Brasa', 15.99, 'catpla002'),
('plato003', 'Tarta de Manzana', 7.25, 'catpla003');

-- Registros para la tabla detallesPedido
INSERT INTO detallesPedido (pedidosId, platosId, cantidad, precioUnitario) VALUES
('pedido001', 'plato001', 2, 10.50),
('pedido002', 'plato002', 1, 15.75),
('pedido003', 'plato003', 3, 8.99);

INSERT INTO detallesPedido (pedidosId, platosId, cantidad, precioUnitario) VALUES
('pedido001', 'plato003', 2, 10.50),
('pedido001', 'plato002', 2, 10.50),
('pedido001', 'plato001', 1, 15.75),
-- Registros para la tabla reservas
INSERT INTO reservas (idReservas, nombreCliente, fecha, numeroPersonas, telefono, nota) VALUES
(1, 'Cliente A', '2024-05-01', 4, '79854623', 'Confirmada'),
(2, 'Cliente B', '2024-05-02', 2, '798456213', 'Pendiente'),
(3, 'Cliente C', '2024-05-03', 6, '879456213', 'Confirmada');


-- Registros para la tabla tipoReporte
INSERT INTO tipoReporte (idTipoReporte, nombreTipoReporte, descripcion) VALUES
('tipo001', 'Ventas diarias', 'Reporte de ventas por día'),
('tipo002', 'Inventario', 'Reporte del estado del inventario'),
('tipo003', 'Rendimiento de empleados', 'Reporte del rendimiento de los empleados');

-- Registros para la tabla reportes
INSERT INTO reportes (idReportes, tipoReporteId, fechaReporte, descripcion, datosReporte, empleadosId, fechaActualizacion) VALUES
('reporte001', 'tipo001', '2024-05-01', 'Ventas del día', 'Datos de ventas...', 'emp001', '2024-05-01'),
('reporte002', 'tipo002', '2024-05-01', 'Inventario actual', 'Datos de inventario...', 'emp002', '2024-05-01'),
('reporte003', 'tipo003', '2024-05-01', 'Rendimiento de empleados', 'Datos de rendimiento...', 'emp003', '2024-05-01');

-- Registros para la tabla inventario
INSERT INTO inventario (idInventario, productosId, stock, fechaRecepcion, precioCompraUnitario, proveedorId) VALUES
('inv001', 'prod001', '100 u', '2024-04-30', 8.00, 'prov001'),
('inv002', 'prod002', '50 u', '2024-04-30', 12.00, 'prov002'),
('inv003', 'prod003', '80 u', '2024-04-30', 6.50, 'prov003');

-- Registros para la tabla mesasAtendidas
INSERT INTO mesasAtendidas (idMesasAtendidas, empleadosId, fecha, numeroMesasAtendidas) VALUES
('atend01', 'emp001', '2024-04-30', 10),
('atend02', 'emp002', '2024-04-30', 8),
('atend03', 'emp003', '2024-04-30', 12);

-- Registros para la tabla domicilios
INSERT INTO domicilios (idDomicilios, nombreCliente, direccion, cantidad, platosId, platos, precioUnitario, precioTotal, fecha, observacion) VALUES
('dom001', 'Cliente D', 'Calle Domicilio 123', 2, 'plato001', 'Ceviche', 18.50, 37.00, '2024-05-01', 'Sin cebolla'),
('dom002', 'Cliente E', 'Avenida Domicilio 456', 1, 'plato002', 'Pollo a la Brasa', 15.99, 15.99, '2024-05-02', 'Extra salsa'),
('dom003', 'Cliente F', 'Plaza Domicilio 789', 3, 'plato003', 'Tarta de Manzana', 7.25, 21.75, '2024-05-03', 'Sin canela');

-- Registros para la tabla carta
INSERT INTO carta (platosId, nombrePlato, descripcion, precio) VALUES
('plato001', 'Ceviche', 'Plato peruano de pescado crudo marinado en limón', 18.50),
('plato002', 'Pollo a la Brasa', 'Pollo asado con especias peruanas', 15.99),
('plato003', 'Tarta de Manzana', 'Postre de manzana con masa hojaldrada y canela', 7.25);

-- Registros para la tabla historialVentas
INSERT INTO historialVentas (idHistorialVentas, fechaVenta, totalVenta, empleadosId, mesasId, productosId, cantidadProductos, precioUnitarioProducto, precioTotal) VALUES
('venta001', '2024-04-30 12:30:00', 45.25, 'emp001', 'mesa001', 'prod001', 2, 10.50, 21.00),
('venta002', '2024-04-30 13:45:00', 63.75, 'emp002', 'mesa002', 'prod002', 1, 15.75, 15.75),
('venta003', '2024-04-30 14:15:00', 26.97, 'emp003', 'mesa003', 'prod003', 3, 8.99, 26.97);

-- Registros para la tabla cajaPago
INSERT INTO cajaPago (idCajaPago, tipoPago, pedidoId, fechaPago, montoPago, metodoPago, observaciones) VALUES
('caja001', 'Efectivo', 'pedido001', '2024-04-30 12:00:00', 35.25, 'Efectivo', 'Pago completo'),
('caja002', 'Tarjeta de crédito', 'pedido002', '2024-04-30 13:00:00', 42.30, 'Visa', 'Aprobado'),
('caja003', 'Efectivo', 'pedido003', '2024-04-30 14:00:00', 28.49, 'Efectivo', 'Pago pendiente');




