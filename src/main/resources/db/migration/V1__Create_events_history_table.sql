CREATE TABLE IF NOT EXISTS event_history
(
    id                BIGSERIAL PRIMARY KEY,
    service_type      VARCHAR(255) NOT NULL,
    event_type        VARCHAR(255) NOT NULL,
    event_description JSONB        NULL,
    user_id           BIGINT       NULL,
    user_uuid         UUID         NULL,
    user_ip_address   VARCHAR(45)  NULL,
    created_at        TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);