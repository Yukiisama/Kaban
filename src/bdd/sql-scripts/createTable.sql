CREATE TABLE projects (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) UNIQUE,
    description varchar(500),
    PRIMARY KEY (id)
);

CREATE TABLE tests (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50),
    description varchar(500),
    lastExecution DATETIME,
    state ENUM('validate', 'refused', 'not executed'),
    project_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES projects(id),
    UNIQUE (project_id, name)
);

