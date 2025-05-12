-- 1. Permisos
INSERT INTO permissions (permission_id, name) VALUES
  (21, 'crear_pedido'),
  (22, 'editar_productos'),
  (23, 'ver_reportes'),
  (24, 'gestionar_usuarios');

-- 2. Roles
INSERT INTO roles (role_id, name) VALUES
  (11, 'ROL_COSTUMER'),
  (12, 'ROL_ADMIN');

-- 3. Roles-Permisos
INSERT INTO roles_permissions (role_id, permission_id) VALUES
  (11, 21),
  (12, 22),
  (12, 23),
  (12, 24);

-- 4. Usuarios
INSERT INTO users (user_id, name, email, password, telephone, home_address, role_id) VALUES
  (31, 'Ana Pérez', 'ana@example.com', '$2b$12$ZGUPuFIRQTie215sSQz2x.f/0LysAkyGtslmurGJiWni0X9y2GKCC', '123456789', 'Calle Falsa 123', 11),
  (32, 'Carlos Admin', 'carlos@example.com', '$2b$12$DYmUSNB7HZydq5Ichgvey.LjgbI02u5cRUzpORHyQ3nRC9TERedfu', '987654321', 'Avenida Siempre 456', 12),
  (33, 'Luis Gamer', 'luis@outlook.com', '$2b$12$jTRSGRtxSxxKSgk8.c6pF.tLI/hDCJfFupn0YxXTnWOukpsb.C/UW', '555-555-5555', 'Calle Juegos 789', 11),
  (34, 'María DM', 'maria_dungeonmaster@gmail.com', '$2b$12$wL2fxMdI4Z2Wr.EXLmoz0u3Ko.YSO1qx7qGcOeF0R6Uzcpdc0SR9y', '777-777-7777', 'Plaza Fantasía 101', 11);

-- 5. Categorías
INSERT INTO categories (category_id, name, description) VALUES
  (1, 'Juegos de Mesa', 'Juegos de rol y estrategia'),
  (2, 'Dados', 'Dados poliédricos para D&D'),
  (3, 'Accesorios', 'Miniaturas, mapas y fichas'),
  (4, 'Libros de Reglas', 'Manuales y guías'),
  (5, 'Escenografías', 'Tableros y fondos temáticos');

-- 6. Productos
INSERT INTO products (product_id, name, description, price, quantity, image_url, category_id) VALUES
  (101, 'Dungeons & Dragons: Manual del Jugador', 'Manual básico de D&D 5e', 59.99, 50, 'dnd_manual.jpg', 4),
  (102, 'Dados de 20 caras (Set de 7)', 'Dados de colores metálicos', 15.50, 200, 'dados_20_met.png', 2),
  (103, 'Mapa de Forgotten Realms', 'Mapa desplegable de 60x80 cm', 25.00, 30, 'mapa_realms.jpg', 5),
  (104, 'Fichas de Aventurero', 'Paquete de 100 fichas personalizables', 12.99, 150, 'fichas_aventura.jpg', 3),
  (105, 'Pathfinder RPG: Guía del Maestro', 'Guía avanzada para Dungeon Masters', 49.99, 40, 'pathfinder_guia.jpg', 4),
  (106, 'Dados de 12 Caras (Azules)', 'Dados translúcidos de cristal', 9.99, 50, 'dados_12_azul.jpg', 2),
  (107, 'Miniatura de Dragón Rojo', 'Figura de PVC de 10 cm', 18.50, 25, 'dragon_rojo.jpg', 3),
  (108, 'Tablero Modular de Batalla', 'Módulos intercambiables de 30x30 cm', 35.00, 20, 'tablero_modular.jpg', 5),
  (109, 'Sobres de Cartas Mágicas', 'Paquete de 50 cartas coleccionables', 7.99, 100, 'cartas_magicas.jpg', 3),
  (110, 'Warhammer Quest: Caja Básica', 'Juego cooperativo de aventuras', 79.99, 15, 'warhammer_quest.jpg', 1);

-- 7. Descuentos
INSERT INTO discounts (discount_id, name, percentage, start_date, end_date) VALUES
  (501, 'Promo Verano 2023', 15.00, '2023-06-01', '2023-08-31'),
  (502, 'Black Friday 2023', 25.00, '2023-11-24', '2023-11-30'),
  (503, 'Navidad 2023', 10.00, '2023-12-20', '2024-01-05');

-- 8. Relación Producto-Descuento
INSERT INTO product_discounts (product_id, discount_id) VALUES
  (101, 501),
  (105, 502),
  (108, 503);

-- 9. Pedidos (Orders)
INSERT INTO orders (order_id, user_id, order_date, status, total_price) VALUES
  (1011, 31, '2023-07-15 10:30:00', 'pendiente', 59.99),
  (1012, 31, '2023-08-20 14:00:00', 'enviado', 31.49),
  (1013, 33, '2023-11-25 09:00:00', 'entregado', 65.00),
  (1014, 34, '2023-12-24 18:00:00', 'pendiente', 79.99);

-- 10. Relación Pedido-Producto
INSERT INTO order_products (order_id, product_id, quantity, subtotal) VALUES
  (1011, 101, 6, 59.99),
  (1012, 102, 3, 31.00),
  (1013, 103, 5, 49.99),
  (1014, 104, 7, 79.99);

-- 11. Métodos de pago
INSERT INTO payment_methods (payment_method_id, name, user_id) VALUES
  (41, 'Tarjeta de Crédito Visa', 31),
  (42, 'PayPal', 31),
  (43, 'Transferencia Bancaria', 32),
  (44, 'Tarjeta de Débito Mastercard', 33);

-- 12. Pagos
INSERT INTO payments (payment_id, order_id, payment_method_id, payment_date, amount) VALUES
  (2011, 1011, 41, '2023-07-15', 59.99),
  (2012, 1012, 42, '2023-08-20', 31.00),
  (2013, 1013, 43, '2023-11-25', 84.99),
  (2014, 1014, 44, '2023-12-24', 79.99);

-- 13. Notificaciones
INSERT INTO notifications (notification_id, user_id, message, send_date, state) VALUES
  (5001, 31, 'Su pedido 1001 ha sido enviado', '2023-07-15 11:05:00', 'no leído'),
  (5002, 33, 'Pedido 1003 entregado exitosamente', '2023-11-26 12:10:00', 'leído'),
  (5003, 34, 'Oferta especial: 20% en todos los libros', '2023-12-20 08:00:00', 'no leído');

-- 14. Reportes
INSERT INTO reports (report_id, type, generation_date, json_data, user_id) VALUES
  (6001, 'Ventas mensuales', '2023-07-31 00:00:00', '{"total": 59.99}', 32),
  (6002, 'Stock crítico', '2023-12-25 00:00:00', '{"productos": [101, 105]}', 32);

-- 15. Entregas (Deliveries)
INSERT INTO deliveries (delivery_id, order_id, delivery_address, delivery_status, delivery_date) VALUES
  (3001, 1011, 'Calle Falsa 123, Ciudad Ficticia', 'en camino', '2023-07-15 11:00:00'),
  (3002, 1012, 'Calle Falsa 123, Ciudad Ficticia', 'entregado', '2023-08-22 16:00:00'),
  (3003, 1013, 'Calle Juegos 789, Ciudad Ficticia', 'entregado', '2023-11-26 12:00:00');

-- 16. Relación Usuario-Producto
INSERT INTO user_products (user_id, product_id) VALUES
  (31, 101),
  (33, 105),
  (34, 110);