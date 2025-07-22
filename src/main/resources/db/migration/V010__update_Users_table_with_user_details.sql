-- Add new columns for user profile information
ALTER TABLE users
ADD COLUMN first_name VARCHAR(100) NOT NULL,
ADD COLUMN last_name VARCHAR(100) NOT NULL,
ADD COLUMN email VARCHAR(255) NOT NULL,
ADD COLUMN phone VARCHAR(20) NOT NULL;

-- Add Unique Constraints on email and phone number for data integrity
ALTER TABLE users
ADD CONSTRAINT uq_user_email UNIQUE (email),
ADD CONSTRAINT uq_user_phone UNIQUE (phone);

-- Modify username column to enforce NOT NULL and uniqueness (if not already)
ALTER TABLE users
ALTER COLUMN username SET NOT NULL;

-- Add unique constraint on username if not already enforced
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_indexes
        WHERE indexname = 'uq_user_username'
    ) THEN
ALTER TABLE users
    ADD CONSTRAINT uq_user_username UNIQUE (username);
END IF;
END $$;

-- Add indexes for performance on commonly searched fields
CREATE INDEX IF NOT EXISTS idx_user_email ON users (email);
CREATE INDEX IF NOT EXISTS idx_user_phone ON users (phone);
CREATE INDEX IF NOT EXISTS idx_user_username ON users (username);
