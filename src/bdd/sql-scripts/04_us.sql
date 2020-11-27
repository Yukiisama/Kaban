CREATE TABLE us (
    project int,
    id int,
    description TEXT,
    priority ENUM('Low', 'Medium', 'High'),
    difficulty int,
    sprint int,
    CONSTRAINT PK_ID PRIMARY KEY (project, id),
    FOREIGN KEY (project) REFERENCES project(id) ON DELETE CASCADE,
    FOREIGN KEY (project, sprint) REFERENCES sprint(project, id)
);

DELIMITER |
CREATE PROCEDURE insert_us(project_ int, description_ text, priority_ text, difficulty_ int, sprint_ int, OUT id_ int) 
BEGIN 
    SET id_ = IFNULL((SELECT MAX(id) FROM us WHERE project = project_),0) + 1; 
    INSERT INTO us (project, id, description, priority, difficulty, sprint) VALUES (project_, id_, description_, priority_, difficulty_, sprint_); 
END; |
DELIMITER ;