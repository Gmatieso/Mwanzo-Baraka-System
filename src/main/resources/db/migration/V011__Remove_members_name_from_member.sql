ALTER  TABLE member
DROP COLUMN  members_name;

ALTER TABLE member
ADD COLUMN user_id BIGINT;

ALTER TABLE member
ADD CONSTRAINT fk_member_user FOREIGN KEY (user_id) REFERENCES users (user_id);