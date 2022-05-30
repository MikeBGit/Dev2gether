#
# --# insert into user_accounts (user_id, enabled, email, password, role, username ) values (10,1,'a@gmail.com','pass', 'ADMIN', 'admin');
#
# -- INSERT EMPLOYEES
# --insert into user (id, first_name, last_name, email, password, username, enabled) values (10, 'John', 'Warton', 'warton@gmail.com', "password123", "JohnW", true);
#
#
# --insert into project (id, name, stage, description, owner_id) values (11, 'New Employee Budget',  'COMPLETED', 'Decide on a new employee bonus budget for the year and figureout who will be promoted', 10);
# --insert into project (id, name, stage, description, owner_id) values (12, 'Office Reconstruction', 'INPROGRESS', 'The office building in Monroe has been damaged due to hurricane in the region. This needs to be reconstructed', 10);
# --insert into project (id, name, stage, description, owner_id) values (13, 'Improve Intranet Security', 'INPROGRESS', 'With the recent data hack, the office security needs to be improved and proper security team needs to be hired for implementation', 10);
#
# -- INSERT PROJECT_EMPLOYEE_RELATION (Removed duplicates from video)
# --insert into project_user (user_id, project_id) values (1,1000);
# --insert into project_user (user_id, project_id) values (1,1001);
# --insert into project_user (user_id, project_id) values (1,1002);
# --insert into project_user (user_id, project_id) values (3,1000);
# --insert into project_user (user_id, project_id) values (6,1002);
# --insert into project_user (user_id, project_id) values (6,1003);
#
#
# --INSERT INTO dev2gether.language
# --(id, name)
# --VALUES
# --(1 , 'Java'),
# --(2 , 'SQL'),
# --(3 , 'HTML'),
# --(4 , 'CSS'),
# --(5 , 'JavaScript'),
# --(6 , 'PHP'),
# --(7 , 'Python'),
# --(8 , 'Go'),
# --(9 , `C#`);