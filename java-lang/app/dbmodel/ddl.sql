-- 수강신청
DROP TABLE IF EXISTS edu_apply RESTRICT;

-- 교육과정
DROP TABLE IF EXISTS edu_lecture RESTRICT;

-- 매니저
DROP TABLE IF EXISTS edu_manager RESTRICT;

-- 수강생
DROP TABLE IF EXISTS edu_student RESTRICT;

-- 강의장
DROP TABLE IF EXISTS edu_room RESTRICT;

-- 강사
DROP TABLE IF EXISTS edu_teacher RESTRICT;

-- 강의실사진
DROP TABLE IF EXISTS edu_room_photo RESTRICT;

-- 교육센터
DROP TABLE IF EXISTS edu_center RESTRICT;

-- 주소
DROP TABLE IF EXISTS edu_juso RESTRICT;

-- 강의
DROP TABLE IF EXISTS edu_lecture_teacher RESTRICT;

-- 회원
DROP TABLE IF EXISTS edu_member RESTRICT;

-- 수강신청
CREATE TABLE edu_apply (
    apply_id     INTEGER  NOT NULL COMMENT '수강신청번호', -- 수강신청번호
    member_id    INTEGER  NOT NULL COMMENT '수강생번호', -- 수강생번호
    lecture_id   INTEGER  NOT NULL COMMENT '교육과정번호', -- 교육과정번호
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수강신청일' -- 수강신청일
)
COMMENT '수강신청';

-- 수강신청
ALTER TABLE edu_apply
    ADD CONSTRAINT PK_edu_apply -- 수강신청 기본키
    PRIMARY KEY (
    apply_id -- 수강신청번호
    );

ALTER TABLE edu_apply
    MODIFY COLUMN apply_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '수강신청번호';

-- 교육과정
CREATE TABLE edu_lecture (
    lecture_id INTEGER      NOT NULL COMMENT '교육과정번호', -- 교육과정번호
    title      VARCHAR(255) NOT NULL COMMENT '교육과정명', -- 교육과정명
    content    TEXT         NOT NULL COMMENT '교육과정설명', -- 교육과정설명
    quantity   INTEGER      NOT NULL COMMENT '모집인원', -- 모집인원
    start_date DATE         NOT NULL COMMENT '시작일', -- 시작일
    end_date   DATE         NOT NULL COMMENT '종료일', -- 종료일
    online     TINYINT      NOT NULL COMMENT '비대면', -- 비대면
    room_id    INTEGER      NULL     COMMENT '강의장번호', -- 강의장번호
    member_id  INTEGER      NULL     COMMENT '매니저번호' -- 매니저번호
)
COMMENT '교육과정';

-- 교육과정
ALTER TABLE edu_lecture
    ADD CONSTRAINT PK_edu_lecture -- 교육과정 기본키
    PRIMARY KEY (
    lecture_id -- 교육과정번호
    );

ALTER TABLE edu_lecture
    MODIFY COLUMN lecture_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '교육과정번호';

-- 매니저
CREATE TABLE edu_manager (
    member_id  INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
    position   VARCHAR(50) NULL     COMMENT '매니저직위', -- 매니저직위
    department VARCHAR(50) NOT NULL COMMENT '부서', -- 부서
    fax        VARCHAR(30) NULL     COMMENT '팩스' -- 팩스
)
COMMENT '매니저';

-- 매니저
ALTER TABLE edu_manager
    ADD CONSTRAINT PK_edu_manager -- 매니저 기본키
    PRIMARY KEY (
    member_id -- 회원번호
    );

-- 수강생
CREATE TABLE edu_student (
    member_id      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
    working        TINYINT      NOT NULL COMMENT '재직자', -- 재직자
    jumin          VARCHAR(13)  NOT NULL COMMENT '주민번호', -- 주민번호
    juso_id        INTEGER      NULL     COMMENT '주소번호', -- 주소번호
    detail_address VARCHAR(255) NULL     COMMENT '상세주소' -- 상세주소
)
COMMENT '수강생';

-- 수강생
ALTER TABLE edu_student
    ADD CONSTRAINT PK_edu_student -- 수강생 기본키
    PRIMARY KEY (
    member_id -- 회원번호
    );

-- 수강생 유니크 인덱스
CREATE UNIQUE INDEX UIX_edu_student
    ON edu_student ( -- 수강생
        jumin ASC -- 주민번호
    );

-- 강의장
CREATE TABLE edu_room (
    room_id   INTEGER     NOT NULL COMMENT '강의장번호', -- 강의장번호
    center_id INTEGER     NOT NULL COMMENT '교육센터번호', -- 교육센터번호
    name      VARCHAR(50) NOT NULL COMMENT '교실명', -- 교실명
    quantity  INTEGER     NOT NULL COMMENT '수용가능인원' -- 수용가능인원
)
COMMENT '강의장';

