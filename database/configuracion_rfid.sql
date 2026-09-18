-- ============================================================
-- Sistema RFID de Gestion de Activos
-- Modulo: CategoriaActivo
-- Evidencia: GA7-220501096-AA2-EV01
-- Autor: Cristian Camilo Betancur Alzate
-- ============================================================

-- Crea la base unicamente si no existe.
CREATE DATABASE IF NOT EXISTS rfid_gestion_activos
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE rfid_gestion_activos;

-- Crea la tabla del modulo unicamente si no existe.
-- No elimina ni reemplaza tablas o datos existentes.
CREATE TABLE IF NOT EXISTS categoriaactivo (
    id_categoria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NULL,
    activo TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id_categoria)
) ENGINE=InnoDB
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;

-- Verificacion de la estructura disponible para el modulo.
SELECT
    id_categoria,
    nombre,
    descripcion,
    activo
FROM categoriaactivo
ORDER BY id_categoria;

-- IMPORTANTE:
-- Las credenciales no se incluyen en este archivo.
-- Cree o utilice un usuario MySQL local con permisos SELECT,
-- INSERT, UPDATE y DELETE sobre rfid_gestion_activos.
-- Configure RFID_DB_USER y RFID_DB_PASSWORD en el sistema operativo.
