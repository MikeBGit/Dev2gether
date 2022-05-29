
--# insert into user_accounts (user_id, enabled, email, password, role, username ) values (10,1,'a@gmail.com','pass', 'ADMIN', 'admin');

-- INSERT EMPLOYEES
insert into user (id, first_name, last_name, email, password, username, enabled) values (10, 'John', 'Warton', 'warton@gmail.com', "password123", "JohnW", true);


 -- INSERT PROJECTS
insert into project (id, name, stage, description) values (10, 'Large Production Deploy', 'NOTSTARTED', 'This requires all hands on deck for the final deployment of the software into production');
insert into project (id, name, stage, description) values (11, 'New Employee Budget',  'COMPLETED', 'Decide on a new employee bonus budget for the year and figureout who will be promoted');
insert into project (id, name, stage, description) values (12, 'Office Reconstruction', 'INPROGRESS', 'The office building in Monroe has been damaged due to hurricane in the region. This needs to be reconstructed');
insert into project (id, name, stage, description) values (13, 'Improve Intranet Security', 'INPROGRESS', 'With the recent data hack, the office security needs to be improved and proper security team needs to be hired for implementation');

-- INSERT PROJECT_EMPLOYEE_RELATION (Removed duplicates from video)
--insert into project_user (user_id, project_id) values (1,1000);
--insert into project_user (user_id, project_id) values (1,1001);
--insert into project_user (user_id, project_id) values (1,1002);
--insert into project_user (user_id, project_id) values (3,1000);
--insert into project_user (user_id, project_id) values (6,1002);
--insert into project_user (user_id, project_id) values (6,1003);


