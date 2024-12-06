CREATE TABLE prompt (
                        id SERIAL PRIMARY KEY,
                        topic TEXT NOT NULL,
                        content TEXT NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE answer (
                        id SERIAL PRIMARY KEY,
                        prompt_id BIGINT NOT NULL REFERENCES prompt(id),
                        keyword VARCHAR(255) NOT NULL,
                        content VARCHAR(1000) NOT NULL,
                        created_at TIMESTAMP NOT NULL
);