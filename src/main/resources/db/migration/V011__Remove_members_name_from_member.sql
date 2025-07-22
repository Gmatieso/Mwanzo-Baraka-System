ALTER  TABLE member
DROP COLUMN  members_name;

ADD COLUMN user_id BIGINT,
ADD CONSTRAINT fk_member_user FOREIGN KEY (user_id) REFERENCES users (id);