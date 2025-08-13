-- users
CREATE TABLE IF NOT EXISTS users(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    role VARCHAR DEFAULT 'user',
    birthday TIMESTAMPZ,
    created_at TIMESTAMPZ DEFAULT now(),
);

-- books
CREATE TABLE IF NOT EXISTS books(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR NOT NULL,
    author VARCHAR NOT NULL,
    series VARCHAR,
    pages INTEGER,
    description VARCHAR NOT NULL,
    published TIMESTAMPZ,
    cover_img_loc VARCHAR,
);

-- tags
CREATE TABLE IF NOT EXISTS tags(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tag VARCHAR UNIQUE NOT NULL,
);

-- genres
CREATE TABLE IF NOT EXISTS genres(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    genre VARCHAR UNIQUE NOT NULL,
);

-- book_wishlist
CREATE TABLE IF NOT EXISTS book_wishlist(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users,
    book_id UUID NOT NULL REFERENCES books,
    rank INTEGER DEFAULT NULL,
);

--book_tags
CREATE TABLE IF NOT EXISTS book_tags(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_id UUID NOT NULL REFERENCES books,
    tag_id UUID NOT NULL REFERENCES tags,
);

--book_genres
CREATE TABLE IF NOT EXISTS book_genres(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_id UUID NOT NULL REFERENCES books,
    genre_id UUID NOT NULL REFERENCES genres,
);

--books_read
CREATE TABLE IF NOT EXISTS books_read(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users,
    book_id UUID NOT NULL REFERENCES books,
    progress INTEGER NOT NULL DEFAULT 0,
    rating INTEGER DEFAULT NULL,
    review VARCHAR,
    completed BOOLEAN DEFAULT FALSE,
    completed_date TIMESTAMPZ,
);

--user_tags
CREATE TABLE IF NOT EXISTS user_tags(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_read_id UUID REFERENCES books_read,
    tag_id UUID REFERENCES tags,
);