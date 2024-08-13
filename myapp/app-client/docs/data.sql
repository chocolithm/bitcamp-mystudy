
-- myapp_users
insert into myapp_users(user_id, name, email, pwd) values
    (1, 'user1', 'user1@test.com', sha1('1111')),
    (2, 'user2', 'user2@test.com', sha1('2222')),
    (3, 'user3', 'user3@test.com', sha1('3333')),
    (4, 'user4', 'user4@test.com', sha1('4444')),
    (5, 'user5', 'user5@test.com', sha1('5555')),
    (6, 'user6', 'user6@test.com', sha1('6666')),
    (7, 'user7', 'user7@test.com', sha1('7777')),
    (8, 'user8', 'user8@test.com', sha1('8888')),
    (9, 'user9', 'user9@test.com', sha1('9999')),
    (10, 'user10', 'user10@test.com', sha1('1010'));


-- myapp_boards
insert into myapp_boards(board_id, title, content) values
    (1, '제목1', '내용'),
    (2, '제목2', '내용'),
    (3, '제목3', '내용'),
    (4, '제목4', '내용'),
    (5, '제목5', '내용'),
    (6, '제목6', '내용'),
    (7, '제목7', '내용');


-- myapp_projects
insert into myapp_projects(project_id, title, description, start_date, end_date) values
    (101, '프로젝트1', '설명', '2024-1-1', '2024-2-15'),
    (102, '프로젝트2', '설명', '2024-2-1', '2024-3-15'),
    (103, '프로젝트3', '설명', '2024-3-1', '2024-4-15');


-- myapp_projects_members
insert into myapp_project_members(project_id, user_id) values
    (101, 1), (101, 2), (101, 5),
    (102, 5), (102, 6), (102, 9),
    (103, 4), (103, 7), (103, 9);


