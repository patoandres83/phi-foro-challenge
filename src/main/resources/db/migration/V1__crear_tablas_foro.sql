CREATE TABLE perfil (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_perfil (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

CREATE TABLE topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso VARCHAR(150) NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuario(id)
);

CREATE TABLE respuesta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    autor_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuario(id),
    FOREIGN KEY (topico_id) REFERENCES topico(id)
);
