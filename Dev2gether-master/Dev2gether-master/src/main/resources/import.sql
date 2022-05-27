-- INSERT EMPLOYEES
insert into student (student_id, first_name, last_name, email) values (100, 'John', 'Warton', 'warton@gmail.com');
insert into student (student_id, first_name, last_name, email) values (200, 'Mike', 'Lanister', 'lanister@gmail.com');
insert into student (student_id, first_name, last_name, email) values (300, 'Steve', 'Reeves', 'Reeves@gmail.com');
insert into student (student_id, first_name, last_name, email) values (400, 'Ronald', 'Connor', 'connor@gmail.com');
insert into student (student_id, first_name, last_name, email) values (500, 'Jim', 'Salvator', 'Sal@gmail.com');
insert into student (student_id, first_name, last_name, email) values (600, 'Peter', 'Henley', 'henley@gmail.com');
insert into student (student_id, first_name, last_name, email) values (700, 'Richard', 'Carson', 'carson@gmail.com');
insert into student (student_id, first_name, last_name, email) values (800, 'Honor', 'Miles', 'miles@gmail.com');
insert into student (student_id, first_name, last_name, email) values (900, 'Tony', 'Roggers', 'roggers@gmail.com');

-- INSERT PROJECTS
insert into project (project_id, name, stage, description) values (1000, 'Large Production Deploy', 'NOTSTARTED', 'This requires all hands on deck for the final deployment of the software into production');
insert into project (project_id, name, stage, description) values (1001, 'New Employee Budget',  'COMPLETED', 'Decide on a new employee bonus budget for the year and figureout who will be promoted');
insert into project (project_id, name, stage, description) values (1002, 'Office Reconstruction', 'INPROGRESS', 'The office building in Monroe has been damaged due to hurricane in the region. This needs to be reconstructed');
insert into project (project_id, name, stage, description) values (1003, 'Improve Intranet Security', 'INPROGRESS', 'With the recent data hack, the office security needs to be improved and proper security team needs to be hired for implementation');

-- INSERT PROJECT_EMPLOYEE_RELATION (Removed duplicates from video)
insert into project_student (student_id, project_id) values (1,1000);
insert into project_student (student_id, project_id) values (1,1001);
insert into project_student (student_id, project_id) values (1,1002);
insert into project_student (student_id, project_id) values (3,1000);
insert into project_student (student_id, project_id) values (6,1002);
insert into project_student (student_id, project_id) values (6,1003);
