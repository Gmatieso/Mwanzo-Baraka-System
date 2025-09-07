CREATE TABLE permissions(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL UNIQUE,
    permissions_group_id UUID NOT NULL,
    CONSTRAINT fk_permission_group
        FOREIGN KEY (permissions_group_id)
        REFERENCES permission_groups(id)
        ON DELETE CASCADE
        );