select faculty.name, student.name,age
from student JOIN faculty
    ON student.faculty_id=faculty.id;

select avatar.file_path, name, age
from student join avatar
    on student.id = avatar.student_id