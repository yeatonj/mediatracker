-- user, aggregate
CREATE TABLE IF NOT EXISTS user_main (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    password VARCHAR,
    role VARCHAR DEFAULT 'user',
    birthday TIMESTAMPTZ,
    created_at TIMESTAMPTZ DEFAULT now()
);

-- book, aggregate
CREATE TABLE IF NOT EXISTS book (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR NOT NULL,
    author VARCHAR NOT NULL,
    series VARCHAR,
    pages INTEGER NOT NULL,
    description VARCHAR NOT NULL,
    published TIMESTAMPTZ NOT NULL,
    cover_img_loc VARCHAR
);

-- tag, belongs to both book and user aggregates
CREATE TABLE IF NOT EXISTS tag (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tag VARCHAR UNIQUE NOT NULL
);

-- genre, belongs to book aggregate
CREATE TABLE IF NOT EXISTS genre (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    genre VARCHAR UNIQUE NOT NULL
);

-- book_wishlist, in user aggregate
CREATE TABLE IF NOT EXISTS book_wishlist (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_main UUID NOT NULL REFERENCES user_main,
    book UUID NOT NULL REFERENCES book,
    rank INTEGER DEFAULT NULL
);

--book_tag, in book aggregate
CREATE TABLE IF NOT EXISTS book_tag (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book UUID NOT NULL REFERENCES book,
    tag UUID NOT NULL REFERENCES tag
);

--book_genre, in book aggregate
CREATE TABLE IF NOT EXISTS book_genre(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book UUID NOT NULL REFERENCES book,
    genre UUID NOT NULL REFERENCES genre
);

--book_read, in user aggregate
CREATE TABLE IF NOT EXISTS book_read(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_main UUID NOT NULL REFERENCES user_main,
    book UUID NOT NULL REFERENCES book,
    progress INTEGER NOT NULL DEFAULT 0,
    rating INTEGER DEFAULT NULL,
    review VARCHAR,
    completed BOOLEAN DEFAULT FALSE,
    completed_date TIMESTAMPTZ
);

--user_tag, in user aggregate
CREATE TABLE IF NOT EXISTS user_tag(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_read UUID NOT NULL REFERENCES book_read,
    tag UUID NOT NULL REFERENCES tag
);