-- 강의장
ALTER TABLE edu_room
    ADD CONSTRAINT PK_edu_room -- 강의장 기본키
    PRIMARY KEY (
    room_id -- 강의장번호
    );

-- 강의장 인덱스
CREATE INDEX IX_edu_room
    ON edu_room( -- 강의장
        name ASC -- 교실명
    );

ALTER TABLE edu_room
    MODIFY COLUMN room_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의장번호';

-- 강사
CREATE TABLE edu_teacher (
    member_id INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
    photo     VARCHAR(255) NOT NULL COMMENT '사진', -- 사진
    major     VARCHAR(50)  NOT NULL COMMENT '전공', -- 전공
    wage      INTEGER      NULL     COMMENT '시급' -- 시급
)
COMMENT '강사';

-- 강사
ALTER TABLE edu_teacher
    ADD CONSTRAINT PK_edu_teacher -- 강사 기본키
    PRIMARY KEY (
    member_id -- 회원번호
    );

-- 강의실사진
CREATE TABLE edu_room_photo (
    room_photo_id INTEGER      NOT NULL COMMENT '강의실사진번호', -- 강의실사진번호
    filepath      VARCHAR(255) NOT NULL COMMENT '사진경로', -- 사진경로
    room_id       INTEGER      NOT NULL COMMENT '강의장번호' -- 강의장번호
)
COMMENT '강의실사진';

-- 강의실사진
ALTER TABLE edu_room_photo
    ADD CONSTRAINT PK_edu_room_photo -- 강의실사진 기본키
    PRIMARY KEY (
    room_photo_id -- 강의실사진번호
    );

ALTER TABLE edu_room_photo
    MODIFY COLUMN room_photo_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의실사진번호';

-- 교육센터
CREATE TABLE edu_center (
    center_id      INTEGER      NOT NULL COMMENT '교육센터번호', -- 교육센터번호
    name           VARCHAR(50)  NOT NULL COMMENT '지점명', -- 지점명
    tel            VARCHAR(30)  NOT NULL COMMENT '전화', -- 전화
    fax            VARCHAR(30)  NOT NULL COMMENT '팩스', -- 팩스
    juso_id        INTEGER      NULL     COMMENT '주소번호', -- 주소번호
    detail_address VARCHAR(255) NULL     COMMENT '상세주소' -- 상세주소
)
COMMENT '교육센터';

-- 교육센터
ALTER TABLE edu_center
    ADD CONSTRAINT PK_edu_center -- 교육센터 기본키
    PRIMARY KEY (
    center_id -- 교육센터번호
    );

-- 교육센터 유니크 인덱스
CREATE UNIQUE INDEX UIX_edu_center
    ON edu_center ( -- 교육센터
        name ASC -- 지점명
    );

ALTER TABLE edu_center
    MODIFY COLUMN center_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '교육센터번호';

-- 주소
CREATE TABLE edu_juso (
    juso_id       INTEGER      NOT NULL COMMENT '주소번호', -- 주소번호
    post_no       CHAR(5)      NOT NULL COMMENT '우편번호', -- 우편번호
    basic_address VARCHAR(255) NOT NULL COMMENT '기본주소' -- 기본주소
)
COMMENT '주소';

-- 주소
ALTER TABLE edu_juso
    ADD CONSTRAINT PK_edu_juso -- 주소 기본키
    PRIMARY KEY (
    juso_id -- 주소번호
    );

ALTER TABLE edu_juso
    MODIFY COLUMN juso_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '주소번호';

