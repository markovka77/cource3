alter table student add constraint age check(age>16);
alter table student alter column name set not null ;
alter table student add constraint student_name unique (name);
alter table student alter column age set default 20;
alter table faculty add constraint faculty_name unique (name,color);