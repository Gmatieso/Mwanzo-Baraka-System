-- Add registration_fee column to group table

ALTER TABLE "Groups"
ADD COLUMN registration_fee DECIMAL NOT NULL;

-- Add phone column to group table
ALTER TABLE "Groups"
ADD COLUMN phone VARCHAR(20);