-- 강의
CREATE TABLE edu_lecture_teacher (
    lecture_id INTEGER NOT NULL COMMENT '교육과정번호', -- 교육과정번호
    member_id  INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '강의';

-- 강의
ALTER TABLE edu_lecture_teacher
    ADD CONSTRAINT PK_edu_lecture_teacher -- 강의 기본키
    PRIMARY KEY (
    lecture_id, -- 교육과정번호
    member_id   -- 회원번호
    );

-- 회원
CREATE TABLE edu_member (
    member_id    INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
    name         VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
    email        VARCHAR(40) NOT NULL COMMENT '이메일', -- 이메일
    tel          VARCHAR(30) NOT NULL COMMENT '연락처', -- 연락처
    created_date DATETIME    NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '가입일' -- 가입일
)
COMMENT '회원';

-- 회원
ALTER TABLE edu_member
    ADD CONSTRAINT PK_edu_member -- 회원 기본키
    PRIMARY KEY (
    member_id -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_edu_member
    ON edu_member ( -- 회원
        email ASC -- 이메일
    );

-- 회원 인덱스
CREATE INDEX IX_edu_member
    ON edu_member( -- 회원
        name ASC -- 이름
    );

-- 회원 인덱스2
CREATE INDEX IX_edu_member2
    ON edu_member( -- 회원
        tel ASC -- 연락처
    );

ALTER TABLE edu_member
    MODIFY COLUMN member_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 수강신청
ALTER TABLE edu_apply
    ADD CONSTRAINT FK_edu_lecture_TO_edu_apply -- 교육과정 -> 수강신청
    FOREIGN KEY (
    lecture_id -- 교육과정번호
    )
    REFERENCES edu_lecture ( -- 교육과정
    lecture_id -- 교육과정번호
    );

-- 수강신청
ALTER TABLE edu_apply
    ADD CONSTRAINT FK_edu_student_TO_edu_apply -- 수강생 -> 수강신청
    FOREIGN KEY (
    member_id -- 수강생번호
    )
    REFERENCES edu_student ( -- 수강생
    member_id -- 회원번호
    );

-- 교육과정
ALTER TABLE edu_lecture
    ADD CONSTRAINT FK_edu_manager_TO_edu_lecture -- 매니저 -> 교육과정
    FOREIGN KEY (
    member_id -- 매니저번호
    )
    REFERENCES edu_manager ( -- 매니저
    member_id -- 회원번호
    );

-- 교육과정
ALTER TABLE edu_lecture
    ADD CONSTRAINT FK_edu_room_TO_edu_lecture -- 강의장 -> 교육과정
    FOREIGN KEY (
    room_id -- 강의장번호
    )
    REFERENCES edu_room ( -- 강의장
    room_id -- 강의장번호
    );

-- 매니저
ALTER TABLE edu_manager
    ADD CONSTRAINT FK_edu_member_TO_edu_manager -- 회원 -> 매니저
    FOREIGN KEY (
    member_id -- 회원번호
    )
    REFERENCES edu_member ( -- 회원
    member_id -- 회원번호
    );

-- 수강생
ALTER TABLE edu_student
    ADD CONSTRAINT FK_edu_juso_TO_edu_student -- 주소 -> 수강생
    FOREIGN KEY (
    juso_id -- 주소번호
    )
    REFERENCES edu_juso ( -- 주소
    juso_id -- 주소번호
    );

-- 수강생
ALTER TABLE edu_student
    ADD CONSTRAINT FK_edu_member_TO_edu_student -- 회원 -> 수강생
    FOREIGN KEY (
    member_id -- 회원번호
    )
    REFERENCES edu_member ( -- 회원
    member_id -- 회원번호
    );

-- 강의장
ALTER TABLE edu_room
    ADD CONSTRAINT FK_edu_center_TO_edu_room -- 교육센터 -> 강의장
    FOREIGN KEY (
    center_id -- 교육센터번호
    )
    REFERENCES edu_center ( -- 교육센터
    center_id -- 교육센터번호
    );

-- 강사
ALTER TABLE edu_teacher
    ADD CONSTRAINT FK_edu_member_TO_edu_teacher -- 회원 -> 강사
    FOREIGN KEY (
    member_id -- 회원번호
    )
    REFERENCES edu_member ( -- 회원
    member_id -- 회원번호
    );

-- 강의실사진
ALTER TABLE edu_room_photo
    ADD CONSTRAINT FK_edu_room_TO_edu_room_photo -- 강의장 -> 강의실사진
    FOREIGN KEY (
    room_id -- 강의장번호
    )
    REFERENCES edu_room ( -- 강의장
    room_id -- 강의장번호
    );

-- 교육센터
ALTER TABLE edu_center
    ADD CONSTRAINT FK_edu_juso_TO_edu_center -- 주소 -> 교육센터
    FOREIGN KEY (
    juso_id -- 주소번호
    )
    REFERENCES edu_juso ( -- 주소
    juso_id -- 주소번호
    );

-- 강의
ALTER TABLE edu_lecture_teacher
    ADD CONSTRAINT FK_edu_lecture_TO_edu_lecture_teacher -- 교육과정 -> 강의
    FOREIGN KEY (
    lecture_id -- 교육과정번호
    )
    REFERENCES edu_lecture ( -- 교육과정
    lecture_id -- 교육과정번호
    );

-- 강의
ALTER TABLE edu_lecture_teacher
    ADD CONSTRAINT FK_edu_teacher_TO_edu_lecture_teacher -- 강사 -> 강의
    FOREIGN KEY (
    member_id -- 회원번호
    )
    REFERENCES edu_teacher ( -- 강사
    member_id -- 회원번호
    );