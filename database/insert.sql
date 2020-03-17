insert into roles values(1, 'ROLE_USER');
insert into roles values(2, 'ROLE_LEADER');
insert into roles values(3, 'ROLE_ADMIN');

insert into programming values(1, 'Red Flower Hell I.', 'In the elements of Red Flower Hell series we are going to develop intelligent agents to collect poppies. After this we would also like develop AGI agents in this environment. It is therefore essential to know that how many poppies can a human player collect?', 10);
insert into programming values(2, 'Red Flower Hell II.', 'In the elements of Red Flower Hell series we are going to develop intelligent agents to collect poppies. After this we would also like develop AGI agents in this environment. It is therefore essential to know that how many poppies can a human player collect?', 20);

insert into tests values(1, 'SMNIST', 'The Semantic "MNIST". Have you seen the movie, Rain Man? Do you remember the scene when Dustin Hoffman can count the exact number of toothpicks on the floor in the blink of an eye. We don\'t count toothpicks but dots in an image.', 20);
insert into tests values(2, 'BrainB', 'This project aims to create an interdisciplinary research program that links human perception, infomation theory, and talent search in the field of e-sports.', 20);

insert into players values(1, 'Steve', 'Admin', 'admin@admin.com', 'admin', '$2a$10$ggTDE3tzIvBIEZaPuJXu3Ogt.UU9uQYLU0mAJUsHZuYXcowu7B7Ju', 35);
insert into players values(2, 'Joe', 'Test', 'test@test.com', 'test', '$2a$10$TnvT.e9jy5mvCKQZ2M4QXuX0Sr2eEe5UFW.Zv7tywTYyxDlH5UT7e', 20);

insert into user_roles values(1, 1);
insert into user_roles values(1, 3);
insert into user_roles values(2, 1);

insert into programming_statistics values(4, 1, 1, '2020-03-17 22:09:47', 10);
insert into test_results values(1, 1, '2020-03-17 22:09:59', 15);
