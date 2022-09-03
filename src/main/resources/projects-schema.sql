DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS category;

CREATE TABLE category(
category_id INT /*AUTO_INCREMENT*/ NOT NULL PRIMARY KEY,
category_name VARCHAR(128) /*AUTO_INCREMENT*/ NOT NULL
);

CREATE TABLE projects(
projects_id INT	/*AUTO_INCREMENT*/ NOT NULL PRIMARY KEY,
projects_name VARCHAR(128) NOT NULL,
estimated_hours DECIMAL(7,2)  ,
actual_hours DECIMAL(7,2) ,
difficulty INT /*AUTO_INCREMENT*/ ,
notes TEXT 
);


CREATE TABLE step(
step_id INT /*AUTO_INCREMENT*/ NOT NULL PRIMARY KEY,
projects_id INT /*AUTO_INCREMENT*/ NOT NULL,
step_text TEXT /*AUTO_INCREMENT*/ NOT NULL,
step_order INT /*AUTO_INCREMENT*/ NOT NULL,
FOREIGN KEY (projects_id) REFERENCES projects(projects_id) ON DELETE CASCADE
);
CREATE TABLE material(
material_id INT /*AUTO_INCREMENT*/ NOT NULL PRIMARY KEY,
projects_id INT /*AUTO_INCREMENT*/ NOT NULL,
	material_name	VARCHAR(128)	NOT NULL,	
    num_required INT /*AUTO_INCREMENT*/ ,
    cost DECIMAL(7,2),
    FOREIGN KEY (projects_id) REFERENCES projects(projects_id) ON DELETE CASCADE
);
CREATE TABLE project_category(
projects_id INT /*AUTO_INCREMENT*/ NOT NULL,
category_id INT /*AUTO_INCREMENT*/ NOT NULL,
FOREIGN KEY (projects_id) REFERENCES projects(projects_id) ON DELETE CASCADE,
FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE
); 